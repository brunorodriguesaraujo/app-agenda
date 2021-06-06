package bruno.rodrigues.agenda.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bruno.rodrigues.agenda.R;
import bruno.rodrigues.agenda.dao.AlunoDAO;
import bruno.rodrigues.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        AlunoDAO dao = new AlunoDAO();

        final EditText campoNome = findViewById(R.id.et_formulario_aluno_nome);
        final EditText campoTelefone = findViewById(R.id.et_formulario_aluno_telefone);
        final EditText campoEmail = findViewById(R.id.et_formulario_aluno_email);

        Button botaoSalvar = findViewById(R.id.formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();

                Aluno aluno = new Aluno(nome, telefone, email);
                dao.salvar(aluno);

                startActivity(new Intent(FormularioAlunoActivity.this, ListaAlunosActivity.class));

                finish();

            }
        });
    }
}