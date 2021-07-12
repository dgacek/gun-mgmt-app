package com.example.demo.controller;

import com.example.demo.dto.Manufacturer;
import com.example.demo.dto.ManufacturerInput;
import com.example.demo.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return new ResponseEntity<>(manufacturerService.findAllManufacturers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable Long id) {
        return new ResponseEntity<>(manufacturerService.findManufacturerById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody ManufacturerInput manufacturerInput) {
        return new ResponseEntity<>(manufacturerService.addManufacturer(manufacturerInput), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Manufacturer> updateManufacturer(@RequestBody Manufacturer manufacturer) {
        return new ResponseEntity<>(manufacturerService.updateManufacturer(manufacturer), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }
}
