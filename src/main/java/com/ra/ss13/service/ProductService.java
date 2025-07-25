package com.ra.ss13.service;

import com.ra.ss13.model.dto.request.ProductDTO;
import com.ra.ss13.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product save(ProductDTO productDTO);
    Product findById(Long id);
    Product update(ProductDTO productDTO, Long id);
    void delete(Long id);
}
