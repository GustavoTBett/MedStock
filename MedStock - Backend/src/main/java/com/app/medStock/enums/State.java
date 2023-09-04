package com.app.medStock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author gustavo
 */
@Getter
@AllArgsConstructor
public enum State {
    AM("Amazonas", "AM", "Manaus"),
    AL("Alagoas", "AL", "Maceió"),
    AC("Acre", "AC", "Rio Branco"),
    AP("Amapá", "AP", "Macapá"),
    BA("Bahia", "BA", "Salvador"),
    PA("Pará", "PA", "Belém"),
    MT("Mato Grosso", "MT", "Cuiabá"),
    MG("Minas Gerais", "MG", "Belo Horizonte"),
    MS("Mato Grosso do Sul", "MS", "Campo Grande"),
    GO("Goiás", "GO", "Goiânia"),
    MA("Maranhão", "MA", "São Luí­s"),
    RS("Rio Grande do Sul", "RS", "Porto Alegre"),
    TO("Tocantins", "TO", "Palmas"),
    PI("Piauí­", "PI", "Teresina"),
    SP("São Paulo", "SP", "São Paulo"),
    RO("Rondônia", "RO", "Porto Velho"),
    RR("Roraima", "RR", "Boa Vista"),
    PR("Paraná", "PR", "Curitiba"),
    CE("Ceará", "CE", "Fortaleza"),
    PE("Pernambuco", "PE", "Recife"),
    SC("Santa Catarina", "SC", "Florianópolis"),
    PB("Paraí­ba", "PB", "João Pessoa"),
    RN("Rio Grande do Norte", "RN", "Natal"),
    ES("Espí­rito Santo", "ES", "Vitória"),
    RJ("Rio de Janeiro", "RJ", "Rio de Janeiro"),
    SE("Sergipe", "SE", "Aracaju"),
    DF("Distrito Federal", "DF", "Brasília");

    private final String nome;
    private final String sigla;
    private final String capital;
}
