package com.threenary.repo;

import com.threenary.domain.Product;
import org.springframework.data.jpa.domain.Specification;


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
