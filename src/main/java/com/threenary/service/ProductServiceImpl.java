package com.threenary.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threenary.domain.Product;
import com.threenary.repo.ProductRepository;
import com.threenary.repo.ProductSpecification;


@Service
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(final ProductRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts()
    {
        return repository.findAll();
    }

    @Override
    public List<Product> getAllProductsWithOrder()
    {
        List<Product> products = repository.findAll();
        products.sort(Comparator.naturalOrder());

        return products;
    }

    @Override
    public List<Product> getAllProductsWithSpecification()
    {
        return repository.findAll(ProductSpecification.sortedByBrandAndPrice());
    }
}
