package com.example.pruebaJPA.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoGetDto {

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
    @Min(value = 1000, message = "El valor mínimo es $1000")
    private int price;
    @NotBlank(message = "De contener un valor")
    private String currency;
    @Positive
    @Min(value = 1, message = "El valor mínimo es 1")
    private int countOfOwners;
}
