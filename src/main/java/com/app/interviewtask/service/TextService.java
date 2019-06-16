package com.app.interviewtask.service;


import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.model.Text;
import com.app.interviewtask.repository.TextRepository;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TextService {

    private TextRepository textRepository;

    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public Text saveText(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            File file = new File("C:/xxx/" + url.replaceAll("[^a-zA-Z^]", "") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + ".txt");
            FileUtils.writeStringToFile(file, doc.text());
            return textRepository.save(Text.builder().url(url).filename(file.toString()).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("ADD TEXT EXCEPTION");
        }
    }

    public Text findById(Long id) {
        try {
            Text text = textRepository.findById(id).orElseThrow(NullPointerException::new);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(text.getFilename()));
            return text;
        } catch (Exception e) {
            throw new MyException("FIND TEXT BY ID EXCEPTION");
        }
    }

    public List<Text> findAll() {
        try {
            return textRepository.findAll();
        } catch (Exception e){
            throw new MyException("FIND ALL TEXT EXCEPTION");
        }
    }
}
