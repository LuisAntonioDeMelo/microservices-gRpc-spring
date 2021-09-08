package br.com.loja.rest.model;

public enum StatusCompra {
    FINALIZADA(1,"Finalizada"),
    ANDAMENTO(2,"Andamento"),
    AGUARDANDO_PAGAMENTO(3," Aguardando Pagamento"),
    CANCELADA(4,"Cancelada");

    private Integer codigo;
    private String descricao;

    StatusCompra(Integer codigo,String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
