package com.epages.interview.service;

import java.util.List;

import com.epages.interview.domain.Product;


public interface ProductService
{

    /**
     * Returns all the products in the inventory
     * @return a {@link List} of {@link Product}
     */
    List<Product> getAllProducts();

    /**
     * IN MEMORY SORTING approach
     *
     * Returns all the Products in the inventory with the requested order
     *  - Products are grouped by `brand`, sorted alphabetically
     *  - Products inside a `brand` should be sorted ascending by `price`
     * @return a {@link List} of {@link Product}
     */
    List<Product> getAllProductsWithOrder();

    /**
     * JPA SPECIFICATION approach
     *
     * Returns all the Products in the inventory with the requested order
     *  - Products are grouped by `brand`, sorted alphabetically
     *  - Products inside a `brand` should be sorted ascending by `price`
     * @return a {@link List} of {@link Product}
     */
    List<Product> getAllProductsWithSpecification();

}
