package entities;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;


public class Transportadoras implements Serializable {

	private static final long serialVersionUID = 4L;
	private String nome;
    private double lucro;
    private boolean especializada;
    private int prazoHoras;
    private int prazoExpedicao;
    private ArrayList<Double> precoExpedicao;
    private ArrayList<Double> precoExpedicaoPremium;

    public Transportadoras() {
        this.lucro=0;
        this.especializada=false;
        this.precoExpedicao = new ArrayList<>();
        this.precoExpedicaoPremium = new ArrayList<>();
        this.nome = null;
        this.prazoHoras = 0;
        this.prazoExpedicao=0;
    }



    // Transportadoras ctt = new Transportadoras( ctt, 0.20, true, (valorbase0*0.20*(Imposto+1), valorbase1*0.20*(Imposto+3), valorbase2*0.20*(Imposto+1)),(valorbase0*0.20*2*(Imposto+1), valorbase1*0.20*2*(Imposto+3), valorbase2*0.20*2*(Imposto+1)) )
    public Transportadoras(String nome, double lucro, boolean especializada, int prazoHoras,int prazoExpedicao,
                           double preco0, double preco1, double preco2, double precoP0, double precoP1, double precoP2){
        this.precoExpedicao = new ArrayList<>();
        this.precoExpedicaoPremium = new ArrayList<>();
        this.lucro=lucro/100;
        this.especializada=especializada;
        this.precoExpedicao.add(0, preco0);
        this.precoExpedicao.add(1, preco1);
        this.precoExpedicao.add(2, preco2);
        this.precoExpedicaoPremium.add(0, precoP0);
        this.precoExpedicaoPremium.add(1, precoP1);
        this.precoExpedicaoPremium.add(2, precoP2);
        this.prazoHoras = prazoHoras;
        this.nome = nome;
        this.prazoExpedicao=prazoExpedicao;
    }

    public Transportadoras(Transportadoras newTransportadoras){
        this.lucro=newTransportadoras.getLucro();
        this.especializada=newTransportadoras.isEspecializada();
        this.nome = newTransportadoras.getNome();
        this.precoExpedicao = new ArrayList<>(newTransportadoras.precoExpedicao);
        this.precoExpedicaoPremium = new ArrayList<Double>(newTransportadoras.precoExpedicaoPremium);
        this.precoExpedicaoPremium = newTransportadoras.precoExpedicaoPremium;
        this.prazoHoras = newTransportadoras.getPrazoHoras();
        this.prazoExpedicao=newTransportadoras.getPrazoExpedicao();


    }

    public ArrayList<Double> getPrecoExpedicao() {
        return precoExpedicao;
    }

    public int getPrazoExpedicao() {
        return prazoExpedicao;
    }

    public void setPrazoExpedicao(int prazoExpedicao) {
        this.prazoExpedicao = prazoExpedicao;
    }

    public void setPrecoExpedicao(ArrayList<Double> precoExpedicao) {
        this.precoExpedicao = precoExpedicao;
    }

    public ArrayList<Double> getPrecoExpedicaoPremium() {
        return precoExpedicaoPremium;
    }

    public void setPrecoExpedicaoPremium(ArrayList<Double> precoExpedicaoPremium) {
        this.precoExpedicaoPremium = precoExpedicaoPremium;
    }

    public int getPrazoHoras() {
        return this.prazoHoras;
    }

