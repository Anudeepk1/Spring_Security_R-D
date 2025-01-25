package com.learning.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private record Product(Integer id, String name) {
    }

    List<Product> products = new ArrayList<>(
            List.of(new Product(1, "Apple"),
                    new Product(2, "Blueberry")
            )
    );

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping
    public List<Product> saveProduct(@RequestBody Product product) {
        products.add(product);
        return products;
    }

}

