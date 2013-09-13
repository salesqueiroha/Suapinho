package com.example.suapinho;

import com.example.entidades.ProcessoBd;
import com.example.gerenciador.DbAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MenuSuapinho extends Activity {
	private ProcessoBd processoBd;
	private DbAdapter dBAdpater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		this.dBAdpater = new DbAdapter(getApplicationContext());
		this.dBAdpater.open();

		this.processoBd = (ProcessoBd) getIntent().getSerializableExtra(
				"processoEnviarVisualizar");
		preencheCampos();
	}

	public void preencheCampos() {

		TextView titulo = ((TextView) findViewById(R.id.textViewDbTitulo));
		titulo.setText(this.processoBd.getTitulo());

		TextView numeroProcesso = ((TextView) findViewById(R.id.textViewDbNumero));
		numeroProcesso.setText(this.processoBd.getNumroProcesso());

		TextView dataProcesso = ((TextView) findViewById(R.id.textViewDbData));
		dataProcesso.setText(this.processoBd.getData());

		TextView statusProcesso = ((TextView) findViewById(R.id.textViewDbStatus));
		statusProcesso.setText(this.processoBd.getSituacao());

	}

	public void atualizar(View view) {
		Intent itent = new Intent();
		itent.putExtra("processoEnviarCaptcha", this.processoBd);
		itent.setClass(this, Captcha.class);
		startActivity(itent);
	}

	public void remover(View view) {
		confirmacao(this.processoBd);

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
						Log.i("confirmacao", "esse comando consulta o processo");

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

	public void excluirProcesso(String processoId) {

		this.dBAdpater.excluirProcesso(processoId);
		this.dBAdpater.close();
		AlertDialog.Builder confirmacaoExclusao = new AlertDialog.Builder(this);
		confirmacaoExclusao
				.setMessage("Processo excluido consucesso!");
		confirmacaoExclusao.show();
		Intent i = new Intent();
		i.setClass(this, NovaConsulta.class);
		startActivity(i);
	}

}
