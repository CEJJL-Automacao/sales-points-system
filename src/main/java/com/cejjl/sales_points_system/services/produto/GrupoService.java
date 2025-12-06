package com.cejjl.sales_points_system.services.produto;

import com.cejjl.sales_points_system.models.produto.Grupo;
import com.cejjl.sales_points_system.models.produto.Produto;
import com.cejjl.sales_points_system.repositories.produto.GrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GrupoService {

    private final GrupoRepository grupoRepository;

    public Grupo adicionarGrupo(Grupo grupo){
        try {
            return grupoRepository.save(grupo);
        }catch (Exception e){
            throw new RuntimeException("Erro ao tentar salvar grupo no banco.");
        }
    }

    public List<Grupo> buscarTodosGrupos(){
        return grupoRepository.findAll();
    }

    public Grupo buscarGrupoPorId(UUID id){
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
    }

    public Grupo atualizarGrupoPorId(UUID id, Grupo grupoAtualizado){

        Grupo grupo = buscarGrupoPorId(id);

        Grupo novoGrupo = Grupo.builder()
                .id(grupo.getId())
                .nome(grupoAtualizado.getNome() == null ? grupo.getNome() : grupoAtualizado.getNome())
                .pontos(grupoAtualizado.getPontos() == null ? grupo.getPontos() : grupoAtualizado.getPontos())
                .build();

        return grupoRepository.save(novoGrupo);
    }

    public void deletarGrupoPorId(UUID id){
        Grupo grupo = buscarGrupoPorId(id);
        grupoRepository.delete(grupo);
    }

}