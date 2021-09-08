package br.com.loja.rest.repository;

import br.com.loja.rest.model.CompraItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraItemRepository extends JpaRepository<CompraItem, Long> {


}
