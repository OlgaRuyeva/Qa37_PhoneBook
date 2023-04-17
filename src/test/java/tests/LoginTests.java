package tests;

import models.User;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if (app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("befor method finish logout");
        }
    }
       @Test
    public void loginSuccess(){
        logger.info("Start test wiht name 'loginSuccess'");
        logger.info("Test data--- email:'oo220719@gmail.com'& password:'OO220719!oo'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("oo220719@gmail.com","OO220719!oo");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sing out' present");
    }
    @Test
    public void loginSuccessModel(){
        logger.info("Test data--- email:'oo220719@gmail.com'& password:'OO220719!oo'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("oo220719@gmail.com","OO220719!oo");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sing out' present");
    }
    @Test
    public void loginWrongEmail(){
        logger.info("Test data--- email:'oo220719gmail.com'& password:'OO220719!oo'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("oo220719gmail.com","OO220719!oo");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }
    @Test
    public void loginWrongPassword(){
        logger.info("Test data--- email:'oo220719gmail.com'& password:'OOoo'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("oo220719@gmail.com","OOoo");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }
    @Test
    public void loginUnregisterUser(){
        logger.info("Test data--- email:'pp 220719@gmail.com'& password:'OOoo'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("pp 220719@gmail.com","OO2207!oo");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }
}
