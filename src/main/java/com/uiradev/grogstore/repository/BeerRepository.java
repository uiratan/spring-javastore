package com.uiradev.grogstore.repository;

import com.uiradev.grogstore.model.Beer;
import com.uiradev.grogstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);

}
