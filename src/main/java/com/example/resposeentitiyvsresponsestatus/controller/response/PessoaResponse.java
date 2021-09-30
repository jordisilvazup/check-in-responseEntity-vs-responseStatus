package com.example.resposeentitiyvsresponsestatus.controller.response;

import com.example.resposeentitiyvsresponsestatus.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PessoaResponse {
    @JsonProperty
    private String nome;

    public PessoaResponse(Pessoa pessoa) {
        this.nome= pessoa.getNome();
    }
}
