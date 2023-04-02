package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}
