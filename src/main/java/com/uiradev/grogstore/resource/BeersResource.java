package com.uiradev.grogstore.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeersResource {

    @GetMapping
    public List<String> all() {
        return Arrays.asList("Popeye Porter", "Cersei Weissbier", "Capim Limão do Velho Toby", "Teste");
    }
}
