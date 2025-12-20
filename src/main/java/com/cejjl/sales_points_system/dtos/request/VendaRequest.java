package com.cejjl.sales_points_system.dtos.request;

import java.util.UUID;

public record VendaRequest(UUID funcionarioId, UUID produtoId, Integer quantidade) {
}
