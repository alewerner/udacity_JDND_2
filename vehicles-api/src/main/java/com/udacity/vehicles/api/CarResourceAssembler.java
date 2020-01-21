package com.udacity.vehicles.api;

import com.udacity.vehicles.domain.car.Car;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Maps the CarController to the Car class using HATEOAS
 */

@Component
public class CarResourceAssembler implements ResourceAssembler<Car, Resource<Car>> {

    /**
     * Converts a Car domain type to a REST resource type.
     * @param car Car
     * @return Resource<Car>
     * @see <a https://www.logicbig.com/tutorials/spring-framework/spring-hateoas/resource-assembler.html</a>
     * */

    @Override
    public Resource<Car> toResource(Car car) {
        return new Resource<>(car,
                linkTo(methodOn(CarController.class).get(car.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).list()).withRel("cars"));

    }
}
