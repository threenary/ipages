package com.epages.interview.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import com.epages.interview.repo.ProductRepository;


@RunWith(SpringRunner.class)
public class ProductServiceShould
{
    private final static Brand MECEDES = Brand.builder().name("Mercedes").build();
    private final static Brand FORD = Brand.builder().name("Ford").build();
    private final static Brand SEAT = Brand.builder().name("Seat").build();

    @Mock
    private ProductRepository repository;

    private ProductService testSubject;

    @Before
    public void setUp()
    {
        testSubject = new ProductServiceImpl(repository);
    }

    @Test
    public void returnNoProductsWhenRepositoryIsEmpty()
    {
        //given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<Product> results = testSubject.getAllProducts();

        //then
        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void returnAllProductsAvailable()
    {
        //given
        final Product mustang = Product.builder().name("Mustang").brand(FORD).onSale(true).price(BigDecimal.valueOf(10000)).build();
        final Product aClass = Product.builder().name("A Class").brand(MECEDES).onSale(false).price(BigDecimal.valueOf(15000)).build();
        final Product ibiza = Product.builder().name("Ibiza").brand(MECEDES).onSale(true).price(BigDecimal.valueOf(1500)).build();
        List<Product> expected = Arrays.asList(mustang, aClass, ibiza);
        when(repository.findAll()).thenReturn(expected);

        //when
        List<Product> result = testSubject.getAllProducts();

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(expected.size(), result.size());
        assertThat(result, containsInAnyOrder(mustang, ibiza, aClass));
        verify(repository, times(1)).findAll();
    }

    @Test
    public void returnAllProductsSortedInMemory()
    {
        //given
        final Product mustang = Product.builder().name("Mustang").brand(FORD).onSale(true).price(BigDecimal.valueOf(10000)).build();
        final Product aClass = Product.builder().name("A Class").brand(MECEDES).onSale(false).price(BigDecimal.valueOf(15000)).build();
        final Product ibiza = Product.builder().name("Ibiza").brand(SEAT).onSale(true).price(BigDecimal.valueOf(1500)).build();
        final Product falcon = Product.builder().name("Falcon").brand(FORD).onSale(true).price(BigDecimal.valueOf(800)).build();
        List<Product> expected = Arrays.asList(mustang, aClass, ibiza, falcon);
        when(repository.findAll()).thenReturn(expected);

        //when
        List<Product> result = testSubject.getAllProductsWithOrder();

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(expected.size(), result.size());
        assertThat(result, IsIterableContainingInOrder.contains(falcon, mustang, aClass, ibiza));
        verify(repository, times(1)).findAll();
    }

    @Test
    public void returnAllProductsSortedJpaSpecification()
    {
        //given
        final Product mustang = Product.builder().name("Mustang").brand(FORD).onSale(true).price(BigDecimal.valueOf(10000)).build();
        final Product aClass = Product.builder().name("A Class").brand(MECEDES).onSale(false).price(BigDecimal.valueOf(15000)).build();
        final Product ibiza = Product.builder().name("Ibiza").brand(SEAT).onSale(true).price(BigDecimal.valueOf(1500)).build();
        final Product falcon = Product.builder().name("Falcon").brand(FORD).onSale(true).price(BigDecimal.valueOf(800)).build();
        List<Product> expected = Arrays.asList(falcon, mustang, aClass, ibiza);

        ArgumentCaptor<Specification> specificationsCaptor = ArgumentCaptor.forClass(Specification.class);
        when(repository.findAll(specificationsCaptor.capture())).thenReturn(expected);

        //when
        List<Product> result = testSubject.getAllProductsWithSpecification();

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(expected.size(), result.size());
        assertThat(result, IsIterableContainingInOrder.contains(falcon, mustang, aClass, ibiza));
        verify(repository, times(1)).findAll(specificationsCaptor.getValue());
    }

}
