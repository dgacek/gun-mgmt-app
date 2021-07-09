package com.example.demo.service;

import com.example.demo.dto.Caliber;
import com.example.demo.dto.CaliberInput;
import com.example.demo.entity.CaliberEntity;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repo.CaliberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaliberService {
    private final CaliberRepo caliberRepo;

    @Autowired
    public CaliberService(CaliberRepo caliberRepo) {
        this.caliberRepo = caliberRepo;
    }

    public Caliber addCaliber(CaliberInput caliberInput) {
        var caliberEntity = caliberRepo.save(new CaliberEntity(caliberInput.getName()));
        return new Caliber(caliberEntity);
    }

    public List<Caliber> findAllCalibers() {
        var caliberEntities = caliberRepo.findAll();
        var calibers = new ArrayList<Caliber>();
        for (CaliberEntity caliberEntity : caliberEntities) {
            calibers.add(new Caliber(caliberEntity));
        }
        return calibers;
    }

    public Caliber updateCaliber(Caliber caliber) {
        CaliberEntity caliberEntity = caliberRepo.findById(caliber.getId())
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+caliber.getId()+" could not be found in the database"));
        caliberEntity.setName(caliber.getName());
        caliberRepo.save(caliberEntity);
        return new Caliber(caliberEntity);
    }

    public Caliber findCaliberById(Long id) {
        return new Caliber(caliberRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+id+" could not be found in the database")));
    }

    @Transactional
    public void deleteCaliber(Long id) {
        caliberRepo.deleteById(id);
    }
}
