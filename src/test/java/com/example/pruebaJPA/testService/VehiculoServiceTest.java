package com.example.pruebaJPA.testService;

import com.example.pruebaJPA.repository.IvehiculoRepository;
import com.example.pruebaJPA.service.VehiculoServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {

    @Mock
    IvehiculoRepository repository;

    @InjectMocks
    VehiculoServiceImpl service;
}
