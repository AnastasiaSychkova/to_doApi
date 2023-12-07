package com.example.to_do_api.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
public class CreateTaskDto {
    private String taskName;
    private String description;
    private MultipartFile file;
}
