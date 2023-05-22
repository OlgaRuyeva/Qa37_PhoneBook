package tests.tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
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

    @Test(dataProvider = "contactSuccess",dataProviderClass = DataProviderContact.class )
    public void addContactSuccessAllFields(Contact contact) {
        /*int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Lisa" + i)
                .lastName("Sova")
                .phone("0534685678" + i)
                .email("a" + i + "@a")
                .address("Varna 12 Varna ")
                .description("friend")
                .build(); */

        logger.info("Test data--->" + contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);//чтоб затормозить процесс и посмотреть что заполняется
        app.getHelperContact().save();
        //Assert.assertTrue(app.getHelperContact().getMessage().contains("5678"));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Assert checks is 'ContactAddedByPhone'");

    }
    @Test(dataProvider = "contactCSV",dataProviderClass = DataProviderContact.class )
    public void addContactSuccessAllFieldsCSV(Contact contact) {

        logger.info("Test data--->" + contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().save();
        //Assert.assertTrue(app.getHelperContact().getMessage().contains("5678"));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Assert checks is 'ContactAddedByPhone'");
    }

    @Test
    public void addContactSuccessRequiredFields() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Lora Requ")
                .lastName("Savina")
                .phone("1234567890" + i)
                .email("as" + i + "@a")
                .address("Sasa 23 Varna ")
                .build();
        logger.info("Test data--->" + contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().save();
        //Assert.assertTrue(app.getHelperContact().getMessage().contains("7890"));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        logger.info("Assert checks is 'ContactAddedByPhone'");

    }

    @Test
    public void addNewContactWrongName() {
        Contact contact = Contact.builder()
                .name(" ")
                .lastName("Savina")
                .phone("1234567890")
                .email("as@a")
                .address("Sasa 23 Varna")
                .description("empty name")
                .build();
        logger.info("Test data--->" + contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        logger.info("Assert checks is 'AddPageStillDisplayed'");
    }

    @Test
    public void addNewContactWrongAddress() {
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890")
                .email("as@a")
                .address("")
                .description("wrong address")
                .build();
        logger.info("Test data--->" + contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        logger.info("Assert checks is 'AddPageStillDisplayed'");
    }

    @Test
    public void addNewContactWrongLastName() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("")
                .phone("1234567890")
                .email("as@a")
                .address("Sasa 23 Varna ")
                .description("empty last name")
                .build();
        logger.info("Test data--->" + contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" + i + ".png");//make screenshort of display
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        logger.info("Assert check is 'AddPageStillDisplayed'");
    }

    @Test(dataProvider = "contactWrongPhone",dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact) {


        logger.info("Test data--->" + contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().save();
        Assert.assertTrue((app.getHelperContact().isAddPageStillDisplayed()));
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number " +
                "must contain only digits! And length min 10, max 15!"));
        logger.info("Assert checks is alert present with error text 'Phone not valid: Phone number \" +\n" +
                "                                  \"must contain only digits! And length min 10, max 15!'");
    }

    @Test
    public void addNewContactWrongEmail() {
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890")
                .email("asa")
                .address("Sasa 23 Varna ")
                .description("wrong email")
                .build();
        logger.info("Test data--->" + contact.toString());
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



