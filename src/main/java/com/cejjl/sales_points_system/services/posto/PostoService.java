package com.cejjl.sales_points_system.services.posto;

import com.cejjl.sales_points_system.models.posto.Posto;
import com.cejjl.sales_points_system.repositories.posto.PostoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostoService {

    @Autowired
    private PostoRepository postoRepository;

    @Transactional
    public Posto criar(Posto posto) {
        return postoRepository.save(posto);
    }

    public List<Posto> listarTodos() {
        return postoRepository.findAll();
    }

    public Posto buscarPorId(UUID id) {
        return postoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posto não cadastrado no sistema"));
    }

    public Posto atualizar(UUID id, Posto dadosAtualizados) {
        Posto postoExistente = postoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posto não Encontrado"));

        postoExistente.setNome(dadosAtualizados.getNome());
        postoExistente.setIsAtivo(dadosAtualizados.getIsAtivo());

        return postoRepository.save(postoExistente);
    }

    public void deletar (UUID id){
        if (postoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Posto não Encontrado");
        }

        postoRepository.deleteById(id);
    }

}