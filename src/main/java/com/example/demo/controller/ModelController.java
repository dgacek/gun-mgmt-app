package com.example.demo.controller;

import com.example.demo.dto.Model;
import com.example.demo.dto.ModelInput;
import com.example.demo.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels() {
        var models = modelService.findAllModels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        var model = modelService.findModelById(id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Model> addModel(@RequestBody ModelInput modelInput) {
        var model = modelService.addModel(modelInput);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Model> updateModel(@RequestBody Model model) {
        var model1 = modelService.updateModel(model);
        return new ResponseEntity<>(model1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
