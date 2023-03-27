package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    //**********************************************************
    @Test
    public void RegistrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);

        User user = new User().setEmail("oo220719"+i+"@gmail.com").setPassword("OO220719!oo");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged(),".contact-page_message__2qafk");
    }
    @Test
    public void RegistrationWrongEmail () {
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);
        User user = new User().setEmail("oo220719" + i + "gmail.com").setPassword("OO220719!oo");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }
    @Test
    public void RegistrationWrongPassword(){
        User user = new User().setEmail("oo220719@gmail.com").setPassword("OO22");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
    }
