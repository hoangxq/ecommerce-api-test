package com.demo.web.rest;

import com.demo.web.dto.response.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderResource {
    @GetMapping("/{userId}")
    public ResponseEntity<Response> getOrderOfUser (@PathVariable Long userId){
        return null;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Response> createOrder (@PathVariable Long userId){
        return null;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Response> updateOrder (@PathVariable Long userId){
        return null;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Response> deleteOrder (@PathVariable Long userId){
        return null;
    }
}
