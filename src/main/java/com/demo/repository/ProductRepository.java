package com.demo.repository;

import com.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("select e from Product e where e.isDeleted=false")
//    List<Product> findAll();
//    @Query("select e from Product e where e.isDeleted=false and e.id=?1")
//    Optional<Product> findById(Long id);
    @Modifying
    @Query("update Product e set e.isDeleted=true where e.id=?1")
    void deleteById(Long id);
}
