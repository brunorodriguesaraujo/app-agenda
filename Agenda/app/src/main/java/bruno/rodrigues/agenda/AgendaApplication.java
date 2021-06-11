package bruno.rodrigues.agenda;

import android.app.Application;

import bruno.rodrigues.agenda.dao.AlunoDAO;
import bruno.rodrigues.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoTeste();

    }

    private void criaAlunoTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salvar(new Aluno("Bruno", "83","bruno@gmail.com"));
        dao.salvar(new Aluno("Juciara", "81","juciara@gmail.com"));
    }
}
