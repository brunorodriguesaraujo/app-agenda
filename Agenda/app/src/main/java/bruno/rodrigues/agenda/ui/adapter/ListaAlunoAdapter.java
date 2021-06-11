package bruno.rodrigues.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bruno.rodrigues.agenda.R;
import bruno.rodrigues.agenda.model.Aluno;

public class ListaAlunoAdapter extends BaseAdapter {


    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = criaView(parent);
        Aluno alunoDevolvido = alunos.get(position);
        vincula(inflate, alunoDevolvido);
        return inflate;
    }

    private void vincula(View inflate, Aluno alunoDevolvido) {
        TextView nome = inflate.findViewById(R.id.tv_nome);
        nome.setText(alunoDevolvido.getNome());
        TextView telefone = inflate.findViewById(R.id.tv_telefone);
        telefone.setText(alunoDevolvido.getTelefone());
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_aluno, parent, false);
    }


    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
