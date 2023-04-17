package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{
    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("oo220719@gmail.com").setPassword("OO220719!oo"));

        }
        app.getHelperContact().provideContacts();

        }

    @Test
    public void removerFirstContact(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(),1);


    }
    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());

    }
   //@AfterMethod
   // public void postConditionBack() {
      // app.getHelperContact().back();    }

    }