package manager;
import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public  Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[]all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public  Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Jone")
                .lastName("Bild")
                .phone("123")
                .email("q@q")
                .address("NY")
                .description("friend")
                .build() });
        list.add( new Object[]{Contact.builder()
                .name("Jone")
                .lastName("Bild")
                .phone("111111111111111111111111111111")
                .email("q@q")
                .address("NY")
                .description("friend")
                .build() });
        list.add( new Object[]{Contact.builder()
                .name("Jone")
                .lastName("Bild")
                .phone("wwwwwwwwwwww")
                .email("q@q")
                .address("NY")
                .description("friend")
                .build() });
        list.add( new Object[]{Contact.builder()
                .name("Jone")
                .lastName("Bild")
                .phone("")
                .email("q@q")
                .address("NY")
                .description("friend")
                .build() });

        return list.iterator();














    }
    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Lisa" )
                .lastName("Sova")
                .phone("05346856781" )
                .email("a@a")
                .address("Varna 12 Varna ")
                .description("friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Lora Requ")
                .lastName("Savina")
                .phone("123456789012")
                .email("as@a")
                .address("Sasa 23 Varna ")
                .build()});

        return list.iterator();
    }
}
