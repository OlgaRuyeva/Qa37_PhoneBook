package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openAddContactForm() {
        click(By.xpath("//*[text()='ADD']"));
    }

    public void fillAddContactForm(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name'] "), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone'] "), contact.getPhone());
        type(By.cssSelector("[placeholder='email'] "), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());

    }

    public void save() {
        click(By.xpath("//*[text()='Save']"));
    }
    public String getMessage() {

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".contact-item_card__2SOIM"))));

        return wd.findElement(By.cssSelector(".contact-item_card__2SOIM>h3")).getText();
    }

    public void back() {
        wd.navigate().back();
    }

    public boolean isContactAddedByPhone(String phone) {
       List<WebElement> list = wd.findElements(By.cssSelector("h3"));//foreach обходим все h3 и ищем equals
        for (WebElement el:list) {
            if(el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }
}
