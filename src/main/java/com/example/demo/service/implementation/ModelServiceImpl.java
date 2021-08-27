package com.example.demo.service.implementation;

import com.example.demo.model.dto.Model;
import com.example.demo.model.dto.ModelInput;
import com.example.demo.model.entity.ManufacturerDictionary;
import com.example.demo.model.entity.ModelEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.mapper.ModelMapper2;
import com.example.demo.model.repo.DictionaryRepo;
import com.example.demo.model.repo.ModelRepo;
import com.example.demo.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepo modelRepo;
    private final DictionaryRepo dictionaryRepo;

    public Model addModel(ModelInput modelInput) {
        var manufacturerDictionary = (ManufacturerDictionary) dictionaryRepo.findById(modelInput.getManufacturerId())
                .orElseThrow(() -> new IdNotFoundException("Manufacture of id:"+modelInput.getManufacturerId()+" could not be found in the database"));
        return ModelMapper2.INSTANCE
                .toModel(modelRepo
                        .save(ModelEntity
                                .builder()
                                .manufacturer(manufacturerDictionary)
                                .name(modelInput.getName())
                                .build()
                        )
                );
    }

    public List<Model> findAllModels() {
        return ModelMapper2.INSTANCE.toModelList(modelRepo.findAll());
    }

    @Transactional
    public Model updateModel(Model model) {
        ModelEntity modelEntity = modelRepo.findById(model.getId())
                .orElseThrow(() -> new IdNotFoundException("Model of id:"+model.getId()+" could not be found in the database"));
        ManufacturerDictionary manufacturerDictionary = (ManufacturerDictionary) dictionaryRepo.findById(model.getManufacturer().getId())
                .orElseThrow(() -> new IdNotFoundException("Manufacturer of id:"+model.getManufacturer().getId()+" could not be found in the database"));
        modelEntity.setManufacturer(manufacturerDictionary);
        modelEntity.setName(model.getName());
        return ModelMapper2.INSTANCE.toModel(modelEntity);
    }

    public Model findModelById(Long id) {
        return ModelMapper2.INSTANCE.toModel(modelRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Model of id:"+id+" could not be found in the database")));
    }

    public void deleteModel(Long id) {
        modelRepo.deleteById(id);
    }
}
