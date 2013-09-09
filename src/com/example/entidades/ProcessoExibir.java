package com.example.entidades;

public class ProcessoExibir {
	private int id;
	private String numroProcesso;
	private String titulo;
	private String data;
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
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return titulo + "  " + data.substring(0, 10) ;
	}

	
	// return titulo + "  " + data.substring(0, 10) ;

}
