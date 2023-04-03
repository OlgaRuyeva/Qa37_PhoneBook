package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewContacts extends TestBase {
    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("oo220719@gmail.com").setPassword("OO220719!oo"));
        }
    }

    @Test
    public void addNewContactsSuccessAll() {
        Contact contact = Contact.builder()
                .name("Lisa")
                .lastName("Sova")
                .phone("0534685678")
                .email("a@a")
                .address("Varna 12 Varna ")
                .description("friend")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().getMessage().contains("5678"));

    }
    @Test
    public void addNewContactsSuccess() {
        Contact contact = Contact.builder()
                .name("Lora")
                .lastName("Savina")
                .phone("1234567890")
                .email("as@a")
                .address("Sasa 23 Varna ")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submit();
       Assert.assertTrue(app.getHelperContact().getMessage().contains("7890"));


    }
    @AfterMethod
    public void postConditionBack() {
        app.getHelperContact().back();
    }
    }



