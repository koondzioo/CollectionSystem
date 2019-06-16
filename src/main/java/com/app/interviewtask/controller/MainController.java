package com.app.interviewtask.controller;

import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.model.Text;
import com.app.interviewtask.service.CollectionsImagesService;
import com.app.interviewtask.service.ImageService;
import com.app.interviewtask.service.TextService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MainController {

    private ImageService imageService;
    private CollectionsImagesService collectionsImagesService;
    private TextService textService;

    public MainController(ImageService imageService, CollectionsImagesService collectionsImagesService, TextService textService) {
        this.imageService = imageService;
        this.collectionsImagesService = collectionsImagesService;
        this.textService = textService;
    }

    @PostMapping("/images")
    public List<Image> downloadImagesFromURL(@RequestBody String url) {
        return imageService.saveImages(url);
    }

    @GetMapping("/images/{id}")
    public Image getImage(@PathVariable Long id) {
        return imageService.findById(id);
    }

    @GetMapping("/images")
    public List<Image> getAllImages() {
        return imageService.findAll();
    }

    @GetMapping("/collectionImages")
    public List<CollectionImages> getAllCollectionImages() {
        return collectionsImagesService.findAll();
    }

    @PostMapping("/text")
    public Text downloadTextFromURL(@RequestBody String url) {
        return textService.saveText(url);
    }

    @GetMapping("/text/{id}")
    public Text getText(@PathVariable Long id) {
        return textService.findById(id);
    }

    @GetMapping("/text")
    public List<Text> getAllTexts(){
        return textService.findAll();
    }

}
