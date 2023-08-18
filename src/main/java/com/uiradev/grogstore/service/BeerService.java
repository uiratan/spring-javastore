package com.uiradev.grogstore.service;

import com.uiradev.grogstore.model.Beer;
import com.uiradev.grogstore.repository.BeerRepository;
import com.uiradev.grogstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    private final BeerRepository beerRepository;

    public BeerService(@Autowired BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }


    public Beer save(final Beer beer) {
        verifyIfBeerExists(beer);
        return beerRepository.save(beer);
    }

    private void verifyIfBeerExists(Beer beer) {
        Optional<Beer> beerByNameAndType = beerRepository.findByNameAndType(beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent() && (beer.isNew() || isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType) {
        return beer.alreadyExist() && !beerByNameAndType.get().equals(beer);
    }
}
