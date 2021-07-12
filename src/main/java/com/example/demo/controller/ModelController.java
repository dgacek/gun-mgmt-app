package com.example.demo.controller;

import com.example.demo.dto.Model;
import com.example.demo.dto.ModelInput;
import com.example.demo.service.ModelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelServiceImpl modelServiceImpl;

    public ModelController(ModelServiceImpl modelServiceImpl) {
        this.modelServiceImpl = modelServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels() {
        var models = modelServiceImpl.findAllModels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        var model = modelServiceImpl.findModelById(id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Model> addModel(@RequestBody ModelInput modelInput) {
        var model = modelServiceImpl.addModel(modelInput);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Model> updateModel(@RequestBody Model model) {
        var model1 = modelServiceImpl.updateModel(model);
        return new ResponseEntity<>(model1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelServiceImpl.deleteModel(id);
    }
}
