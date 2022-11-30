package com.demo.web.dto.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CategoryRequest {
    private String name;
    private String description;
}
