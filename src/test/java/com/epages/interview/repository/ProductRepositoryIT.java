package com.epages.interview.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.epages.interview.domain.Product;
import com.epages.interview.repo.ProductRepository;


@DataJpaTest
@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"spring.datasource.data=test_data.sql"})
public class ProductRepositoryIT
{
    @Autowired
    private ProductRepository testSubject;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getAllAvailableProductsTest()
    {
        List<Product> products = testSubject.findAll();

        assertNotNull(products);
        assertFalse(products.isEmpty());
    }
}
