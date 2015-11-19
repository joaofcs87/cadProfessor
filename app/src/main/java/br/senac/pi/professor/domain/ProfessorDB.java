package br.senac.pi.professor.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 19/11/2015.
 */
public class ProfessorDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String NOME_BANCO = "professor.sqlite";
    private static final int VERSAO_BANCO = 1;

    public ProfessorDB(Context context){
        //context,nome do banco, factory, versao
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.d(TAG, "Criação da tabela professores...");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS professores(" +
                "_id integer primary key autoincrement," +
                "nome text," +
                "disciplina text)");
        Log.d(TAG,"Tabela criada com sucesso!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
