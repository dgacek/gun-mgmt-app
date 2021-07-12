package com.example.demo.service;

import com.example.demo.dto.Type;
import com.example.demo.dto.TypeInput;
import com.example.demo.entity.TypeDictionary;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repo.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {
    private final TypeRepo typeRepo;

    @Autowired
    public TypeService(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }

    public Type addType(TypeInput typeInput) {
        var typeEntity = typeRepo.save(new TypeDictionary(typeInput.getName()));
        return new Type(typeEntity);
    }

    public List<Type> findAllTypes() {
        var typeEntities = typeRepo.findAll();
        var types = new ArrayList<Type>();
        for (TypeDictionary typeDictionary : typeEntities) {
            types.add(new Type(typeDictionary));
        }
        return types;
    }

    public Type updateType(Type type) {
        var typeEntity = typeRepo.findById(type.getId())
                .orElseThrow(() -> new IdNotFoundException("Type of id:"+type.getId()+" could not be found in the database"));
        typeEntity.setName(type.getName());
        typeRepo.save(typeEntity);
        return new Type(typeEntity);
    }

    public Type findTypeById(Long id) {
        return new Type(typeRepo.findById(id)
            .orElseThrow(() -> new IdNotFoundException("Type of id:"+id+" could not be found in the database")));
    }

    @Transactional
    public void deleteType(Long id) {
        typeRepo.deleteById(id);
    }
}
