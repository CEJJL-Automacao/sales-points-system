package com.cejjl.sales_points_system.services.produto;

import com.cejjl.sales_points_system.dtos.request.ProdutoRequest;
import com.cejjl.sales_points_system.models.produto.Grupo;
import com.cejjl.sales_points_system.models.produto.Produto;
import com.cejjl.sales_points_system.repositories.produto.GrupoRepository;
import com.cejjl.sales_points_system.repositories.produto.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final GrupoRepository grupoRepository;

    @Transactional
    public Produto adicionarProduto(ProdutoRequest request) {
        Grupo grupo = buscarGrupoPorId(request.grupoId());
        Produto produto = new Produto(null, request.nome(), grupo);

        try {
            return produtoRepository.save(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar salvar produto no banco.");
        }
    }

    @Transactional
    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public List<Produto> buscarTodosProdutosPorIdGrupo(UUID grupoId) {
        return produtoRepository.findByGrupoId(grupoId);
    }

    @Transactional
    public Produto buscarProdutoPorId(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public Produto alterarProdutoPorId(UUID id, ProdutoRequest request) {
        Produto produto = buscarProdutoPorId(id);

        if (request.nome() != null)
            produto.setNome(request.nome());
        if (request.grupoId() != null)
            produto.setGrupo(buscarGrupoPorId(request.grupoId()));

        return produtoRepository.save(produto);
    }

    @Transactional
    public void removerProduto(UUID id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }

        try {
            produtoRepository.deleteById(id);
            produtoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível remover o produto: ele possui registros vinculados no sistema.");
        }
    }

    private Grupo buscarGrupoPorId(UUID id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
    }

}
