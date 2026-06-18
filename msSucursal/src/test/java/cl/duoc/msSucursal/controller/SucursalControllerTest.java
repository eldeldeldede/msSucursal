package cl.duoc.msSucursal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.duoc.msSucursal.model.Direccion;
import cl.duoc.msSucursal.model.Sucursal;
import cl.duoc.msSucursal.service.SucursalService;

@WebMvcTest(SucursalController.class)
public class SucursalControllerTest {

    @Autowired
    private MockMvc mock;
    
    @MockitoBean
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
    void buscarPorId_retora200() throws Exception {
        when(sucursalServ.buscarSucursal(1)).thenReturn(ejemSucursal);

        //ACT + ASSERT
        mock.perform(get("/api/v1/sucursales/id/1"))
            .andExpect(status().isOk()); // o sea un codigo HTTPS 200
        
    }

    @Test
    void buscarPorId_retornar404() throws Exception{

        when(sucursalServ.buscarSucursal(99)).thenThrow(new RuntimeException("Sucursal no encontrada"));

        //ACT + ASSERT
        mock.perform(get("/api/v1/sucursales/id/99"))
            .andExpect(status().isNotFound()); // o sea un codigo HTTPS 404

    }

    

    

}
