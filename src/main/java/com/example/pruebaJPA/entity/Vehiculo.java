package com.example.pruebaJPA.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Long id;

    private String brand;
    private String model;
    private LocalDate manufacturingDate;
    private int numberOfKilometers;
    private int doors;
    private int price;
    private String currency;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    List<Service> services;
    private int countOfOwners;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return numberOfKilometers == vehiculo.numberOfKilometers && doors == vehiculo.doors && price == vehiculo.price && countOfOwners == vehiculo.countOfOwners && Objects.equals(id, vehiculo.id) && Objects.equals(brand, vehiculo.brand) && Objects.equals(model, vehiculo.model) && Objects.equals(manufacturingDate, vehiculo.manufacturingDate) && Objects.equals(currency, vehiculo.currency) && Objects.equals(services, vehiculo.services);
    }
}
