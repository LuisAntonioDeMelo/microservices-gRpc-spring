package br.com.loja.rest.model;

import br.com.loja.rest.dto.ProdutoDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class CompraItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "compraItem",cascade = CascadeType.ALL)
    private List<Produto> produtos;
    private Long idUser;
    private LocalDateTime dataCompra;
    private StatusCompra statusCompra;
}
