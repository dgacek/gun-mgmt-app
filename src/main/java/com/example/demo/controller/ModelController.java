package com.example.demo.controller;

import com.example.demo.model.dto.Model;
import com.example.demo.model.dto.ModelInput;
import com.example.demo.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/models")
public class ModelController {
    private final ModelService modelService;

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels() {
        return new ResponseEntity<>(modelService.findAllModels(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        return new ResponseEntity<>(modelService.findModelById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Model> addModel(@RequestBody ModelInput modelInput) {
        return new ResponseEntity<>(modelService.addModel(modelInput), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Model> updateModel(@RequestBody Model model) {
        return new ResponseEntity<>(modelService.updateModel(model), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
    }
}
