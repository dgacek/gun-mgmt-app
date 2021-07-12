package com.example.demo.service.implementation;

import com.example.demo.dto.Caliber;
import com.example.demo.dto.CaliberInput;
import com.example.demo.entity.CaliberDictionary;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repo.CaliberRepo;
import com.example.demo.service.CaliberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaliberServiceImpl implements CaliberService {
    private final CaliberRepo caliberRepo;

    public CaliberServiceImpl(CaliberRepo caliberRepo) {
        this.caliberRepo = caliberRepo;
    }

    public Caliber addCaliber(CaliberInput caliberInput) {
        var caliberEntity = caliberRepo.save(new CaliberDictionary(caliberInput.getName()));
        return new Caliber(caliberEntity);
    }

    public List<Caliber> findAllCalibers() {
        var caliberEntities = caliberRepo.findAll();
        var calibers = new ArrayList<Caliber>();
        for (CaliberDictionary caliberDictionary : caliberEntities) {
            calibers.add(new Caliber(caliberDictionary));
        }
        return calibers;
    }

    public Caliber updateCaliber(Caliber caliber) {
        CaliberDictionary caliberDictionary = caliberRepo.findById(caliber.getId())
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+caliber.getId()+" could not be found in the database"));
        caliberDictionary.setName(caliber.getName());
        caliberRepo.save(caliberDictionary);
        return new Caliber(caliberDictionary);
    }

    public Caliber findCaliberById(Long id) {
        return new Caliber(caliberRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+id+" could not be found in the database")));
    }

    public void deleteCaliber(Long id) {
        caliberRepo.deleteById(id);
    }
}
