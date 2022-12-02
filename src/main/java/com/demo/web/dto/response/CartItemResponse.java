package com.demo.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CartItemResponse {
    private Long id;
    private ProductResponse productRes;
    private Long quantity;
    private Double totalPrice;
}
