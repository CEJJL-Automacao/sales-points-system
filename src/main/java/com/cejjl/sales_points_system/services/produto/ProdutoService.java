package com.cejjl.sales_points_system.services.produto;


import com.cejjl.sales_points_system.models.produto.Grupo;
import com.cejjl.sales_points_system.models.produto.Produto;
import com.cejjl.sales_points_system.repositories.produto.GrupoRepository;
import com.cejjl.sales_points_system.repositories.produto.ProdutoRepository;
import com.cejjl.sales_points_system.services.produto.dto.ProdutoDtoRequest;
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
    public Produto adicionarProduto(ProdutoDtoRequest produtoDtoRequest) {

        Grupo grupo = buscarGrupoPorId(produtoDtoRequest.grupoId());

        Produto produto = new Produto(null, produtoDtoRequest.nome(),grupo);

        try {
            return produtoRepository.save(produto);
        }catch (Exception e){
            throw new RuntimeException("Erro ao tentar salvar produto no banco.");
        }
    }

    @Transactional
    public List<Produto> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }

    @Transactional
    public List<Produto> buscarTodosProdutosPorIdGrupo(UUID grupoId){

        return produtoRepository.findByGrupoId(grupoId);

    }

    @Transactional
    public Produto buscarProdutoPorId(UUID id){

        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    }

    @Transactional
    public Produto alterarProdutoPorId(UUID id, ProdutoDtoRequest produtoDtoRequest){

        Produto produto = buscarProdutoPorId(id);

        Produto novoProduto = Produto.builder()
                .id(produto.getId())
                .nome(produtoDtoRequest.nome() == null ? produto.getNome() : produtoDtoRequest.nome())
                .grupo(produtoDtoRequest.grupoId() == null ? produto.getGrupo() : buscarGrupoPorId(produtoDtoRequest.grupoId()))
                .build();

        return produtoRepository.save(novoProduto);
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

    private Grupo buscarGrupoPorId(UUID id){
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
    }

}
