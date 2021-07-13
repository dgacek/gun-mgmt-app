package com.example.demo.controller;

import com.example.demo.dto.DictionaryData;
import com.example.demo.dto.DictionaryDataInput;
import com.example.demo.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    @GetMapping
    public ResponseEntity<List<DictionaryData>> getAllTypes() {
        return new ResponseEntity<>(typeService.findAllTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryData> getTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(typeService.findTypeById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DictionaryData> addType(@RequestBody DictionaryDataInput dictionaryDataInput) {
        return new ResponseEntity<>(typeService.addType(dictionaryDataInput), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DictionaryData> updateType(@RequestBody DictionaryData dictionaryData) {
        return new ResponseEntity<>(typeService.updateType(dictionaryData), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }
}
