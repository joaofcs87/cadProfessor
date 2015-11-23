package br.senac.pi.professor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.senac.pi.professor.domain.ProfessorDB;

public class ListaProfessorActivity extends AppCompatActivity {

    private CursorAdapter dataSource;
    private SQLiteDatabase database;
    private static final String campos[] = {"_id", "nome", "disciplina",};
    ListView listView;
    ProfessorDB professorDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);

        listView = (ListView) findViewById(R.id.listViewProfessor);
        professorDB = new ProfessorDB(this);
        database = professorDB.getWritableDatabase();
        findViewById(R.id.btnListarProfessorCadastrado).setOnClickListener(listarProfessor());
        listView.setOnItemClickListener(itemProfessor());
    }

    private View.OnClickListener listarProfessor() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor professores = database.query("professores", campos, null, null, null, null, null);
                if (professores.getCount() > 0) {

                    dataSource = new SimpleCursorAdapter(ListaProfessorActivity.this, R.layout.row_professor, professores, campos, new int[]{R.id.txtIdProfessor,R.id.txtNomeProfessor, R.id.txtDisciplina});
                    listView.setAdapter(dataSource);
                } else {
                    Toast.makeText(ListaProfessorActivity.this, getString(R.string.zero_professores), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private AdapterView.OnItemClickListener itemProfessor() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final long itemSelecionado = id;

                final int posicao = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaProfessorActivity.this);
                builder.setTitle(getString(R.string.opcoes));
                builder.setMessage(getString(R.string.msg_opcoes));
                builder.setPositiveButton(getString(R.string.editar_professor), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String codigo;

                        Cursor professor = database.query("professores", campos, null, null, null, null, null);
                        professor.moveToPosition(posicao);
                        codigo = professor.getString(professor.getColumnIndexOrThrow("_id"));

                        Intent intent = new Intent(getApplicationContext(), EditProfessorActivity.class);
                        intent.putExtra("id", codigo);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.setNegativeButton(getString(R.string.excluir_professor), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Log.i("professores", "ID do Professor: " + itemSelecionado);

                        String whereArgs = String.valueOf(itemSelecionado);

                        database.delete("professores", "_id = " + whereArgs, null);
                        Toast.makeText(getApplicationContext(),getString(R.string.delete_ok), Toast.LENGTH_LONG).show();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        };
    }
}
