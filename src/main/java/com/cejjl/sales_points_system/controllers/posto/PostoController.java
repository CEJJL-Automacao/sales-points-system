package com.cejjl.sales_points_system.controllers.posto;

import com.cejjl.sales_points_system.models.posto.Posto;
import com.cejjl.sales_points_system.services.posto.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postos")
public class PostoController {

    @Autowired
    private PostoService postoService;

    @PostMapping
    public ResponseEntity<Posto> criar(@RequestBody Posto posto) {
        Posto novoPosto = postoService.criar(posto);

        return ResponseEntity.ok(novoPosto);
    }
}