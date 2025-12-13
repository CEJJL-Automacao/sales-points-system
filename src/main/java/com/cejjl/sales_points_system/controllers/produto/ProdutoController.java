package com.cejjl.sales_points_system.controllers.produto;

import com.cejjl.sales_points_system.models.produto.Produto;
import com.cejjl.sales_points_system.services.produto.GrupoService;
import com.cejjl.sales_points_system.services.produto.ProdutoService;
import com.cejjl.sales_points_system.services.produto.dto.ProdutoDtoRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable UUID id){
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodosProdutos(){
        return ResponseEntity.ok(produtoService.buscarTodosProdutos());
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<Produto>> buscarTodosProdutosPorGrupoId(@PathVariable UUID grupoId){
        return ResponseEntity.ok(produtoService.buscarTodosProdutosPorIdGrupo(grupoId));
    }


    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoDtoRequest produto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoService.adicionarProduto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProdutoPorId(@PathVariable UUID id){
        produtoService.removerProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterarProdutoPorId(@PathVariable UUID id,
                                                       @RequestBody ProdutoDtoRequest produtoDtoRequest){
        return ResponseEntity.ok(produtoService.alterarProdutoPorId(id, produtoDtoRequest));
    }

}

