package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContacts extends TestBase {
    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("oo220719@gmail.com").setPassword("OO220719!oo"));
        }
    }
    @Test
    public void addContactSuccessAllFields() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lisa"+i)
                .lastName("Sova")
                .phone("0534685678"+i)
                .email("a"+i+"@a")
                .address("Varna 12 Varna ")
                .description("friend")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().save();
        //Assert.assertTrue(app.getHelperContact().getMessage().contains("5678"));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }
    @Test
    public void  addContactSuccessRequiredFields() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890"+i)
                .email("as"+i+"@a")
                .address("Sasa 23 Varna ")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().save();
       //Assert.assertTrue(app.getHelperContact().getMessage().contains("7890"));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }
@Test
    public void addNewContactWrongName(){
    Contact contact = Contact.builder()
            .name("")
            .lastName("Savina")
            .phone("1234567890")
            .email("as@a")
            .address("Sasa 23 Varna")
            .build();
    app.getHelperContact().openAddContactForm();
    app.getHelperContact().fillAddContactForm(contact);
    app.getHelperContact().save();
    Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));

}
    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890")
                .email("as@a")
                .address("")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));

    }
    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("")
                .phone("1234567890")
                .email("as@a")
                .address("Sasa 23 Varna ")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));

    }
    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                 .name("Lora")
                .lastName("Savina")
                .phone("")
                .email("as@a")
                .address("Sasa 23 Varna ")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number " +
                                        "must contain only digits! And length min 10, max 15!"));

    }
    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890")
                .email("asa")
                .address("Sasa 23 Varna ")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid: must be a well-formed email address"));

    }


   @AfterMethod
    public void postConditionBack() {
        app.getHelperContact().back();
    }
        
    }



