package com.example.pruebaJPA.service;


import com.example.pruebaJPA.dto.VehiculoDto;
import com.example.pruebaJPA.dto.VehiculoGetDto;
import com.example.pruebaJPA.dto.VehiculoResponseDto;
import com.example.pruebaJPA.entity.Vehiculo;
import com.example.pruebaJPA.repository.IvehiculoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VehiculoServiceImpl implements IvehiculoService{

    IvehiculoRepository repository;

    public VehiculoServiceImpl(IvehiculoRepository vehiculoRepository) {
        this.repository = vehiculoRepository;
    }

    @Override
    public VehiculoResponseDto guardarVehiculo(VehiculoDto auto) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Vehiculo vehiculo = mapper.convertValue(auto,Vehiculo.class);
        Vehiculo respuestaRepo = repository.save(vehiculo);

        if(respuestaRepo == null){
            return new VehiculoResponseDto("Error!!! No se logró guardar el vehiculo.");
        }
        return new VehiculoResponseDto("El vehiculo modelo "+ respuestaRepo.getModel() + " se guardó correctamente.");
    }

    @Override
    public List<VehiculoGetDto> findAllSinServices() {
        List<Vehiculo> result = repository.findAll();
        return convertirDto(result);
    }

    /*
    * SOLUCIÓN ERROR => "Java 8 date/time type not supported by default"
    *
    * (El error se produce al querer serializar un LocalDate con la librería
    * default de ObjectMapper, dado que esta no reconoce las clases tipo TIME que
    * fueron incluidas en Java 8 por lo cual se hace necesario trabajar con una
    * versión actualizada de esta librería).
    *
    * Paso 1:
    *       Agregar la siguiente dependencia:
        *   <dependency>
                  <groupId>com.fasterxml.jackson.datatype</groupId>
                  <artifactId>jackson-datatype-jsr310</artifactId>
                  <version>2.13.4</version>
             </dependency>
    * Paso 2:
    *       Registrar el módulo JavaTimeModule() una vez inicializado el mapper, para
    *       indicar que vamos a trabajar con la versión actualizada y no con el default:
    *
    *       ObjectMapper mapper = new ObjectMapper();
    *       mapper.registerModule(new JavaTimeModule());
    *
    * Fuente:
    *
    * https://howtodoinjava.com/jackson/java-8-date-time-type-not-supported-by-default/
    * https://stackoverflow.com/questions/74188846/how-to-fix-jackson-java-8-data-time-error
    * */

    @Override
    public VehiculoDto findVehiculoById(int id) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Long idVehiculo = (long) id;
        /*
        Usar el Método findById en lugar del referentById para evitar el error
        en el mapeo que se da cuando se usa este último
         */
        Vehiculo auto = repository.findById(idVehiculo).get();
        return mapper.convertValue(auto,VehiculoDto.class);
    }

   @Override
    public List<VehiculoGetDto> findVehiculosByDate(LocalDate date1, LocalDate date2) {
        List<Vehiculo> result = repository.findVehiculosByDateBetween(date1, date2);
        return convertirDto(result);
    }

    /*
    // Usando un Dto sin Services

    @Override
    public List<VehiculoGetDto> findVehiculosByPrice(int price1, int price2) {
        List<Vehiculo> result = repository.findVehiculosByPrice(price1, price2);
        return convertirDto(result);
    }*/


    // Usando un dto con todos los atributos (Incluyendo Services)
    @Override
    public List<VehiculoDto> findVehiculosByPrice(int price1, int price2) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Vehiculo> result = repository.findVehiculosByPriceBetween(price1, price2);
        return result.stream().map(v -> mapper.convertValue(v, VehiculoDto.class)).toList();
    }

    /*
    // Método lambda para convertir a dto usando Foreach()

    private List<VehiculoGetDto> convertirDto(List<Vehiculo> lista){
        List<VehiculoGetDto> listaResponse = new ArrayList<>();
        lista.stream().forEach(v ->{
            listaResponse.add(new VehiculoGetDto(v.getId(), v.getBrand(),v.getModel(), v.getManufacturingDate(),
                    v.getNumberOfKilometers(), v.getDoors(), v.getPrice(), v.getCurrency(),
                    v.getCountOfOwners()));
        });
        return listaResponse;
    }*/

    // Método lambda para convertir a dto usando Map()
    private List<VehiculoGetDto> convertirDto(List<Vehiculo> lista){
        List<VehiculoGetDto> listaResponse = lista.stream().map(v -> new VehiculoGetDto(v.getId(), v.getBrand(),
                v.getModel(), v.getManufacturingDate(), v.getNumberOfKilometers(), v.getDoors(), v.getPrice(),
                v.getCurrency(), v.getCountOfOwners())).toList();
        return listaResponse;
    }

    // Método para convertir String en LocalDate
    private LocalDate convertirFecha(String date) {
        // Se da formato a la fecha
        DateTimeFormatter formato =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Se parsea el parámetro de tipo String a tipo LocalDate
        LocalDate fecha = LocalDate.parse(date, formato);
        return fecha;
    }

   /*
   // Método para convertir String en Date

   private Date convertirFecha(String date) {
        // Se da formato a la fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            // Se parsea el parámetro de tipo String a tipo Date
            fecha = formato.parse(date);
        } catch (ParseException e) {
            System.out.println("Error parsing date" + e.getMessage());
        }
        return fecha;
    }
    */
}