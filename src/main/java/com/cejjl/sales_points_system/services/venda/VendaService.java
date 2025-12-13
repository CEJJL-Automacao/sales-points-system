package com.cejjl.sales_points_system.services.venda;

import com.cejjl.sales_points_system.models.venda.Venda;
import com.cejjl.sales_points_system.repositories.venda.VendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository repository;

    @Transactional
    public Venda adicionar(Venda venda) {
        if (venda == null) throw new RuntimeException("Dados nulos.");

        Venda vendaParaSalvar = Venda.builder()
                .funcionario(venda.getFuncionario())
                .produto(venda.getProduto())
                .quantidade(venda.getQuantidade())
                .build();
        return repository.save(vendaParaSalvar);
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
    public Venda atualizar(UUID id, Venda dadosNovos) {
        Venda vendaExistente = buscarPorId(id);

        Venda vendaAtualizada = Venda.builder()
                .id(vendaExistente.getId())
                .funcionario(dadosNovos.getFuncionario() != null ? dadosNovos.getFuncionario() : vendaExistente.getFuncionario())
                .produto(dadosNovos.getProduto() != null ? dadosNovos.getProduto() : vendaExistente.getProduto())
                .quantidade(dadosNovos.getQuantidade() > 0 ? dadosNovos.getQuantidade() : vendaExistente.getQuantidade())
                .build();

        return repository.save(vendaAtualizada);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar: Venda inexistente.");
        }
        repository.deleteById(id);
    }
}