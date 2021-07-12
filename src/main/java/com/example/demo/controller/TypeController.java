package com.example.demo.controller;

import com.example.demo.dto.Type;
import com.example.demo.dto.TypeInput;
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
    public ResponseEntity<List<Type>> getAllTypes() {
        return new ResponseEntity<>(typeService.findAllTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(typeService.findTypeById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Type> addType(@RequestBody TypeInput typeInput) {
        return new ResponseEntity<>(typeService.addType(typeInput), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Type> updateType(@RequestBody Type type) {
        return new ResponseEntity<>(typeService.updateType(type), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
    }
}
