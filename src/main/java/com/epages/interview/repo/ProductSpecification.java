package com.epages.interview.repo;

import org.springframework.data.jpa.domain.Specification;

import com.epages.interview.domain.Product;


public class ProductSpecification
{
    public static Specification<Product> sortedByBrandAndPrice()
    {
        return ((root, query, cb) -> {
            query.orderBy(cb.asc(root.join("brand").get("name")), cb.asc(root.get("price")));
            return cb.and();
        });
    }

}
