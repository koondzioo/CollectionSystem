package com.app.interviewtask.service;


import com.app.interviewtask.DriverConfig;
import com.app.interviewtask.dto.ImageDto;
import com.app.interviewtask.dto.mappers.ModelMapper;
import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.repository.CollectionImagesRepository;
import com.app.interviewtask.repository.ImageRepository;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private ImageRepository imageRepository;
    private CollectionImagesRepository collectionImagesRepository;
    private ModelMapper modelMapper;
    private DriverConfig driverConfig;
    private String IMG_ROOT = "files/img/";
    //private String IMG_ROOT = "C://xxx//images//";

    @Autowired
    public ImageService(ImageRepository imageRepository, CollectionImagesRepository collectionImagesRepository, ModelMapper modelMapper, DriverConfig driverConfig) {
        this.imageRepository = imageRepository;
        this.collectionImagesRepository = collectionImagesRepository;
        this.modelMapper = modelMapper;
        this.driverConfig = driverConfig;
    }

    public List<ImageDto> saveImages(String url) {
        try {
            List<WebElement> elements = driverConfig.getAllImagesByUrl(url);
            CollectionImages collectionImages = CollectionImages.builder().url(url).build();
            List<Image> list = new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {
                try {
                    URL urlImage = new URL(elements.get(i).getAttribute("src"));
                    File file = new File(IMG_ROOT + url.replaceAll("[^a-zA-Z^]", "") + "_photo" + i + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + ".jpg");
                    Image image = Image.builder().url(url).collectionImages(collectionImages).filename(file.toString()).build();
                    imageRepository.save(image);
                    FileUtils.copyURLToFile(urlImage, file);
                    list.add(image);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
            collectionImages.setImageList(list);
            collectionImagesRepository.save(collectionImages);
            driverConfig.closeConnection();
            return list.stream().map(modelMapper::fromImagetoImageDto).collect(Collectors.toList());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new MyException("SAVE IMAGES EXCEPTION");
        }
    }

    public ImageDto findById(Long id) {
        try {
            Image image = imageRepository.findById(id).orElseThrow(NullPointerException::new);
            return modelMapper.fromImagetoImageDto(image);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("FIND IMAGE BY ID EXCEPTION");
        }
    }

    public List<ImageDto> findAll() {
        try {

            return imageRepository.findAll().stream().map(modelMapper::fromImagetoImageDto).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("FIND ALL IMAGES EXCEPTION");
        }
    }
}
