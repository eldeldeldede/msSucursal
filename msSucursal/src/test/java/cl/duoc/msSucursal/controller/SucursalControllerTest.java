package cl.duoc.msSucursal.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.duoc.msSucursal.dto.SucursalDTO;
import cl.duoc.msSucursal.model.Direccion;
import cl.duoc.msSucursal.model.Sucursal;
import cl.duoc.msSucursal.service.SucursalService;

@WebMvcTest(SucursalController.class)
public class SucursalControllerTest {

    @Autowired
    private MockMvc mock;

    @MockitoBean
    private SucursalService sucursalServ;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Sucursal ejemSucursal;
    private SucursalDTO dtoEjemplo;

    @BeforeEach
    void setUp() {
        ejemSucursal = new Sucursal();
        ejemSucursal.setId(1);
        ejemSucursal.setNombre("Sucursal");
        ejemSucursal.setTelefono("98765432");
        ejemSucursal.setEmail("be.curinanco@duocuc.cl");
        ejemSucursal.setHorarioAtencion("Lunes a Viernes de 9:00 a 18:00");
        ejemSucursal.setDireccion(new Direccion(1, "Calle Falsa", "123", null, null));

        dtoEjemplo = new SucursalDTO(1, "Calle Falsa 123, Santiago");
    }

    // ---------- listarSucursales ----------

    @Test
    public void listarSucursales_retorna200conLista() throws Exception {
        when(sucursalServ.listar()).thenReturn(List.of(ejemSucursal));

        mock.perform(get("/api/v1/sucursales"))
            .andExpect(status().isOk());
    }

    @Test
    public void listarSucursales_retornaNotFoundSiHayError() throws Exception {
        when(sucursalServ.listar()).thenThrow(new RuntimeException());

        mock.perform(get("/api/v1/sucursales"))
            .andExpect(status().isNotFound());
    }

    // ---------- buscarSucursal por id ----------

    @Test
    public void buscarPorId_retora200() throws Exception {
        when(sucursalServ.buscarSucursal(1)).thenReturn(ejemSucursal);

        mock.perform(get("/api/v1/sucursales/id/1"))
            .andExpect(status().isOk());
    }

    @Test
    public void buscarPorId_retornar404() throws Exception {
        when(sucursalServ.buscarSucursal(99)).thenThrow(new RuntimeException());

        mock.perform(get("/api/v1/sucursales/id/99"))
            .andExpect(status().isNotFound());
    }

    // ---------- guardarSucursal (POST) ----------

    @Test
    public void guardarSucursal_retorna200() throws Exception {
        when(sucursalServ.crearSucursal(any(Sucursal.class))).thenReturn(ejemSucursal);

        mock.perform(post("/api/v1/sucursales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ejemSucursal)))
            .andExpect(status().isOk());
    }

    @Test
    public void guardarSucursal_retornaNoContentSiHayError() throws Exception {
        when(sucursalServ.crearSucursal(any(Sucursal.class))).thenThrow(new RuntimeException());

        mock.perform(post("/api/v1/sucursales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ejemSucursal)))
            .andExpect(status().isNoContent());
    }


    @Test
    public void buscarDTO_retorna200() throws Exception {
        when(sucursalServ.buscarDTO(1)).thenReturn(dtoEjemplo);

        mock.perform(get("/api/v1/sucursales/dto/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.direccion").value("Calle Falsa 123, Santiago"));
    }

    @Test
    public void buscarDTO_retorna404() throws Exception {
        when(sucursalServ.buscarDTO(99)).thenThrow(new RuntimeException());

        mock.perform(get("/api/v1/sucursales/dto/99"))
            .andExpect(status().isNotFound());
    }

    // ---------- eliminarSucursal (DELETE) ----------

    @Test
    public void eliminarSucursal_exitoso_retornaNoContent() throws Exception {
        doNothing().when(sucursalServ).eliminarSucursal(1);

        mock.perform(delete("/api/v1/sucursales/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarSucursal_error_retornaNotFound() throws Exception {
        doThrow(new RuntimeException()).when(sucursalServ).eliminarSucursal(99);

        mock.perform(delete("/api/v1/sucursales/99"))
            .andExpect(status().isNotFound());
    }
}