package com.beauty.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureDTO {
    private Integer id;
    private String name;
    private String cost;  // ← String
    private String duration;
}