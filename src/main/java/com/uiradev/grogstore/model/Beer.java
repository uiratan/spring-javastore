package com.uiradev.grogstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

    @Id
    @SequenceGenerator(name = "beer_seq", sequenceName = "beer_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beer_seq")
    private Long id;

    @NotBlank(message = "beers-1")
    private String name;

    @NotNull(message = "beers-2")
    private BeerType type;
    @NotNull(message = "beers-3")
    @DecimalMin(value = "0", message = "beers-4")
    private BigDecimal volume;

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }
    @JsonIgnore
    public boolean alreadyExist() {
        return getId() != null;
    }
}
