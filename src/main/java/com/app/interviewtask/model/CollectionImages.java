package com.app.interviewtask.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CollectionImages {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "collectionImages")
    private List<Image> imageList;

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public List<Image> getImageList() {
        return imageList;
    }

    @Override
    public String toString() {
        return "CollectionImages{" +
                "id=" + id +
                '}';
    }


}
