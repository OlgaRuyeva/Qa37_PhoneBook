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
        @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+100;//можно и без 1000
        //System.out.println(i);

        User user = new User().setEmail("oo220719"+i+"@gmail.com").setPassword("OO220719!oo");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());//мэссадж тут только для нашей информации
            Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());

    }
    @Test
    public void registrationWrongEmail () {
        Random random = new Random();
        int i = random.nextInt(1000);
        System.out.println(i);
        User user = new User().setEmail("oo220719" + i + "gmail.com").setPassword("OO220719!oo");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));

    }
    @Test (description = "Bug report N......")//write if we found mistake
    public void registrationWrongPassword(){
        User user = new User().setEmail("oo220719@gmail.com").setPassword("OO22");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
    }
    @Test
    public void registrationRegistredUser(){
        User user = new User().setEmail("oo220719@gmail.com").setPassword("OO220719!oo");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }

    }
