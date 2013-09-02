package com.example.suapinho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.entidades.Processo;
import com.example.gerenciador.Gerenciador;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.AlertDialog;

public class Consulta extends Activity {
	private Gerenciador gerenciador = new Gerenciador();
	private String tempCapt = "";
	private ImageView imaView;
	private String url = "https://www.google.com/recaptcha/api/image?c=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_layout);
		captcha();
	}

	public void captcha() {
		this.imaView = (ImageView) findViewById(R.id.imageCaptcha);
		this.tempCapt = gerarCapatcha();
		loadImageFromURL(this.url + this.tempCapt, this.imaView);

	}

	public void voltarHome(View view) {
		Intent i = new Intent();
		i.setClass(this, MainActivity.class);
		startActivity(i);
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

	public String gerarCapatcha() {

		return gerenciador.gerar();
	}

	public void consultaDeProcessos(View view) throws IOException {

		Processo processoEnviar = new Processo();
		Intent itent = new Intent();
		EditText processoTemp = ((EditText) findViewById(R.id.editText1));
		EditText cpfTemp = ((EditText) findViewById(R.id.editText2));
		EditText idLetrasTemp = ((EditText) findViewById(R.id.editText3));

		// verificando os campos processo e cpf

		
		if ((!verificacao(processoTemp.getText().toString()))
				|| (!verificacao(cpfTemp.getText().toString()))) {
			AlertDialog.Builder alertaCampos = new AlertDialog.Builder(this);
			alertaCampos
					.setMessage("Informe apenas números nos campos Processo e Cpf, porfavor!");
			alertaCampos.show();

		} else if ((!numeroDigitosProcesso(processoTemp.getText().toString()))
				|| (!numeroDigitosCpf(cpfTemp.getText().toString()))) {
			AlertDialog.Builder alertaCamposDigitos = new AlertDialog.Builder(this);
			alertaCamposDigitos
					.setMessage("O Processo requer 17 números e o Cpf 11 verifique, porfavor!");
			alertaCamposDigitos.show();

		}

		else {

			// insercao de pontos e ifem no numero do processo
			StringBuilder processoFormatado = new StringBuilder(processoTemp
					.getText().toString());
			processoFormatado.insert(5, ".");
			processoFormatado.insert(12, ".");
			processoFormatado.insert(17, "-");

			// enviando o objeto para outra activy

			processoEnviar = consultarProcessoController(idLetrasTemp.getText()
					.toString(), processoFormatado.toString(), cpfTemp
					.getText().toString());
			processoEnviar.setNumeroDocumento(cpfTemp.getText().toString());
			itent.putExtra("processoEnviar", processoEnviar);
			itent.setClass(this, Resultado.class);
			startActivity(itent);
		}

	}

	public Processo consultarProcessoController(String idLetras,
			String processo, String cpf) throws IOException {

		return gerenciador.consultarProcesso(this.tempCapt, idLetras, processo,
				cpf);
	}

	public static boolean verificacao(String editText) {
		if (editText == null || editText.length() == 0)
			return false;

		for (int i = 0; i < editText.length(); i++) {

			// Se o digito for diferente de um digito, retorna falso.
			if (!Character.isDigit(editText.toString().charAt(i)))
				return false;
		}
		return true;

	}

	public static boolean numeroDigitosCpf(String editText) {
		if (editText.length() < 11)
			return false;
		return true;
	}

	public static boolean numeroDigitosProcesso(String editText) {
		if (editText.length() < 17)
			return false;
		return true;
	}

}
