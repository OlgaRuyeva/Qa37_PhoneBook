package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddContactForm() {
        click(By.xpath("//*[text()='ADD']"));
    }

    public void fillAddContactForm(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name'] "), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone'] "), contact.getPhone());
        type(By.cssSelector("[placeholder='email'] "), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());

    }
     public void save() {
         getScreenElement("src/test/screenshots/screen-btn.png",By.xpath("//*[text()='Save']"));
         click(By.xpath("//*[text()='Save']"));
    }
    public String getMessage() {

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".contact-item_card__2SOIM"))));

        return wd.findElement(By.cssSelector(".contact-item_card__2SOIM>h3")).getText();
    }

    public void back() {
        wd.navigate().back();
    }

    public boolean isContactAddedByPhone(String phone) {
       List<WebElement> list = wd.findElements(By.cssSelector("h3"));//foreach обходим все h3 и ищем equals
        for (WebElement el:list) {
            if(el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of Contacts list before remove--->"+before);
        removeContact();

        int after = countOfContacts();
        logger.info("Number of Contacts list after remove--->"+after);

        return before-after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//*[text()='Remove']"));
        pause(1000);
    }

    private int countOfContacts() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
                                            //looking for how many contacts we have
    }

    public void removeAllContacts() {
        while(wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size()!=0){
            removeContact();

        }
    }

    public void provideContacts() {
        if(countOfContacts()<3){
            for (int i = 0; i < 3; i++) {
                addOneContact();

            }
            }
    }

    private void addOneContact() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lisa"+i)
                .lastName("Sova")
                .phone("0534685678"+i)
                .email("a"+i+"@a")
                .address("Varna 12 Varna ")
                .description("friend")
                .build();
        openAddContactForm();
        fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);//чтоб затормозить процесс и посмотреть что заполняется
        save();
        pause(2000);
    }


}







