package com.app.interviewtask.dto.mappers;

import com.app.interviewtask.dto.CollectionImagesDto;
import com.app.interviewtask.dto.ImageDto;
import com.app.interviewtask.dto.TextDto;
import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.model.Text;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public Image fromImageDtotoImage(ImageDto imageDto) {
        return imageDto == null ? null : Image.builder()
                .id(imageDto.getId())
                .filename(imageDto.getFilename())
                .collectionImages(imageDto.getCollectionImages())
                .url(imageDto.getUrl())
                .build();
    }

    public ImageDto fromImagetoImageDto(Image image) {
        return image == null ? null : ImageDto.builder()
                .id(image.getId())
                .filename(image.getFilename())
                .collectionImages(image.getCollectionImages())
                .url(image.getUrl())
                .build();
    }

    public CollectionImages fromCollectionImagesDtoToCollectionImages(CollectionImagesDto collectionImagesDto) {
        return collectionImagesDto == null ? null : CollectionImages.builder()
                .id(collectionImagesDto.getId())
                .imageList(collectionImagesDto.getImageList())
                .url(collectionImagesDto.getUrl())
                .build();
    }

    public CollectionImagesDto fromCollectionImagesToCollectionImagesDto(CollectionImages collectionImages) {
        return collectionImages == null ? null : CollectionImagesDto.builder()
                .id(collectionImages.getId())
                .imageList(collectionImages.getImageList())
                .url(collectionImages.getUrl())
                .build();
    }

    public Text fromTextDtoToText(TextDto textDto) {
        return textDto == null ? null : Text.builder()
                .id(textDto.getId())
                .url(textDto.getUrl())
                .filename(textDto.getFilename())
                .build();
    }

    public TextDto fromTextToTextDto(Text text) {
        return text == null ? null : TextDto.builder()
                .id(text.getId())
                .url(text.getUrl())
                .filename(text.getFilename())
                .build();
    }
}
