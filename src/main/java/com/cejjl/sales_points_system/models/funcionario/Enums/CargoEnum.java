package com.cejjl.sales_points_system.models.funcionario.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CargoEnum {

    GERENTE("Gerente"),
    CHEFE_DE_PISTA("Chefe de Pista"),
    CAIXA("Caixa"),
    TROCADOR_DE_OLEO("Trocador de Óleo"),
    FRENTISTA("Frentista"),
    JOVEM_APRENDIZ("Jovem Aprendiz");

    private final String descricao;
}