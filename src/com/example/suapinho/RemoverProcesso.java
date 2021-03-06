package com.example.suapinho;

import java.util.List;

import com.example.entidades.ProcessoBd;
import com.example.gerenciador.DbAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class RemoverProcesso extends Activity {

 private DbAdapter dBAdpater;
 private ArrayAdapter<ProcessoBd> adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remover_layout);
		this.dBAdpater = new DbAdapter(getApplicationContext());
		this.dBAdpater.open();

		listarDadosProcesso(this.dBAdpater.listarProcessos());
		int cont = 0;
		for (ProcessoBd p : this.dBAdpater.listarProcessos()) {
			cont++;
			Log.i(p.toString(), "esse � o processo que esta no banco!!!  "
					+ cont);
		}
		this.dBAdpater.close();
		
	}
	
	
	
	public void listarDadosProcesso(List<ProcessoBd> processos) {
		final List<ProcessoBd> temp = processos;
		
		
		Log.i("Exibindo lista de dados", "entrou na rotina");
		ListView lista = (ListView) findViewById(R.id.listView2);

		adapter = new ArrayAdapter<ProcessoBd>(this,
				android.R.layout.simple_list_item_checked, processos);

		lista.setAdapter(this.adapter);


		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				confirmacao();
				
			}
			
		});

	}
	
	public void confirmacao(){
	
		AlertDialog.Builder mensagemConfirmacao =  new AlertDialog.Builder(this);
		
		mensagemConfirmacao.setTitle("-Excluir processo-");
		mensagemConfirmacao.setMessage("Realmente deseja excluir o processo?");
		mensagemConfirmacao.setPositiveButton("OK", new DialogInterface.OnClickListener() {

		  public void onClick(DialogInterface arg0, int arg1) {
		 
			  Log.i("confirmacao", "esse comando exclui  ");
		  
		  }});
		mensagemConfirmacao.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
		       
		  public void onClick(DialogInterface arg0, int arg1) {
		 
			  Log.i("confirmacao", "esse comando cancela ");
			  
		  }});
		 mensagemConfirmacao.show();
	

	
	}
	
	public void excluirProcesso(String idProcesso){
		
		this.dBAdpater.excluirProcesso(idProcesso);
	}
	
}
