package cl.duoc.msSucursal.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.msSucursal.dto.SucursalDTO;
import cl.duoc.msSucursal.model.Direccion;
import cl.duoc.msSucursal.model.Sucursal;
import cl.duoc.msSucursal.repository.SucursalRepository;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepo;

    @InjectMocks
    private SucursalService sucursalServ;

    private Sucursal ejemSucursal;

    @BeforeEach
    void setUp() {
        ejemSucursal = new Sucursal();
        ejemSucursal.setId(1);
        ejemSucursal.setNombre("Sucursal");
        ejemSucursal.setTelefono("98765432");
        ejemSucursal.setEmail("be.curinanco@duocuc.cl");
        ejemSucursal.setHorarioAtencion("Lunes a Viernes de 9:00 a 18:00");
        ejemSucursal.setDireccion(new Direccion(1, "Calle Falsa", "123", null, null));

    }

    @Test
    void Guardar() {
        when(sucursalRepo.save(ejemSucursal)).thenReturn(ejemSucursal);

        Sucursal resultado = sucursalServ.crearSucursal(ejemSucursal);

        assertEquals(ejemSucursal, resultado);
    }

    @Test
    void buscarSucursalPorId_encontrado() {
        Optional<Sucursal> sucursal = Optional.of(ejemSucursal);
        when(sucursalRepo.findById(1)).thenReturn(sucursal);

        Sucursal resultado = sucursalServ.buscarSucursal(1);
        assertEquals(1, resultado.getId());
        assertEquals("Sucursal", resultado.getNombre());
    }

    @Test
    void buscarSucursalPorId_noEncontrado() {
        when(sucursalRepo.findById(1)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sucursalServ.buscarSucursal(1);
        });
        assertEquals("Sucursal no encontrada", exception.getMessage());
    }

    @Test
    void listar_retornaLista() {
        when(sucursalRepo.findAll()).thenReturn(List.of(ejemSucursal));

        List<Sucursal> resultado = sucursalServ.listar();

        assertEquals(1, resultado.size());
        assertEquals("Sucursal", resultado.get(0).getNombre());
    }

    @Test
    void eliminarSucursal_exitoso() {
        when(sucursalRepo.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> sucursalServ.eliminarSucursal(1));
        verify(sucursalRepo, times(1)).deleteById(1);
    }

    @Test
    void eliminarSucursal_noExiste() {
        when(sucursalRepo.existsById(99)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sucursalServ.eliminarSucursal(99);
        });

        assertEquals("Sucursal no encontrada", exception.getMessage());
        verify(sucursalRepo, times(0)).deleteById(99);
    }

    @Test
    void actualizarSucursal_exitoso() {
        Sucursal datosNuevos = new Sucursal();
        datosNuevos.setNombre("Sucursal Actualizada");
        datosNuevos.setDireccion(ejemSucursal.getDireccion());
        datosNuevos.setEmail("nueva@sucursal.cl");
        datosNuevos.setHorarioAtencion("24/7");
        datosNuevos.setTelefono("11111111");

        when(sucursalRepo.findById(1)).thenReturn(Optional.of(ejemSucursal));
        when(sucursalRepo.save(ejemSucursal)).thenReturn(ejemSucursal);

        Sucursal resultado = sucursalServ.actualizarSucursal(1, datosNuevos);

        assertEquals("Sucursal Actualizada", resultado.getNombre());
        assertEquals("nueva@sucursal.cl", resultado.getEmail());
        verify(sucursalRepo, times(1)).save(ejemSucursal);
    }

    @Test
    void actualizarSucursal_noEncontrado() {
        when(sucursalRepo.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sucursalServ.actualizarSucursal(99, ejemSucursal);
        });

        assertEquals("Sucursal no encontrada", exception.getMessage());
        verify(sucursalRepo, times(0)).save(any(Sucursal.class));
    }

    
}