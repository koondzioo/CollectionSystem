package com.app.interviewtask.service;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.io.File;
import java.net.URL;
import java.util.List;

public class ImageService {

    public void saveImages(String url)
    {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        List<WebElement> elements = driver.findElements(By.tagName("img"));
        for (int i = 0; i < elements.size() ; i++)
        {
            try{
                URL urlImage = new URL(elements.get(i).getAttribute("src"));
                File file = new File("C:/xxx/photo"+i+".jpg");
                FileUtils.copyURLToFile(urlImage, file);
            } catch (Exception ee) {ee.printStackTrace();}
        }
    }
}
