package com.app.interviewtask.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExceptionInfo {
    private LocalDateTime dateTime;
    private String description;
}
