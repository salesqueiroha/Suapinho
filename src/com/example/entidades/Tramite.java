package com.example.entidades;

import java.io.Serializable;

public class Tramite implements Serializable {

	private int numero;
    private String origem;
    private String enviadoEm;
    private String destino;
    private String recebidoEm;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getEnviadoEm() {
        return enviadoEm;
    }

    public void setEnviadoEm(String enviadoEm) {
        this.enviadoEm = enviadoEm;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRecebidoEm() {
        return recebidoEm;
    }

    public void setRecebidoEm(String recebidoEm) {
        this.recebidoEm = recebidoEm;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.numero;
        hash = 97 * hash + (this.origem != null ? this.origem.hashCode() : 0);
        hash = 97 * hash + (this.enviadoEm != null ? this.enviadoEm.hashCode() : 0);
        hash = 97 * hash + (this.destino != null ? this.destino.hashCode() : 0);
        hash = 97 * hash + (this.recebidoEm != null ? this.recebidoEm.hashCode() : 0);
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
        final Tramite other = (Tramite) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if ((this.origem == null) ? (other.origem != null) : !this.origem.equals(other.origem)) {
            return false;
        }
        if ((this.enviadoEm == null) ? (other.enviadoEm != null) : !this.enviadoEm.equals(other.enviadoEm)) {
            return false;
        }
        if ((this.destino == null) ? (other.destino != null) : !this.destino.equals(other.destino)) {
            return false;
        }
        if ((this.recebidoEm == null) ? (other.recebidoEm != null) : !this.recebidoEm.equals(other.recebidoEm)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tramites{" + "numero=" + numero + ", origem=" + origem + ", enviadoEm=" + enviadoEm + ", destino=" + destino + ", recebidoEm=" + recebidoEm + '}';
    }
    
	
}
