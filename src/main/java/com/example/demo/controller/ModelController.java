package com.example.demo.controller;

import com.example.demo.model.dto.Model;
import com.example.demo.model.dto.ModelInput;
import com.example.demo.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;

    @GetMapping
    @PreAuthorize("hasAuthority('MODEL_READ')")
    public ResponseEntity<List<Model>> getAllModels() {
        return new ResponseEntity<>(modelService.findAllModels(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MODEL_READ')")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        return new ResponseEntity<>(modelService.findModelById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MODEL_CREATE')")
    public ResponseEntity<Model> addModel(@RequestBody ModelInput modelInput) {
        return new ResponseEntity<>(modelService.addModel(modelInput), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('MODEL_UPDATE')")
    public ResponseEntity<Model> updateModel(@RequestBody Model model) {
        return new ResponseEntity<>(modelService.updateModel(model), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MODEL_DELETE')")
    public void deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
    }
}
