package cl.duoc.msSucursal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    
    

}
