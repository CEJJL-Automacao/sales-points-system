package com.cejjl.sales_points_system.controllers.venda;

import com.cejjl.sales_points_system.models.venda.Venda;
import com.cejjl.sales_points_system.services.venda.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService service;

    @PostMapping
    public ResponseEntity<Venda> criar(@RequestBody Venda venda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.adicionar(venda));
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable UUID id, @RequestBody Venda venda) {
        return ResponseEntity.ok(service.atualizar(id, venda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}