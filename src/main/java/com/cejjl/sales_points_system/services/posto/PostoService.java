package com.cejjl.sales_points_system.services.posto;

import com.cejjl.sales_points_system.models.posto.Posto;
import com.cejjl.sales_points_system.repositories.posto.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostoService {

    @Autowired
    private PostoRepository postoRepository;

    public Posto criar(Posto posto) {
        return postoRepository.save(posto);
    }
}