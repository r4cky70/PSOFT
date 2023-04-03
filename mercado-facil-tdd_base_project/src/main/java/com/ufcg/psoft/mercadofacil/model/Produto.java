package com.ufcg.psoft.mercadofacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table
public class Produto {
    @JsonProperty("id")t
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JsonProperty("nome")
    @Column(nullable = false)
    private String nome;
    @JsonProperty("preco")
    @Column(nullable = false)
    private double preco;
    @JsonProperty("codigoBarra")
    @Column(nullable = false)
    private String codigoBarra;
    @JsonProperty("fabricante")
    @Column(nullable = false)
    private String fabricante;
}

