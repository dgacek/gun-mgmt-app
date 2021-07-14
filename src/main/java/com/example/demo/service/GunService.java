package com.example.demo.service;

import com.example.demo.model.dto.Gun;
import com.example.demo.model.dto.GunInput;

import java.util.List;

public interface GunService {
    Gun addGun(GunInput gunInput);
    List<Gun> findAllGuns();
    Gun updateGun(Gun gun);
    Gun findGunById(Long id);
    void deleteGun(Long id);
}
