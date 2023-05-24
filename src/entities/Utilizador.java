package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
public class Utilizador extends Artigos implements Serializable {

	private static final long serialVersionUID = 3L;
	private long code;
    private String mail;
    private String nome;
    private String morada;
    private long nif;
    private ArrayList<Artigos> sold;
    private ArrayList<Double> soldPrices;
	private ArrayList<Artigos> selling;
    private ArrayList<Artigos> bought;
    private char tipo;
    private ArrayList<Artigos> carrinho;

    // construtor sem informação adicional
    public Utilizador() {
        this.code = 000000000;
		this.sold = new ArrayList<Artigos>();
        this.soldPrices = new ArrayList<Double>();
        this.selling = new ArrayList<Artigos>();
        this.bought = new ArrayList<Artigos>();
		this.mail = null;
		this.carrinho = new ArrayList<Artigos>();
    }

    // construtor padrão para registo
    public Utilizador(String mail, String nome, String morada, long nif, char tipo) {
        super();
        this.code = 000000000;
        this.mail = mail;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.tipo = tipo;
        this.selling = new ArrayList<>();
        this.sold = new ArrayList<>();
        this.soldPrices = new ArrayList<>();
        this.bought = new ArrayList<>();
        this.carrinho = new ArrayList<Artigos>();
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
        this.carrinho = new ArrayList<Artigos>();
    }

    // get e set do codigo do utilizador
    public long getCode() {
        return code;
    }

    public void setCode() {
        int numeroAlgarismos = 9, numeroDeZeros;
        long code;
        String codeS;
        StringBuilder builder;

        Random random = new Random();
        code = random.nextLong(999999999);
        codeS = Long.toString(code);
        numeroDeZeros = numeroAlgarismos - codeS.length();

        builder = new StringBuilder();

        for (int i = 0; i < numeroDeZeros; i++) { builder.append("0"); }

        builder.append(codeS);

        codeS = builder.toString();
        code= Long.parseLong(codeS);
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
    public char getTipo() {
        return tipo;
    }


    public void setTipo(char tipo) {
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
    
    public ArrayList<Artigos> getCarrinho() {
		return carrinho;
	}
    
    public void addSelling(Artigos e) {
		selling.add(e);
	}

	public void sellingRemove(Object o) {
		selling.remove(o);
	}
	public void addBought(Artigos e) {
		bought.add(e);
	}
	
    public void addSold(Artigos e) {
		sold.add(e);
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
                this.sold == x.getSold() &&
                this.carrinho == x.getCarrinho()
        );
    }

	// total de receita adquirida com a venda de artigos
    public double totalSold() {
        double r = 0;
        Malas aux = new Malas();
        Sapatilhas aux1 = new Sapatilhas();
        for(Artigos a: this.sold) {
        	
        	if(a.getClass().getName().equals("entities.Tshirts")) r+= a.getprecoBase();
        	else if (a.getClass().getName().equals("entities.Malas")) { aux = (Malas) a.clone(); r+=aux.precoFinal(); }
        	else if (a.getClass().getName().equals("entities.Sapatilhas")) { aux1 = (Sapatilhas) a.clone(); r+=aux1.precoFinal(); }
        }
        return r;

    }

    // toString
    public String toString() {
        String r;
        if (this.tipo == 'c' || this.tipo == 'C'){
            r = ("-Code: " + this.code + "\n-Nome: " + this.nome + "\n-Morada: " + this.morada +"\n-E-mail:" + this.mail +
                    "\n-NIF: " + this.nif + "\n-Artigos comprados: " + this.bought);
        }
        else if (this.tipo == 'v' || this.tipo == 'V') {
            r = ("-Code: " + this.code + "\n-Nome: " + this.nome + "\n-Morada: " + this.morada
                    +"\n-E-mail:" + this.mail +  "\n-NIF: " + this.nif + "\n-Artigos a venda no momento: " + this.selling + "\n-Artigos vendidos: " + this.sold +
                    "\n-Receita adquirida com vendas: " + totalSold());
        }
        else {
            r = ("-Code: " + this.code + "\n-Nome: " + this.nome + "\n-Morada: " + this.morada +"\n-E-mail:" + this.mail +
                    "\n-NIF: " + this.nif + "\n-Artigos comprados: " + this.bought + "\n-Artigos a venda no momento: " + this.selling + "\n-Artigos vendidos: " + this.sold +
                    "\n-Receita adquirida com vendas: " + totalSold());
        }

        return r;
    }
    
    // CarrinhoToString
    
    public String carrinhoToString() {
    	
    	String r = "";
    	int i = 0;
    	while(i<this.carrinho.size()) {
    		
    		r += "[" + (i+1) + "] " + carrinho.get(i).toString() + "\n";
    		i++;
    	}
    	
    	return r;
    }
    
    public boolean removeCarrinho(Object o) {
		return carrinho.remove(o);
	}

	// clone
    public Utilizador clone() { return new Utilizador(this); }

	public void addCarrinho(Artigos e) {
		carrinho.add(e);
	}
    
    

}
