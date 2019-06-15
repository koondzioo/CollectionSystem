package com.app.interviewtask.service;

import com.app.interviewtask.exceptions.MyException;
import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.repository.CollectionImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionsImagesService {


    private CollectionImagesRepository collectionImagesRepository;

    public CollectionsImagesService(CollectionImagesRepository collectionImagesRepository) {
        this.collectionImagesRepository = collectionImagesRepository;
    }

    public List<CollectionImages> findAll()
    {
        try {
            return collectionImagesRepository.findAll();
        } catch (Exception e){
            throw new MyException("FIND ALL COLLECTIONS IMAGES EXCEPTION");
        }
    }
}
