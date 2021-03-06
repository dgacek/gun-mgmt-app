package com.example.demo.controller;

import com.example.demo.model.dto.Gun;
import com.example.demo.model.dto.GunInput;
import com.example.demo.service.GunService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/guns")
public class GunController {
    private final GunService gunService;

    @GetMapping
    @PreAuthorize("hasAuthority('GUN_READ')")
    public ResponseEntity<List<Gun>> getAllGuns() {
        return new ResponseEntity<>(gunService.findAllGuns(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GUN_READ')")
    public ResponseEntity<Gun> getGunById(@PathVariable Long id) {
        return new ResponseEntity<>(gunService.findGunById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('GUN_CREATE')")
    public ResponseEntity<Gun> addGun(@RequestBody GunInput gunInput) {
        return new ResponseEntity<>(gunService.addGun(gunInput), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('GUN_UPDATE')")
    public ResponseEntity<Gun> updateGun(@RequestBody Gun gun) {
        return new ResponseEntity<>(gunService.updateGun(gun), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('GUN_DELETE')")
    public void deleteGun(@PathVariable Long id) {
        gunService.deleteGun(id);
    }
}
