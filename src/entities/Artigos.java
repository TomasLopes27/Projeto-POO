package entities;

import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class Artigos implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean novo;
    private int contador = 0;
    private double estado;
    private int nrDonos;
    private String descricao;
    private String marca;
    private double precoBase;
    private double desconto;
    private Transportadoras transp;

    public Artigos(){
        this.novo = true;
        this.estado = 100;
        this.nrDonos = 0;
        this.descricao = "";
        this.marca = "";
        this.precoBase = 0;
        this.desconto = 0;
        this.transp = null;
    }

    public Artigos(boolean novo,double estado, int nrDonos, String descricao, String marca, double precoBase, double desconto, Transportadoras transp){
        this.novo = novo;
        this.estado = estado;
        this.nrDonos = nrDonos;
        this.descricao = descricao;
        this.marca = marca;
        this.contador++;
        this.precoBase = precoBase;
        this.desconto = desconto/100;
        this.transp = transp;
    }

    public Artigos (Artigos artigo){
        this.novo = artigo.isnovo();
        this.estado = artigo.getestado();
        this.nrDonos = artigo.getnrDonos();
        this.descricao = artigo.getdescricao();
        this.marca = artigo.getmarca();
        this.precoBase = artigo.getprecoBase();
        this.desconto = artigo.getdesconto();
        this.transp = artigo.transp;
    }

    public double getprecoBase() {
		return this.precoBase;
	}

	public void setprecoBase(double precoBase) {
		this.precoBase = precoBase;
	}

	public double getdesconto() {
		return desconto;
	}

	public void setdesconto(double desconto) {
		this.desconto = desconto;
	}

	public void setnovo(boolean novo) {
        this.novo = novo;
    }

    public boolean isnovo() {
        return this.novo;
    }

    public double getestado() {
        return this.estado;
    }

    public void setestado(float estado) {
        this.estado = estado;
    }

    public int getnrDonos() {
        return this.nrDonos;
    }

    public void setnrDonos(int nrDonos) {
        this.nrDonos = nrDonos;
    }

    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getdescricao() {
        return this.descricao;
    }

    public String getmarca() {
        return this.marca;
    }

    public void setmarca(String marca) {
        this.marca = marca;
    }

	public Transportadoras getTransp() {
		return transp;
	}

	public void setTransp(Transportadoras transp) {
		this.transp = transp;
	}
	
	public void minimalRep() {
		System.out.println("- Marca: " + this.marca);
		System.out.println("- Descricao: " + this.descricao);
		System.out.print("- Preco: " + this.precoBase + "\n\n");
	}
	public abstract boolean equals(Object o);

    public abstract Artigos clone();

    public abstract String toString();



}
