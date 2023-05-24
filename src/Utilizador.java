import java.util.ArrayList;
public class Utilizador extends Artigos {

    private long code;
    private String mail;
    private String nome;
    private String morada;
    private long nif;
    private ArrayList<Artigos> sold;
    private ArrayList<Double> soldPrices;
    private ArrayList<Artigos> selling;
    private ArrayList<Artigos> bought;
    private enum Tipo{c,v,cv}
    private Tipo tipo;

    // construtor sem informação adicional
    public Utilizador() {
        this.sold = new ArrayList<Artigos>();
        this.soldPrices = new ArrayList<Double>();
        this.selling = new ArrayList<Artigos>();
        this.bought = new ArrayList<Artigos>();
    }

    // construtor padrão para registo
    public Utilizador(long code, String mail, String nome, String morada, long nif, Tipo tipo) {
        super();
        this.code = code;
        this.mail = mail;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.tipo = tipo;
        this.selling = new ArrayList<>();
        this.sold = new ArrayList<>();
        this.soldPrices = new ArrayList<>();
        this.bought = new ArrayList<>();
    }

    // construtor "this" com todos os parâmetros para utilizadores previamente definidos
    public Utilizador(Utilizador user) {
        this.tipo = user.getTipo();
        this.mail = user.getMail();
        this.code = user.getCode();
        this.nome = user.getNome();
        this.morada = user.getMorada();
        this.nif = user.getNif();
        this.sold = user.getSold();
        this.soldPrices = user.getSoldPrices();
        this.selling = user.getSelling();
        this.bought = user.bought;
    }

    // get e set do codigo do utilizador
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
    // get e set do e-mail
    public String getMail() {
        return mail;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }

    // get e set do tipo
    public Tipo getTipo() {
        return tipo;
    }


    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    // get e set do nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // get e set da morada
    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    // get e set do NIF
    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    // get e set da lista de produtos vendidos
    public ArrayList<Artigos> getSold() {
        return this.sold;
    }


    public void setSold(ArrayList<Artigos> sold) {
        this.sold = sold;
    }

    // get e set da lista de receitas correspondentes ás encomendas realizadas
    public void setSoldPrices(ArrayList<Double> soldPrices) {
        this.soldPrices = soldPrices;
    }

    public ArrayList<Double> getSoldPrices() {
        return this.soldPrices;
    }

    // get e set da lista de produtos que o utilizador tem á venda no momento
    public ArrayList<Artigos> getSelling() {
        return this.selling;
    }

    public void setSelling(ArrayList<Artigos> selling) {
        this.selling = selling;
    }

    // get e set da lista de produtos já comprados pelo utilizador
    public ArrayList<Artigos> getBought() {
        return this.bought;
    }

    public void setBought(ArrayList<Artigos> bought) {
        this.bought = bought;
    }

    // equals (compara 2 users )
    public boolean equals(Object o) {
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        Utilizador x = (Utilizador) o;
        return (this.code == x.getCode() &&
                this.nome == x.getNome() &&
                this.morada == x.getMorada() &&
                this.bought == x.getBought() &&
                this.nif == x.getNif() &&
                this.soldPrices == x.getSoldPrices() &&
                this.selling == x.getSelling() &&
                this.sold == x.getSold()
        );
    }

    // total de receita adquirida com a venda de artigos
    public double totalSold() {
        double r = 0;
        for(double p: this.soldPrices) r += p;
        return r;

    }

    // toString
    public String toString() {
        String r;
        if(this.tipo == Tipo.c){
            r = ("-Code: " + this.code + "\n-Nome: " + this.nome + "\n-Morada: " + this.morada +
                    "\n-NIF: " + this.nif + "\n-Artigos comprados: " + this.bought);
        }
        else if(this.tipo == Tipo.v) {
            r = ("-Code: " + this.code + "\n-Nome: " + this.nome + "\n-Morada: " + this.morada
                    + "\n-NIF: " + this.nif + "\n-Artigos a venda no momento: " + this.selling + "\n-Artigos vendidos: " + this.sold +
                    "\n-Receita adquirida com vendas: " + totalSold());
        }
        else {
            r = ("-Code: " + this.code + "\n-Nome: " + this.nome + "\n-Morada: " + this.morada +
                    "\n-NIF: " + this.nif + "\n-Artigos comprados: " + this.bought + "\n-Artigos a venda no momento: " + this.selling + "\n-Artigos vendidos: " + this.sold +
                    "\n-Receita adquirida com vendas: " + totalSold());
        }

        return r;
    }

    // clone
    public Utilizador clone() { return new Utilizador(this); }

}
