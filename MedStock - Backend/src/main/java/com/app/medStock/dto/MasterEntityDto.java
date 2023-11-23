package com.app.medStock.dto;

import java.time.LocalDateTime;

/**
 *
 * @author gustavo
 */
public class MasterEntityDto {
    private Long id;
    private LocalDateTime criado;
    private int versao;
    
    public MasterEntityDto() {
    }

    public MasterEntityDto(Long id, LocalDateTime criado, int versao) {
        this.id = id;
        this.criado = criado;
        this.versao = versao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCriado() {
        return criado;
    }

    public void setCriado(LocalDateTime criado) {
        this.criado = criado;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    
}
