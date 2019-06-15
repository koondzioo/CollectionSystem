package com.app.interviewtask.repository;

import com.app.interviewtask.model.CollectionImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionImagesRepository extends JpaRepository<CollectionImages, Long>{
}
