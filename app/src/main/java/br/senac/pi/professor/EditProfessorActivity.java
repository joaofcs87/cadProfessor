package br.senac.pi.professor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditProfessorActivity extends AppCompatActivity {
    /*Nessa Activity, a partir do id capturado na ListaProfessorActivity
    * Faz-se um casting de dois EditText (nome, disciplina) e tbm faz-se um casting de um TextView
    * que recebe o id (_id) do registro que será editado
    * Depois é feito o update a partir de uma chamada do objeto db, onde os 'SET values'
    * serão os valores que estiverem nos dois casting de EditText*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_professor);
    }
}
