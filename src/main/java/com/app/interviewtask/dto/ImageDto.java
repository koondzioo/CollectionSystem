package com.app.interviewtask.dto;

import com.app.interviewtask.model.CollectionImages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    private Long id;
    private String url;
    private String filename;
    private CollectionImages collectionImages;
}
