package com.demo.web.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class CartItemRequest {
    private Long productId;
    @Size(min = 1)
    private Long quantity;
}
