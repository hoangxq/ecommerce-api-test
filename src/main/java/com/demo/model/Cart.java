package com.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(name = "description")
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private Set<CartItem> cartItems = new HashSet<>();
}
