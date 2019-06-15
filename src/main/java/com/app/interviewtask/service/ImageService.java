package com.app.interviewtask.service;


import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.repository.CollectionImagesRepository;
import com.app.interviewtask.repository.ImageReposiotory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private ImageReposiotory imageReposiotory;
    private CollectionImagesRepository collectionImagesRepository;

    @Autowired
    public ImageService(ImageReposiotory imageReposiotory, CollectionImagesRepository collectionImagesRepository) {
        this.imageReposiotory = imageReposiotory;
        this.collectionImagesRepository = collectionImagesRepository;
    }

    public void saveImages(String url) {
        System.setProperty("webdriver.chrome.driver", "D:\\Projekty\\Interview_Task\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        List<WebElement> elements = driver.findElements(By.tagName("img"));
        CollectionImages collectionImages = CollectionImages.builder().build();
        List<Image> list = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            try {
                URL urlImage = new URL(elements.get(i).getAttribute("src"));
                File file = new File("C:/xxx/" + url.replaceAll("[^a-zA-Z^]", "") + "_photo" + i + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + ".jpg");
                Image image = Image.builder().url(url).collectionImages(collectionImages).filename(file.toString()).build();
                imageReposiotory.save(image);
                FileUtils.copyURLToFile(urlImage, file);
                list.add(image);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        collectionImages.setImageList(list);
        collectionImagesRepository.save(collectionImages);
    }

    public Image findById(Long id) {
        try {
            Image image = imageReposiotory.findById(id).orElseThrow(NullPointerException::new);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(image.getFilename()));
            return image;
        } catch (Exception e) {
            throw new MyException("FIND IMAGE BY ID EXCEPTION");
        }
    }

    public List<Image> findAll() {
        try {

            return imageReposiotory.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("FIND ALL IMAGES EXCEPTION");
        }
    }
}
