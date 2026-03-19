package com.example.proyecto_procesos.Repository;

import com.example.proyecto_procesos.Model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {
}
