package com.example.suapinho;

import java.util.List;

import com.example.entidades.ProcessoBd;
import com.example.gerenciador.DbAdapter;

import android.app.Activity;
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
			Log.i(p.toString(), "esse é o processo que esta no banco!!!  "
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
				Log.i(arg0.toString(), "esse é valor do arq0");
				Log.i(arg1.toString(), "esse é valor do arq1");
				Log.i(String.valueOf(arg2), "esse é valor do arq2");
				Log.i(String.valueOf(arg3), "esse é valor do arq3");
			}
			
		});

	}
	
	public void removerProcesso(String idProcesso){
		this.dBAdpater.excluirProcesso(idProcesso);
	}
	
}
