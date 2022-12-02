package com.demo.service.impl;

import com.demo.model.Cart;
import com.demo.model.CartItem;
import com.demo.model.Product;
import com.demo.repository.CartItemRepository;
import com.demo.repository.CartRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.CartService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.request.CartItemRequest;
import com.demo.web.dto.response.CartItemResponse;
import com.demo.web.dto.response.ProductResponse;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final MappingHelper mappingHelper;

    @Override
    public List<CartItemResponse> getCartItemOfUser(Long userId) {
        var cartItems = cartItemRepository.findByCart_Account_Id(userId);
        return cartItems.stream()
                .map(e -> {
                    return CartItemResponse.builder()
                            .productRes(productMapper(e.getProduct()))
                            .id(e.getId())
                            .quantity(e.getQuantity())
                            .totalPrice(e.getQuantity() * e.getProduct().getPrice())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public CartItemResponse addProductToCart(Long userId, CartItemRequest cartItemReq) {
        var cartItem = cartItemRepository.findByCart_Account_IdAndProduct_Id(userId, cartItemReq.getProductId())
                .orElseGet(() -> {
                    var cart = cartRepository.findByAccountId(userId)
                            .orElseThrow(() -> new EntityNotFoundException(Cart.class.getName(), userId.toString()));
                    var product = productRepository.findById(cartItemReq.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException(Product.class.getName(), cartItemReq.getProductId().toString()));
                    var cartItem1 = modelMapper.map(cartItemReq, CartItem.class);
                    cartItem1.setCart(cart);
                    cartItem1.setProduct(product);
                    return cartItemRepository.save(cartItem1);
                });
        cartItem.setQuantity(cartItemReq.getQuantity());
        cartItemRepository.save(cartItem);
        return CartItemResponse.builder()
                .productRes(productMapper(cartItem.getProduct()))
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice())
                .build();
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    private ProductResponse productMapper(Product product) {
        var productRes = modelMapper.map(product, ProductResponse.class);
        var categoryRes = product.getCategories().stream()
                .map(i -> i.getName()).collect(Collectors.toSet());
        productRes.setCategoryRes(categoryRes);
        return productRes;
    }
}
