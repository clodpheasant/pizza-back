package com.fs.master.pizzaback.repo;

import com.fs.master.pizzaback.model.Price;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Long> {
}
