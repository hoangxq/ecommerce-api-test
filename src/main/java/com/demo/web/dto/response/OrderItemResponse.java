package com.demo.web.dto.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private Long quantity;
    private ProductResponse productResponse;
    private Double totalPrice;
}
