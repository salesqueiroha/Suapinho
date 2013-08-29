package com.example.suapinho;


import java.util.List;

import com.example.entidades.ProcessoBd;
import com.example.gerenciador.DbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;

public class NovaConsulta extends Activity {

	private DbAdapter dBAdpater;
	private ArrayAdapter<ProcessoBd> adapter = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nova_consulta_layout);
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
		ListView lista = (ListView) findViewById(R.id.listView1);

		adapter = new ArrayAdapter<ProcessoBd>(this,
				android.R.layout.simple_list_item_checked, processos);

		lista.setAdapter(this.adapter);


		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			captcha(arg1);
			
			ProcessoBd processoEnviar = temp.get(arg2);
			enviarProcesso(processoEnviar);
						
			Log.i( processoEnviar.getCpf(), "esse é CPf do elemento selecionado!!");
			Log.i( processoEnviar.getNumroProcesso(), "esse é PROCESSO do elemento selecionado!!");
			Log.i( processoEnviar.getData(), "esse é DATA do elemento selecionado!!");
			Log.i( processoEnviar.getTitulo(), "esse é TITULO do elemento selecionado!!");
			
			
			
			
			}
			
		});

	}
	
	public void enviarProcesso(ProcessoBd processoBd){
		Intent itent = new Intent();
		itent.putExtra("processoEnviarCaptcha", processoBd);
		itent.setClass(this, Captcha.class);
		startActivity(itent);
	}
	
	public void captcha(View view) {
		Intent i = new Intent();
		i.setClass(this, Captcha.class);
		startActivity(i);
	}

}
