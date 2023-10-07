package com.example.pruebaJPA.controller;


import com.example.pruebaJPA.dto.VehiculoDto;
import com.example.pruebaJPA.service.IvehiculoService;
import com.example.pruebaJPA.service.VehiculoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/api/vehicles")
public class controller {

    IvehiculoService service;

    public controller(VehiculoServiceImpl vehiculoService) {
        this.service = vehiculoService;
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarVehiculo(@RequestBody VehiculoDto vehiculoDto){
        return new ResponseEntity<>(service.guardarVehiculo(vehiculoDto), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllSinServices(){
        return new ResponseEntity<>(service.findAllSinServices(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllVehiculoById(@PathVariable int id){
        return new ResponseEntity<>(service.findVehiculoById(id), HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<?> findVehiculoByDate(@RequestParam LocalDate since, @RequestParam LocalDate to){
        return new ResponseEntity<>(service.findVehiculosByDate(since, to), HttpStatus.OK);
    }

    @GetMapping("/prices")
    public ResponseEntity<?> findVehiculoByPrices(@RequestParam int since, @RequestParam int to){
        return new ResponseEntity<>(service.findVehiculosByPrice(since, to), HttpStatus.OK);
    }
}
