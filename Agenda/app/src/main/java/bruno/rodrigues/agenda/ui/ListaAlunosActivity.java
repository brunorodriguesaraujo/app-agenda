package bruno.rodrigues.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import bruno.rodrigues.agenda.R;
import bruno.rodrigues.agenda.dao.AlunoDAO;
import bruno.rodrigues.agenda.model.Aluno;

import static bruno.rodrigues.agenda.ui.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de alunos";
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);
        configuraBotaoAdicionarAluno();

    }

    private void configuraBotaoAdicionarAluno() {
        FloatingActionButton botaoNovoAluno = fabNovoAluno();
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abreFormularioAluno();

            }
        });
    }

    private void abreFormularioAluno() {
        startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class));
    }

    private FloatingActionButton fabNovoAluno() {
        return findViewById(R.id.fab_novo_aluno);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunos();

    }

    private void listaAlunos() {
        ListView listaDeAlunos = findViewById(R.id.lv_lista_de_alunos);
        List<Aluno> alunos = dao.todos();
        configuraAdapter(listaDeAlunos, alunos);
        configuraListenerClickItem(listaDeAlunos);

        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                dao.remove(alunoEscolhido);
                return true;
            }
        });
    }

    private void configuraListenerClickItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                abreFormularioEditaAluno(alunoEscolhido);
            }
        });
    }

    private void configuraAdapter(ListView listaDeAlunos, List<Aluno> alunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                alunos));
    }

    private void abreFormularioEditaAluno(Aluno alunoEscolhido) {
        Intent mundandoParaFormulario = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        mundandoParaFormulario.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(mundandoParaFormulario);
    }
}
