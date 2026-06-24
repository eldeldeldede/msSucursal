package cl.duoc.msSucursal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la sucursal", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Nombre de la sucursal", example = "Sucursal Central")
    private String nombre;

    @Column(nullable = false, length = 12)
    @Schema(description = "Numero de telefono de la sucursal", example = "98765432")
    private String telefono;

    @Column(nullable = false)
    @Schema(description = "Correo electronico de la sucursal", example = "sucursal@duocuc.cl")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Horario de atencion de la sucursal", example = "Lunes a viernes de 9:00 a 18:00")
    private String horarioAtencion;

    @ManyToOne
    @JoinColumn(name = "direccion_id", nullable = false)
    @Schema(description = "Direccion de la sucursal")
    private Direccion direccion;

}
