package com.example.gerenciador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.R.integer;
import android.provider.OpenableColumns;
import android.util.Log;

import com.example.entidades.Processo;
import com.example.entidades.Tramite;


public class Gerenciador {

	private String nomeBanco = "bancoSuapinho";
	private SQLiteDatabase dbSuapinho = null;

	public String gerar() {
		URL url;
		String temp = "";
		try {

			url = new URL(
					"https://www.google.com/recaptcha/api/challenge?k=6LfC6N8SAAAAAE0F_SZyj5h9B7ho3tMShVi7MXpC");
			HttpURLConnection conection = (HttpURLConnection) url
					.openConnection();
			conection.setRequestMethod("GET");
			conection.setDoOutput(true);
			conection.setInstanceFollowRedirects(true);
			OutputStreamWriter writer = new OutputStreamWriter(
					conection.getOutputStream());
			StringBuffer newData = new StringBuffer(10000);

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conection.getInputStream()));
				String s = "";
				int cont = 0;
				while (null != ((s = br.readLine()))) {
					if (cont == 4) {
						temp += cont + s.toString() + "\n";
					}
					cont++;
					newData.append(s);
				}
				br.close();
			} catch (IOException e) {
			} finally {
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		temp = temp.substring(18, 203);
		return temp;

	}

	public Processo consultarProcesso(String tempCapt, String idLetras,
			String processo, String cpf) throws IOException
			 {

		URL url;
		HttpURLConnection conection;
		Processo processoR = new Processo();

		url = new URL("http://suap.ifpb.edu.br/protocolo/consulta_publica/");

		conection = (HttpURLConnection) url.openConnection();

		conection.setRequestMethod("POST");
		conection.setDoOutput(true);
		conection.setDoInput(true);
		conection.setInstanceFollowRedirects(true);
		conection.setUseCaches(true);

		// parametros de controle da requisição
		conection.setRequestProperty("Cookie",
				"csrftoken=09cf7fc81a409a4a9aebefed603a0612");
		conection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		conection.setRequestProperty("SOAPAction",
				"http://suap.ifpb.edu.br/protocolo/consulta_publica/");

		String parametros = URLEncoder.encode("csrfmiddlewaretoken", "UTF-8")
				+ "="
				+ URLEncoder
						.encode("09cf7fc81a409a4a9aebefed603a0612", "UTF-8");
		parametros += "&" + URLEncoder.encode("numero_processo", "UTF-8") + "="
				+ URLEncoder.encode(processo, "UTF-8");
		parametros += "&" + URLEncoder.encode("documento", "UTF-8") + "="
				+ URLEncoder.encode(cpf, "UTF-8");
		parametros += "&"
				+ URLEncoder.encode("recaptcha_challenge_field", "UTF-8") + "="
				+ URLEncoder.encode(tempCapt, "UTF-8");
		parametros += "&"
				+ URLEncoder.encode("recaptcha_response_field", "UTF-8") + "="
				+ URLEncoder.encode(idLetras, "UTF-8");

		OutputStreamWriter writer = new OutputStreamWriter(
				conection.getOutputStream());
		writer.write(parametros);
		writer.flush();

		if (conection.getResponseMessage().toString().equals("OK")) {
			Log.i(conection.getResponseMessage().toString(), "conexao OK");
		} else {
			Log.i(conection.getResponseMessage().toString(), "conexao faill"
					+ conection.getResponseCode());
		}

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(
					conection.getInputStream()));

			Log.i("", "Chegou aqui, inicio!!!");

			processoR = convertStringProcesso(br);

			Log.i("", "fim da execucao, processo !!!");

		} catch (Exception e) {
			System.err.println(e.getMessage());
			
		} finally {
			conection.disconnect();
		}

		return processoR;
	}

	public Processo convertStringProcesso(BufferedReader br)
			throws NumberFormatException, IOException {
		Processo processoResultado = new Processo();
		Tramite tramite = new Tramite();
		List<Tramite> tremites = new ArrayList<Tramite>();
		int contadorTramites = 0;
		boolean iniciaTramites = false;
		boolean finalizarTramites = false;
		boolean tabela = false;

		String s = "";

		int cont = 0;

		while (null != ((s = br.readLine())))
			
		{
			cont++;

			if (cont >= 160 && cont < 190) {

				switch (cont) {

				case 160:

					processoResultado.setNumero(s.substring(
							s.indexOf("<td>") + 4, s.indexOf("</td>")));
					break;
				case 164:
					processoResultado.setSituacao(s.substring(
							s.indexOf("<td>") + 4, s.indexOf("</td>")));
					break;
				case 168:
					processoResultado.setAssunto(s.substring(
							s.indexOf("<td>") + 4, s.indexOf("</td>")));
					break;
				case 172:
					processoResultado.setPessoaInteressada(s.substring(
							s.indexOf("<td>") + 4, s.indexOf("</td>")));
					break;
				case 180:
					processoResultado.setDataCadastro(s.substring(
							s.indexOf("<td>") + 4, s.indexOf("</td>")));
					break;
				case 184:
					processoResultado.setTipoProcesso(s.substring(
							s.indexOf("<td>") + 4, s.indexOf("</td>")));
					break;
				case 188:
					processoResultado.setPalavraChave(s.substring(
							s.indexOf("<td>") + 4, s.indexOf("</td>")));
					break;
				}
			}// fim do preenchimento do cabeçalho
			else if (cont > 192 && finalizarTramites == false) {

				if (s.trim().equals("<tbody>")) {
					iniciaTramites = true;// inicia o corpo da tabela
				} else if (s.trim().equals("</tbody>")) {
					finalizarTramites = true;
				}
				if (iniciaTramites == true) {
					if (s.trim().equals("<tr>")) {
						tabela = true;// inicia a tabela de elementos
					} else if (s.trim().equals("</tr>")) {
						tabela = false;// finaliza a tabela de elementos
					}
					if (tabela == true && !s.trim().equals("")
							&& !s.trim().equals("<tr>")) {
						if (s.contains("class=\"processo-resultado\"")) {
							// processoResultado.setSituacao(s.substring(s.indexOf("<td colspan=\"6\" class=\"processo-resultado\">")
							// + 40,s.indexOf("</td>")));
							processoResultado
									.setAcoes(s.substring(
											s.indexOf("<td colspan=\"6\" class=\"processo-resultado\">") + 43,
											s.indexOf("</td>")));
							contadorTramites = 10;
						}
						switch (contadorTramites) {
						case 0:
							tramite = new Tramite();
							tramite.setNumero(Integer.parseInt(s.substring(
									s.indexOf("<td>") + 4, s.indexOf("</td>"))));
							contadorTramites++;
							break;
						case 1:
							tramite.setOrigem(s.substring(
									s.indexOf("<td>") + 4, s.indexOf("</td>")));
							contadorTramites++;
							break;
						case 2:
							tramite.setEnviadoEm(s.substring(
									s.indexOf("<td>") + 4, s.indexOf("</td>")));
							contadorTramites++;
							break;
						case 3:
							contadorTramites++;
							break;
						case 4:
							tramite.setDestino(s.substring(
									s.indexOf("<td>") + 4, s.indexOf("</td>")));
							contadorTramites++;
							break;
						case 5:
							tramite.setRecebidoEm(s.substring(
									s.indexOf("<td>") + 4, s.indexOf("</td>")));
							tremites.add(tramite);
							contadorTramites = 0;
							break;
						}
					}
				}
			}

			processoResultado.setTramites(tremites);
		}
		br.close();

		return processoResultado;
	}


    public boolean verificaErroCpfCnpj(BufferedReader br) throws IOException {
        String temp = "";
        String s = "";
        int cont = 0;
        while (null != ((s = br.readLine()))) {
            cont++;
            if (cont == 169) {
                temp = s.substring(s.indexOf("<ul class=\"errorlist\"><li>") + 26, s.indexOf("<ul class=\"errorlist\"><li>") + 43).trim();
            }
        }
        if (temp.equals("CPF/CNPJ inválido")) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean verificaErroCapctha(BufferedReader br) throws IOException {
        String temp = "";
        String s = "";
        int cont = 0;
        while (null != ((s = br.readLine()))) {
            cont++;
            if (cont == 169) {
                temp = s.substring(s.indexOf("<ul class=\"errorlist\"><li>") + 26, s.indexOf("<ul class=\"errorlist\"><li>") + 49).trim();
            }
        }
        if (temp.equals("Informe um valor válido")) {
            return false;
        } else {
            return true;
        }
    }

}
