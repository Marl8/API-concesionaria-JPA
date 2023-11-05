package com.example.pruebaJPA.testService;

import com.example.pruebaJPA.Utils.ObjectsUtils;
import com.example.pruebaJPA.dto.VehiculoDto;
import com.example.pruebaJPA.dto.VehiculoGetDto;
import com.example.pruebaJPA.dto.VehiculoResponseDto;
import com.example.pruebaJPA.entity.Vehiculo;
import com.example.pruebaJPA.exception.VehiculoNotFoundException;
import com.example.pruebaJPA.repository.IvehiculoRepository;
import com.example.pruebaJPA.service.VehiculoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {

    @Mock // Objeto simulado del repository
    IvehiculoRepository repository;

    @InjectMocks // Le decimos que inyecte todos los Mocks disponibles en el VehiculoService
    VehiculoServiceImpl service;


    @Test
    @DisplayName("Test de guardar vehiculo OK")
    void guardarVehiculoOkTest(){

        // Arrange
        VehiculoDto vehiculoDto = ObjectsUtils.objetoVehiculoDto();
        Vehiculo vehiculo = ObjectsUtils.objetoVehiculo();
        VehiculoResponseDto expected = new VehiculoResponseDto("El vehiculo modelo uno se guardó correctamente.");

        // Act
        when(repository.save(vehiculo)).thenReturn(vehiculo);
        VehiculoResponseDto actual = service.guardarVehiculo(vehiculoDto);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test OK de mostrar todos los vehículos excluyendo el atributo services")
    void findAllSinServicesOkTest(){

        // Arrange
        List<Vehiculo> vehiculos = ObjectsUtils.listaVehiculos();
        List<VehiculoGetDto> expected = ObjectsUtils.listaVehiculoGetDto();

        // Act
        when(repository.findAll()).thenReturn(vehiculos);
        List<VehiculoGetDto> actual = service.findAllSinServices();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test FAIL cuando la lista viene vacía del repository lanza VehiculoNotFoundException")
    void findAllSinServicesFailTest(){

        // Act
        when(repository.findAll()).thenReturn(emptyList());

        // Assert
        assertThrows(VehiculoNotFoundException.class, () -> service.findAllSinServices());
    }

    @Test
    @DisplayName("Test OK buscar vehiculo por id usando existsById()")
    void findVehiculoByIdOkTest(){

        // Arrange
        int id = 1;
        VehiculoDto expected = ObjectsUtils.objetoVehiculoDto();
        Vehiculo vehiculo = ObjectsUtils.objetoVehiculo();
        List<Vehiculo> lista = ObjectsUtils.listaVehiculos();

        // Act
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(vehiculo));
        VehiculoDto actual = service.findVehiculoById(1);

        // Assert
        assertAll(
                () -> assertEquals(expected.getBrand(), actual.getBrand()),
                () -> assertEquals(expected.getModel(), actual.getModel()),
                () -> assertEquals(expected.getManufacturingDate(), actual.getManufacturingDate())
        );
    }

    @Test
    @DisplayName("Test OK buscar vehiculo por id con método privado")
    void findVehiculoByIdOkTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Arrange
        int id = 1;
        VehiculoDto expected = ObjectsUtils.objetoVehiculoDto();
        Vehiculo vehiculo = ObjectsUtils.objetoVehiculo();
        List<Vehiculo> lista = ObjectsUtils.listaVehiculos();

        // Se usa API Refection para poder acceder al método privado
        // https://www.educative.io/answers/how-to-use-junit-5-to-test-for-private-methods
        Method metodoMockeado = VehiculoServiceImpl.class.getDeclaredMethod("verificarSiExisteId", int.class);
        metodoMockeado.setAccessible(true);

        // Act

        // Mockeamos el método privado
        when(metodoMockeado.invoke(service,1)).thenReturn(lista);
        when(repository.findById(any())).thenReturn(Optional.of(vehiculo));
        VehiculoDto actual = service.findVehiculoById(1);

        // Assert
        assertAll(
                () -> assertEquals(expected.getBrand(), actual.getBrand()),
                () -> assertEquals(expected.getModel(), actual.getModel()),
                () -> assertEquals(expected.getManufacturingDate(), actual.getManufacturingDate()),
                () -> assertEquals(expected.getId(), actual.getId())
        );
    }

    @Test
    @DisplayName("Test OK para buscar vehículos por fechas")
    void findVehiculosByDateOkTest(){

        // Arrange
        LocalDate fecha1 = LocalDate.parse("2022-07-18");
        LocalDate fecha2 = LocalDate.parse("2019-09-25");
        List<Vehiculo> listaVehiculos = ObjectsUtils.listaVehiculos();
        List<VehiculoGetDto> expected = ObjectsUtils.listaVehiculoGetDto();

        // Act
        when(repository.findVehiculosByDateBetween(fecha1, fecha2)).thenReturn(listaVehiculos);
        List<VehiculoGetDto> actual = service.findVehiculosByDate(fecha1, fecha2);

        // Arrange
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test OK para buscar vehículos por precios")
    void findVehiculosByDPriceOkTest(){

        // Arrange
        int price1 = 2500;
        int price2 = 12000;
        List<Vehiculo> listaVehiculos = ObjectsUtils.listaVehiculos();
        List<VehiculoDto> expected = ObjectsUtils.listaVehiculoDto();

        // Act
        when(repository.findVehiculosByPriceBetween(price1,price2)).thenReturn(listaVehiculos);
        List<VehiculoDto> actual = service.findVehiculosByPrice(price1, price2);

        // Arrange
        assertEquals(expected.get(1).getManufacturingDate(), actual.get(1).getManufacturingDate());
        assertEquals(expected.get(0).getManufacturingDate(), actual.get(0).getManufacturingDate());
        assertEquals(expected.size(), actual.size());
    }
}

