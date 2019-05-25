package com.epages.interview.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;

import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import com.epages.interview.rest.mapper.ProductRestMapper;
import com.epages.interview.rest.utils.EventType;


public class ProductRestMapperShould
{
    final Brand HONDA = Brand.builder().name("Honda").build();

    private ProductRestMapper testSubject = new ProductRestMapper();

    @Test
    public void returnNullIfNoProduct()
    {
        assertNull(testSubject.map(null));
    }

    @Test
    public void mapProductsToRestProducts()
    {
        //given
        final Product candidate = Product.builder().id(55L).brand(HONDA).name("Civic").price(BigDecimal.valueOf(1000)).onSale(true).build();

        //when
        ProductRest result = testSubject.map(candidate);

        //then
        assertNotNull(result);
        assertEquals(candidate.getPrice(), result.getPrice());
        assertEquals(candidate.getName(), result.getName());
        assertEquals(EventType.ON_SALE.name(), result.getEvent());
    }

    @Test
    public void mapProductToRestWithNoEvent()
    {
        //given
        final Product candidate = Product.builder().id(55L).brand(HONDA).name("Civic").price(BigDecimal.valueOf(1000)).onSale(false).build();

        //when
        ProductRest result = testSubject.map(candidate);

        //then
        assertNotNull(result);
        assertEquals(candidate.getPrice(), result.getPrice());
        assertEquals(candidate.getName(), result.getName());
        assertNull(result.getEvent());

    }

}
