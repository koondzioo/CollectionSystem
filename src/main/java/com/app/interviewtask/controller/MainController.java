package com.app.interviewtask.controller;

import com.app.interviewtask.dto.CollectionImagesDto;
import com.app.interviewtask.dto.ImageDto;
import com.app.interviewtask.dto.TextDto;
import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.service.CollectionsImagesService;
import com.app.interviewtask.service.ImageService;
import com.app.interviewtask.service.TextService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

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
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            ImageDto imageDto = imageService.findById(id);
            File initialFile = new File(imageDto.getFilename());
            InputStream in = new FileInputStream(initialFile);
            return ResponseEntity.ok(IOUtils.toByteArray(in));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found image");
        }
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

    @GetMapping("/text")
    public ResponseEntity<List<TextDto>> getAllTexts() {
        return ResponseEntity.ok(textService.findAll());
    }

    @GetMapping(value = "/text/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<byte[]> getText(@PathVariable Long id) throws Exception {
        try {
            TextDto textDto = textService.findById(id);
            File initialFile = new File(textDto.getFilename());
            InputStream in = new FileInputStream(initialFile);
            return ResponseEntity.ok(IOUtils.toByteArray(in));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found text");
        }
    }
}



