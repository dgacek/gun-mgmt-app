package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gun {
    private Long id;
    private Long productionYear;
    private Model model;
    private DictionaryData caliber;
    private DictionaryData type;
}
