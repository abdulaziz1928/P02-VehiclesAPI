package com.udacity.vehicles.api;

import com.udacity.vehicles.domain.car.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URISyntaxException;


@Tag(name = "Car")
interface CarApi {

    /**
     * Creates a list to store any vehicles.
     *
     * @return list of vehicles
     */
    @Operation(summary = "Retrieve all Vehicles")
    @ApiResponse(responseCode = "200", description = "Vehicles Retrieved Successfully")
    Resources<Resource<Car>> list();

    /**
     * Gets information of a specific car by ID.
     *
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
    @Operation(summary = "Retrieve a Vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle Retrieved Successfully")
    Resource<Car> get(@PathVariable Long id);

    /**
     * Posts information to create a new vehicle in the system.
     *
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @Operation(summary = "Create a Vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle Created Successfully")
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException;

    /**
     * Updates the information of a vehicle in the system.
     *
     * @param id  The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @Operation(summary = "Update a Vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle Updated Successfully")
    ResponseEntity<Resource<Car>> put(@PathVariable Long id, @Valid @RequestBody Car car);

    /**
     * Removes a vehicle from the system.
     *
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @Operation(summary = "Delete a Vehicle")
    @ApiResponse(responseCode = "200", description = "Vehicle Deleted Successfully")
    ResponseEntity<?> delete(@PathVariable Long id);
}
