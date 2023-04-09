package manager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HelperBase {
    WebDriver wd;//after as we wrote it, we generate constructor right mause's button

    public HelperBase(WebDriver wd) {
        this.wd = wd;   }//это сгенерированный конструктор по WebDriver wd
    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        if (text != null){
            element.sendKeys(text);
        }
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
}
