package cl.duoc.msSucursal.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    private SucursalService service;

    @GetMapping
    public ResponseEntity<List<Sucursal>> listarSucursales(){
        try {
            List<Sucursal> listaSucursales = service.listar();
            return ResponseEntity.ok(listaSucursales);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Sucursal> guardarSucursal(Sucursal sucursal){
        try {
            Sucursal sucursalNueva = service.crearSucursal(sucursal);
            return ResponseEntity.ok(sucursalNueva);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Sucursal> buscarSucursal(@PathVariable Integer id){
        try {
            Sucursal sucursal = service.buscarSucursal(id);
            return ResponseEntity.ok(sucursal);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable Integer id,@RequestBody Sucursal sucursal){
        try {
            Sucursal sucursalActualizada = service.actualizarSucursal(id, sucursal);
            return ResponseEntity.ok(sucursalActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id){
        try {
            service.eliminarSucursal(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<SucursalDTO> buscarDTO(Integer id){
        try {
            SucursalDTO dto = service.buscarDTO(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }



}
