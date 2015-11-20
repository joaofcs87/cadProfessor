package br.senac.pi.professor;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.senac.pi.professor.domain.ProfessorDB;

public class MainActivity extends AppCompatActivity {

    ProfessorDB professorDB;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        professorDB = new ProfessorDB(this);

        findViewById(R.id.btnCadastrarProfessor).setOnClickListener(cadastrarProfessor());
        findViewById(R.id.btnListarProfessor).setOnClickListener(listarProfessor());
    }

    private View.OnClickListener cadastrarProfessor() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = professorDB.getWritableDatabase();

                EditText edtNomeProfessor = (EditText) findViewById(R.id.edtNomeProfessor);
                EditText edtDisciplinaProfessor = (EditText) findViewById(R.id.edtDisciplinaProfessor);

                ContentValues values = new ContentValues();

                values.put("nome",edtNomeProfessor.getText().toString());
                values.put("disciplina",edtDisciplinaProfessor.getText().toString());

                long id = database.insert("professores",null,values);

                if (id != 0){
                    Toast.makeText(getApplicationContext(),getString(R.string.cad_ok),Toast.LENGTH_LONG).show();

                    edtNomeProfessor.setText("");
                    edtDisciplinaProfessor.setText("");
                    edtNomeProfessor.requestFocus();
                }else {
                    Toast.makeText(getApplicationContext(),getString(R.string.cad_erro),Toast.LENGTH_LONG).show();
                }

            }
        };
    }

    private View.OnClickListener listarProfessor(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListaProfessorActivity.class);
                startActivity(intent);
            }
        };
    }

}
