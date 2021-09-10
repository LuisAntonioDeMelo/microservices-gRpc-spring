package br.com.loja.rest.dto;

import br.com.loja.rest.model.StatusCompra;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CompraItemDTO {

    private Long id;
    private Long idUser;
    private List<ProdutoDTO> produtos;
    private LocalDateTime dataCompra;
    private StatusCompra statusCompra;

}
