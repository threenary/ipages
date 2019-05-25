package com.epages.interview.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epages.interview.domain.Product;
import com.epages.interview.rest.mapper.ProductRestMapper;
import com.epages.interview.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(
    tags = "Products",
    value = "Rest resource to listing Products"
)
@Validated
public class ProductRestController
{
    private final ProductService productService;

    @Autowired
    public ProductRestController(final ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/all")
    @ApiOperation(
        value = "Retrieves all the products in the inventory")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All available Products grouped by brand."),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 405, message = "Method Not Allowed.")
    })
    public Map<String, List<ProductRest>> listProducts()
    {
        final List<Product> sortedProducts = productService.getAllProducts();

        return mapToBizz(sortedProducts);
    }

    @GetMapping("/sorted")
    @ApiOperation(
        value = "Returns all the Products in the inventory with the requested order")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All the products grouped by brand, sorted alphabetically and ascending by price"),
        @ApiResponse(code = 400, message = "Bad Request."),
        @ApiResponse(code = 405, message = "Method Not Allowed.")
    })
    public Map<String, List<ProductRest>> listProductsSorted()
    {
        final List<Product> sortedProducts = productService.getAllProductsWithOrder();

        return mapToBizz(sortedProducts);
    }

    /**
     * Maps the obtained products to the specified business representation
     * @param products
     * @return all the products grouped by brand
     */
    private LinkedHashMap<String, List<ProductRest>> mapToBizz(final List<Product> products)
    {
        return products.stream().collect(
            Collectors.groupingBy(o -> o.getBrand().getName(),
                LinkedHashMap::new,
                Collectors.mapping(ProductRestMapper::map, Collectors.toList())));
    }
}