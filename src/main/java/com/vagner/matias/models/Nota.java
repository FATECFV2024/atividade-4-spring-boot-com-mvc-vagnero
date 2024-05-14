package com.vagner.matias.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Nota implements Serializable { 

    // Transforma o objeto em bits para percorrer a rede 
    private static final long serialVersionUID = 1L;
    
    private

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Integer id;

    Double logica_e_Programacao;
    Double banco_de_Dados;
    Double desenv_Web;

    // @JsonIgnore
    // @OneToOne(mappedBy = "nota", cascade = CascadeType.ALL)
    // Aluno aluno;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private Aluno aluno;

    public Nota(Integer id, Double logica_e_Programacao, Double banco_de_Dados, Double desenv_Web) {
        this.id = id;
        this.logica_e_Programacao = logica_e_Programacao;
        this.banco_de_Dados = banco_de_Dados;
        this.desenv_Web = desenv_Web;
    }



    // Getters and Setters que serão personalizados no json
 
    @JsonProperty("Lógica e Programação")
    public Double getLogica_e_Programacao() {
        return logica_e_Programacao;
    }

    public void setLogica_e_Programacao(Double logica_e_Programacao) {
        this.logica_e_Programacao = logica_e_Programacao;
    }

    @JsonProperty("Banco de Dados")
    public Double getBanco_de_Dados() {
        return banco_de_Dados;
    }

    public void setBanco_de_Dados(Double banco_de_Dados) {
        this.banco_de_Dados = banco_de_Dados;
    }

    @JsonProperty("Desenvolvimento Web I")
    public Double getDesenv_Web() {
        return desenv_Web;
    }

    public void setDesenv_Web(Double desenv_Web) {
        this.desenv_Web = desenv_Web;
    }



 
       
}
