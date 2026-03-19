package com.example.proyecto_procesos.Controller;

import com.example.proyecto_procesos.Model.ProductoModel;
import com.example.proyecto_procesos.Repository.ProductoRepository;
import com.example.proyecto_procesos.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    @PostMapping("/guardar")
    public ResponseEntity<ProductoModel> guardarProducto(@RequestBody ProductoModel producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProductoModel>> listarProductos() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/listar/{id_producto}")
    public ResponseEntity<ProductoModel> obtenerProductoPorId(@PathVariable Long id_producto) {
        return productoService.listarPorId(id_producto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_producto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id_producto) {
        productoService.eliminar(id_producto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_producto}")
    public ResponseEntity<ProductoModel> actualizar(@PathVariable Long id_producto, @RequestBody ProductoModel producto) {
        producto.setId_producto(id_producto);
        ProductoModel productoActualizado = productoService.actualizar(id_producto, producto);
        return ResponseEntity.ok(productoActualizado);
    }
}
