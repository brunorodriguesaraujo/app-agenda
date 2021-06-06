package bruno.rodrigues.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import bruno.rodrigues.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> listaDeAlunos = new ArrayList<>();

    public void salvar(Aluno aluno){
        listaDeAlunos.add(aluno);
    }

    public List<Aluno> todos(){
        return new ArrayList<>(listaDeAlunos);
    }



}

