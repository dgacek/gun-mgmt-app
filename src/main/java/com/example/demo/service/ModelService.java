package com.example.demo.service;

import com.example.demo.model.dto.Model;
import com.example.demo.model.dto.ModelInput;

import java.util.List;

public interface ModelService {
    Model addModel(ModelInput modelInput);
    List<Model> findAllModels();
    Model updateModel(Model model);
    Model findModelById(Long id);
    void deleteModel(Long id);
}
