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

import static bruno.rodrigues.agenda.ui.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Editar aluno";
    AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        inicializacaoDosCampos();
        configuraBotaoSalvarAluno();

        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampo();
        }else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampo() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void configuraBotaoSalvarAluno() {
        Button botaoSalvar = findViewById(R.id.formulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aluno alunoCriado = criaAluno();
                //salva(alunoCriado);
                preencheAluno();
                if(aluno.temId()) {
                    dao.editar(aluno);
                }else{
                    dao.salvar(aluno);
                }
                finish();

            }
        });
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.et_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.et_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.et_formulario_aluno_email);
    }
}