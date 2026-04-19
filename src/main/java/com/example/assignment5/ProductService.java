package com.example.assignment5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        Product saved = repository.save(product);
        return new ProductDTO(saved.getName(), saved.getPrice());
    }

    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return new ProductDTO(product.getName(), product.getPrice());
    }

    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(p -> new ProductDTO(p.getName(), p.getPrice()))
                .collect(Collectors.toList());
    }
}

