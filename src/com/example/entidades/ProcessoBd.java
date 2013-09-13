package com.example.entidades;

import java.io.Serializable;

public class ProcessoBd implements Serializable {
	private int id;
	private String numroProcesso;
	private String titulo;
	private String data;
	private String situacao;
	private String cpf;

	public String getNumroProcesso() {
		return numroProcesso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNumroProcesso(String numroProcesso) {
		this.numroProcesso = numroProcesso;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getData() {
		return data.substring(0, 10);
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "ProcessoBd [id=" + id + ", numroProcesso=" + numroProcesso
				+ ", titulo=" + titulo + ", data=" + data.substring(0, 10) + ", situacao="
				+ situacao + ", cpf=" + cpf + "]";
	}


}
