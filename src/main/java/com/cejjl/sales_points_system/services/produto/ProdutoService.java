package com.cejjl.sales_points_system.services.produto;


import com.cejjl.sales_points_system.models.produto.Grupo;
import com.cejjl.sales_points_system.models.produto.Produto;
import com.cejjl.sales_points_system.repositories.produto.GrupoRepository;
import com.cejjl.sales_points_system.repositories.produto.ProdutoRepository;
import com.cejjl.sales_points_system.services.produto.dto.ProdutoDtoRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final GrupoRepository grupoRepository;

    public Produto adicionarProduto(ProdutoDtoRequest produtoDtoRequest) {

        Grupo grupo = buscarGrupoPorId(produtoDtoRequest.grupoId());

        Produto produto = new Produto(null, produtoDtoRequest.nome(),grupo);

        try {
            return produtoRepository.save(produto);
        }catch (Exception e){
            throw new RuntimeException("Erro ao tentar salvar produto no banco.");
        }
    }

    public List<Produto> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> buscarTodosProdutosPorIdGrupo(UUID grupoId){

        return produtoRepository.findByGrupoId(grupoId);

    }

    public Produto buscarProdutoPorId(UUID id){

        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    }

    public Produto alterarProdutoPorId(UUID id, ProdutoDtoRequest produtoDtoRequest){

        Produto produto = buscarProdutoPorId(id);

        Produto novoProduto = Produto.builder()
                .id(produto.getId())
                .nome(produtoDtoRequest.nome() == null ? produto.getNome() : produtoDtoRequest.nome())
                .grupo(produtoDtoRequest.grupoId() == null ? produto.getGrupo() : buscarGrupoPorId(produtoDtoRequest.grupoId()))
                .build();

        return produtoRepository.save(novoProduto);
    }

    public void removerProduto(UUID id){

        Produto produto = buscarProdutoPorId(id);
        produtoRepository.delete(produto);

    }

    private Grupo buscarGrupoPorId(UUID id){
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
    }

}
