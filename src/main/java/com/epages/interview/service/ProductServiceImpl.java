package com.epages.interview.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epages.interview.domain.Product;
import com.epages.interview.repo.ProductRepository;


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
}
