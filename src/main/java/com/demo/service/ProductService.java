package com.demo.service;

import com.demo.web.dto.request.ProductRequest;
import com.demo.web.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct();
    ProductResponse getProductById(Long id);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProduct(ProductRequest productSource, Long id);
    void deleteProduct(Long id);
}
