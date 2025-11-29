package com.cejjl.sales_points_system.models.funcionario;


import com.cejjl.sales_points_system.models.funcionario.Enums.CargoEnum;
import com.cejjl.sales_points_system.models.posto.Posto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private Long matricula;

    @Column(nullable = false)
    private String nome;

    @Value(value = "true")
    private boolean status;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime criado_em;

    @Column
    @UpdateTimestamp
    private LocalDateTime atualizado_em;

    @Column(nullable = false)
    private CargoEnum cargo;

    @OneToOne
    @JoinColumn(name = "id_posto", nullable = false)
    private Posto id_posto;
}
