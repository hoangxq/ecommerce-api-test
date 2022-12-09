package com.demo.service;

import com.demo.web.dto.request.OrderRequest;
import com.demo.web.dto.response.OrderItemResponse;
import com.demo.web.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrderOfUser(Long userId);
    OrderResponse createOrder (Long userId, OrderRequest orderRequest);
    OrderResponse updateOrderIsPending (Long orderId, OrderRequest source);
    OrderResponse cancelOrderIsPending (Long orderId);
    List<OrderItemResponse> getOrderItemOfOrder (Long orderId);
}
