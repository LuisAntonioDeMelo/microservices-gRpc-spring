package br.com.loja.rest.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal peso;
    private String codigoBarra;
    private BigDecimal valor;

    @ManyToOne
    private CompraItem compraItem;

    @ManyToOne
    private Categoria categoria;

    private BigDecimal quantidadeDisponivel;
    private LocalDate prazoVencimento;
    private Boolean ativo;
    private BigDecimal ratingVenda;
    private LocalDateTime dataInclusao;

    @PrePersist
    public void prePersist(){
        this.dataInclusao = LocalDateTime.now();
    }

}
