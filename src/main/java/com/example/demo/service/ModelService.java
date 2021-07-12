package com.example.demo.service;

import com.example.demo.dto.Model;
import com.example.demo.dto.ModelInput;
import com.example.demo.entity.ModelEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repo.ManufacturerRepo;
import com.example.demo.repo.ModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {
    private final ModelRepo modelRepo;
    private final ManufacturerRepo manufacturerRepo;

    @Autowired
    public ModelService(ModelRepo modelRepo, ManufacturerRepo manufacturerRepo) {
        this.modelRepo = modelRepo;
        this.manufacturerRepo = manufacturerRepo;
    }

    public Model addModel(ModelInput modelInput) {
        var manufacturerEntity = manufacturerRepo.findById(modelInput.getManufacturerId())
                .orElseThrow(() -> new IdNotFoundException("Manufacture of id:"+modelInput.getManufacturerId()+" could not be found in the database"));
        var modelEntity = new ModelEntity(manufacturerEntity, modelInput.getName());
        modelRepo.save(modelEntity);
        return new Model(modelEntity);
    }

    public List<Model> findAllModels() {
        var modelEntities = modelRepo.findAll();
        var models = new ArrayList<Model>();
        for (ModelEntity modelEntity : modelEntities) {
            models.add(new Model(modelEntity));
        }
        return models;
    }

    public Model updateModel(Model model) {
        var modelEntity = modelRepo.findById(model.getId())
                .orElseThrow(() -> new IdNotFoundException("Model of id:"+model.getId()+" could not be found in the database"));
        var manufacturerEntity = manufacturerRepo.findById(model.getManufacturer().getId())
                .orElseThrow(() -> new IdNotFoundException("Manufacturer of id:"+model.getManufacturer().getId()+" could not be found in the database"));
        modelEntity.setManufacturerDictionary(manufacturerEntity);
        modelEntity.setName(model.getName());
        return new Model(modelEntity);
    }

    public Model findModelById(Long id) {
        var modelEntity = modelRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Model of id:"+id+" could not be found in the database"));
        return new Model(modelEntity);
    }

    @Transactional
    public void deleteModel(Long id) {
        modelRepo.deleteById(id);
    }
}
