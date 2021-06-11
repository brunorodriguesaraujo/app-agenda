package bruno.rodrigues.agenda.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import bruno.rodrigues.agenda.R;
import bruno.rodrigues.agenda.dao.AlunoDAO;
import bruno.rodrigues.agenda.model.Aluno;
import bruno.rodrigues.agenda.ui.adapter.ListaAlunoAdapter;

import static bruno.rodrigues.agenda.ui.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de alunos";
    final AlunoDAO dao = new AlunoDAO();
    private ListaAlunoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);
        configuraBotaoAdicionarAluno();
        listaAlunos();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menu_remover) {
            removeAlunoDialog(item);

        }
        return super.onContextItemSelected(item);
    }

    private void removeAlunoDialog(@NotNull final MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que deseja remove-ló?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        removeAluno(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
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
        adapter.atualiza(dao.todos());
    }

    private void listaAlunos() {
        ListView listaDeAlunos = findViewById(R.id.lv_lista_de_alunos);
        configuraAdapter(listaDeAlunos);
        configuraListenerClickItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }


    private void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
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

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunoAdapter(this);
        listaDeAlunos.setAdapter(adapter);

    }

    private void abreFormularioEditaAluno(Aluno alunoEscolhido) {
        Intent mundandoParaFormulario = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        mundandoParaFormulario.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(mundandoParaFormulario);
    }
}
