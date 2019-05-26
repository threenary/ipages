package com.epages.interview.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class BrandShould
{
    final private static Brand brand1 = Brand.builder().name("AMG").id(1L).build();

    final private static Brand brand2 = Brand.builder().name("Maybach").id(1L).build();

    @Test
    public void returnNegativeValueWhenComparingWithBrand()
    {
        final int result = brand1.compareTo(brand2);
        assertTrue(result < 0);
    }

    @Test
    public void returnPositiveValueWhenComparingWithBrand()
    {
        final int result = brand2.compareTo(brand1);
        assertTrue(result > 0);
    }
}