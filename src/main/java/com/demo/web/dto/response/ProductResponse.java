package com.demo.web.dto.response;

import com.demo.web.dto.request.CategoryRequest;
import lombok.Data;

import java.util.Set;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    private String description;
    private Set<String> categoryRes;
}
