package com.example.myEcomProjectPractice.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDTO {
    private Long commentId;
    @NotBlank(message = "Content is required")
    private String commentText;
    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 5,message = "Score cannot be more than 5")
    private Integer score;
    private Long userId;
}
