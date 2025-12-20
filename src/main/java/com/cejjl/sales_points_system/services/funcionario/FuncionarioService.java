package com.cejjl.sales_points_system.services.funcionario;

import com.cejjl.sales_points_system.dtos.request.FuncionarioRequest;
import com.cejjl.sales_points_system.models.funcionario.Enums.StatusEnum;
import com.cejjl.sales_points_system.models.funcionario.Funcionario;
import com.cejjl.sales_points_system.models.posto.Posto;
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
    public Funcionario criar(FuncionarioRequest request) {
        if (request.postoId() == null) {
            throw new IllegalArgumentException("O funcionário precisa estar vinculado a um Posto.");
        }

        Posto posto = postoRepository.findById(request.postoId())
                .orElseThrow(() -> new IllegalArgumentException("Posto não encontrado com este ID."));

        Funcionario funcionario = new Funcionario();
        funcionario.setMatricula(request.matricula());
        funcionario.setNome(request.nome());
        funcionario.setCargo(request.cargo());
        funcionario.setStatus(request.status() != null ? request.status() : StatusEnum.ATIVO);
        funcionario.setPosto(posto);

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
    public Funcionario atualizar(UUID id, FuncionarioRequest request) {
        Funcionario funcionario = buscarPorId(id);

        if (request.nome() != null)
            funcionario.setNome(request.nome());
        if (request.cargo() != null)
            funcionario.setCargo(request.cargo());
        if (request.status() != null)
            funcionario.setStatus(request.status());

        return funcionarioRepository.save(funcionario);
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