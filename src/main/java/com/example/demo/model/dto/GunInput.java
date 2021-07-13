package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GunInput {
    private Long productionYear;
    private Long modelId;
    private Long caliberId;
    private Long typeId;
}
