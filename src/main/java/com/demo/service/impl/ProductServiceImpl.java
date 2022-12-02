package com.demo.service.impl;

import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.request.CategoryRequest;
import com.demo.web.dto.request.ProductRequest;
import com.demo.web.dto.response.ProductResponse;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<ProductResponse> getAllProduct() {
        var products = productRepository.findAll();
        return products.stream()
                .map(e -> productMapper(e))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map(e -> productMapper(e))
                .orElseThrow(() -> new EntityNotFoundException(Product.class.getName(), id.toString()));
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        var product = modelMapper.map(productRequest, Product.class);
        var categories = productRequest.getCategories().stream()
                .map(e -> getCategoryByName(e)).collect(Collectors.toSet());
        product.setCategories(categories);
        productRepository.save(product);
        return productMapper(product);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productSource, Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class.getName(), id.toString()));
        mappingHelper.mapIfSourceNotNullAndStringNotBlank(productSource, product);
        var categories = productSource.getCategories().stream()
                .map(e -> getCategoryByName(e)).collect(Collectors.toSet());
        product.setCategories(categories);
        return productMapper(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getProductByCategory(String categoryName) {
        var category = categoryRepository.findOneWithProductsByName(categoryName)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getName(), categoryName));
        return category.getProducts().stream()
                .map(e -> productMapper(e))
                .collect(Collectors.toList());
    }

    private Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getName(), name));
    }

    private ProductResponse productMapper(Product product) {
        var productRes = modelMapper.map(product, ProductResponse.class);
        var categoryRes = product.getCategories().stream()
                .map(i -> i.getName()).collect(Collectors.toSet());
        productRes.setCategoryRes(categoryRes);
        return productRes;
    }
}
