package com.sli.spmm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sli.spmm.model.Product;
import com.sli.spmm.repository.ProductRepository;

public class ProductVariantController
{
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false)
    String name)
    {
        try
        {
            List<Product> products = new ArrayList<Product>();

            if (name == null)
            {
                productRepository.findAll().forEach(products::add);
            }
            else
            {
                productRepository.findByNameContaining(name).forEach(products::add);
            }

            if (products.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
