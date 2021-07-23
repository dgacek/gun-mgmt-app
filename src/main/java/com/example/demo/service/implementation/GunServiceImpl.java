package com.example.demo.service.implementation;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.dto.Gun;
import com.example.demo.model.dto.GunInput;
import com.example.demo.model.entity.CaliberDictionary;
import com.example.demo.model.entity.GunEntity;
import com.example.demo.model.entity.ModelEntity;
import com.example.demo.model.entity.TypeDictionary;
import com.example.demo.model.mapper.GunMapper;
import com.example.demo.model.repo.DictionaryRepo;
import com.example.demo.model.repo.GunRepo;
import com.example.demo.model.repo.ModelRepo;
import com.example.demo.service.GunService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GunServiceImpl implements GunService {
    private final GunRepo gunRepo;
    private final ModelRepo modelRepo;
    private final DictionaryRepo dictionaryRepo;
    private final GunMapper gunMapper;

    public Gun addGun(GunInput gunInput) {
        ModelEntity modelEntity = modelRepo.findById(gunInput.getModelId())
                .orElseThrow(() -> new IdNotFoundException("Model of id:"+gunInput.getModelId()+" could not be found in the database"));
        CaliberDictionary caliberDictionary = (CaliberDictionary) dictionaryRepo.findById(gunInput.getCaliberId())
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+gunInput.getCaliberId()+" could not be found in the database"));
        TypeDictionary typeDictionary = (TypeDictionary) dictionaryRepo.findById(gunInput.getTypeId())
                .orElseThrow(() -> new IdNotFoundException("Type of id:"+gunInput.getTypeId()+" could not be found in the database"));

        return gunMapper
                .toGun(gunRepo
                        .save(GunEntity
                                .builder()
                                .model(modelEntity)
                                .caliber(caliberDictionary)
                                .type(typeDictionary)
                                .productionYear(gunInput.getProductionYear())
                                .build()
        ));
    }

    public List<Gun> findAllGuns() {
        return gunMapper.toGunList(gunRepo.findAll());
    }

    @Transactional
    public Gun updateGun(Gun gun) {
        GunEntity gunEntity = gunRepo.findById(gun.getId())
                .orElseThrow(() -> new IdNotFoundException("Gun of id:"+gun.getModel().getId()+" could not be found in the database"));
        ModelEntity modelEntity = modelRepo.findById(gun.getModel().getId())
                .orElseThrow(() -> new IdNotFoundException("Model of id:"+gun.getModel().getId()+" could not be found in the database"));
        CaliberDictionary caliberDictionary = (CaliberDictionary) dictionaryRepo.findById(gun.getCaliber().getId())
                .orElseThrow(() -> new IdNotFoundException("Caliber of id:"+gun.getCaliber().getId()+" could not be found in the database"));
        TypeDictionary typeDictionary = (TypeDictionary) dictionaryRepo.findById(gun.getType().getId())
                .orElseThrow(() -> new IdNotFoundException("Type of id:"+gun.getType().getId()+" could not be found in the database"));
        gunEntity.setModel(modelEntity);
        gunEntity.setCaliber(caliberDictionary);
        gunEntity.setType(typeDictionary);
        gunEntity.setProductionYear(gun.getProductionYear());
        return gunMapper.toGun(gunEntity);
    }

    public Gun findGunById(Long id){
        return gunMapper.toGun(gunRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Gun of id:"+id+" could not be found in the database")));
    }

    public void deleteGun(Long id){
        gunRepo.deleteById(id);
    }

}
