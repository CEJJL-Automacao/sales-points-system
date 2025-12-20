package com.cejjl.sales_points_system.services.funcionario;

import com.cejjl.sales_points_system.models.funcionario.Enums.StatusEnum;
import com.cejjl.sales_points_system.models.funcionario.Funcionario;
import com.cejjl.sales_points_system.repositories.funcionario.FuncionarioRepository;
import com.cejjl.sales_points_system.repositories.posto.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    @Transactional
    public Funcionario buscarPorId(UUID id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }

    @Transactional
    public Funcionario atualizar(UUID id, Funcionario dadosAtualizados) {
        Funcionario funcionarioExistente = buscarPorId(id);

        funcionarioExistente.setNome(dadosAtualizados.getNome());
        funcionarioExistente.setCargo(dadosAtualizados.getCargo());
        funcionarioExistente.setStatus(dadosAtualizados.getStatus());

        return funcionarioRepository.save(funcionarioExistente);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado com o ID: " + id);
        }

        try {
            funcionarioRepository.deleteById(id);
            funcionarioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível deletar: existem dados vinculados a este funcionário.");
        }
    }

}