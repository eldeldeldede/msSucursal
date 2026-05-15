package cl.duoc.msSucursal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msSucursal.dto.SucursalDTO;
import cl.duoc.msSucursal.model.Sucursal;
import cl.duoc.msSucursal.repository.SucursalRepository;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository repo;

    public List<Sucursal> listar(){
        return repo.findAll();
    }

    public Sucursal buscarSucursal(Integer id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

    public Sucursal crearSucursal(Sucursal sucursal){
        return repo.save(sucursal);
    }

    public void eliminarSucursal(Integer id){
        if(repo.existsById(id)){
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Sucursal no encontrada");
        }
    }

    public Sucursal actualizarSucursal(Integer id, Sucursal sucursalActualizada){
        Sucursal sucursal = repo.findById(id).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        sucursal.setNombre(sucursalActualizada.getNombre());
        sucursal.setDireccion(sucursalActualizada.getDireccion());
        sucursal.setEmail(sucursalActualizada.getEmail());
        sucursal.setHorarioAtencion(sucursalActualizada.getHorarioAtencion());
        sucursal.setTelefono(sucursalActualizada.getTelefono());
        return repo.save(sucursal);
    }

    public SucursalDTO buscarDTO(Integer id){
        Sucursal sucursal = buscarSucursal(id);
        return new SucursalDTO(sucursal.getId(), sucursal.getDireccion().getCalle() + " " + sucursal.getDireccion().getNumero() + 
        ", " + sucursal.getDireccion().getComuna());
    }
}
