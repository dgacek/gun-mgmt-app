package com.example.demo.controller;

import com.example.demo.dto.Caliber;
import com.example.demo.dto.CaliberInput;
import com.example.demo.service.CaliberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/calibers")
public class CaliberController {
    private final CaliberService caliberService;

    @GetMapping
    public ResponseEntity<List<Caliber>> getAllCalibers() {
        return new ResponseEntity<>(caliberService.findAllCalibers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caliber> getCaliberById(@PathVariable Long id) {
        return new ResponseEntity<>(caliberService.findCaliberById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Caliber> addCaliber(@RequestBody CaliberInput caliberInput) {
        return new ResponseEntity<>(caliberService.addCaliber(caliberInput), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Caliber> updateCaliber(@RequestBody Caliber caliber) {
        return new ResponseEntity<>(caliberService.updateCaliber(caliber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCaliber(@PathVariable Long id) {
        caliberService.deleteCaliber(id);
    }
}
