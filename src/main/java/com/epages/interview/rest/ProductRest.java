package com.epages.interview.rest;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(
    value = "ProductRest",
    description = "A Product REST representation."
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRest
{
    @ApiModelProperty(value = "ID of the product")
    private long id;

    @ApiModelProperty(value = "Name of the product")
    private String name;

    @ApiModelProperty(value = "Price of the product")
    private BigDecimal price;

    @ApiModelProperty(value = "Status of the product")
    private String event;
}
