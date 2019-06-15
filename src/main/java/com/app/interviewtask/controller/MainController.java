package com.app.interviewtask.controller;

import com.app.interviewtask.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class MainController {

    private ImageService imageService;

    public MainController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images")
    public String printURL(@RequestBody String url)
    {
        imageService.saveImages(url);
        return "HELLO";
    }
}
