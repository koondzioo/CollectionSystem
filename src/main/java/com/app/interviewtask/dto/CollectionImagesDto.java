package com.app.interviewtask.dto;

import com.app.interviewtask.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionImagesDto {
    private Long id;
    private List<Image> imageList;
    private String url;
}
