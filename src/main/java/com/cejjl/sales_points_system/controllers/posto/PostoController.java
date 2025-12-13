package com.cejjl.sales_points_system.controllers.posto;

import com.cejjl.sales_points_system.models.posto.Posto;
import com.cejjl.sales_points_system.services.funcionario.FuncionarioService;
import com.cejjl.sales_points_system.services.posto.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/postos")
public class PostoController {

    @Autowired
    private PostoService postoService;
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Posto> criar(@RequestBody Posto posto) {
        Posto novoPosto = postoService.criar(posto);

        return ResponseEntity.ok(novoPosto);
    }

    @GetMapping
    public ResponseEntity<List<Posto>> listarTodos() {
        return ResponseEntity.ok(postoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(postoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Posto> atualizar(@PathVariable UUID id, @RequestBody Posto posto) {
        return ResponseEntity.ok(postoService.atualizar(id, posto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        postoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}