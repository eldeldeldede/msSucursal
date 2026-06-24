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
@Table(name = "ciudad")
@Schema(description = "Entidad que representa la ciudad de una direccion de una sucursal del Rent a Car")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la ciudad", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Nombre de la ciudad", example = "Santiago")
    private String nombre;
}
