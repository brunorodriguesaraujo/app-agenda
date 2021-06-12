package bruno.rodrigues.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import bruno.rodrigues.agenda.dao.AlunoDAO;
import bruno.rodrigues.agenda.model.Aluno;
import bruno.rodrigues.agenda.ui.adapter.ListaAlunoAdapter;

public class ListaAlunosView {


    private final Context context;
    private final AlunoDAO dao;
    private final ListaAlunoAdapter adapter;

    public ListaAlunosView(Context context) {
        this.context = context;
        adapter = new ListaAlunoAdapter(this.context);
        dao = new AlunoDAO();
    }

    public void removeAlunoDialog(@NotNull final MenuItem item) {
        new AlertDialog.Builder(this.context)
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

    public void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    public void configuraAdapter(ListView listaDeAlunos) {

        listaDeAlunos.setAdapter(adapter);

    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

}
