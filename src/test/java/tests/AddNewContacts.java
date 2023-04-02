package tests;

import models.Contact;
import org.testng.annotations.Test;

public class AddNewContacts  extends  TestBase{
    @Test
    public void addNewContactsSuccess(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("")
                .phone("")
                .email("")
                .address("")
                .description("")
                .build();

app.getHelperContact().openAddContactForm();
app.getHelperContact().fillAddContactForm(contact);
app.getHelperContact().submit();


    }
}

