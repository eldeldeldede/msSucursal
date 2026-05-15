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
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String calle;

    @Column (nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", nullable = false)
    @JsonBackReference
    private Ciudad ciudad;

    @JoinColumn(name = "comuna_id", nullable = false)
    @ManyToOne
    @JsonBackReference
    private Comuna comuna;

}
