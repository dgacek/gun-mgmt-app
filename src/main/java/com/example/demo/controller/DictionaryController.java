package com.example.demo.controller;

import com.example.demo.model.dto.DictionaryData;
import com.example.demo.model.dto.DictionaryDataInput;
import com.example.demo.model.entity.CaliberDictionary;
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

    @GetMapping("/{type}")
    public ResponseEntity<List<DictionaryData>> getAllDictionaries(@PathVariable String type) {
        switch (type) {
            case "calibers":
                return new ResponseEntity<>(dictionaryService.findAllDictionaries(CaliberDictionary.class), HttpStatus.OK);
            case "manufacturers":
                return new ResponseEntity<>(dictionaryService.findAllDictionaries(ManufacturerDictionary.class), HttpStatus.OK);
            case "types":
                return new ResponseEntity<>(dictionaryService.findAllDictionaries(TypeDictionary.class), HttpStatus.OK);
            default: return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{type}/{id}")
    public ResponseEntity<DictionaryData> getDictionaryById(@PathVariable String type, @PathVariable Long id) {
        switch (type) {
            case "calibers":
                return new ResponseEntity<>(dictionaryService.findDictionaryById("caliber", id), HttpStatus.OK);
            case "manufacturers":
                return new ResponseEntity<>(dictionaryService.findDictionaryById("manufacturer", id), HttpStatus.OK);
            case "types":
                return new ResponseEntity<>(dictionaryService.findDictionaryById("type", id), HttpStatus.OK);
            default: return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{type}")
    public ResponseEntity<DictionaryData> addDictionary(@PathVariable String type, @RequestBody DictionaryDataInput dictionaryDataInput) {
        try {
            switch (type) {
                case "calibers":
                    return new ResponseEntity<>(dictionaryService.addDictionary(CaliberDictionary.class, dictionaryDataInput), HttpStatus.OK);
                case "manufacturers":
                    return new ResponseEntity<>(dictionaryService.addDictionary(ManufacturerDictionary.class, dictionaryDataInput), HttpStatus.OK);
                case "types":
                    return new ResponseEntity<>(dictionaryService.addDictionary(TypeDictionary.class, dictionaryDataInput), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{type}")
    public ResponseEntity<DictionaryData> updateDictionary(@PathVariable String type, @RequestBody DictionaryData dictionaryData) {
        switch (type) {
            case "calibers":
                return new ResponseEntity<>(dictionaryService.updateDictionary("caliber", dictionaryData), HttpStatus.OK);
            case "manufacturers":
                return new ResponseEntity<>(dictionaryService.updateDictionary("manufacturer", dictionaryData), HttpStatus.OK);
            case "types":
                return new ResponseEntity<>(dictionaryService.updateDictionary("type", dictionaryData), HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{type}/{id}")
    public void deleteDictionary(@PathVariable String type, @PathVariable Long id) {
        switch (type) {
            case "calibers":
                dictionaryService.deleteDictionary("caliber", id);
                break;
            case "manufacturers":
                dictionaryService.deleteDictionary("manufacturer", id);
                break;
            case "types":
                dictionaryService.deleteDictionary("type", id);
                break;
            default:
                break;
        }
    }
}
