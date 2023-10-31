package com.example.pruebaJPA.Utils;

import com.example.pruebaJPA.dto.VehiculoDto;
import com.example.pruebaJPA.entity.Service;
import com.example.pruebaJPA.entity.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ObjectsUtils {

    public static Vehiculo objetoVehiculo(){
        List<Service> servicios = new ArrayList<>();
        LocalDate fechaFabricacion = LocalDate.parse("2007-02-26");
        Vehiculo vehiculo = new Vehiculo(1L,"fiat", "uno", fechaFabricacion, 102500,
                3, 3500, "AR", servicios  , 6);
        servicios.add(new Service(1L, "2007-02-26", "filters", 10000, vehiculo));
        vehiculo.setServices(servicios);
        return vehiculo;
    }

    public static VehiculoDto objetoVehiculoDto() {
        List<Service> servicios = new ArrayList<>();
        LocalDate fechaFabricacion = LocalDate.parse("2007-02-26");
        VehiculoDto vehiculodto = new VehiculoDto(1L,"fiat", "uno", fechaFabricacion, 102500,
                3, 3500, "AR", servicios  , 6);

        ObjectMapper mapper = new ObjectMapper();
        Vehiculo vehiculo = mapper.convertValue(vehiculodto, Vehiculo.class);
        servicios.add(new Service(1L, "2007-02-26", "filters", 10000, vehiculo));
        vehiculodto.setServices(servicios);
        return vehiculodto;
    }

    public static List<Vehiculo> listaVehiculos() {
        List<Vehiculo> traerTodosSinServices = new ArrayList<>();
        List<Service> servicios = new ArrayList<>();
        LocalDate fechaFabricacion = LocalDate.parse("2019-06-16");
        Vehiculo vehiculo = new Vehiculo(2L,"renault", "logan", fechaFabricacion, 7500,
                4, 9500, "AR", servicios  , 3);
        servicios.add(new Service(2L, "2020-04-16", "filters", 10000, vehiculo));

        List<Service> servicios1 = new ArrayList<>();
        LocalDate fechaFabricacion1 = LocalDate.parse("2010-02-26");
        Vehiculo vehiculo1 = new Vehiculo(3L,"ford", "fiesta", fechaFabricacion, 92700,
                3, 4900, "AR", servicios  , 4);
        servicios.add(new Service(3L, "2013-02-26", "filters", 10000, vehiculo));

        traerTodosSinServices.add(vehiculo);
        traerTodosSinServices.add(vehiculo1);
        return traerTodosSinServices;
    }
}
