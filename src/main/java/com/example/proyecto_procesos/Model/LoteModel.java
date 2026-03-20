package com.example.proyecto_procesos.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lote;

    private String numeroLote;

    private LocalDate fechaVencimiento;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    @JsonIgnore
    private ProductoModel producto;

    @Transient
    private Long id_producto;

    @PostLoad
    public void asignarIds() {
        if (producto != null) {
            this.id_producto = producto.getId_producto();
        }
    }
}
