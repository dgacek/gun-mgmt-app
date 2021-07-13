package com.example.demo.controller;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;
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
    public ResponseEntity<List<DictionaryData>> getAllManufacturers() {
        return new ResponseEntity<>(manufacturerService.findAllManufacturers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryData> getManufacturerById(@PathVariable Long id) {
        return new ResponseEntity<>(manufacturerService.findManufacturerById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DictionaryData> addManufacturer(@RequestBody DictionaryDataInput dictionaryDataInput) {
        return new ResponseEntity<>(manufacturerService.addManufacturer(dictionaryDataInput), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DictionaryData> updateManufacturer(@RequestBody DictionaryData dictionaryData) {
        return new ResponseEntity<>(manufacturerService.updateManufacturer(dictionaryData), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }
}
