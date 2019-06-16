package com.app.interviewtask.service;


import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.repository.CollectionImagesRepository;
import com.app.interviewtask.repository.ImageRepository;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;
    private CollectionImagesRepository collectionImagesRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, CollectionImagesRepository collectionImagesRepository) {
        this.imageRepository = imageRepository;
        this.collectionImagesRepository = collectionImagesRepository;
    }

    public List<Image> saveImages(String url) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
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
                imageRepository.save(image);
                FileUtils.copyURLToFile(urlImage, file);
                list.add(image);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        collectionImages.setImageList(list);
        collectionImages.setUrl(url);
        collectionImagesRepository.save(collectionImages);
        return list;
    }

    public Image findById(Long id) {
        try {
            Image image = imageRepository.findById(id).orElseThrow(NullPointerException::new);
/*            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(image.getFilename()));*/
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("FIND IMAGE BY ID EXCEPTION");
        }
    }

    public List<Image> findAll() {
        try {

            return imageRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("FIND ALL IMAGES EXCEPTION");
        }
    }
}
