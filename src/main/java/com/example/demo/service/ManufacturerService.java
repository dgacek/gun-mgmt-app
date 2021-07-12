package com.example.demo.service;

import com.example.demo.dto.Manufacturer;
import com.example.demo.dto.ManufacturerInput;

import java.util.List;

public interface ManufacturerService {
    Manufacturer addManufacturer(ManufacturerInput manufacturerInput);
    List<Manufacturer> findAllManufacturers();
    Manufacturer updateManufacturer(Manufacturer manufacturer);
    Manufacturer findManufacturerById(Long id);
    void deleteManufacturer(Long id);
}
