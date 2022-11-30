package com.demo.web.rest;

import com.demo.service.ProductService;
import com.demo.web.dto.request.ProductRequest;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/products")
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Response> getAllProduct (){
        return ResponseUtils.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById (@PathVariable Long id){
        return ResponseUtils.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Response> createProduct (@RequestBody ProductRequest productReq){
        return ResponseUtils.created(productService.createProduct(productReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProductById (@PathVariable Long id, @RequestBody ProductRequest productReq){
        return ResponseUtils.ok("Updated", productService.updateProduct(productReq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct (@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseUtils.ok("Deleted");
    }
}
