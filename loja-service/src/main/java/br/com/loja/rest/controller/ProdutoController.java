package br.com.loja.rest.controller;

import br.com.loja.rest.dto.ProdutoDTO;
import br.com.loja.rest.model.Produto;
import br.com.loja.rest.repository.ProdutoRepository;
import br.com.loja.rest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getall(){
        List<ProdutoDTO> produtos = produtoRepository.findAllDTO();
        return produtos.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok(produtos);
    }

    @GetMapping("completo")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto){
        Produto produtoSaved = produtoService.salvarProduto(produto);
        return ResponseEntity.ok(produtoSaved);
    }

}
