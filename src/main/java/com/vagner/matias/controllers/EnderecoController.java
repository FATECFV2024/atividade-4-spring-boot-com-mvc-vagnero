package com.vagner.matias.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
import com.vagner.matias.services.AlunoService;
import com.vagner.matias.services.EnderecoService;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {
    
    @Autowired
    EnderecoService enderecoService;

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Endereco>>  findAll(){
        List<Endereco> listEndereco = enderecoService.findAll();
        return ResponseEntity.ok().body(listEndereco);
    }

    // A requisição vai aceitar um ID dentro do URL
    @GetMapping(value = "/{id}") 
    public ResponseEntity<Endereco> findById(@PathVariable Integer id){
        Endereco obj = enderecoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Endereco> insert(@RequestBody Endereco obj, @PathVariable Integer id){
        Aluno aluno = alunoService.findById(id);
        Endereco endereco = obj;
        endereco.setAluno(aluno);
        endereco = enderecoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(endereco); //Código 201
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Integer id, @RequestBody Endereco endereco) {

        endereco = enderecoService.update(id, endereco);
        return ResponseEntity.ok().body(endereco);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws NotFoundException {
         enderecoService.delete(id);
        return ResponseEntity.ok().body("Endereço deletado com sucesso!");
    }

    







}
