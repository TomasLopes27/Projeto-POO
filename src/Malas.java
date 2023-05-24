public class Malas extends Artigos {
    private double altura;
    private double largura;
    private double comprimento;
    private boolean premium;
    private String material;
    private int ano;

    public Malas(){
        super();
        this.altura = 0;
        this.comprimento = 0;
        this.largura = 0;
        this.ano = 0;
        this.premium = false;
    }

    public Malas(boolean novo, float estado, int nrDonos, String descricao, String marca, double precoBase, float desconto, double altura, double comprimento, double largura, String material, int ano, boolean premium){
        super(novo,estado,nrDonos,descricao,marca,precoBase,desconto);
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.ano = ano;
        this.premium = premium;
    }

    public Malas(Malas malas){
        super(malas);
        this.altura = malas.altura;
        this.comprimento = malas.comprimento;
        this.largura = malas.largura;
        this.ano = malas.ano;
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

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String toString() {
        return "-Novo: " + this.isnovo()+"\n"+
                "-Estado: "+ this.getestado()+"\n"+
                "-Número de Donos: " + this.getnrDonos()+"\n"+
                "-Descrição: " + this.getdescricao()+"\n"+
                "-Marca: " + this.getmarca()+"\n"+
                "-Preço: " + this.getprecoBase()+"\n"+
                "-Desconto: "+ this.getdesconto()+"\n"+
                "-Altura: "+this.getAltura()+"\n"+
                "-Largura: "+this.getLargura()+"\n"+
                "-Comprimento: "+this.getComprimento()+"\n"+
                "-Material: "+this.getMaterial()+"\n"+
                "-Ano de fabrico: "+this.getAno()+"\n"+
                "-Premium: "+this.isPremium();
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
                this.getAno()==mala.getAno()&&
                this.isPremium()==mala.isPremium());
    }
    public Artigos clone() {
        return new Malas(this);
    }
}
