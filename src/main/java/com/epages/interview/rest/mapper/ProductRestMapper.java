package com.epages.interview.rest.mapper;

import com.epages.interview.domain.Product;
import com.epages.interview.rest.utils.EventType;
import com.epages.interview.rest.ProductRest;


public class ProductRestMapper
{
    public static ProductRest map(final Product origin)
    {
        if (null != origin)
        {
            return ProductRest.builder()
                .name(origin.getName())
                .price(origin.getPrice())
                .id(origin.getId())
                .event(mapEvent(origin.isOnSale()))
                .build();
        }
        return null;
    }

    private static String mapEvent(final boolean onSale)
    {
        return onSale ? EventType.ON_SALE.name() : null;
    }
}
