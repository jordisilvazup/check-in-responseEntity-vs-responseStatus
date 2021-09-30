package com.example.resposeentitiyvsresponsestatus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Pessoa() {
    }



    public String getNome() {
        return nome;
    }

}
