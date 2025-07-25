package com.ra.ss13.controller;

import com.ra.ss13.model.dto.request.ProductDTO;
import com.ra.ss13.model.dto.response.APIResponse;
import com.ra.ss13.model.entity.Product;
import com.ra.ss13.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<APIResponse<Product>> addProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(new APIResponse<>(true, "Them thanh cong!", productService.save(productDTO), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Product updated = productService.update(productDTO, id);
        return new ResponseEntity<>(new APIResponse<>(true, "Cap nhat thanh cong!", updated, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(new APIResponse<>(true, "Xoa thanh cong!", null, HttpStatus.OK), HttpStatus.OK);
    }
}
