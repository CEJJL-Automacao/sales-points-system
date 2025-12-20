package com.cejjl.sales_points_system.services.venda;

import com.cejjl.sales_points_system.dtos.request.VendaRequest;
import com.cejjl.sales_points_system.models.funcionario.Funcionario;
import com.cejjl.sales_points_system.models.produto.Produto;
import com.cejjl.sales_points_system.models.venda.Venda;
import com.cejjl.sales_points_system.repositories.funcionario.FuncionarioRepository;
import com.cejjl.sales_points_system.repositories.produto.ProdutoRepository;
import com.cejjl.sales_points_system.repositories.venda.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository repository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional
    public Venda adicionar(VendaRequest request) {
        if (request == null)
            throw new RuntimeException("Dados nulos.");

        Funcionario funcionario = funcionarioRepository.findById(request.funcionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        Produto produto = produtoRepository.findById(request.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        Venda venda = Venda.builder()
                .funcionario(funcionario)
                .produto(produto)
                .quantidade(request.quantidade())
                .build();

        return repository.save(venda);
    }

    @Transactional
    public List<Venda> listar() {
        return repository.findAll();
    }

    @Transactional
    public Venda buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com o ID: " + id));
    }

    @Transactional
    public Venda atualizar(UUID id, VendaRequest request) {
        Venda venda = buscarPorId(id);

        if (request.funcionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(request.funcionarioId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
            venda.setFuncionario(funcionario);
        }
        if (request.produtoId() != null) {
            Produto produto = produtoRepository.findById(request.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
            venda.setProduto(produto);
        }
        if (request.quantidade() != null && request.quantidade() > 0) {
            venda.setQuantidade(request.quantidade());
        }

        return repository.save(venda);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Venda inexistente." + id);
        }

        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(
                    "Erro de integridade: Esta venda possui registros vinculados e não pode ser removida.");
        }
    }

}