package com.cejjl.sales_points_system.controllers.produto;

import com.cejjl.sales_points_system.dtos.request.GrupoRequest;
import com.cejjl.sales_points_system.models.produto.Grupo;
import com.cejjl.sales_points_system.services.produto.GrupoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("grupos")
public class GrupoController {

    private final GrupoService grupoService;

    @PostMapping
    public ResponseEntity<Grupo> criarGrupo(@RequestBody GrupoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoService.adicionarGrupo(request));
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> buscarTodosGrupos() {
        return ResponseEntity.ok(grupoService.buscarTodosGrupos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> buscarGrupoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(grupoService.buscarGrupoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGrupoPorId(@PathVariable UUID id) {
        grupoService.deletarGrupoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> atualizarGrupoPorId(@PathVariable UUID id, @RequestBody GrupoRequest request) {
        return ResponseEntity.ok(grupoService.atualizarGrupoPorId(id, request));
    }
}
