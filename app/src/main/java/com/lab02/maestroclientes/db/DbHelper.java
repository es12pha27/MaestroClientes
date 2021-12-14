package com.lab02.maestroclientes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="negocios";
    public static final String TABLE_ZONA="zona";
    public static final String TABLE_TIPOCLIENTE="tipocliente";
    public static final String TABLE_CLIENTE="cliente";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase  sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTE + "(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "ruc TEXT NOT NULL," +
                "codigozona INTEGER ," +
                "codigotipocliente INTEGER ," +
                "estadoRegistro TEXT NOT NULL," +
                "CONSTRAINT Relationship3 FOREIGN KEY (codigozona) REFERENCES zona (codigo)," +
                " CONSTRAINT Relationship4 FOREIGN KEY (codigotipocliente) REFERENCES tipocliente (codigo))");

        sqLiteDatabase.execSQL("CREATE INDEX IX_Relationship1 ON cliente(codigo)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TIPOCLIENTE + "(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "estadoRegistro TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE INDEX IX_Relationship2 ON cliente(codigo)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ZONA + "(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "estadoRegistro TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ZONA);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TIPOCLIENTE);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTE);
        onCreate(sqLiteDatabase);
    }
}
