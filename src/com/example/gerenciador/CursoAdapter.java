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
import android.util.Log;

public class CursoAdapter {
	private SQLiteDatabase db = null;
	private CursoDBHelper cursoDBHelper = null;

	public CursoAdapter(Context context) {
		cursoDBHelper = new CursoDBHelper(context);
	}

	public long incluir(Processo processo) throws SQLException {
		Log.i("ate aqui entrou", "AQUI ESTA TUDO CERTO");
		this.db = cursoDBHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("titulo", processo.getAssunto());
		cv.put("numeroProcesso", processo.getNumero());
		cv.put("data", processo.getDataCadastro());
		long retorno = db.insert("processo", null, cv);

		db.close();
		Log.i(processo.toString(), "Registro criado com sucesso.");
		return retorno;

	}

	public Processo buscarPorNome(String numeroProcesso) {
		Processo processo;
		String[] columns = new String[] { "titulo", "numeroProcesso", "data" };
		String[] args = new String[] { numeroProcesso };
		Cursor c = db.query("processo", columns, "numeroProcesso = ?", args,
				null, null, "titulo");

		c.moveToFirst();
		processo = new Processo();
		processo.setAssunto(c.getString(0));
		processo.setNumero(c.getString(1));
		processo.setDataCadastro(c.getString(2));
		return processo;
	}

	public List<Processo> listarProcessos() {
		Processo processo = new Processo();

		List<Processo> processos = new ArrayList<Processo>();
		String[] columns = new String[] { "titulo", "numeroProcesso", "data" };
		Cursor c = db.query("processo", columns, "numeroProcesso = ?", null,
				null, null, "titulo");

		while (c.isAfterLast()) {
			processo.setAssunto(c.getString(0));
			processo.setNumero(c.getString(1));
			processo.setDataCadastro(c.getString(2));
			processos.add(processo);
		}
		c.close();
		return processos;
	}

	
	
	public boolean excluirProcesso(String numeroProcesso) {
		return db.delete("processo", "numeroProcesso " + "=?",
				new String[] { numeroProcesso }) > 0;

	}
}
