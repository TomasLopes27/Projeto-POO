import java.util.ArrayList;
import java.util.List;

public class Encomendas {

    private List<Artigos> artigos;
    private int dimensao;
    private double precoFinal;
    private double custoExpedicao;
    private String estado;

    public Encomendas(List<Artigos>artigos, int dimensao,double precoFinal,double custoExpedicao,String estado){
        this.artigos=artigos;
        this.dimensao=dimensao;
        this.precoFinal=precoFinal;
        this.custoExpedicao=custoExpedicao;
        this.estado=estado;
    }

    public Encomendas() {
        this.artigos = new ArrayList<>();
        this.dimensao = 0;
        this.precoFinal = 0;
        this.custoExpedicao = 0;
        this.estado = "pendente";
    }

    public Encomendas(Encomendas newEncomendas){
        this.artigos= newEncomendas.getArtigos();
        this.dimensao=newEncomendas.getDimensao();
        this.precoFinal=newEncomendas.getPrecoFinal();
        this.custoExpedicao=newEncomendas.getCustoExpedicao();
        this.estado=newEncomendas.getEstado();
    }

    public void adicionarArtigo(Artigos artigo) {
        this.artigos.add(artigo);
    }

    public void removerArtigo(Artigos artigo) {
        this.artigos.remove(artigo);
    }

    public void calcularPrecoFinal() {
        double precoTotal = 0;
        double taxa=0;
        for (Artigos artigo : artigos) {
            if (artigo.getestado()==100) {
                taxa += 0.5;
            } else if (artigo.getestado()!=100) {
                taxa += 0.25;
            }
            precoTotal += artigo.getprecoBase();
        }
        this.precoFinal = precoTotal + taxa + custoExpedicao;
    }


    public List<Artigos> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigos> artigos) {
        this.artigos = artigos;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }



    public double getCustoExpedicao() {
        return custoExpedicao;
    }

    public void setCustoExpedicao(double custoExpedicao) {
        this.custoExpedicao = custoExpedicao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean equals(Object newEncomenda){
        if(this==newEncomenda){
            return true;
        }
        if((newEncomenda==null)||(this.getClass()!=newEncomenda.getClass())){
            return false;
        }
        Encomendas newEncomendas1 =(Encomendas) newEncomenda;
        return newEncomendas1.getArtigos().equals(this.artigos);
    }

    public Encomendas clone(){
        return new Encomendas(this);
    }

    public String toString(){
        return "-O preço final é: " + this.precoFinal;
    }



}
