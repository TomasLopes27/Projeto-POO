public class Sapatilhas extends Artigos {

    private float tamanho;
    private boolean atacadores;
    private String cor;
    private int data;
    private boolean premium;
    private float preco; // calcular o preço se está usada... É preciso fazer e formula da sapatilha premium

    public Sapatilhas(){
        super();
        this.tamanho = 0;
        this.atacadores = false;
        this.cor = "";
        this.data = 0;
        this.premium = false;
    }

    public Sapatilhas(boolean novo,float estado, int nrDonos, String descricao, String marca, double precoBase, float desconto, float tamanho, boolean atacadores, String cor,int data, boolean premium){
        super(novo, estado, nrDonos, descricao, marca, precoBase, desconto);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.data = data;
        this.premium = premium;
    }

    public Sapatilhas(Sapatilhas sapatilha){
        super (sapatilha);
        this.tamanho = sapatilha.tamanho;
        this.atacadores = sapatilha.atacadores;
        this.cor = sapatilha.cor;
        this.data = sapatilha.data;
        this.premium = sapatilha.premium;
    }

/*    public double precoUsada (Sapatilhas sapatilhas){
        double precobase = getprecoBase();
        if(sapatilhas.isnovo()==false) {
            precobase = (precobase/getnrDonos()*(getestado())/5);
        }
        if (sapatilhas.getPremium()==true){
            precobase = precobase;//ACABAR ISTO, FALTA POR AS DATAS, COMPARAR DATA DE LANCAMENTO COM DATA DE HOJE
        }
        return precobase;
    }*/
    public boolean getAtacadores() {
        return this.atacadores;
    }

    public void setAtacadores(boolean Atacadores){
        this.atacadores = Atacadores;
    }

    public float getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean getPremium() {
        return this.premium;
    }

    public void setPremium(boolean Premium){
        this.premium = Premium;
    }

    public String toString() {
        return "-Novo: " + this.isnovo()+"\n"+
                "-Estado: "+ this.getestado()+"\n"+
                "-Número de Donos: " + this.getnrDonos()+"\n"+
                "-Descrição: " + this.getdescricao()+"\n"+
                "-Marca: " + this.getmarca()+"\n"+
                "-Preço: " + this.getprecoBase()+"\n"+
                "-Desconto: "+ this.getdesconto()+"\n"+
                "-Tamanho: "+this.getTamanho()+"\n"+
                "-Atacadores: "+this.getAtacadores()+"\n"+
                "-Cor: "+this.getCor()+"\n"+
                "-Data de Lançamento: "+this.getData()+"\n"+
                "-Premium: "+ this.getPremium();
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if((o==null)||(this.getClass()!=o.getClass())) return false;
        Sapatilhas s = (Sapatilhas) o;
        return (super.isnovo()==s.isnovo()&&
                super.getestado()==s.getestado()&&
                super.getnrDonos()==s.getnrDonos()&&
                super.getdescricao()==s.getdescricao()&&
                super.getmarca()==s.getmarca()&&
                super.getprecoBase()==s.getprecoBase()&&
                super.getdesconto()==s.getdesconto()&&
                this.getTamanho()==s.getTamanho()&&
                this.getAtacadores()==s.getAtacadores()&&
                this.getCor()==s.getCor()&&
                this.getData()==s.getData()&&
                this.getPremium()==s.getPremium());
    }

    public Artigos clone() {
        return new Sapatilhas(this);
    }
}
