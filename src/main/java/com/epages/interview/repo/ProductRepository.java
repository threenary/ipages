package com.epages.interview.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epages.interview.domain.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{

}
