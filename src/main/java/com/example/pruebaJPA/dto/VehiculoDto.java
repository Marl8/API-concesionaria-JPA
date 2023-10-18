package com.example.pruebaJPA.dto;

import com.example.pruebaJPA.entity.Service;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "El vehiculo debe contener una Marca")
    private String brand;
    @NotBlank(message = "El vehiculo debe contener una Modelo")
    private String model;
    @NotNull(message = "El vehiculo debe contener una fecha de fabricación")
    private LocalDate manufacturingDate;
    @Positive
    @Min(value = 1, message = "El vehiculo debe mostrar los kilometros recorridos")
    private int numberOfKilometers;
    @Positive
    @Min(value = 2, message = "El vehiculo debe tener al menos 2 puertas")
    private int doors;
    @Positive
    @Min(1000)
    private int price;
    @NotBlank
    private String currency;
    List<Service> services;
    @Positive
    @Min(1)
    private int countOfOwners;
}
