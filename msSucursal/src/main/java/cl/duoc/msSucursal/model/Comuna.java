package cl.duoc.msSucursal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comuna")
@Schema(description = "Entidad que representa la comuna de una direccion de una sucursal del Rent a Car")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la comuna", example = "1")
    private Integer id;

    @Column(nullable = false)   
    @Schema(description = "Nombre de la comuna", example = "Maipú")
    private String nombre;
}
