package com.app.interviewtask;

import com.app.interviewtask.exceptions.MyException;
import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.ProxySelector;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverConfig {

    private RemoteWebDriver driver;
    //private WebDriver driver;

    public List<WebElement> getAllImagesByUrl(String url) throws Exception {
        try {
/*            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
             driver = new ChromeDriver();*/
            ProxySelector proxy = ProxySelector.getDefault();
            ProxySelector.setDefault(proxy);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            driver = new RemoteWebDriver(new URL("http://hub:4444/wd/hub"), options);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(url);
            return driver.findElements(By.tagName("img"));
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new MyException("DRVIER EXCEPTION");
        }
    }

    public void closeConnection() {
        driver.close();
        driver.quit();
    }

}
