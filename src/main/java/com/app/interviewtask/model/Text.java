package com.app.interviewtask.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Text {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
}
