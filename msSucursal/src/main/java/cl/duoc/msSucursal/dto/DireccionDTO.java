package cl.duoc.msSucursal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DireccionDTO {

    private Integer id;
    private String calle;
    private String numero;
    private ComunaDTO comuna;
}
