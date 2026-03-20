package com.example.proyecto_procesos.Service;

import com.example.proyecto_procesos.Model.LoteModel;
import com.example.proyecto_procesos.Model.ProductoModel;
import com.example.proyecto_procesos.Repository.LoteRepository;
import com.example.proyecto_procesos.Repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoteService {
    private final LoteRepository loteRepository;
    private final ProductoRepository productoRepository;

    public LoteModel guardar(LoteModel lote) {
        try {
            ProductoModel producto = productoRepository.findById(lote.getId_producto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID " + lote.getId_producto()));

            lote.setProducto(producto);
            return loteRepository.save(lote);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el lote " + e.getMessage(), e);
        }
    }

    public List<LoteModel> listar() {
        try {
            return loteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los lotes " + e.getMessage(), e);
        }
    }

    public Optional<LoteModel> listarPorId(Long id_lote) {
        try {
            Optional<LoteModel> lote = loteRepository.findById(id_lote);
            if (lote.isEmpty()) {
                throw new IllegalArgumentException("Lote con ID " + id_lote + " no encontrado.");
            }
            return lote;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar lote por ID " + e.getMessage(), e);
        }
    }

    public void eliminar(Long id_lote) {
        try {
            if (!loteRepository.existsById(id_lote)) {
                throw new IllegalArgumentException("No se encontró un lote con el ID " + id_lote);
            }
            loteRepository.deleteById(id_lote);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el lote " + e.getMessage(), e);
        }
    }

    public LoteModel actualizar(Long id_lote, LoteModel lote) {
        try {
            if (!loteRepository.existsById(id_lote)) {
                throw new IllegalArgumentException("No se encontró un lote con el ID " + id_lote);
            }

            ProductoModel producto = productoRepository.findById(lote.getId_producto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID " + lote.getId_producto()));

            lote.setId_lote(id_lote);
            lote.setProducto(producto);

            return loteRepository.save(lote);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el lote " + e.getMessage(), e);
        }
    }
}
