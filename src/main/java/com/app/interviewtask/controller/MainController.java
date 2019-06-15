package com.app.interviewtask.controller;

import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.service.CollectionsImagesService;
import com.app.interviewtask.service.ImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MainController {

    private ImageService imageService;
    private CollectionsImagesService collectionsImagesService;

    public MainController(ImageService imageService, CollectionsImagesService collectionsImagesService) {
        this.imageService = imageService;
        this.collectionsImagesService = collectionsImagesService;
    }

    @PostMapping("/images")
    public String printURL(@RequestBody String url)
    {
        imageService.saveImages(url);

        System.out.println(imageService.findById(1L));
        return "HELLO";
    }

    @GetMapping("/images/{id}")
    public Image getImage(@PathVariable Long id)
    {
        return imageService.findById(id);
    }

    @GetMapping("/images")
    public List<Image> getAllImages()
    {
        return imageService.findAll();
    }
}
