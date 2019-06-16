package com.app.interviewtask.service;


import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.model.Text;
import com.app.interviewtask.repository.TextRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

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
            return textRepository.save(Text.builder().text(doc.text()).build());
        } catch (Exception e) {
            throw new MyException("ADD TEXT EXCEPTION");
        }
    }

    public Text findById(Long id) {
        try {
            return textRepository.findById(id).orElseThrow(NullPointerException::new);
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
