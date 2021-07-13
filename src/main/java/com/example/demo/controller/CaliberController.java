package com.example.demo.controller;

import com.example.demo.dto.DictionaryData;
import com.example.demo.dto.DictionaryDataInput;
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
    public ResponseEntity<List<DictionaryData>> getAllCalibers() {
        return new ResponseEntity<>(caliberService.findAllCalibers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryData> getCaliberById(@PathVariable Long id) {
        return new ResponseEntity<>(caliberService.findCaliberById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DictionaryData> addCaliber(@RequestBody DictionaryDataInput dictionaryDataInput) {
        return new ResponseEntity<>(caliberService.addCaliber(dictionaryDataInput), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DictionaryData> updateCaliber(@RequestBody DictionaryData dictionaryData) {
        return new ResponseEntity<>(caliberService.updateCaliber(dictionaryData), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCaliber(@PathVariable Long id) {
        caliberService.deleteCaliber(id);
    }
}
