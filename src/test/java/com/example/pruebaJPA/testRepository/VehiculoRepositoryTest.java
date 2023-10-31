package com.example.pruebaJPA.testRepository;

import com.example.pruebaJPA.Utils.ObjectsUtils;
import com.example.pruebaJPA.dto.VehiculoDto;
import com.example.pruebaJPA.entity.Service;
import com.example.pruebaJPA.entity.Vehiculo;
import com.example.pruebaJPA.repository.IvehiculoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Configuración para testear con base de datos

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VehiculoRepositoryTest {

    @Autowired
    IvehiculoRepository repository;

    @Test
    @Rollback(false) // Para que guarde los datos en la base de datos
    void guardarVehiculoTest(){

        // 1. Arrange
        Vehiculo vehiculo = ObjectsUtils.objetoVehiculo();

        // 2. Act
        Vehiculo guardado = repository.save(vehiculo);

        // 3. Assert
        assertNotNull(guardado);
    }

    @Test
    @Rollback(false)
    void findAllSinServicesTest(){

        List<Vehiculo> traerTodosSinServices = new ArrayList();
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

        repository.save(vehiculo);
        repository.save(vehiculo1);

        traerTodosSinServices.add(vehiculo);
        traerTodosSinServices.add(vehiculo1);
        Object [] esperados = new List[]{traerTodosSinServices};
        Object [] encontrados = new List[]{repository.findAll()};

        assertAll(
                () -> assertNotNull(encontrados),
                () -> assertArrayEquals(esperados, encontrados),
                () -> assertEquals(1, encontrados.length)
         );
    }

    @Test
    void findVehiculoByIdTest(){
        Vehiculo vehiculo = ObjectsUtils.objetoVehiculo();

        Vehiculo esperado = repository.save(vehiculo);
        Vehiculo encontrado = repository.findById(vehiculo.getId()).get();

        assertAll(
                () -> assertNotNull(esperado),
                () -> assertEquals(esperado, encontrado),

        /*
        * Para poder usar assertThat()
        *
        * Agregar el import => import static org.hamcrest.MatcherAssert.assertThat;
        *
        * https://junit.org/junit5/docs/snapshot/user-guide/
        * */
                () ->assertThat(encontrado.getModel(), is(equalTo(vehiculo.getModel())))
        );
    }

}
