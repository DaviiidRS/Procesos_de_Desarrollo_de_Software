package com.example.proyecto_procesos.Service;

import com.example.proyecto_procesos.Model.ProductoModel;
import com.example.proyecto_procesos.Repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoModel guardar(ProductoModel producto) {
        try {
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el producto " + e.getMessage(), e);
        }
    }

    public List<ProductoModel> listar() {
        try {
            return productoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los productos " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_producto) {
        try {
            if (!productoRepository.existsById(id_producto)) {
                throw new IllegalArgumentException("No se encontró un producto con el ID " + id_producto);
            }
            productoRepository.deleteById(id_producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto " + e.getMessage(), e);
        }
    }

    public Optional<ProductoModel> listarPorId(Long id_producto) {
        try {
            Optional<ProductoModel> producto = productoRepository.findById(id_producto);
            if (producto.isEmpty()) {
                throw new IllegalArgumentException("Producto con ID " + id_producto + " no encontrado.");
            }
            return producto;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar producto por ID " + e.getMessage(), e);
        }
    }

    public ProductoModel actualizar(Long id_producto, ProductoModel producto) {
        try {
            if (!productoRepository.existsById(id_producto)) {
                throw new IllegalArgumentException("No se encontró un producto con el ID " + id_producto);
            }
            producto.setId_producto(id_producto);
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto " + e.getMessage(), e);
        }
    }
}
