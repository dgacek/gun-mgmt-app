package com.example.demo.service;

import com.example.demo.dto.Caliber;
import com.example.demo.dto.CaliberInput;

import java.util.List;

public interface CaliberService {
    Caliber addCaliber(CaliberInput caliberInput);
    List<Caliber> findAllCalibers();
    Caliber updateCaliber(Caliber caliber);
    Caliber findCaliberById(Long id);
    void deleteCaliber(Long id);
}
