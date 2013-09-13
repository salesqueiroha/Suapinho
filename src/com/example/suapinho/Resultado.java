package com.example.suapinho;

import java.io.IOException;


import com.example.entidades.Processo;
import com.example.entidades.ProcessoBd;
import com.example.entidades.Tramite;
import com.example.exceptions.DadosInvalidosException;
import com.example.gerenciador.CursoAdapter;
import com.example.gerenciador.DbAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Resultado extends Activity {
	private DbAdapter dbAdapter;
	private Processo processo;
	private Processo processoBanco;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultado_consulta_layout);
		
	
		this.dbAdapter = new DbAdapter(getApplicationContext());

		this.dbAdapter.open();

		// recebendo objeto vindo da consulta via URL
		this.processo = (Processo) getIntent().getSerializableExtra(
				"processoEnviar");
		// recebendo objeto vindo da consulta do banco

		this.processoBanco = (Processo) getIntent().getSerializableExtra(
				"processoEnviarBanco");

		if (this.processo != null)

		{
			// listarDadosProcessoNovo();
			preencherCabProcesso();
		}
		if (this.processoBanco != null)

		{
		for(Tramite t: this.processoBanco.getTramites() ){
			
			Log.i("Imprimindo tramites", t.getDestino());
			Log.i("Imprimindo tramites", t.getEnviadoEm());
			Log.i("Imprimindo tramites", t.getOrigem());
			Log.i("Imprimindo tramites", t.getRecebidoEm());
			
		}
			preencherCabProcessoDb();
			// listarDadosProcessoBanco();
		}
		
		
		// verifica se a consulta retornou um objeto valido
		if (this.processo != null) {
			Log.i("Exibindo lista de dados", "testenado insercao!!!");

			// consulta na base local a existencia do processo, caso exista
			// o
			// sistema nao salva
			if (this.dbAdapter.verificaProcesso(this.processo.getNumero())) {
				ProcessoBd processoBd = new ProcessoBd();
				processoBd.setNumroProcesso(this.processo.getNumero());
				processoBd.setTitulo(this.processo.getAssunto());
				processoBd.setData(this.processo.getDataCadastro());
				processoBd.setCpf(this.processo.getNumeroDocumento());
				processoBd.setSituacao(this.processo.getSituacao());

				salvarProcesso(processoBd);
				Log.i("Exibindo lista de dados",
						"inser'cao realizado com sucesso!!!");
			} else {
				Log.i("ESSE PROCESSO NAO FOI SALVO",
						"processo já existe na lista!!!");
			}

			this.dbAdapter.close();
		}

	}

	public void preencherCabProcessoDb() {
		
		
		TextView titulo = ((TextView)
				 findViewById(R.id.textViewTitulo)); 
		titulo.setText(this.processoBanco.getAssunto()); 
		
		TextView status = ((TextView)
				 findViewById(R.id.textViewStatus)); 
		status.setText(this.processoBanco.getSituacao()); 
		
				
	}
	
public void preencherCabProcesso() {
		
		
		TextView titulo = ((TextView)
				 findViewById(R.id.textViewTitulo)); 
		titulo.setText(this.processo.getAssunto()); 
		
		TextView status = ((TextView)
				 findViewById(R.id.textViewStatus)); 
		status.setText(this.processo.getSituacao()); 
		
				
	}

	public void listarDadosProcessoNovo() {

		ListView lista = (ListView) findViewById(R.id.listView1);

		String[] strings = new String[] { this.processo.getNumero(),
				this.processo.getAssunto(),
				this.processo.getPessoaInteressada(),
				this.processo.getSituacao(), this.processo.getDataCadastro() };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, strings);

		lista.setAdapter(adapter);

	}

	public void listarDadosProcessoBanco() {

		ListView lista = (ListView) findViewById(R.id.listView1);

		String[] strings = new String[] { this.processoBanco.getNumero(),
				this.processoBanco.getAssunto(),
				this.processoBanco.getPessoaInteressada(),
				this.processoBanco.getSituacao(),
				this.processoBanco.getDataCadastro() };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, strings);

		lista.setAdapter(adapter);

	}

	public void salvarProcesso(ProcessoBd processo) {
		this.dbAdapter.salvarProcesso(processo);
	}

}
