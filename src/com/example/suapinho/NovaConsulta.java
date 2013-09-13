package com.example.suapinho;

import java.util.ArrayList;
import java.util.List;

import com.example.entidades.ProcessoBd;
import com.example.entidades.ProcessoExibir;
import com.example.gerenciador.DbAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
	private ArrayAdapter<ProcessoExibir> adapter = null;

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
		
		final List<ProcessoExibir> processosTemp = converteProcessoDb(processos);
		final List<ProcessoBd> temp = processos;

		Log.i("Exibindo lista de dados", "entrou na rotina");
		ListView lista = (ListView) findViewById(R.id.listView1);

		adapter = new ArrayAdapter<ProcessoExibir>(this,
				android.R.layout.simple_list_item_checked, processosTemp);

		lista.setAdapter(this.adapter);

		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ProcessoBd processoEnviar = temp.get(arg2);
				enviarProcesso(processoEnviar, arg1);
				//operacao(processoEnviar, arg1);
				
			}

		});

	}

	public void enviarProcesso(ProcessoBd processoBd, View arg1) {
		captcha(arg1);
		Intent itent = new Intent();
		itent.putExtra("processoEnviarVisualizar", processoBd);
		itent.setClass(this, MenuSuapinho.class);
		startActivity(itent);
	}
	
	public void captcha(View view) {
		Intent i = new Intent();
		i.setClass(this, Captcha.class);
		startActivity(i);
	}

	public void operacao(ProcessoBd processoBd, View view) {
		final ProcessoBd processo = processoBd;
		final View novaview = view;

		AlertDialog.Builder mensagemConfirmacao = new AlertDialog.Builder(this);
		mensagemConfirmacao.setTitle("Operacao");
		mensagemConfirmacao.setMessage("Escolha sua opção, por favor!");
		mensagemConfirmacao.setPositiveButton("Consultar",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {

						enviarProcesso(processo, novaview);

					}
				});
		mensagemConfirmacao.setNegativeButton("Excluir",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
						confirmacao(processo);
						Log.i("confirmacao", "esse comando exclui o processo ");

					}
				});
		mensagemConfirmacao.show();

	}

	public void confirmacao(ProcessoBd processoBd) {
		final ProcessoBd processoExclusao = processoBd;

		AlertDialog.Builder mensagemConfirmacaoExclusao = new AlertDialog.Builder(
				this);
		mensagemConfirmacaoExclusao.setTitle("Exclusão");
		mensagemConfirmacaoExclusao
				.setMessage("Confirma a exclusão do processo?");
		mensagemConfirmacaoExclusao.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {

						excluirProcesso(processoExclusao.getNumroProcesso());
						Log.i("confirmacao",
								"esse comando consulta o processo");

					}
				});
		mensagemConfirmacaoExclusao.setNegativeButton("CANCELAR",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {

						Log.i("confirmacao", "esse comando exclui o processo ");

					}
				});
		mensagemConfirmacaoExclusao.show();
	}

	public void excluirProcesso(String idProcesso) {
		this.dBAdpater = new DbAdapter(getApplicationContext());
		this.dBAdpater.open();
		this.dBAdpater.excluirProcesso(idProcesso);
		this.dBAdpater.close();
		Intent itent = new Intent();
		itent.setClass(this, NovaConsulta.class);
		startActivity(itent);
		
	}
	
	public List<ProcessoExibir> converteProcessoDb(List<ProcessoBd> processosBd){
		List<ProcessoExibir> processosExibir = new ArrayList<ProcessoExibir>();
		
		for(ProcessoBd p: processosBd){
			ProcessoExibir exibir = new ProcessoExibir();
			exibir.setData(p.getData());
			exibir.setTitulo(p.getTitulo());
			processosExibir.add(exibir);
		}
		
		return processosExibir;
	}

}
