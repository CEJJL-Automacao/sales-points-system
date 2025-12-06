package com.cejjl.sales_points_system.models.funcionario;


import com.cejjl.sales_points_system.models.funcionario.Enums.CargoEnum;
import com.cejjl.sales_points_system.models.funcionario.Enums.StatusEnum;
import com.cejjl.sales_points_system.models.posto.Posto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Long matricula;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CargoEnum cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_posto", nullable = false)
    private Posto posto;
}
