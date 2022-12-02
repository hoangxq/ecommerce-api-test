package com.demo.web.rest;

import com.demo.service.CartService;
import com.demo.web.dto.request.CartItemRequest;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
public class CartResource {
    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response> getCartItemOfUser (@PathVariable Long userId){
        return ResponseUtils.ok(cartService.getCartItemOfUser(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Response> addProductToCart (@PathVariable Long userId,
                                @RequestBody CartItemRequest cartItemReq){
        return ResponseUtils.created(cartService.addProductToCart(userId, cartItemReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCartItem (@PathVariable Long id){
        cartService.deleteCartItem(id);
        return ResponseUtils.ok("deleted");
    }
}
