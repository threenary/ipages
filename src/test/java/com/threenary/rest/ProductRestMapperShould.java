package com.threenary.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import com.threenary.domain.Brand;
import com.threenary.domain.Product;
import com.threenary.rest.mapper.ProductRestMapper;
import com.threenary.rest.utils.EventType;
import org.junit.Assert;
import org.junit.Test;


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
        Assert.assertEquals(EventType.ON_SALE.name(), result.getEvent());
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
