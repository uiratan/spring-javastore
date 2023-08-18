package com.uiradev.grogstore.service;

import com.uiradev.grogstore.model.Beer;
import com.uiradev.grogstore.model.BeerType;
import com.uiradev.grogstore.repository.BeerRepository;
import com.uiradev.grogstore.service.exception.BeerAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepositoryMocked;
    private BeerService beerService;
    private Beer beerInDatabase;
    private Beer newBeer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        beerService = new BeerService(beerRepositoryMocked);
        newBeer = new Beer(null, "Heineken", BeerType.LAGER, new BigDecimal("355"));
        beerInDatabase = new Beer(10L, "Heineken", BeerType.LAGER, new BigDecimal("355"));
    }

    @Test
    public void should_deny_creation_of_beer_that_exist() {
        when(beerRepositoryMocked.findByNameAndType("Heineken", BeerType .LAGER)).thenReturn(Optional.of(beerInDatabase));

        assertThrows(BeerAlreadyExistException.class, () -> beerService.save(newBeer));
    }

    @Test
    public void should_create_new_beer() {
        when(beerRepositoryMocked.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.empty());
        when(beerRepositoryMocked.save(newBeer)).thenReturn(beerInDatabase);

        final Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }

    @Test
    public void should_update_beer_volume() {
        final Beer beerInDatabase = new Beer();
        beerInDatabase.setId(10L);
        beerInDatabase.setName("Devassa");
        beerInDatabase.setType(BeerType.PILSEN);
        beerInDatabase.setVolume(new BigDecimal("300"));
        when(beerRepositoryMocked.findByNameAndType("Devassa", BeerType.PILSEN)).thenReturn(Optional.of(beerInDatabase));

        final Beer beerToUpdate = new Beer();
        beerToUpdate.setId(10L);
        beerToUpdate.setName("Devassa");
        beerToUpdate.setType(BeerType.PILSEN);
        beerToUpdate.setVolume(new BigDecimal("200"));

        final Beer beerMocked = new Beer();
        beerMocked.setId(10L);
        beerMocked.setName("Devassa");
        beerMocked.setType(BeerType.PILSEN);
        beerMocked.setVolume(new BigDecimal("200"));

        when(beerRepositoryMocked.save(beerToUpdate)).thenReturn(beerMocked);

        final Beer beerUpdated = beerService.save(beerToUpdate);
        assertThat(beerUpdated.getId(), equalTo(10L));
        assertThat(beerUpdated.getName(), equalTo("Devassa"));
        assertThat(beerUpdated.getType(), equalTo(BeerType.PILSEN));
        assertThat(beerUpdated.getVolume(), equalTo(new BigDecimal("200")));
    }

    @Test
    public void should_deny_update_of_an_existing_beer_that_already_exists() {
        final Beer beerInDatabase = new Beer(10L, "Heineken", BeerType.LAGER, new BigDecimal("355"));
        when(beerRepositoryMocked.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.of(beerInDatabase));
        final Beer beerToUpdate = new Beer(5L, "Heineken",BeerType.LAGER, new BigDecimal("355"));

        assertThrows(BeerAlreadyExistException.class, () -> beerService.save(beerToUpdate));
    }
}
