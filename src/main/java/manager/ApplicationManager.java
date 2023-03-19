package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;
    HelperUser helperUser;
    public void init(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        wd = new ChromeDriver(options);
        wd.manage().window().maximize();//раскрытие на полный экран
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//позволяет в теч этого времени сделать тест
        wd.navigate().to("https://telranedu.web.app/");

        helperUser = new HelperUser(wd);
    }
    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop(){
        wd.quit();

    }

}
