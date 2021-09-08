package br.com.loja.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private Long idCategoria;
    private String nome;
    private BigDecimal peso;
    private String codigoBarra;
    private BigDecimal preçoUnidade;
}
