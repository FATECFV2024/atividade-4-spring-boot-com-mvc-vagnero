package com.vagner.matias.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vagner.matias.models.Aluno;
import com.vagner.matias.models.Endereco;
import com.vagner.matias.models.Nota;
import com.vagner.matias.services.AlunoService;
import com.vagner.matias.services.EnderecoService;
import com.vagner.matias.services.NotaService;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {
    
    @Autowired
    AlunoService alunoService;

    @Autowired
    EnderecoService enderecoService;
   
    @Autowired
    NotaService notaService;

    @GetMapping
    public ResponseEntity<List<Aluno>>  findAll(){
        List<Aluno> listAluno = alunoService.findAll();
        return ResponseEntity.ok().body(listAluno);
    }

    // A requisição vai aceitar um ID dentro do URL
    @GetMapping(value = "/{id}") 
    public ResponseEntity<Aluno> findById(@PathVariable Integer id){
        Aluno obj = alunoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Aluno> insert(@RequestBody Aluno obj){
        Aluno aluno = alunoService.insert(obj);


        Endereco endereco = obj.getEndereco();
        if (endereco != null){
            endereco.setAluno(aluno);
            enderecoService.insert(endereco);
        }
 

        Nota nota = obj.getNota();
        if(nota != null){
            nota.setAluno(aluno);
            notaService.insert(nota);
        }
     

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(aluno); //Código 201
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody Aluno aluno) {
        //Pega o json de nota e endereco no corpo da requisição
        Endereco endereco = aluno.getEndereco();
        Nota nota = aluno.getNota();

        //Atualiza somente o aluno
        aluno = alunoService.update(id, aluno);


        //Caso o json tenha endereco, será atualizado o endereco também
        if (endereco != null){
            System.out.println(aluno.getEndereco().getId());
            enderecoService.update(aluno.getEndereco().getId(), endereco);
        }
 

        //Caso o json tenha nota, será atualizado nota também
        if(nota != null){
            System.out.println(aluno.getNota().getId());
            notaService.update(aluno.getNota().getId(),nota);
        }
     
        return ResponseEntity.ok().body(aluno);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
         alunoService.delete(id);
        return ResponseEntity.ok().body("Aluno deletado com sucesso!");
    }

    







}
