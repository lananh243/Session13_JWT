package com.ra.ss13.controller;

import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.model.entity.Product;
import com.ra.ss13.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class PublicProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAllProducts(){
        return new ResponseEntity<>(new APIResponse<>(true, "Lay danh sach san pham thanh cong!",  productService.getAllProducts(), HttpStatus.OK), HttpStatus.OK);
    }
}
