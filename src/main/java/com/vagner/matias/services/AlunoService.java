package com.vagner.matias.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vagner.matias.models.Aluno;
import com.vagner.matias.models.Endereco;
import com.vagner.matias.models.Nota;
import com.vagner.matias.repositories.AlunoRepository;

@Component
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

  
    public Aluno insert(Aluno obj) {


        return alunoRepository.save(obj);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.get();
    }

    
    public void delete(Integer id) {
        alunoRepository.deleteById(id);
    }

    public Aluno update(Integer id, Aluno obj) {
        Aluno aluno = alunoRepository.getReferenceById(id);
        updateData(aluno, obj);
        return alunoRepository.save(aluno);
    }


    private void updateData(Aluno aluno, Aluno obj){
        aluno.setNome(obj.getNome());
        aluno.setIdade(obj.getIdade());
        aluno.setCurso(obj.getCurso());
        aluno.setMatriculado(obj.getMatriculado());

        //Associando nota e endereco ao aluno atualizado, se não o valor fica null
        Endereco endereco = aluno.getEndereco();
        if (endereco != null){
            endereco.setAluno(aluno);
        }
        Nota nota = aluno.getNota();
        if (nota != null){
            nota.setAluno(aluno);
        }
    }


}
