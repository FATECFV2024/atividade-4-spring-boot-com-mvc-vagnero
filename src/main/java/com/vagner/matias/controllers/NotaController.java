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
import com.vagner.matias.models.Nota;
import com.vagner.matias.services.AlunoService;
import com.vagner.matias.services.NotaService;

@RestController
@RequestMapping(value = "/nota")
public class NotaController {
    
    @Autowired
    NotaService notaService;

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Nota>>  findAll(){
        List<Nota> listNota = notaService.findAll();
        return ResponseEntity.ok().body(listNota);
    }

    // A requisição vai aceitar um ID dentro do URL
    @GetMapping(value = "/{id}") 
    public ResponseEntity<Nota> findById(@PathVariable Integer id){
        Nota obj = notaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Nota> insert(@RequestBody Nota obj, @PathVariable Integer id){
        Aluno aluno = alunoService.findById(id);
        Nota nota = obj;
        nota.setAluno(aluno);
        nota = notaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(nota); //Código 201
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Nota> update(@PathVariable Integer id, @RequestBody Nota nota) {

        nota = notaService.update(id, nota);
        return ResponseEntity.ok().body(nota);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws NotFoundException {
         notaService.delete(id);
        return ResponseEntity.ok().body("Notas deletadas com sucesso!");
    }


    







}
