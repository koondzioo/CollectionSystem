package com.app.interviewtask.repository;

import com.app.interviewtask.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageReposiotory extends JpaRepository<Image, Long> {
}
