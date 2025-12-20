package com.cejjl.sales_points_system.dtos.request;

import java.util.UUID;

public record ProdutoRequest(String nome, UUID grupoId) {
}
