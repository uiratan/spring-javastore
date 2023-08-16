package com.uiradev.grogstore.repository;

import com.uiradev.grogstore.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
