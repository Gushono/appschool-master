package com.example.aluno.tccappschoolll;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {


    private final String tabelaNutricionista = "CREATE TABLE imagemNutricionista("+
                                                "imaCardapio BLOB PRIMARY KEY NOT NULL);";

    public BancoDeDados(Context context, int version)
    {
        super(context, "AppSchoolDatabase", null, version);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(tabelaNutricionista);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
