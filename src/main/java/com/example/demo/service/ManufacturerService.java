package com.example.demo.service;

import com.example.demo.dto.Manufacturer;
import com.example.demo.dto.ManufacturerInput;
import com.example.demo.entity.ManufacturerDictionary;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repo.ManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManufacturerService {
    private final ManufacturerRepo manufacturerRepo;

    @Autowired
    public ManufacturerService(ManufacturerRepo manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public Manufacturer addManufacturer(ManufacturerInput manufacturerInput) {
        var manufacturerEntity = manufacturerRepo.save(new ManufacturerDictionary(manufacturerInput.getName()));
        return new Manufacturer((manufacturerEntity));
    }

    public List<Manufacturer> findAllManufacturers() {
        var manufacturerEntities = manufacturerRepo.findAll();
        var manufacturers = new ArrayList<Manufacturer>();
        for (ManufacturerDictionary manufacturerDictionary : manufacturerEntities) {
            manufacturers.add(new Manufacturer(manufacturerDictionary));
        }
        return manufacturers;
    }

    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        ManufacturerDictionary manufacturerDictionary = manufacturerRepo.findById(manufacturer.getId())
                .orElseThrow(() -> new IdNotFoundException("Manufacturer of id:"+manufacturer.getId()+" could not be found in the database"));
        manufacturerDictionary.setName(manufacturer.getName());
        manufacturerRepo.save(manufacturerDictionary);
        return new Manufacturer(manufacturerDictionary);
    }

    public Manufacturer findManufacturerById(Long id) {
        return new Manufacturer(manufacturerRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Manufacturer of id:"+id+" could not be found in the database")));
    }

    @Transactional
    public void deleteManufacturer(Long id) {
        manufacturerRepo.deleteById(id);
    }
}
