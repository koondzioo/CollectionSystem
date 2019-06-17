package com.app.interviewtask.controller;

import com.app.interviewtask.dto.CollectionImagesDto;
import com.app.interviewtask.dto.ImageDto;
import com.app.interviewtask.dto.TextDto;
import com.app.interviewtask.service.CollectionsImagesService;
import com.app.interviewtask.service.ImageService;
import com.app.interviewtask.service.TextService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ImageDto>> downloadImagesFromURL(RequestEntity<String> requestEntity) {
        return new ResponseEntity<>(imageService.saveImages(requestEntity.getBody()), HttpStatus.OK);
        // return imageService.saveImages(url);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<ImageDto> getImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.findById(id));
    }

    @GetMapping("/images")
    public ResponseEntity<List<ImageDto>> getAllImages() {
        return ResponseEntity.ok(imageService.findAll());
    }

    @GetMapping("/collectionImages")
    public ResponseEntity<List<CollectionImagesDto>> getAllCollectionImages() {
        return ResponseEntity.ok(collectionsImagesService.findAll());
    }

    @PostMapping("/text")
    public ResponseEntity<TextDto> downloadTextFromURL(RequestEntity<String> requestEntity) {
        return new ResponseEntity<>(textService.saveText(requestEntity.getBody()), HttpStatus.OK);
    }

    @GetMapping("/text/{id}")
    public ResponseEntity<TextDto> getText(@PathVariable Long id) {
        return ResponseEntity.ok(textService.findById(id));
    }

    @GetMapping("/text")
    public ResponseEntity<List<TextDto>> getAllTexts() {
        return ResponseEntity.ok(textService.findAll());
    }

}
