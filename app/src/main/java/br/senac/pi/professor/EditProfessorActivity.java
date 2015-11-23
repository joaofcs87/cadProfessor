package br.senac.pi.professor;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.senac.pi.professor.domain.ProfessorDB;

public class EditProfessorActivity extends AppCompatActivity {

    private ProfessorDB professorDB;
    private SQLiteDatabase db;
    private EditText edtProfessor, edtDisciplina;
    private TextView txtProfessor;
    private String id;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_professor);

        id = getIntent().getStringExtra("id");

        professorDB = new ProfessorDB(this);

        txtProfessor = (TextView) findViewById(R.id.txtIdProfessor);
        edtProfessor = (EditText) findViewById(R.id.edtNomeProfessor);
        edtDisciplina = (EditText) findViewById(R.id.edtDisciplinaProfessor);

        cursor =  loadProfessor(Integer.parseInt(id));

        txtProfessor.setText(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
        edtProfessor.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        edtDisciplina.setText(cursor.getString(cursor.getColumnIndexOrThrow("disciplina")));

        findViewById(R.id.btnEditarProfessor).setOnClickListener(editarProfessor());
    }

    private Cursor loadProfessor(int id){
        db = professorDB.getWritableDatabase();
        String[] campos = {"_id", "nome", "disciplina"};
        String whereArgs = String.valueOf(id);
        cursor = db.query("professores", campos, "_id = " + whereArgs, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    private View.OnClickListener editarProfessor(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = professorDB.getWritableDatabase();

                ContentValues values = new ContentValues();

                String whereArgs = id;


                values.put("nome", edtProfessor.getText().toString());
                values.put("disciplina", edtDisciplina.getText().toString());

                db.update("professores", values, "_id = " + whereArgs, null);
                Toast.makeText(getApplicationContext(),getString(R.string.edit_ok),Toast.LENGTH_LONG).show();
                db.close();
            }
        };
    }
}
