package com.example.entidades;

import java.io.Serializable;
import java.util.List;

public class Processo implements Serializable{

	
	 private String numero;
	    private String situacao;
	    private String assunto;
	    private String pessoaInteressada;
	    private String numeroDocumento;
	    private String dataCadastro;
	    private String tipoProcesso;
	    private String palavraChave;
	    private List<Tramite> tramites;
	    private String acoes;

	    public String getNumero() {
	        return numero;
	    }

	    public void setNumero(String numero) {
	        this.numero = numero;
	    }

	    public String getSituacao() {
	        return situacao;
	    }

	    public void setSituacao(String situacao) {
	        this.situacao = situacao;
	    }

	    public String getAssunto() {
	        return assunto;
	    }

	    public void setAssunto(String assunto) {
	        this.assunto = assunto;
	    }

	    public String getPessoaInteressada() {
	        return pessoaInteressada;
	    }

	    public void setPessoaInteressada(String pessoaInteressada) {
	        this.pessoaInteressada = pessoaInteressada;
	    }

	    public String getNumeroDocumento() {
	        return numeroDocumento;
	    }

	    public void setNumeroDocumento(String numeroDocumento) {
	        this.numeroDocumento = numeroDocumento;
	    }

	    public String getDataCadastro() {
	        return dataCadastro;
	    }

	    public void setDataCadastro(String dataCadastro) {
	        this.dataCadastro = dataCadastro;
	    }

	    public String getTipoProcesso() {
	        return tipoProcesso;
	    }

	    public void setTipoProcesso(String tipoProcesso) {
	        this.tipoProcesso = tipoProcesso;
	    }

	    public String getPalavraChave() {
	        return palavraChave;
	    }

	    public void setPalavraChave(String palavraChave) {
	        this.palavraChave = palavraChave;
	    }

	    public List<Tramite> getTramites() {
	        return tramites;
	    }

	    public void setTramites(List<Tramite> tramites) {
	        this.tramites = tramites;
	    }

	    public String getAcoes() {
	        return acoes;
	    }

	    public void setAcoes(String acoes) {
	        this.acoes = acoes;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 43 * hash + (this.numero != null ? this.numero.hashCode() : 0);
	        hash = 43 * hash + (this.situacao != null ? this.situacao.hashCode() : 0);
	        hash = 43 * hash + (this.assunto != null ? this.assunto.hashCode() : 0);
	        hash = 43 * hash + (this.pessoaInteressada != null ? this.pessoaInteressada.hashCode() : 0);
	        hash = 43 * hash + (this.numeroDocumento != null ? this.numeroDocumento.hashCode() : 0);
	        hash = 43 * hash + (this.dataCadastro != null ? this.dataCadastro.hashCode() : 0);
	        hash = 43 * hash + (this.tipoProcesso != null ? this.tipoProcesso.hashCode() : 0);
	        hash = 43 * hash + (this.palavraChave != null ? this.palavraChave.hashCode() : 0);
	        hash = 43 * hash + (this.tramites != null ? this.tramites.hashCode() : 0);
	        hash = 43 * hash + (this.acoes != null ? this.acoes.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final Processo other = (Processo) obj;
	        if ((this.numero == null) ? (other.numero != null) : !this.numero.equals(other.numero)) {
	            return false;
	        }
	        if ((this.situacao == null) ? (other.situacao != null) : !this.situacao.equals(other.situacao)) {
	            return false;
	        }
	        if ((this.assunto == null) ? (other.assunto != null) : !this.assunto.equals(other.assunto)) {
	            return false;
	        }
	        if ((this.pessoaInteressada == null) ? (other.pessoaInteressada != null) : !this.pessoaInteressada.equals(other.pessoaInteressada)) {
	            return false;
	        }
	        if ((this.numeroDocumento == null) ? (other.numeroDocumento != null) : !this.numeroDocumento.equals(other.numeroDocumento)) {
	            return false;
	        }
	        if ((this.dataCadastro == null) ? (other.dataCadastro != null) : !this.dataCadastro.equals(other.dataCadastro)) {
	            return false;
	        }
	        if ((this.tipoProcesso == null) ? (other.tipoProcesso != null) : !this.tipoProcesso.equals(other.tipoProcesso)) {
	            return false;
	        }
	        if ((this.palavraChave == null) ? (other.palavraChave != null) : !this.palavraChave.equals(other.palavraChave)) {
	            return false;
	        }
	        if (this.tramites != other.tramites && (this.tramites == null || !this.tramites.equals(other.tramites))) {
	            return false;
	        }
	        if ((this.acoes == null) ? (other.acoes != null) : !this.acoes.equals(other.acoes)) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "Processo{" + "numero=" + numero + ", situacao=" + situacao + ", assunto=" + assunto + ", pessoaInteressada=" + pessoaInteressada + ", numeroDocumento=" + numeroDocumento + ", dataCadastro=" + dataCadastro + ", tipoProcesso=" + tipoProcesso + ", palavraChave=" + palavraChave + ", tramites=" + tramites + ", acoes=" + acoes + '}';
	    }
}
