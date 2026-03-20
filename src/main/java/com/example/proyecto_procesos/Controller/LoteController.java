package com.example.proyecto_procesos.Controller;

import com.example.proyecto_procesos.Model.LoteModel;
import com.example.proyecto_procesos.Repository.LoteRepository;
import com.example.proyecto_procesos.Service.LoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lote")
@RequiredArgsConstructor
public class LoteController {
    private final LoteService loteService;
    private final LoteRepository loteRepository;

    @PostMapping("/guardar")
    public ResponseEntity<LoteModel> guardarLote(@RequestBody LoteModel lote) {
        return ResponseEntity.ok(loteService.guardar(lote));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LoteModel>> listarLotes() {
        return ResponseEntity.ok(loteService.listar());
    }

    @GetMapping("/listar/{id_lote}")
    public ResponseEntity<LoteModel> obtenerLotePorId(@PathVariable Long id_lote) {
        return loteService.listarPorId(id_lote)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id_lote}")
    public ResponseEntity<Void> eliminarLote(@PathVariable Long id_lote) {
        loteService.eliminar(id_lote);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id_lote}")
    public ResponseEntity<LoteModel> actualizar(@PathVariable Long id_lote, @RequestBody LoteModel lote) {
        lote.setId_lote(id_lote);
        LoteModel loteActualizado = loteService.actualizar(id_lote, lote);
        return ResponseEntity.ok(loteActualizado);
    }
}
