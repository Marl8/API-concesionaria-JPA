package com.example.pruebaJPA.dto;

import com.example.pruebaJPA.entity.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoDto {

    private Long id;
    private String brand;
    private String model;
    private LocalDate manufacturingDate;
    private int numberOfKilometers;
    private int doors;
    private int price;
    private String currency;
    List<Service> services;
    private int countOfOwners;
}
