package com.uiradev.grogstore.resource;

import com.uiradev.grogstore.model.Beer;
import com.uiradev.grogstore.repository.BeerRepository;
import com.uiradev.grogstore.service.BeerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeersResource {

    @Autowired
    private BeerService beerService;

    @GetMapping
    public List<Beer> findAll() {
        return beerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        return beerService.save(beer);
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable Long id, @Valid @RequestBody Beer beer) {
        beer.setId(id);
        return beerService.save(beer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        beerService.delete(id);
    }
}
