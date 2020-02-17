package com.threenary.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.threenary.domain.Product;
import com.threenary.repo.ProductRepository;
import com.threenary.repo.ProductSpecification;


@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"spring.datasource.data=test_data.sql"})
public class ProductRepositoryIT
{
    private static final int DBSIZE = 15;

    @Autowired
    private ProductRepository testSubject;

    @Before
    public void setUp()
    {

    }

    @Test
    public void getAllProducts()
    {
        List<Product> products = testSubject.findAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(DBSIZE, products.size());
    }

    @Test
    public void getAllProductsWithSpecification()
    {
        List<Product> products = testSubject.findAll(ProductSpecification.sortedByBrandAndPrice());

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(DBSIZE, products.size());
    }
}
