package com.example.demo.controller;

import com.example.demo.dto.Caliber;
import com.example.demo.dto.CaliberInput;
import com.example.demo.service.CaliberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calibers")
public class CaliberController {
    private final CaliberService caliberService;

    public CaliberController(CaliberService caliberService) {
        this.caliberService = caliberService;
    }

    @GetMapping
    public ResponseEntity<List<Caliber>> getAllCalibers() {
        var calibers = caliberService.findAllCalibers();
        return new ResponseEntity<>(calibers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caliber> getCaliberById(@PathVariable Long id) {
        var caliber = caliberService.findCaliberById(id);
        return new ResponseEntity<>(caliber, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Caliber> addCaliber(@RequestBody CaliberInput caliberInput) {
        var caliber = caliberService.addCaliber(caliberInput);
        return new ResponseEntity<>(caliber, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Caliber> updateCaliber(@RequestBody Caliber caliber) {
        var caliber1 = caliberService.updateCaliber(caliber);
        return new ResponseEntity<>(caliber1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCaliber(@PathVariable Long id) {
        caliberService.deleteCaliber(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
