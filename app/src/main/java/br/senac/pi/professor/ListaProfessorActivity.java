package br.senac.pi.professor;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import br.senac.pi.professor.domain.ProfessorDB;

public class ListaProfessorActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ProfessorDB professorDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);

        professorDB = new ProfessorDB(this);

        findViewById(R.id.btnListarProfessorCadastrado).setOnClickListener(listarProfessores());
    }

    private View.OnClickListener listarProfessores(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListView listProfessor = (ListView) findViewById(R.id.listViewProfessor);

                /*Nesse listView é exibido todos os professores cadastrados
                * Cada linha (registro) mostrado ao ser clicado é exibido um AlertDialog,
                * onde tem-se duas opções: PositiveButton(Editar) ou NegativeButton(Excluir)
                * -Se for clicado em Editar: Implementa-se uma Intent para uma outra Activity de Editar
                * (EditProfessor), sendo enviado o id do registro
                * -Se for clicado em Excluir: Nesta Activity mesmo é feita a instrução de Excluir,
                 * usando a propria instancia do ProfessorDB, também tendo como parâmetro o id do registro
                 * (_id)*/

            }
        };
    }
}

