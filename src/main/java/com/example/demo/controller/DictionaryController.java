package com.example.demo.controller;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;
import com.example.demo.model.entity.CaliberDictionary;
import com.example.demo.model.entity.DictionaryEntity;
import com.example.demo.model.entity.ManufacturerDictionary;
import com.example.demo.model.entity.TypeDictionary;
import com.example.demo.service.DictionaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    private Class<? extends DictionaryEntity> parseType(String type) {
        switch (type) {
            case "calibers":
                return CaliberDictionary.class;
            case "manufacturers":
                return ManufacturerDictionary.class;
            case "types":
                return TypeDictionary.class;
            default:
                return null;
        }
    }

    private String parseTypeString(String type) {
        switch (type) {
            case "calibers":
                return "caliber";
            case "manufacturers":
                return "manufacturer";
            case "types":
                return "type";
            default:
                return null;
        }
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<DictionaryData>> getAllDictionaries(@PathVariable String type) {
        return new ResponseEntity<>(dictionaryService.findAllDictionaries(parseType(type)), HttpStatus.OK);
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<DictionaryData> getDictionaryById(@PathVariable String type, @PathVariable Long id) {
        return new ResponseEntity<>(dictionaryService.findDictionaryById(parseTypeString(type), id), HttpStatus.OK);
    }

    @PostMapping("/{type}")
    public ResponseEntity<DictionaryData> addDictionary(@PathVariable String type, @RequestBody DictionaryDataInput dictionaryDataInput) {
        try {
            return new ResponseEntity<>(dictionaryService.addDictionary(parseType(type), dictionaryDataInput), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{type}")
    public ResponseEntity<DictionaryData> updateDictionary(@PathVariable String type, @RequestBody DictionaryData dictionaryData) {
        return new ResponseEntity<>(dictionaryService.updateDictionary(parseTypeString(type), dictionaryData), HttpStatus.OK);
    }

    @DeleteMapping("/{type}/{id}")
    public void deleteDictionary(@PathVariable String type, @PathVariable Long id) {
        dictionaryService.deleteDictionary(parseTypeString(type), id);
    }
}
