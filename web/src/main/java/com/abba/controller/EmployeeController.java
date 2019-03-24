package com.abba.controller;

import com.abba.model.Employee;
import com.abba.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @author dengbojing
 */
@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    @Autowired
    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> findAll() {

        List<EntityModel<Employee>> employeeResources = repository.findAll().stream()
                .map(employee -> new EntityModel<>(employee,
                        linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel()
                                .andAffordance(afford(methodOn(EmployeeController.class).updateEmployee(new Employee(), employee.getId())))
                                .andAffordance(afford(methodOn(EmployeeController.class).deleteEmployee(employee.getId()))),
                        linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new CollectionModel<>(
                employeeResources,
                linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel()
                        .andAffordance(afford(methodOn(EmployeeController.class).newEmployee(null)))));
    }

    @PostMapping("/employees")
    public ResponseEntity<?> newEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = repository.save(employee);
        return new EntityModel<>(savedEmployee,
                linkTo(methodOn(EmployeeController.class).findOne(savedEmployee.getId())).withSelfRel()
                        .andAffordance(afford(methodOn(EmployeeController.class).updateEmployee(new Employee(), savedEmployee.getId())))
                        .andAffordance(afford(methodOn(EmployeeController.class).deleteEmployee(savedEmployee.getId()))),
                linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")).getLink(IanaLinkRelations.SELF)
                .map(Link::getHref)
                .map(href -> {
                    try {
                        return new URI(href);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(uri -> ResponseEntity.noContent().location(uri).build())
                .orElse(ResponseEntity.badRequest().body("Unable to create " + employee));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EntityModel<Employee>> findOne(@PathVariable String id) {

        return repository.findById(id)
                .map(employee -> new EntityModel<>(employee,
                        linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel()
                                .andAffordance(afford(methodOn(EmployeeController.class).updateEmployee(null, employee.getId())))
                                .andAffordance(afford(methodOn(EmployeeController.class).deleteEmployee(employee.getId()))),
                        linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable String id) {

        Employee employeeToUpdate = employee;
        employeeToUpdate.setId(id);

        Employee updatedEmployee = repository.save(employeeToUpdate);

        return new EntityModel<>(updatedEmployee,
                linkTo(methodOn(EmployeeController.class).findOne(updatedEmployee.getId())).withSelfRel()
                        .andAffordance(afford(methodOn(EmployeeController.class).updateEmployee(null, updatedEmployee.getId())))
                        .andAffordance(afford(methodOn(EmployeeController.class).deleteEmployee(updatedEmployee.getId()))),
                linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")).getLink(IanaLinkRelations.SELF)
                .map(Link::getHref).map(href -> {
                    try {
                        return new URI(href);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(uri -> ResponseEntity.noContent().location(uri).build())
                .orElse(ResponseEntity.badRequest().body("Unable to update " + employeeToUpdate));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
