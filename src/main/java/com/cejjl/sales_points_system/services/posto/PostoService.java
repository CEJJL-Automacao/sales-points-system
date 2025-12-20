package com.cejjl.sales_points_system.services.posto;

import com.cejjl.sales_points_system.dtos.request.PostoRequest;
import com.cejjl.sales_points_system.models.funcionario.Enums.StatusEnum;
import com.cejjl.sales_points_system.models.posto.Posto;
import com.cejjl.sales_points_system.repositories.posto.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostoService {

    @Autowired
    private PostoRepository postoRepository;

    @Transactional
    public Posto criar(PostoRequest request) {
        Posto posto = new Posto();
        posto.setNome(request.nome());
        posto.setIsAtivo(request.isAtivo() != null ? request.isAtivo() : StatusEnum.ATIVO);
        return postoRepository.save(posto);
    }

    @Transactional
    public List<Posto> listarTodos() {
        return postoRepository.findAll();
    }

    @Transactional
    public Posto buscarPorId(UUID id) {
        return postoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posto não cadastrado no sistema"));
    }

    @Transactional
    public Posto atualizar(UUID id, PostoRequest request) {
        Posto posto = postoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posto não Encontrado"));

        if (request.nome() != null)
            posto.setNome(request.nome());
        if (request.isAtivo() != null)
            posto.setIsAtivo(request.isAtivo());

        return postoRepository.save(posto);
    }

    @Transactional
    public void deletar(UUID id) {
        if (postoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Posto não encontrado com o ID: " + id);
        }

        try {
            postoRepository.deleteById(id);
            postoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível deletar o posto: existem registros vinculados a ele.");
        }
    }

}