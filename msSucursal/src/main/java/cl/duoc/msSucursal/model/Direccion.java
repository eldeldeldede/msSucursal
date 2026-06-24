package cl.duoc.msSucursal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "direccion")
@Schema(description = "Entidad que representa la direccion de una sucursal del Rent a Car")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la direccion", example = "1")
    private Integer id;

    @Column (nullable = false)
    @Schema(description = "Calle de la direccion", example = "Calle Falsa")
    private String calle;

    @Column (nullable = false)
    @Schema(description = "Numero de la direccion", example = "123")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false)
    @Schema(description = "Ciudad de la direccion")
    @JsonIgnore
    private Ciudad ciudad;

    @JoinColumn(name = "comuna_id", nullable = false)
    @ManyToOne
    @Schema(description = "Comuna de la direccion")
    @JsonIgnore
    private Comuna comuna;

}
