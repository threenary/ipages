package com.epages.interview.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
@Table(name = "product")
public class Product implements Comparable<Product>
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Product must contain a name")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Product must contain a price")
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "boolean default true")
    @NotNull(message = "Product should contain a onSale value")
    private boolean onSale;

    @NotNull(message = "Product must belongs to a brand")
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Override
    public String toString()
    {
        return "Product [name=" + name + ", brand=" + getBrand().getName()
            + ", price=" + getPrice() + "]";
    }

    @Override
    public int compareTo(final Product otherProduct)
    {
        if (brand.equals(otherProduct.getBrand()))
        {
            return price.compareTo(otherProduct.getPrice());
        }
        return brand.compareTo(otherProduct.getBrand());
    }
}

