package com.example.pruebaJPA.dto;

import com.example.pruebaJPA.entity.Service;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoDto {

    @Min(1)
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotNull
    private LocalDate manufacturingDate;
    @Min(1)
    private int numberOfKilometers;
    @Min(2)
    private int doors;
    @Min(1000)
    private int price;
    @NotBlank
    private String currency;
    List<Service> services;
    @Min(1)
    private int countOfOwners;
}
