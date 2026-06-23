package cl.duoc.msSucursal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.msSucursal.dto.SucursalDTO;
import cl.duoc.msSucursal.model.Sucursal;
import cl.duoc.msSucursal.service.SucursalService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @GetMapping
    @Operation(
        summary = "Obtener la lista de sucursales registradas",
        description = "Retorna la lista de sucursales registradas en el sistema del Rent a Car."
    )
    public ResponseEntity<List<Sucursal>> listarSucursales(){
        try {
            List<Sucursal> listaSucursales = service.listar();
            return ResponseEntity.ok(listaSucursales);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
        summary = "Registrar una nueva sucursal",
        description = "Permite registrar una nueva sucursal en el sistema del Rent a Car."
    )
    public ResponseEntity<Sucursal> guardarSucursal(Sucursal sucursal){
        try {
            Sucursal sucursalNueva = service.crearSucursal(sucursal);
            return ResponseEntity.ok(sucursalNueva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/id/{id}")
    @Operation(
        summary = "Buscar sucursal por ID",
        description = "Retorna los detalles de una sucursal específica por su ID."
    )
    public ResponseEntity<Sucursal> buscarSucursal(@PathVariable Integer id){
        try {
            Sucursal sucursal = service.buscarSucursal(id);
            return ResponseEntity.ok(sucursal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar sucursal",
        description = "Permite actualizar los detalles de una sucursal específica por su ID."
    )
    public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable Integer id,@RequestBody Sucursal sucursal){
        try {
            Sucursal sucursalActualizada = service.actualizarSucursal(id, sucursal);
            return ResponseEntity.ok(sucursalActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar sucursal",
        description = "Permite eliminar una sucursal específica por su ID."
    )
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id){
        try {
            service.eliminarSucursal(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    @Operation(
        summary = "Buscar sucursal por ID (DTO)",
        description = "Retorna los detalles de una sucursal específica por su ID en formato DTO."
    )
    public ResponseEntity<SucursalDTO> buscarDTO(@PathVariable Integer id){
        try {
            SucursalDTO dto = service.buscarDTO(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
