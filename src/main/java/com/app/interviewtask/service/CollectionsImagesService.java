package com.app.interviewtask.service;

import com.app.interviewtask.dto.CollectionImagesDto;
import com.app.interviewtask.dto.mappers.ModelMapper;
import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.repository.CollectionImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionsImagesService {


    private CollectionImagesRepository collectionImagesRepository;
    private ModelMapper modelMapper;

    public CollectionsImagesService(CollectionImagesRepository collectionImagesRepository, ModelMapper modelMapper) {
        this.collectionImagesRepository = collectionImagesRepository;
        this.modelMapper = modelMapper;
    }

    public List<CollectionImagesDto> findAll()
    {
        try {
            return collectionImagesRepository.findAll().stream().map(modelMapper::fromCollectionImagesToCollectionImagesDto).collect(Collectors.toList());
        } catch (Exception e){
            throw new MyException("FIND ALL COLLECTIONS IMAGES EXCEPTION");
        }
    }
}
