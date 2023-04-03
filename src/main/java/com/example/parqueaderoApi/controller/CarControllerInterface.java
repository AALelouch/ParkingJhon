package com.example.parqueaderoApi.controller;

import com.example.parqueaderoApi.entity.Car;
import com.example.parqueaderoApi.entity.Parking;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CarControllerInterface {
    @Operation(summary = "Obtener todos los carros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Se obtuvieron los carros",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "No existen carros",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<Car>> getAllCarros();

    @Operation(summary = "Obtener el sitio de parqueo disponible")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Se obtuvo el sitio",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Parking.class)) }),
            @ApiResponse(responseCode = "404", description = "No hay parqueaderos disponibles",
                    content = @Content) })
    @GetMapping("/obtenerParqueaderos")
    public ResponseEntity<Parking> getAllParkingAvailable();
    @Operation(summary = "Guardar parqueadero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Se guardo el parqueadero",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Parking.class)) }),
            @ApiResponse(responseCode = "400", description = "Datos del parqueadero erroneos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se pudo guardar el parqueadero",
                    content = @Content) })
    @PostMapping("/parking")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveParking(@Parameter(description = "datos del parqueadero")@RequestBody Parking parking);

    @Operation(summary = "Obtener carro por placa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="Carro encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Parking.class)) }),
            @ApiResponse(responseCode = "400", description = "Placa invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el carro",
                    content = @Content) })
    @GetMapping("/carro/{placa}")
    public ResponseEntity<Car> getCarroByPlaca(@Parameter(description = "Placa del carro")@PathVariable String placa);

    @Operation(summary = "Salir del parqueadero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="El vehiculo salio correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Parking.class)) }),
            @ApiResponse(responseCode = "400", description = "Placa invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se pudo generar la salida del vehiculo",
                    content = @Content) })
    @GetMapping("/salir")
    public void exitCarro();

    @Operation(summary = "Guardar un carro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="El vehiculo se guardo correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Parking.class)) }),
            @ApiResponse(responseCode = "400", description = "Placa invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se pudo guardar el vehiculo",
                    content = @Content) })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCarro(@Parameter(description = "Carro a guardar")  @RequestBody Car car);

    @Operation(summary = "Actualizar datos de un carro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="El vehiculo se actualizo correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Parking.class)) }),
            @ApiResponse(responseCode = "400", description = "Placa invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizar el vehiculo",
                    content = @Content) })
    @PatchMapping("/update/{placa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCarro(@Parameter(description = "Placa del carro a actualizar")  @RequestBody Car car, @PathVariable String placa);

    @Operation(summary = "Eliminar un carro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="El vehiculo se elimino correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Parking.class)) }),
            @ApiResponse(responseCode = "400", description = "Placa invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el vehiculo",
                    content = @Content) })
    @DeleteMapping("/delete/{placa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarro(@Parameter(description = "Placa del carro a eliminar")  @RequestBody Car car, @PathVariable String placa);
}
