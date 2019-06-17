package com.app.interviewtask.service;


import com.app.interviewtask.dto.TextDto;
import com.app.interviewtask.dto.mappers.ModelMapper;
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
import java.util.stream.Collectors;

@Service
public class TextService {

    private TextRepository textRepository;
    private ModelMapper modelMapper;

    public TextService(TextRepository textRepository, ModelMapper modelMapper) {
        this.textRepository = textRepository;
        this.modelMapper = modelMapper;
    }

    public TextDto saveText(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            File file = new File("C:/xxx/" + url.replaceAll("[^a-zA-Z^]", "") + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + ".txt");
            FileUtils.writeStringToFile(file, doc.text());
            return modelMapper.fromTextToTextDto(textRepository.save(Text.builder().url(url).filename(file.toString()).build()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("ADD TEXT EXCEPTION");
        }
    }

    public TextDto findById(Long id) {
        try {
            Text text = textRepository.findById(id).orElseThrow(NullPointerException::new);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(text.getFilename()));
            return modelMapper.fromTextToTextDto(text);
        } catch (Exception e) {
            throw new MyException("FIND TEXT BY ID EXCEPTION");
        }
    }

    public List<TextDto> findAll() {
        try {
            return textRepository.findAll().stream().map(modelMapper::fromTextToTextDto).collect(Collectors.toList());
        } catch (Exception e){
            throw new MyException("FIND ALL TEXT EXCEPTION");
        }
    }
}
