package com.eduardo.estoque.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    @Column(nullable = false)
    private int quantidade;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "O preço não pode ser negativo")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(String nome, int quantidade, BigDecimal preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}