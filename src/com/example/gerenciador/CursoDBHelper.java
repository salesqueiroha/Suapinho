package com.example.gerenciador;

import com.example.entidades.Processo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CursoDBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "suapinhoDb.db";
	public static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "processo";
	
	private static final String COLUNA_UM = "titulo";
	private static final String COLUNA_DOIS = "numeroProcesso";
	private static final String COLUNA_TRES = "data";
	

	public CursoDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		StringBuilder create = new StringBuilder(" ");
		create.append(" CREATE TABLE ");
		create.append(Processo.class.getSimpleName());
		create.append("( titulo VARCHAR(70),");
		create.append(" numeroProcesso VARCHAR(30),");
		create.append(" data VARCHAR(30)");
		create.append(" );");
		
		db.execSQL(create.toString());
		
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if ((newVersion - oldVersion) > 2) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

}
