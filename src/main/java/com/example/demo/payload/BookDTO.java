package com.example.demo.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotEmpty(message = "author password cannot be empty")
    private String author;
    @Min(value = 1, message = "count is wrong")
    private Integer count;
    private Double price;
    private Integer libraryId;
}
