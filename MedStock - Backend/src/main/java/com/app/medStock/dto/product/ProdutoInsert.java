package com.app.medStock.dto.product;

import com.app.medStock.model.Product;
import lombok.Getter;

/**
 *
 * @author gustavo
 */
@Getter
public class ProdutoInsert {
    private String nome;
    private String descricao;
    private Long codigo;
    private String categoria;
    private String fabricante;
}
