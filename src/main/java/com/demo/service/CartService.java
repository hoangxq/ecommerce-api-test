package com.demo.service;

import com.demo.web.dto.request.CartItemRequest;
import com.demo.web.dto.response.CartItemResponse;

import java.util.List;

public interface CartService {
    List<CartItemResponse>  getCartItemOfUser(Long userId);
    CartItemResponse addProductToCart (Long userId, CartItemRequest cartItemReq);
    void deleteCartItem (Long cartItemId);
}
