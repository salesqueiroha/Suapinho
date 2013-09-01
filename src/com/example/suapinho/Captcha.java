package com.example.suapinho;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.entidades.Processo;
import com.example.entidades.ProcessoBd;
import com.example.exceptions.DadosInvalidosException;
import com.example.gerenciador.Gerenciador;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Captcha extends Activity {
	private Gerenciador gerenciador = new Gerenciador();
	private String tempCapt = "";
	private ImageView imaView;
	private String url = "https://www.google.com/recaptcha/api/image?c=";
	private ProcessoBd processoBd;
	private Processo processo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.captcha_layout);
		captcha();
		// recebe o objeto
		this.processoBd = (ProcessoBd) getIntent().getSerializableExtra(
				"processoEnviarCaptcha");

	}

	public void voltarHome(View view) {
		Intent i = new Intent();
		i.setClass(this, MainActivity.class);
		startActivity(i);
	}

	public void captcha() {
		this.imaView = (ImageView) findViewById(R.id.imageCaptchaNova);
		this.tempCapt = gerarCapatcha();
		loadImageFromURL(this.url + this.tempCapt, this.imaView);

	}

	public String gerarCapatcha() {

		return gerenciador.gerar();
	}

	public boolean loadImageFromURL(String fileUrl, ImageView iv) {
		try {

			URL myFileUrl = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();

			InputStream is = conn.getInputStream();
			iv.setImageBitmap(BitmapFactory.decodeStream(is));

			return true;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public void consultaDeProcessos(View view) throws IOException {
		Processo processoEnviarBanco = new Processo();
		Intent itent = new Intent();

		EditText idLetrasTemp = ((EditText) findViewById(R.id.editText4));

		// enviando o objeto para outra activy

			
		processoEnviarBanco = consultarProcessoController(idLetrasTemp
				.getText().toString(), this.processoBd.getNumroProcesso(),
				this.processoBd.getCpf());

		Log.i(processoEnviarBanco.getNumero(), "NUMERO DO PROCESSO!!!");
		Log.i(processoEnviarBanco.getNumeroDocumento(), "NUMERO DO CPF!!!");
		Log.i(processoEnviarBanco.getAssunto(), "TITULO !!!");

		itent.putExtra("processoEnviarBanco", processoEnviarBanco);

		itent.setClass(this, Resultado.class);

		startActivity(itent);

	}

	public Processo consultarProcessoController(String idLetras,
			String processo, String cpf) throws IOException {

		return gerenciador.consultarProcesso(this.tempCapt, idLetras, processo,
				cpf);
	}
	
	

}
