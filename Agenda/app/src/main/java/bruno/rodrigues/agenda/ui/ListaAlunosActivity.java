package bruno.rodrigues.agenda.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bruno.rodrigues.agenda.R;

public class ListaAlunosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_lista_alunos);
       setTitle("Lista de alunos");
        List<String> alunos = new ArrayList<>(Arrays.asList("Bruno", "Juciara", "Mimo Bazin"));
        ListView listaDeAlunos = findViewById(R.id.lv_lista_de_alunos);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this , android.R.layout.simple_expandable_list_item_1, alunos));
    }
}
