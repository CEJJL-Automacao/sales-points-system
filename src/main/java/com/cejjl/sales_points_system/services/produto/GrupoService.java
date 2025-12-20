package com.cejjl.sales_points_system.services.produto;

import com.cejjl.sales_points_system.dtos.request.GrupoRequest;
import com.cejjl.sales_points_system.models.produto.Grupo;
import com.cejjl.sales_points_system.repositories.produto.GrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;

    @Transactional
    public Grupo adicionarGrupo(GrupoRequest request) {
        Grupo grupo = new Grupo();
        grupo.setNome(request.nome());
        grupo.setPontos(request.pontos());
        return grupoRepository.save(grupo);
    }

    @Transactional
    public List<Grupo> buscarTodosGrupos() {
        return grupoRepository.findAll();
    }

    @Transactional
    public Grupo buscarGrupoPorId(UUID id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
    }

    @Transactional
    public Grupo atualizarGrupoPorId(UUID id, GrupoRequest request) {
        Grupo grupo = buscarGrupoPorId(id);
        if (request.nome() != null)
            grupo.setNome(request.nome());
        if (request.pontos() != null)
            grupo.setPontos(request.pontos());
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void deletarGrupoPorId(UUID id) {
        if (grupoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Grupo não encontrado com o ID: " + id);
        }

        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível deletar o grupo: existem registros vinculados a ele.");
        }
    }
}