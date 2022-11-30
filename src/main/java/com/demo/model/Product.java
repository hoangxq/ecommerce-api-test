package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
@Where(clause = "is_deleted=0")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;
    @Column(name = "description")
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<ImageModel> images = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "thumbnail_id")
    private ImageModel thumbnail;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
