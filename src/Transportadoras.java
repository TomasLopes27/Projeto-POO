public class Transportadoras {

    private double Imposto;
    private  double ValorBase;
    private double lucro;
    private double precoExpedicao;
    private boolean especializada;


    public Transportadoras(){
        this.Imposto=0;
        this.ValorBase=0;
        this.lucro=0;
        this.especializada=false;
        this.precoExpedicao=0;
    }

    public Transportadoras(double Imposto,double ValorBase,double lucro,boolean especializada,double precoExpedicao){
        this.Imposto=Imposto;
        this.ValorBase=ValorBase;
        this.lucro=lucro;
        this.especializada=especializada;
        this.precoExpedicao=precoExpedicao;
    }

    public Transportadoras(Transportadoras newTransportadoras){
        this.Imposto=newTransportadoras.getImposto();
        this.ValorBase=newTransportadoras.getValorBase();
        this.lucro=newTransportadoras.getLucro();
        this.especializada=newTransportadoras.isEspecializada();
        this.precoExpedicao= newTransportadoras.getPrecoExpedicao();

    }

    public double getImposto() {
        return Imposto;
    }

    public void setImposto(double imposto) {
        Imposto = imposto;
    }

    public double getValorBase() {
        return ValorBase;
    }

    public void setValorBase(double valorBase) {
        ValorBase = valorBase;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    public boolean isEspecializada() {
        return especializada;
    }

    public void setEspecializada(boolean especializada) {
        this.especializada = especializada;
    }

    public double getPrecoExpedicao() {
        return precoExpedicao;
    }

    public void setPrecoExpedicao(double precoExpedicao) {
        this.precoExpedicao = precoExpedicao;
    }

    public double PrecoExpedicao(int quantidade, boolean premium) {
        double fatorMultiplicativoImposto = 1 + Imposto;
        if(premium && especializada){
            return this.precoExpedicao=(ValorBase * (1 + lucro) * fatorMultiplicativoImposto) * 1.5;
        }
        else if (quantidade > 5) {
            fatorMultiplicativoImposto *= 1.2;
            return this.precoExpedicao=(ValorBase * (1 + lucro) * fatorMultiplicativoImposto) * 0.9;
        } else if (quantidade > 1) {
            return this.precoExpedicao=ValorBase * (1 + lucro) * fatorMultiplicativoImposto;
        } else {
            return this.precoExpedicao=ValorBase * (1 + lucro) * fatorMultiplicativoImposto * 0.8;
        }
    }

    public boolean equals(Object newTranportadora){
        if(this==newTranportadora){
            return true;
        }
        if((newTranportadora==null)||(this.getClass()!=newTranportadora.getClass())){
            return false;
        }
        Transportadoras newTransportadora1 =(Transportadoras) newTranportadora;
        return newTransportadora1.getImposto()==this.Imposto &&
                newTransportadora1.getLucro()==this.lucro &&
                newTransportadora1.getValorBase()==this.ValorBase &&
                newTransportadora1.isEspecializada()==this.especializada &&
                newTransportadora1.getPrecoExpedicao()==this.precoExpedicao;

    }

    public Transportadoras clone(){
        return new Transportadoras(this);
    }

    public String toString(){
        return "-O preço de Expedição é: " + this.precoExpedicao;
    }
}
