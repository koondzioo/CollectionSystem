package com.app.interviewtask.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private String url;
    private String filename;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "collectionImages_id")
    private CollectionImages collectionImages;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", filename='" + filename + '\'' +
                ", collectionsImages_id" + collectionImages.getId() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) &&
                Objects.equals(url, image.url) &&
                Objects.equals(filename, image.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, filename);
    }
}
