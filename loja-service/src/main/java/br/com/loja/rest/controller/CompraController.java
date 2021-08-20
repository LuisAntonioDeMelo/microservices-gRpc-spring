package br.com.loja.rest.controller;

import br.com.loja.rest.dto.CompraItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compras")
public class CompraController {

    @PostMapping("/compra-item")
    public ResponseEntity<CompraItemDTO> salvarCompra(@RequestBody CompraItemDTO dto){
        // code template
        return ResponseEntity.ok(dto);
    }

}
