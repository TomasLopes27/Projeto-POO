package entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Malas extends Artigos implements Serializable {

	private static final long serialVersionUID = 11L;
	private double altura;
    private double largura;
    private double comprimento;
    private boolean premium;
    private String material;
    private long data;

    public Malas(){
        super();
        this.altura = 0;
        this.comprimento = 0;
        this.largura = 0;
        this.data = 0;
        this.premium = false;
    }

    public Malas(boolean novo, double estado, int nrDonos, String descricao, String marca, double precoBase, double desconto,
    				double altura, double comprimento, double largura, String material, long data, boolean premium, Transportadoras transp){
    	
        super(novo,estado,nrDonos,descricao,marca,precoBase,desconto,transp);
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.data = data;
        this.premium = premium;
        this.material = material;
    }

    public Malas(Malas malas){
        super(malas);
        this.altura = malas.altura;
        this.comprimento = malas.comprimento;
        this.largura = malas.largura;
        this.data = malas.data;
        this.premium = malas.premium;
    }

    public boolean isPremium() {
        return this.premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getAltura() {
        return this.altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getComprimento() {
        return this.comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getLargura() {
        return this.largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public long getData() {
        return this.data;
    }

    public void setAno(int data) {
        this.data = data;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return "-Novo: " + this.isnovo() + "\n" +
                "-Estado: " + this.getestado() + "\n" +
                "-Número de Donos: " + this.getnrDonos() + "\n" +
                "-Descrição: " + this.getdescricao() + "\n" +
                "-Marca: " + this.getmarca() + "\n" +
                "-Preço: " + decimalFormat.format(precoFinal()) + "\n" +
                "-Desconto: " + this.getdesconto() + "\n" +
                "-Altura: " + this.getAltura() + "\n" +
                "-Largura: " + this.getLargura() + "\n" +
                "-Comprimento: " + this.getComprimento() + "\n" +
                "-Material: " + this.getMaterial() + "\n" +
                "-Ano de fabrico: " + this.getData() + "\n" +
                "-Premium: " + this.isPremium();
    }


    public boolean equals(Object o) {
        if(this == o) return true;
        if((o==null)||(this.getClass()!=o.getClass())) return false;
        Malas mala = (Malas) o;
        return (super.isnovo()==mala.isnovo()&&
                super.getestado()==mala.getestado()&&
                super.getnrDonos()==mala.getnrDonos()&&
                super.getdescricao()==mala.getdescricao()&&
                super.getmarca()==mala.getmarca()&&
                super.getprecoBase()==mala.getprecoBase()&&
                super.getdesconto()==mala.getdesconto()&&
                this.getAltura()==mala.getAltura()&&
                this.getLargura()==mala.getLargura()&&
                this.getComprimento()==mala.getComprimento()&&
                this.getMaterial()==mala.getmarca()&&
                this.getData()==mala.getData()&&
                this.isPremium()==mala.isPremium());
    }
    public Artigos clone() {
        return new Malas(this);
    }
    
    public double precoFinal() {
        int ano = LocalDateTime.now().getYear();
        double precoBase = getprecoBase();
        double precoFinal = precoBase;

        if (isPremium()) {
            precoFinal += precoBase * ((ano - getData()) / 10.0);
        } else {
        	precoFinal -= precoBase * (1.0 / (getAltura() * getComprimento() * getLargura()));
        }

        return precoFinal;
    }
}
