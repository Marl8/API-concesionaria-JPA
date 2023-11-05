package com.example.pruebaJPA.testController;

import com.example.pruebaJPA.Utils.ObjectsUtils;
import com.example.pruebaJPA.controller.Controller;
import com.example.pruebaJPA.dto.VehiculoDto;
import com.example.pruebaJPA.dto.VehiculoResponseDto;
import com.example.pruebaJPA.service.IvehiculoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VehiculoControllerTest {

    @Mock
    IvehiculoService service;

    @InjectMocks
    Controller controller;

    @Test
    void guardarVehiculoTest(){

        // Arrange
        VehiculoDto argumentSut = ObjectsUtils.objetoVehiculoDto();
        VehiculoResponseDto responseDto = new VehiculoResponseDto("Se guardo correctamente");
        ResponseEntity<?> expected = new ResponseEntity<>(responseDto, HttpStatus.OK);

        // Act
        when(service.guardarVehiculo(any())).thenReturn(new VehiculoResponseDto("Se guardo correctamente"));
        ResponseEntity<?> actual = controller.guardarVehiculo(argumentSut);

        // Assert
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getBody(), actual.getBody()),
                () -> assertEquals(expected.getStatusCode(), actual.getStatusCode())
        );
    }
}
