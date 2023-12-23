package com.udacity.vehicles.api;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.service.CarService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
@AllArgsConstructor
class CarController implements CarApi {

    private final CarService carService;
    private final CarResourceAssembler assembler;

    @Override
    @GetMapping
    public Resources<Resource<Car>> list() {
        List<Resource<Car>> resources = carService.list().stream().map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }

    @Override
    @GetMapping("/{id}")
    public Resource<Car> get(@PathVariable Long id) {
        return assembler.toResource(carService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
        Resource<Car> resource = assembler.toResource(carService.save(car));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Resource<Car>> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        car.setId(id);
        Resource<Car> resource = assembler.toResource(carService.save(car));
        return ResponseEntity.ok(resource);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
