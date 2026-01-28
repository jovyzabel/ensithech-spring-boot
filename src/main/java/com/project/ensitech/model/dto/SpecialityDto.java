package com.project.ensitech.model.dto;

import com.project.ensitech.enumeration.Cycle;
import lombok.Data;

@Data
public class SpecialityDto {
    private String label;
    private String description;
    private Cycle cycle;
}
