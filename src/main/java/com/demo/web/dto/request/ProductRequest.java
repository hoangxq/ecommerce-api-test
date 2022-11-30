package com.demo.web.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class ProductRequest {
    private String name;
    private Integer quantity;
    private Double price;
    private String description;
    private Set<String> categories;
}
