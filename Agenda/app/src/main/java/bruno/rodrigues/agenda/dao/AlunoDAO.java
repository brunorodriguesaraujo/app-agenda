package bruno.rodrigues.agenda.dao;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import bruno.rodrigues.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> listaDeAlunos = new ArrayList<>();
    private static int contador = 1;

    public void salvar(Aluno aluno){
        aluno.setId(contador);
        listaDeAlunos.add(aluno);
        contador++;
    }

    public void editar(Aluno aluno){
        Aluno alunoEncontrado = encontraAluno(aluno);
        if(alunoEncontrado != null){
            int posicaoDoAluno = listaDeAlunos.indexOf(alunoEncontrado);
            listaDeAlunos.set(posicaoDoAluno, aluno);
            }

    }

    @Nullable
    private Aluno encontraAluno(Aluno aluno) {
        for (Aluno a:
             listaDeAlunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos(){
        return new ArrayList<>(listaDeAlunos);
    }


    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = encontraAluno(aluno);
        if(alunoDevolvido != null) {
            listaDeAlunos.remove(alunoDevolvido);
        }
    }
}

