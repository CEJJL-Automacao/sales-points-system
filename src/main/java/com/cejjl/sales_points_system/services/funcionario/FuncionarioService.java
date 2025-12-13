package com.cejjl.sales_points_system.services.funcionario;

import com.cejjl.sales_points_system.models.funcionario.Enums.StatusEnum;
import com.cejjl.sales_points_system.models.funcionario.Funcionario;
import com.cejjl.sales_points_system.models.posto.Posto;
import com.cejjl.sales_points_system.repositories.funcionario.FuncionarioRepository;
import com.cejjl.sales_points_system.repositories.posto.PostoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PostoRepository postoRepository;

    @Transactional
    public Funcionario criar(Funcionario funcionario) {
        if (funcionario.getPosto() == null || funcionario.getPosto().getId() == null) {
            throw new IllegalArgumentException("O funcionário precisa estar vinculado a um Posto.");
        }

        var postoReal = postoRepository.findById(funcionario.getPosto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Posto não encontrado com este ID."));

        funcionario.setPosto(postoReal);

        if (funcionario.getStatus() == null) {
            funcionario.setStatus(StatusEnum.ATIVO);
        }

        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(UUID id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }

    @Transactional
    public Funcionario atualizar(UUID id, Funcionario dadosAtualizados) {
        Funcionario funcionarioExistente = buscarPorId(id);

        Funcionario novoFuncionario = Funcionario.builder()
                .id(funcionarioExistente.getId())
                .matricula(dadosAtualizados.getMatricula() == null ? funcionarioExistente.getMatricula() : dadosAtualizados.getMatricula())
                .nome(dadosAtualizados.getNome() == null ? funcionarioExistente.getNome() : dadosAtualizados.getNome())
                .status(dadosAtualizados.getStatus() == null ? funcionarioExistente.getStatus() : dadosAtualizados.getStatus())
                .criadoEm(funcionarioExistente.getCriadoEm())
                .cargo(dadosAtualizados.getCargo() == null ? funcionarioExistente.getCargo() : dadosAtualizados.getCargo())
                .posto(dadosAtualizados.getPosto() == null ? funcionarioExistente.getPosto() : dadosAtualizados.getPosto())
                .build();

        return funcionarioRepository.save(novoFuncionario);
    }

    public void deletar(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado para deletar.");
        }
        funcionarioRepository.deleteById(id);
    }

}