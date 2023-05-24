package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.Calendar;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Data_Base implements Serializable {
    private ArrayList<Utilizador> users;

    //DataBase dos Artigos
    private ArrayList<Malas> malas;
    private ArrayList<Tshirts> tshirts;
    private ArrayList<Sapatilhas> sapatilhas;

    //DataBase de Encomendas
    private ArrayList<Encomendas> encomendas;

    //DataBase das Transportadoras
    private ArrayList<Transportadoras> transportadoras;

    //Construtores

    public Data_Base() {
        this.users = new ArrayList<>();
        this.malas = new ArrayList<>();
        this.tshirts = new ArrayList<>();
        this.sapatilhas = new ArrayList<>();
        this.transportadoras = new ArrayList<>();
        this.encomendas = new ArrayList<>();
    }

    //Gets & Sets
    public ArrayList<Utilizador> getUsers() {
        return users;
    }

    public ArrayList<Malas> getMalas() {
        return malas;
    }

    public ArrayList<Tshirts> getTshirts() {
        return tshirts;
    }

    public ArrayList<Sapatilhas> getSapatilhas() {
        return sapatilhas;
    }


    public ArrayList<Encomendas> getEncomendas() {
        return encomendas;
    }

    public ArrayList<Transportadoras> getTransportadoras() {
        return transportadoras;
    }

    //Método add

    public void add(Utilizador e) {
        users.add(e);
    }

    public void add(Malas e) {
        malas.add(e);
    }

    public void add(Tshirts e) {
        tshirts.add(e);
    }

    public void add(Sapatilhas e) {
        sapatilhas.add(e);
    }

    public void add(Encomendas e) {
        encomendas.add(e);
    }

    public void add(Transportadoras e) {
        transportadoras.add(e);
    }

    public boolean userMailVerify(String email) {
        boolean r;
        if(users.stream().filter(x -> x.getMail() == email).collect(Collectors.toList()).isEmpty()) r = false;
        else r = true;
        return r;

    }

    public boolean userNifVerify(long nif) {
        boolean r;
        if(users.stream().filter(x -> x.getNif() == nif).collect(Collectors.toList()).isEmpty()) r = false;
        else r = true;
        return r;

    }

    public boolean userCodeVerify(long code) {
        boolean r;
        if(users.stream().filter(x -> x.getCode() == code).collect(Collectors.toList()).isEmpty()) r = false;
        else r = true;
        return r;

    }

    // isEmpty

    public boolean isEmptyU() {
        Data_Base aux = new Data_Base(); boolean r;
        if (users.isEmpty() || users == aux.getUsers()) r = true;
        else r = false;
        return r;
    }

    // toStrings

    public String malasToString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        String r = "";
        for (Malas m : this.malas) {
            if (m.isnovo()) {
                r += ("[" + (this.malas.indexOf(m) + 1) + "] Novo\n" + "Marca: " + m.getmarca() + "\nAltura: " + m.getAltura()
                        + "\nLargura: " + m.getLargura() + "\nMaterial: " + m.getMaterial()
                        + "\nPreço: " + df.format(m.precoFinal()) + ".\n\n");
            } else {
                r += ("[" + (this.malas.indexOf(m) + 1) + "] Usado\n" + "Marca: " + m.getmarca() + "\nAltura: " + m.getAltura()
                        + "\nLargura: " + m.getLargura() + "\nMaterial: " + m.getMaterial()
                        + "\nNumero de Donos: " + m.getnrDonos() + "\nEstado: " + m.getestado()
                        + "\nPreço: " + df.format(m.precoFinal()) + ".\n\n");
            }

        }
        return r;
    }


    public String sapatilhasToString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        String r = "";
        for(Sapatilhas m : this.sapatilhas) {
            if(m.isnovo()) {
                r+= ("["+ sapatilhas.indexOf(m)+1 + "]" + " Novo\n" + "Marca: " + m.getmarca() +"\nTamanho: " + m.getTamanho()
                        + "\nCor: " + m.getCor() + "\nAtacadores: " + m.getAtacadores()
                        + "\nPreço: " + df.format(m.precoFinal()) + ".\n");
            }
            else {
                r+= ("["+ sapatilhas.indexOf(m)+1 + "]" + " Usado\n" + "Marca: " + m.getmarca() +"\nTamanho: " + m.getTamanho() + "\nCor: " + m.getCor() +
                        "\nNumero de Donos : " + m.getnrDonos() + "\nEstado: " + m.getestado() + "\nAtacadores: " + m.getAtacadores()
                        + "\nPreço: " + df.format(m.precoFinal()) + ".\n");
            }

        }
        return r;
    }

    public String tshirtsToString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        String r = "";
        for(Tshirts m : this.tshirts) {
            m.descontoUsado();
            if(m.isnovo()) {
                r+= ("["+ tshirts.indexOf(m)+1 + "]" + " Novo\n" + "Marca: " + m.getmarca() + "\nTamanho: " + m.getSize() + "\nPadrao: " + m.getPattern()
                        + "\nPreço: " + df.format(m.getprecoBase()) + ".\n");
            }
            else {
                r+= ("["+ tshirts.indexOf(m)+1 + "]" + " Usado\n" + "Marca: " + m.getmarca() +
                        "\nNumero de Donos : " + m.getnrDonos() + "\nEstado: " + m.getestado()
                        + "\nPreço: " + df.format(m.getprecoBase()) + ".\n");
            }

        }
        return r;
    }

    public String transportadorasToString() {
        int i = 0;
        String r = "";
        while (i < this.transportadoras.size()) {
            r += "\n[" + (i + 1) + "]\n" + this.transportadoras.get(i).toString();
            i++; // Incrementa o valor de i em cada iteração
        }
        return r;
    }
    
    public Utilizador sold(Artigos a) {
    	Utilizador aux = new Utilizador();
    	for(Utilizador u: users) {
    		if(u.getSelling().contains(a)) {
    			u.sellingRemove(a);
    			u.addSold(a);
    			aux = u.clone();
    			break;
    		}
    	}
    	return aux;
    }
    
    public void update() {
        this.encomendas.forEach(e -> e.updateEstado());
    }
    public void template() {
        Utilizador u1 = new Utilizador("user1@mail.com", "User 1", "Rua 1", 123456789, 'v');
        Utilizador u2 = new Utilizador("user2@mail.com", "User 2", "Rua 2", 987654321, 'c');
        Utilizador u3 = new Utilizador("user3@mail.com", "User 3", "Rua 3", 555555555, 'c');
        Utilizador u4 = new Utilizador("user4@mail.com", "User 4", "Rua 4", 444444444, 'a');
        Utilizador u5 = new Utilizador("user5@mail.com", "User 5", "Rua 5", 111111111, 'v');
        users.add(u1);
        u1.setCode();
        users.add(u2);
        u2.setCode();
        users.add(u3);
        u3.setCode();
        users.add(u4);
        u4.setCode();
        users.add(u5);
        u5.setCode();

        Transportadoras t1 = new Transportadoras("Transportadora 1", 98, true, 24,24, 10.0, 20.0, 30.0, 15.0, 25.0, 35.0);
        Transportadoras t2 = new Transportadoras("Transportadora 2", 90, false, 48,24, 12.0, 22.0, 32.0, 17.0, 27.0, 37.0);
        Transportadoras t3 = new Transportadoras("Transportadora 3", 120, true, 72,24, 8.0, 18.0, 28.0, 13.0, 23.0, 33.0);
        Transportadoras t4 = new Transportadoras("Transportadora 4", 200, false, 96,12, 11.0, 21.0, 31.0, 16.0, 26.0, 36.0);
        Transportadoras t5 = new Transportadoras("Transportadora 5", 180, true, 120,48, 9.0, 19.0, 29.0, 14.0, 24.0, 34.0);
        transportadoras.add(t5);
        transportadoras.add(t4);
        transportadoras.add(t3);
        transportadoras.add(t2);
        transportadoras.add(t1);

        // Adicionando 5 Malas
        Malas m1 = new Malas(true, 0.8, 1, "Mala 1", "Marca 1", 50, 0.1, 3.0, 6.0, 2.0, "Couro", 20220501, true, t1);
        Malas m2 = new Malas(false, 0.5, 2, "Mala 2", "Marca 2", 70, 0.2, 35, 7.0, 2.5, "Nylon", 20220510, false, t2);
        Malas m3 = new Malas(true, 0.9, 1, "Mala 3", "Marca 3", 100, 0.3, 4.0, 8.0, 3.0, "Couro sintético", 20220601, true, t5);
        Malas m4 = new Malas(false, 0.6, 2, "Mala 4", "Marca 4", 80, 0.1, 4.5, 9.0, 3.5, "Poliéster", 20220610, false, t5);
        Malas m5 = new Malas(true, 0.7, 1, "Mala 5", "Marca 5", 120, 0.4, 5.0, 1.0, 4.0, "Couro", 20220701, true, t1);
        malas.add(m1);
        malas.add(m2);
        malas.add(m3);
        malas.add(m4);
        malas.add(m5);

        // Adicionando 5 Tshirts
        Tshirts tsh1 = new Tshirts(false, 0.8, 1, "Tshirt 1", "Marca 1",20.00, 0.1, Tshirts.Tamanho.M, Tshirts.Padrao.palmeiras, t5);
        tshirts.add(tsh1);
    }}
