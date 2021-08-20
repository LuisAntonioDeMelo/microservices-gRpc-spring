package br.com.loja.rest.service;

import br.com.loja.rest.model.Produto;
import br.com.loja.rest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto){
        // regras :::
        // gerar codigo ...


        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoSalvo;
    }
}
