package com.vagner.matias.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity //Diz que a classe é uma entidade para persistir no banco de dados
@Data //Gera getters and setters, equals e hashcode
@AllArgsConstructor //Gera construtor com todos os parâmetros
@NoArgsConstructor //Gera um construtor sem parâmetros
public class Endereco implements Serializable { 

    // Transforma o objeto em bits para percorrer a rede 
    private static final long serialVersionUID = 1L;
    
    private

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Integer id;

    String rua;

    Integer numero;

    String cidade;

    String estado;

    String cep;

    @JsonIgnore
     @OneToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;



       
}
