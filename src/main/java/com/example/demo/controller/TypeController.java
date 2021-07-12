package com.example.demo.controller;

import com.example.demo.dto.Type;
import com.example.demo.dto.TypeInput;
import com.example.demo.service.TypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    private final TypeServiceImpl typeServiceImpl;

    public TypeController(TypeServiceImpl typeServiceImpl) {
        this.typeServiceImpl = typeServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes() {
        var types = typeServiceImpl.findAllTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        var type = typeServiceImpl.findTypeById(id);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Type> addType(@RequestBody TypeInput typeInput) {
        var type = typeServiceImpl.addType(typeInput);
        return new ResponseEntity<>(type, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Type> updateType(@RequestBody Type type) {
        var type1 = typeServiceImpl.updateType(type);
        return new ResponseEntity<>(type1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteType(@PathVariable Long id) {
        typeServiceImpl.deleteType(id);
    }
}
