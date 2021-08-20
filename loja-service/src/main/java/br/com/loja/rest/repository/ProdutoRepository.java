package br.com.loja.rest.repository;

import br.com.loja.rest.dto.ProdutoDTO;
import br.com.loja.rest.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query("SELECT new br.com.loja.rest.dto.ProdutoDTO(" +
            "p.id," +
            "p.categoria.id," +
            "p.nome," +
            "p.peso," +
            "p.codigoBarra," +
            "p.valor) " +
            "FROM Produto  p")
    public List<ProdutoDTO> findAllDTO();
}
