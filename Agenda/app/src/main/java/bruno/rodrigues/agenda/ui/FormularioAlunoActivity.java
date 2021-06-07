package bruno.rodrigues.agenda.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bruno.rodrigues.agenda.R;
import bruno.rodrigues.agenda.dao.AlunoDAO;
import bruno.rodrigues.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    AlunoDAO dao = new AlunoDAO();

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR);

        inicializacaoDosCampos();
        configuraBotaoSalvarAluno();
    }

    private void configuraBotaoSalvarAluno() {
        Button botaoSalvar = findViewById(R.id.formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno alunoCriado = criaAluno();
                salva(alunoCriado);

            }
        });
    }

    private void salva(Aluno alunoCriado) {
        dao.salvar(alunoCriado);
        finish();
    }

    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.et_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.et_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.et_formulario_aluno_email);
    }
}