package entities;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import aux_functions.DataBaseAux;

public class Encomendas implements Serializable {

	private static final long serialVersionUID = 2L;
	private List<Artigos> artigos;
    private int dimensao = 0;
    private String estado;
    private Date dataEncomenda;
    private static long contador = 0;
    private long id;

    public Encomendas(List<Artigos> artigos, String estado, Date dataEncomenda) {
        this.artigos = artigos;
        this.dimensao = artigos.size();
        this.estado = estado;
        this.dataEncomenda = dataEncomenda;
        this.id = contador++;
    }

    public Encomendas() {
        this.artigos = new ArrayList<>();
        this.dimensao = 0;
        this.estado = "Confirmacao pendente";
        this.dataEncomenda = new Date();
        this.id = contador++;
    }

    public Encomendas(Encomendas newEncomendas) {
        this.artigos = newEncomendas.getArtigos();
        this.dimensao = newEncomendas.getDimensao();
        this.estado = newEncomendas.getEstado();
        this.dataEncomenda = newEncomendas.getDataEncomenda();
        this.dimensao = newEncomendas.getDimensao();
        this.id = newEncomendas.getId();
    }
    
    public void setId(int id){
        this.id =id;
    }

    public long getId() {
        return id;
    }
    
	public void adicionarArtigo(Artigos artigo) {
		this.artigos.add(artigo);
		
	}

	public void removerArtigo(Artigos artigo) {
		this.artigos.remove(artigo);
	}

	public int transpCounter(String t) {

		List<Artigos> artigosFiltrados = this.artigos.stream()
				.filter(a -> a.getTransp().getNome().equals(t))
				.collect(Collectors.toList());

		return artigosFiltrados.size();
	}


	public double precoFinal() {
		int i;
		ArrayList<Transportadoras> aux = new ArrayList<>();
		for(i = 0; i < this.artigos.size(); i++) aux.add(this.artigos.get(i).getTransp());
		List<Transportadoras> removerDuplicados = DataBaseAux.removerDuplicados(aux);
		double preco = removerDuplicados.stream().mapToDouble(x -> x.getPrecoTotal(this)).sum();
		return preco;
	}

	public void updateEstado() {
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		int counterToFinal = 0;
		for(Artigos a: this.artigos) {
			if(!(a.getTransp().verificarPrazo(this).equals(null)) && a.getTransp().verificarPrazo(this).isBefore(dataHoraAtual))
				counterToFinal++;
		}
		
		if(counterToFinal == this.artigos.size()) this.estado = "finalizado";
		else {
			for(Artigos a: this.artigos) {
				if(!(a.getTransp().verificarPrazoExpedicao(this).equals(null)) && a.getTransp().verificarPrazoExpedicao(this).isBefore(dataHoraAtual)) this.estado = "expedido";	
			}
		}
		
	}
	public Date getDataEncomenda() {
		return dataEncomenda;
	}

	public void setDataEncomenda(Date dataEncomenda) {
		this.dataEncomenda = dataEncomenda;
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

	public void realizarEncomenda() {
		this.dataEncomenda = new Date();
	}



	// ...

	// Verificar se a encomenda está dentro do prazo de 30 dias para devolução
	public boolean PrazoDevolucao(Encomendas encomenda) {
		encomenda.realizarEncomenda();
		Date dataAtual = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(encomenda.getDataEncomenda());
		cal.add(Calendar.DATE, 30);

		return dataAtual.before(cal.getTime());
	}



	public Encomendas clone(){
		return new Encomendas(this);
	}

	public String toString(){
		return "Preço Final: " + this.precoFinal()+
				"-Dimensao: "+this.dimensao+
				"-Artigos:"+this.artigos.toString()+
				"Data da Encomenda:"+this.dataEncomenda+
				"Estado:"+this.estado;
	}



}
