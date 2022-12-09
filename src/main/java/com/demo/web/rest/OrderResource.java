package com.demo.web.rest;

import com.demo.service.OrderService;
import com.demo.web.dto.request.OrderRequest;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderResource {
    private final OrderService orderService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response> getOrderOfUser (@PathVariable Long userId){
        return ResponseUtils.ok(orderService.getAllOrderOfUser(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Response> createOrder (@PathVariable Long userId, @RequestBody OrderRequest orderRequest){
        return ResponseUtils.created(orderService.createOrder(userId, orderRequest));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Response> updateOrderIsPending (@PathVariable Long orderId){
        return null;
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Response> cancelOrderIsPending (@PathVariable Long orderId){
        return null;
    }

    @GetMapping("/order-item/{orderId}")
    public ResponseEntity<Response> getOrderItemOfOrder(@PathVariable Long orderId){
        return null;
    }
}
