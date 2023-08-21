package com.giang.books.repository;

import com.giang.books.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
