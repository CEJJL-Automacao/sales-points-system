package com.cejjl.sales_points_system.services.produto.dto;

import java.util.UUID;

public record ProdutoDtoRequest(
        String nome,
        UUID grupoId
) {
}
