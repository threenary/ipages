package com.epages.interview.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Entity
@Table(name = "brand")
public class Brand
{
    @OneToMany(mappedBy = "brand")
    List<Product> products;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    @SequenceGenerator(name = "brand_generator", sequenceName = "brand_seq", allocationSize = 1)
    private long id;
    @NotNull(message = "Brand should have a name")
    @Column(nullable = false)
    private String name;
}
