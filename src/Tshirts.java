public class Tshirts extends Artigos {
    private enum Tamanho {S,M,L,XL}
    private enum Padrao {liso,riscas,palmeiras}

    private Tamanho size;
    private Padrao pattern;


    public Tshirts (){
        super ();
        this.size = null;
        this.pattern = null;
    }

    public Tshirts(boolean novo,float estado, int nrDonos, String descricao, String marca, double precoBase, float desconto,Tamanho size, Padrao pattern){
        super(novo,estado,nrDonos,descricao,marca,precoBase,desconto);
        this.size = size;
        this.pattern = pattern;
    }

    public Tshirts(Tshirts tshirt){
        super(tshirt);
        this.size = tshirt.size;
        this.pattern = tshirt.pattern;
    }

    public void descontoUsado(Tshirts tshirt){
        if(!(this.pattern.equals(Padrao.liso))&&(tshirt.isnovo()==false)){
            tshirt.setprecoBase(tshirt.getprecoBase()*0.5);
        }
    }

    public Padrao getPattern() {
        return this.pattern;
    }

    public void setPattern(Padrao pattern) {
        this.pattern = pattern;
    }

    public Tamanho getSize() {
        return this.size;
    }

    public void setSize(Tamanho size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "-Novo: " + this.isnovo()+"\n"+
                "-Estado: "+ this.getestado()+"\n"+
                "-Número de Donos: " + this.getnrDonos()+"\n"+
                "-Descrição: " + this.getdescricao()+"\n"+
                "-Marca: " + this.getmarca()+"\n"+
                "-Preço: " + this.getprecoBase()+"\n"+
                "-Desconto: "+ this.getdesconto()+"\n"+
                "-Tamanho: "+ this.getSize()+"\n"+
                "-Padrão: "+this.getPattern()+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if((o==null)||(this.getClass()!=o.getClass())) return false;
        Tshirts tshirt = (Tshirts) o;
        return (super.isnovo()==tshirt.isnovo()&&
                super.getestado()==tshirt.getestado()&&
                super.getnrDonos()==tshirt.getnrDonos()&&
                super.getdescricao()==tshirt.getdescricao()&&
                super.getmarca()==tshirt.getmarca()&&
                super.getprecoBase()==tshirt.getprecoBase()&&
                super.getdesconto()==tshirt.getdesconto()&&
                this.getSize()==tshirt.getSize()&&
                this.getPattern()==tshirt.getPattern());
    }

    public Artigos clone() {
        return new Tshirts(this);
    }
}
