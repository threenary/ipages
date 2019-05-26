package com.epages.interview.rest;

import static com.epages.interview.infra.error.ApiErrorEnum.METHOD_NOT_SUPPORTED;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import com.epages.interview.rest.utils.EventType;
import com.epages.interview.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestControllerShould
{
    private final static String PRODUCTS_URI = "/products";
    private final static String RESOURCE_ALL = "/all";
    private final static String RESOURCE_SORTED = "/sorted";
    private final static Brand HONDA = Brand.builder().name("Honda").build();
    private final static Brand MITSUBISHI = Brand.builder().name("Mitsubishi").build();
    private final static Brand MERCEDES = Brand.builder().name("Mercedes").build();
    private final static long civicID = 1L;
    private final static long cityID = 2L;
    private final static long aClassID = 3L;
    private final static long busID = 4L;
    private final static long eclipseID = 5L;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void returnTheUniqueProductAvailable() throws Exception
    {
        //given
        final Product civic = Product.builder().brand(HONDA).id(55L).name("Civic").price(new BigDecimal(1000.55)).onSale(false).build();
        List<Product> inventory = Arrays.asList(civic);

        when(productService.getAllProducts()).thenReturn(inventory);

        //when // then
        mvc.perform(get(buildUri(RESOURCE_ALL))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"Honda\":[{\"id\":55,\"name\":\"Civic\",\"price\":1000.55}]}"));
    }

    @Test
    public void returnOnSaleProduct() throws Exception
    {
        //given
        final Product civic = Product.builder().brand(HONDA).id(55L).name("Civic").price(new BigDecimal(1000.55)).onSale(true).build();
        List<Product> inventory = Arrays.asList(civic);

        when(productService.getAllProducts()).thenReturn(inventory);

        //when // then
        mvc.perform(get(buildUri(RESOURCE_ALL))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"Honda\":[{\"id\":55,\"name\":\"Civic\",\"price\":1000.55,\"event\":\"ON_SALE\"}]}"));
    }

    @Test
    public void resturnAllAvailableProducts() throws Exception
    {
        //given
        final Product civic = Product.builder().name("Civic").brand(HONDA).price(BigDecimal.valueOf(15000L)).onSale(false).id(civicID).build();
        final Product city = Product.builder().name("City").brand(HONDA).price(BigDecimal.valueOf(800L)).onSale(true).id(cityID).build();
        final Product aClass = Product.builder().name("aClass").brand(MERCEDES).price(BigDecimal.valueOf(25000L)).onSale(true).id(aClassID).build();
        final Product bus = Product.builder().name("bus").brand(MERCEDES).price(BigDecimal.valueOf(25000L)).onSale(true).id(busID).build();
        final Product eclipse =
            Product.builder().name("eclipse").brand(MITSUBISHI).price(BigDecimal.valueOf(1000L)).onSale(true).id(eclipseID).build();
        final List<Product> inventory = Arrays.asList(civic, city, eclipse, aClass, bus);

        when(productService.getAllProducts()).thenReturn(inventory);

        final Map<String, List<ProductRest>> expectedResult = new LinkedHashMap<>();
        expectedResult.put(HONDA.getName(), Arrays.asList(buildRestProduct(civic), buildRestProduct(city)));
        expectedResult.put(MITSUBISHI.getName(), Arrays.asList(buildRestProduct(eclipse)));
        expectedResult.put(MERCEDES.getName(), Arrays.asList(buildRestProduct(aClass), buildRestProduct(bus)));
        final String expectedContent = objectMapper.writeValueAsString(expectedResult);

        //when // then
        mvc.perform(get(buildUri(RESOURCE_ALL))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedContent));
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void returnAllProductsSorted() throws Exception
    {
        //given
        final Product civic = Product.builder().name("Civic").brand(HONDA).price(BigDecimal.valueOf(15000L)).onSale(false).id(civicID).build();
        final Product city = Product.builder().name("City").brand(HONDA).price(BigDecimal.valueOf(800L)).onSale(true).id(cityID).build();
        final Product aClass = Product.builder().name("aClass").brand(MERCEDES).price(BigDecimal.valueOf(25000L)).onSale(true).id(aClassID).build();
        final Product bus = Product.builder().name("bus").brand(MERCEDES).price(BigDecimal.valueOf(25000L)).onSale(true).id(busID).build();
        final Product eclipse =
            Product.builder().name("eclipse").brand(MITSUBISHI).price(BigDecimal.valueOf(1000L)).onSale(true).id(eclipseID).build();
        final List<Product> inventory = Arrays.asList(civic, city, eclipse, aClass, bus);
        inventory.sort(Comparator.naturalOrder());

        when(productService.getAllProductsWithOrder()).thenReturn(inventory);

        final Map<String, List<ProductRest>> expectedResult = new LinkedHashMap<>();
        expectedResult.put(HONDA.getName(), Arrays.asList(buildRestProduct(city), buildRestProduct(civic)));
        expectedResult.put(MERCEDES.getName(), Arrays.asList(buildRestProduct(aClass), buildRestProduct(bus)));
        expectedResult.put(MITSUBISHI.getName(), Arrays.asList(buildRestProduct(eclipse)));
        final String expectedContent = objectMapper.writeValueAsString(expectedResult);

        //when // then
        mvc.perform(get(buildUri(RESOURCE_SORTED))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedContent));
        verify(productService, times(1)).getAllProductsWithOrder();
    }

    @Test
    public void returnMethodNowSupported() throws Exception
    {
        // given a post request // when // then
        mvc.perform(post(buildUri(RESOURCE_SORTED))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isMethodNotAllowed())
            .andExpect(content().string(containsString(METHOD_NOT_SUPPORTED.getErrorDescription("POST"))));

        verifyZeroInteractions(productService);
    }

    private String buildUri(final String path)
    {
        return UriComponentsBuilder.fromUriString(PRODUCTS_URI).path(path).build().toUriString();
    }

    private ProductRest buildRestProduct(final Product product)
    {
        final String event = product.isOnSale() ? EventType.ON_SALE.name() : null;
        return ProductRest.builder().id(product.getId()).price(product.getPrice()).name(product.getName()).event(event).build();
    }

}