package br.com.loja.rest.service;

import br.com.loja.rest.repository.CompraItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraItemService {

    private final CompraItemRepository compraItemRepository;

    @Autowired
    public CompraItemService(CompraItemRepository compraItemRepository) {
        this.compraItemRepository = compraItemRepository;
    }




}
