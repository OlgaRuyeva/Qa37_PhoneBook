package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HelperBase {
    WebDriver wd;//after as we wrote it, we generate constructor right mause's button
    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;   }//это сгенерированный конструктор по WebDriver wd
    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);//вычищает поле
        if (text != null){
            element.sendKeys(text);
        }
            }
            public void clearNew(WebElement element){
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

            }

    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
    }
    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size()>0;//что не появился no such element exeption, что четко было true or False
    }
    public boolean isAlertPresent(String message) {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        if(alert!=null && alert.getText().equals(message)){
            alert.accept();//этим мы делаем клик на кнопку ок
            //alert.dismiss();//этим мы кликаем на кнопку кенсел
            //alert.sendKeys("hello");если надо чтото напечатать в алерт
            return true;
        }
        return false;
    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void getScreen(String link) {
        TakesScreenshot takescreenshort = (TakesScreenshot) wd;//сделали кастомизацию wd
        File tmp =takescreenshort.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getScreenElement(String link,By locator) {
        WebElement element = wd.findElement(locator);
        File tmp =element.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
