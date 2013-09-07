package com.example.gerenciador;

import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Processo;
import com.example.entidades.ProcessoBd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {

	public static final String TABELA_PROCESSO = "PROCESSO";
	public static final String COLUNA_ID = "id";
	public static final String COLUNA_TITULO = "TITULO";
	public static final String COLUNA_NUMERO_PROCESSO = "NUMERO_PROCESSO";
	public static final String COLUNA_DATA = "DATA";
	public static final String COLUNA_CPF = "CPF";

	private static final String PROCESSO_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_PROCESSO + "  (" + COLUNA_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_TITULO + " TEXT NOT NULL, "
			+ COLUNA_NUMERO_PROCESSO + " TEXT NOT NULL," + COLUNA_DATA + " TEXT NOT NULL,"+ COLUNA_CPF
			+ " TEXT NOT NULL);";

	private static final String TAG = "DbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DB_NAME = "suapinhoDb";
	private static final int DATABASE_VERSION = 2;

	private final Context mCtx;

	public DbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public DbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
		mDb.close();
	}

	public void salvarProcesso(ProcessoBd processoDb) {
		ContentValues values = new ContentValues();
		values.put(COLUNA_ID, processoDb.getId());
		values.put(COLUNA_TITULO, processoDb.getTitulo());
		values.put(COLUNA_NUMERO_PROCESSO, processoDb.getNumroProcesso());
		values.put(COLUNA_DATA, processoDb.getData());
		values.put(COLUNA_CPF, processoDb.getCpf());

		 mDb.insert(TABELA_PROCESSO, null, values);
		mDb.close();
	}

	public Cursor consultarCategoria(long idCategoria) throws SQLException {

		Cursor mCursor = mDb.query(true, TABELA_PROCESSO,
				new String[] { COLUNA_NUMERO_PROCESSO }, COLUNA_NUMERO_PROCESSO
						+ "=?", new String[] { String.valueOf(idCategoria) },
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	public List<ProcessoBd> listarProcessos() {
		
		List<ProcessoBd> processosDb = new ArrayList<ProcessoBd>();

		Cursor c = mDb.query(TABELA_PROCESSO, null, null, null, null, null,
				null);

		while (c.moveToNext()) {
			ProcessoBd processoDb = new ProcessoBd();
			processoDb.setId(Integer.parseInt(c.getString(0)));
			processoDb.setTitulo(c.getString(1));
			processoDb.setNumroProcesso(c.getString(2));
			processoDb.setData(c.getString(3));
			processoDb.setCpf(c.getString(4));
			processosDb.add(processoDb);
		}

		return processosDb;
	}

	public boolean verificaProcesso(String numero) {

		Cursor mCursor = mDb.query(true, TABELA_PROCESSO, new String[] {
				COLUNA_TITULO, COLUNA_NUMERO_PROCESSO, COLUNA_DATA },
				COLUNA_NUMERO_PROCESSO + "=?", new String[] { numero }, null,
				null, null, null);
		if (mCursor != null) {
			return true;
		}else return false;

				
	}

	public boolean excluirProcesso(String idProcesso) {
		return mDb.delete(TABELA_PROCESSO, COLUNA_ID + "=?",
				new String[] { idProcesso }) > 0;

	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
		}

		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
			if (!db.isReadOnly()) {
				db.execSQL("PRAGMA foreign_keys=ON;");
			}
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(PROCESSO_CREATE_TABLE);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS " + TABELA_PROCESSO);
			onCreate(db);

		}

	}

}
