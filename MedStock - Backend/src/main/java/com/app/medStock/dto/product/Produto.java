package com.app.medStock.dto.product;

import com.app.medStock.dto.MasterEntityDto;
import com.app.medStock.model.Product;

/**
 *
 * @author gustavo
 */
public class Produto extends MasterEntityDto {
    private String nome;
    private String descricao;
    private Long codigo;
    private String categoria;
    private String fabricante;
    
    public Produto(Product product) {
        this.setId(product.getId());
        this.setCriado(product.getCreatedAt());
        this.setVersao(product.getVersion());
        this.nome = product.getName();
        this.descricao = product.getDescription();
        this.codigo = product.getCode();
        this.categoria = product.getCategory();
        this.fabricante = product.getProducer();
    }
}
