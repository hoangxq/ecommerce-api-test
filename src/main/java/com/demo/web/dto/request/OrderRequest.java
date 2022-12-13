package com.demo.web.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderRequest {
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private Date timeOrder;
    private String status;
    private Set<Long> cartItemIds = new HashSet<>();
}
