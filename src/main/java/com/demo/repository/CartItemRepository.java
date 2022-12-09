package com.demo.repository;

import com.demo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_Account_Id(Long accountId);
    Optional<CartItem> findByCart_Account_IdAndProduct_Id(Long accountId, Long productId);
    Optional<CartItem> findById(Long id);
    void deleteById(Long id);
}
