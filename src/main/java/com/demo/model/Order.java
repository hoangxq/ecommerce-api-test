package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receiverAddress;
    private String receiverName;
}
