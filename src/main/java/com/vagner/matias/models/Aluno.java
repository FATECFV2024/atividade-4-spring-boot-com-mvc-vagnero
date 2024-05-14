package com.vagner.matias.models;

import java.io.Serializable;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity //Diz que a classe é uma entidade para persistir no banco de dados
@Data //Gera getters and setters, equals e hashcode
@AllArgsConstructor //Gera construtor com todos os parâmetros
@NoArgsConstructor //Gera um construtor sem parâmetros
public class Aluno implements Serializable { 

    // Transforma o objeto em bits para percorrer a rede 
    private static final long serialVersionUID = 1L;
    
    private

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String nome;

    Integer idade;

    String curso;

    Boolean matriculado;

    // @OneToOne(cascade = CascadeType.ALL) //COM ISSO PERMITE DELETAR E ATUALIZAR TABELAS ASSOCIADAS
    // @JoinColumn(name = "id_fk_Nota", referencedColumnName = "id") //Cria uma chave estrangeira referenciando o id de nota
    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Nota nota;

    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;
       
}
