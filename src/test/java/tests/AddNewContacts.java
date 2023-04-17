package tests;

import models.Contact;
import models.User;
import org.slf4j.Logger;
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
        logger.info("Start test wiht name 'addContactSuccess'");
        logger.info("Test data--- 'Random, all fields'");

        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lisa"+i)
                .lastName("Sova")
                .phone("0534685678"+i)
                .email("a"+i+"@a")
                .address("Varna 12 Varna ")
                .description("friend    all fields")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);//чтоб затормозить процесс и посмотреть что заполняется
        app.getHelperContact().save();
        //Assert.assertTrue(app.getHelperContact().getMessage().contains("5678"));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Assert check is Element button 'Phone' present");

    }
    @Test
    public void  addContactSuccessRequiredFields() {
        logger.info("Test data--- 'Random, RequiredFields'");

        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lora Requ")
                .lastName("Savina")
                .phone("1234567890"+i)
                .email("as"+i+"@a")
                .address("Sasa 23 Varna ")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().save();
       //Assert.assertTrue(app.getHelperContact().getMessage().contains("7890"));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Assert check is Element button 'Phone' present");

    }
@Test
    public void addNewContactWrongName(){
    logger.info("Test data--- 'Random, WrongName '");

    Contact contact = Contact.builder()
            .name(" ")
            .lastName("Savina")
            .phone("1234567890")
            .email("as@a")
            .address("Sasa 23 Varna")
            .description("empty name")
            .build();
    app.getHelperContact().openAddContactForm();
    app.getHelperContact().fillAddContactForm(contact);
    //app.getHelperContact().pause(15000);
    app.getHelperContact().save();
    Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
    logger.info("Assert check is AddPageStillDisplayed");


}
    @Test
    public void addNewContactWrongAddress(){
        logger.info("Test data--- 'Random, WrongAddress '");

        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890")
                .email("as@a")
                .address("")
                .description("wrong address")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        logger.info("Assert check is AddPageStillDisplayed");

    }
    @Test
    public void addNewContactWrongLastName(){
        logger.info("Test data--- 'Random, WrongLastName '");

        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("")
                .phone("1234567890")
                .email("as@a")
                .address("Sasa 23 Varna ")
                .description("empty last name")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");//make screenshort of display
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        logger.info("Assert check is AddPageStillDisplayed");


    }
    @Test
    public void addNewContactWrongPhone(){
        logger.info("Test data--- 'Random,WrongPhone '");

        Contact contact = Contact.builder()
                 .name("Lora")
                .lastName("Savina")
                .phone("")
                .email("as@a")
                .address("Sasa 23 Varna ")
                .description("empty phone")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number " +
                                        "must contain only digits! And length min 10, max 15!"));
        logger.info("Assert check is alert present with error text 'Phone not valid: Phone number \" +\n" +
                "                                  \"must contain only digits! And length min 10, max 15!'");


    }
    @Test
    public void addNewContactWrongEmail(){
        logger.info("Test data--- 'Random,WrongEmail '");
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890")
                .email("asa")
                .address("Sasa 23 Varna ")
                .description("wrong email")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid: must be a well-formed email address"));
        logger.info("Assert check is alert present with error text" +
                       " 'Email not valid: must be a well-formed email address'");

    }


   /*  @AfterMethod
    public void postConditionBack() {
        app.getHelperContact().back();
    }
    */
    }



