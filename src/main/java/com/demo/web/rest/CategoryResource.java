package com.demo.web.rest;

import com.demo.service.CategoryService;
import com.demo.web.dto.request.CategoryRequest;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryResource {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Response> getAllCategory (){
        return ResponseUtils.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCategoryById(@PathVariable Long id){
        return ResponseUtils.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Response> createCategory (@RequestBody CategoryRequest categoryReq){
        return ResponseUtils.created(categoryService.createCategory(categoryReq));
    }
}
