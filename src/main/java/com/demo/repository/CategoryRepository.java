package com.demo.repository;

import com.demo.model.Category;
import com.demo.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query("select e from Category e where e.isDeleted=false")
//    List<Category> findAll();
//    @Query("select e from Category e where e.isDeleted=false and e.id=?1")
//    Optional<Category> findById(Long id);
//    @Query("select e from Category e where e.isDeleted=false and e.name=?1")
    Optional<Category> findByName(String name);
    @Modifying
    @Query("update Category e set e.isDeleted=true where e.id=?1")
    void deleteById(Long id);
    @EntityGraph(attributePaths = "products")
    Optional<Category> findOneWithProductsByName(String name);
}
