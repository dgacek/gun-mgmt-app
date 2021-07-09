package com.example.demo.controller;

import com.example.demo.dto.Type;
import com.example.demo.dto.TypeInput;
import com.example.demo.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes() {
        var types = typeService.findAllTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        var type = typeService.findTypeById(id);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Type> addType(@RequestBody TypeInput typeInput) {
        var type = typeService.addType(typeInput);
        return new ResponseEntity<>(type, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Type> updateType(@RequestBody Type type) {
        var type1 = typeService.updateType(type);
        return new ResponseEntity<>(type1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
