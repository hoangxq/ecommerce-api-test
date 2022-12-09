package com.demo.web.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderResponse {
    private Long id;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private Date timeOrder;
    private Double totalPrice;
    private boolean isDelete;
    private String status;
    private AccountResponse accountResponse;
    private Set<OrderItemResponse> orderItemResponses = new HashSet<>();
}
