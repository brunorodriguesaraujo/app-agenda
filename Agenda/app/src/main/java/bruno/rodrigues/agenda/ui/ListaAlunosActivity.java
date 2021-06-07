package bruno.rodrigues.agenda.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import bruno.rodrigues.agenda.R;
import bruno.rodrigues.agenda.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
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
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                dao.todos()));
    }
}