    public void setPrazoHoras(int prazoHoras) {
        this.prazoHoras = prazoHoras;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public double getLucro() {
        return this.lucro;
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
    

    public double getPrecoTotal(Encomendas e) {
        int count = 0, countPremium = 0, novo = 0, usado = 0;
        double preco = 0;
        Malas aux1 = new Malas();
        Sapatilhas aux2 = new Sapatilhas();
        for(Artigos a : e.getArtigos()) {
            if(a.getTransp().equals(this)) {
                switch(a.getClass().getName()) {

                    case "entities.Malas":
                        aux1 = (Malas) a.clone();
                        if(aux1.isPremium()) countPremium ++;
                        else count ++;
                        preco += aux1.precoFinal();
                        break;

                    case "entities.Sapatilhas":
                        aux2 = (Sapatilhas) a.clone();
                        if(aux2.isPremium()) countPremium ++;
                        else count ++;
                        preco += aux2.precoFinal();

                        break;
                    default:
                        count++;
                        preco += a.getprecoBase();

                        break;

                }
            }
            if (a.isnovo()) novo++;
            else usado++;
        }

        if(count > 5) preco += this.precoExpedicao.get(2);
        else if(count == 1) preco+= this.precoExpedicao.get(0);
        else if (count > 1) preco += this.precoExpedicao.get(1);
        else  if(countPremium > 5) preco += this.precoExpedicao.get(2);
        if(countPremium == 1) preco+= this.precoExpedicaoPremium.get(0);
        else if (countPremium > 1) preco += this.precoExpedicao.get(1);

        preco+= novo*0.5;
        preco+=usado*0.25;
        return preco;
    }

    public LocalDateTime verificarPrazoExpedicao(Encomendas encomenda) {
        if(encomenda.getArtigos().stream().filter(x -> x.getTransp().equals(this.clone())).collect(Collectors.toList()).size() > 0){
            LocalDateTime horarioAtual = LocalDateTime.now();

            return horarioAtual.plusHours(prazoExpedicao);

        }
        else return null;
    }



    public LocalDateTime verificarPrazo(Encomendas encomenda) {
        if(encomenda.getArtigos().stream().filter(x -> x.getTransp().equals(this.clone())).collect(Collectors.toList()).size() > 0){
            LocalDateTime horarioAtual = LocalDateTime.now();

            return horarioAtual.plusHours(prazoHoras);
        }
        else return null;
    }


    public Transportadoras clone(){
        return new Transportadoras(this);
    }

    public boolean equals(Object newTranportadora){
        if(this==newTranportadora){
            return true;
        }
        if((newTranportadora==null)||(this.getClass()!=newTranportadora.getClass())){
            return false;
        }
        Transportadoras newTransportadora1 =(Transportadoras) newTranportadora;
        return 	newTransportadora1.getLucro()==this.lucro &&
                newTransportadora1.isEspecializada()==this.especializada &&
                newTransportadora1.precoExpedicao.equals(this.precoExpedicao) &&
                newTransportadora1.precoExpedicaoPremium.equals(this.precoExpedicaoPremium) &&
                newTransportadora1.getNome() == this.nome &&
                newTransportadora1.getPrazoHoras() == this.prazoHoras &&
                newTransportadora1.getPrazoExpedicao()==this.prazoExpedicao;

    }

    public String toStringPrecoExpedicao(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String precoEncomendasPequenas = decimalFormat.format(precoExpedicao.get(0));
        String precoEncomendasMedias = decimalFormat.format(precoExpedicao.get(1));
        String precoEncomendasGrandes = decimalFormat.format(precoExpedicao.get(2));

        return "-Preco de Expedicao de Encomendas pequenas: "+ String.format("%.2f", precoEncomendasPequenas)+
                "\n-Preco de Expedicao de Encomendas medias:"+ String.format("%.2f", precoEncomendasMedias)+
                "\n-Preco de Expedicao de Encomendas grandes:"+ String.format("%.2f", precoEncomendasGrandes);


    }
    public String toStringPrecoExpedicaoPremium(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String precoEncomendasPequenas = decimalFormat.format(precoExpedicaoPremium.get(0));
        String precoEncomendasMedias = decimalFormat.format(precoExpedicaoPremium.get(1));
        String precoEncomendasGrandes = decimalFormat.format(precoExpedicaoPremium.get(2));
        return  "\n-Preco de Expedicao de Encomendas Premium pequenas: "+ String.format("%.2f", precoEncomendasPequenas) +
                "\n-Preco de Expedicao de Encomendas Premium medias:"+ String.format("%.2f", precoEncomendasMedias) +
                "\n-Preco de Expedicao de Encomendas Premium grandes:"+ String.format("%.2f", precoEncomendasGrandes) ;


    }

    public String toString(){
        return 	"\n-Lucro: " + lucro + "\n" +
                "-Especializada: " + especializada + "\n" +
                "-Nome: " + nome + "\n"+
                "-Preço de Expedicao de Encomendas Premium: "+toStringPrecoExpedicao()+
                "\n-Preço de Expedicao de Encomendas: "+toStringPrecoExpedicaoPremium() + "\n"+
                "-Prazo Horas:"+prazoHoras+"\n"+
                "Prazo Expedicao:"+prazoExpedicao+"\n";


    }
}