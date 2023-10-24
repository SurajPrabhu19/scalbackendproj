package com.sp.scalbackendproj.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({ "id", "rating" })

public class GenericProductDto {
    private Long id;
    private String title;
    private String category;
    private String description;
    private double price;
    private String image;
    private Double rating;
}
