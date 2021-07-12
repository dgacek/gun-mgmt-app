package com.example.demo.controller;

import com.example.demo.dto.Manufacturer;
import com.example.demo.dto.ManufacturerInput;
import com.example.demo.service.ManufacturerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerServiceImpl manufacturerServiceImpl;

    public ManufacturerController(ManufacturerServiceImpl manufacturerServiceImpl) {
        this.manufacturerServiceImpl = manufacturerServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        var manufacturers = manufacturerServiceImpl.findAllManufacturers();
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable Long id) {
        var manufacturer = manufacturerServiceImpl.findManufacturerById(id);
        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody ManufacturerInput manufacturerInput) {
        var manufacturer = manufacturerServiceImpl.addManufacturer(manufacturerInput);
        return new ResponseEntity<>(manufacturer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer) {
        var manufacturer1 = manufacturerServiceImpl.updateManufacturer(manufacturer);
        return new ResponseEntity<>(manufacturer1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable Long id) {
        manufacturerServiceImpl.deleteManufacturer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
