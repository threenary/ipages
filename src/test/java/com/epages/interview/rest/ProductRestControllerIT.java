package com.epages.interview.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * This incomplete IT is for showing off how all the app layers interact with each other
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestControllerIT
{
    private final static String PRODUCTS_URI = "/products";
    private final static String RESOURCE_ALL = "/all";
    private final static String RESOURCE_SORTED_IN_MEMORY = "/sortedInMemory";
    private final static String RESOURCE_SORTED_BY_SPECIFICATION = "/sortedBySpecification";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllAvailableProductsTest() throws Exception
    {
        mvc.perform(get(buildUri(RESOURCE_ALL))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllProductsSortedInMemoryTest() throws Exception
    {
        mvc.perform(get(buildUri(RESOURCE_SORTED_IN_MEMORY))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllProductsSortedBySpecification() throws Exception
    {
        mvc.perform(get(buildUri(RESOURCE_SORTED_BY_SPECIFICATION))
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk()).andReturn();
    }

    private String buildUri(final String path)
    {
        return UriComponentsBuilder.fromUriString(PRODUCTS_URI).path(path).build().toUriString();
    }

}