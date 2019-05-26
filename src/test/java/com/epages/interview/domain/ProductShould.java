package com.epages.interview.domain;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;


public class ProductShould
{
    final private static Brand brand1 = Brand.builder().name("AMG").id(1L).build();
    final private static Brand brand2 = Brand.builder().name("Maybach").id(1L).build();

    final private static Product product1 = Product.builder().brand(brand1).name("Name1").id(1L).price(new BigDecimal(1000L)).onSale(false).build();
    final private static Product product2 = Product.builder().brand(brand2).name("Name2").id(1L).price(new BigDecimal(9000L)).onSale(false).build();
    final private static Product product3 = Product.builder().brand(brand2).name("Name3").id(1L).price(new BigDecimal(500L)).onSale(false).build();

    @Test
    public void returnNegativeValueWhenCompartingWithProduct()
    {
        assertTrue(product1.compareTo(product2) < 0);
    }

    @Test
    public void returnPositiveWhenSameBrandWithDifferentPrice()
    {
        assertTrue(product2.compareTo(product3) > 0);
    }

    public void returnNegativeWhenSameBrandAndLowerPrice()
    {
        assertTrue(product3.compareTo(product2) < 0);
    }
}