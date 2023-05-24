package program;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import aux_functions.DataBaseAux;
import entities.*;
import program.StreamFile;

public class Main {
	static Scanner sc = new Scanner(System.in);
	private static Data_Base dataBase;
	private static LocalDateTime tempo;
	private static Utilizador user;
	public static void main(String[] args) {
		dataBase = new Data_Base();
		/*tempo = LocalDateTime.now();
		dataBase = new Data_Base();
		Object meuObjeto = new Object();
		StreamFile streamFile = new StreamFile("meuArquivo.obj",dataBase);*/

		// VALORES BASE DA LOJA 
		double valorBase0 = 3.00; // encomendas pequenas (1 artigo)
		@SuppressWarnings("unused")
		double valorBase1 = 5.00; // encomendas médias (2 a 5 artigos)
		@SuppressWarnings("unused")
		double valorBase2 = 6.50; // encomendas grandes (+5 artigos)
		double Imposto = 0.23;

		// -------------------------------------------------------------------//
		user = null;
		dataBase.template();
		int opcao_produto, menuCriarArtigo1;
		int entrada = entrada();
		@SuppressWarnings("unused")
		String s, opcao_menu2; // é dada entrada no programa e guardado o resultado da opçao na variavel
		while (true) {
			switch(entrada) {
			case 0: System.out.println("\n-Obrigado pela sua escolha, volte sempre!");	
			/*
			try {
				FileOutputStream fileOut = new FileOutputStream("C:\\Users\\pedro\\OneDrive\\Ambiente de Trabalho\\code\\data_base.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(dataBase);
				out.close();
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			} */return;

			case 1:
				String logResult = login();
				if (logResult.equals("!q")) {
					entrada = entrada();
					break;
				}
				while (!dataBase.getUsers().stream().map(x -> x.getMail()).collect(Collectors.toList()).contains(logResult)) {  // se nao se verificar a existencia do email pede de novo
					System.out.print("-Utilizador Invalido.\n");
					logResult = login();
					if (logResult.equals("!q")) {
						break;
					}

				}
				if(dataBase.getUsers().stream().map(x -> x.getMail()).collect(Collectors.toList()).contains(logResult)) {
					for( Utilizador x : dataBase.getUsers()) {
						if(logResult.equals(x.getMail())) user = x.clone();
					}
				}
				entrada+=2;
				break;
			case 2:
				user = registo();
				while(DataBaseAux.verify(dataBase, user)){
					System.out.println("-O NIF ou endereco e-mail ja se encontra registado, tente novamente clicando em qualquer tecla ou escreva '!q' para sair");
					logResult = sc.next();
					if(logResult.equals("!q")) {
						entrada = entrada();
						break;
					}
					user = registo();
				}
				if (entrada == 2) {
					dataBase.add(user);
					entrada++;
				}
				break;

			case 3:
				logResult = null;
				int menu; 
				menu = mainMenu(user);
				switch(menu) {

				case 0: return;
				case 1:
					int opcao_perfil =(perfil(user));
					perfil:
						switch (opcao_perfil) {
						case 1:
							System.out.println("-Introduza o seu novo email ou '!q' para cancelar:");
							String email = sc.next();
							List<String> emails = dataBase.getUsers().stream().map(u -> u.getMail()).collect(Collectors.toList());
							emails.remove(user.getMail());
							while (emails.contains(email)) {
								System.out.println("-Endereco de e-mail indisponivel, introduza outro ou '!q' para cancelar:\n");
								email = sc.next();
							}
							if (!email.equals("!q")) {
								user.setMail(email);
							}
							break perfil;

						case 2:
							System.out.println("-Introduza a sua nova morada ou '!q' para cancelar:");
							String morada = sc.next();
							if (!morada.equals("!q")) {
								user.setMorada(morada);
							}
							break perfil;

						case 3:
							switch (menuCarrinho(user)) {
							case 1:
								int checkout = checkout(user);
								if(checkout == 1) {
									System.out.println("Encomenda confirmada!\n");
									break perfil;
								}
								else break;
							case 2:
								int alterCarrinho = alterarCarrinho(user.getCarrinho());
								switch(alterCarrinho) {

								case 0:
									break;

								default:
									user.removeCarrinho(user.getCarrinho().get(alterCarrinho-1));
									break;
								}
							}
							break;

						case 4:
							break;
						}
				case 2:

					if(user.getTipo() == 'v' || user.getTipo() == 'V') { 

						opcao_menu2 = "";

						while(!opcao_menu2.equals("!q")) {
							menuCriarArtigo1 = menuCriarArtigo1(user);
							menuCriarArtigo2(user,menuCriarArtigo1);
							System.out.print("-Introduza '!q' para voltar atras ou qualquer texto para listar outro artigo.");
							sc.nextLine();
							opcao_menu2 = sc.next();
						}
					}
					else {
						int opcao_artigos = menuArtigos();

						menuArtigos:
							switch(opcao_artigos) {

							case 1:

								int menuMalas = menuMalas(dataBase);
								switch(menuMalas) {

								case 0:
									break menuArtigos;

								default: 
									opcao_produto = menuProduto(dataBase.getMalas().get(menuMalas-1)); 

									switch(opcao_produto) {

									case 1:
										if(!user.getCarrinho().contains(dataBase.getMalas().get(menuMalas-1))) { 
											user.addCarrinho(dataBase.getMalas().get(menuMalas-1));
										}
										break;

									case 2:
										break;
									}
									break;
								}
								break;
							case 2:

								int menuSapatilhas = menuSapatilhas(dataBase);

								switch(menuSapatilhas){
								case 0: menu = mainMenu(user);
								break;

								default: 
									opcao_produto = menuProduto(dataBase.getSapatilhas().get(menuSapatilhas-1)); 

									switch(opcao_produto) {

									case 1:
										if(!user.getCarrinho().contains(dataBase.getSapatilhas().get(menuSapatilhas-1))) user.addCarrinho(dataBase.getSapatilhas().get(menuSapatilhas-1));
										break;

									case 2:
										break;
									}
								}
								break;

							case 3:

								int menuTshirts = menuTshirts(dataBase);
								tshirts:
									switch(menuTshirts) {

									case 0: menu = mainMenu(user);
									break;

									default: opcao_produto = menuProduto(dataBase.getTshirts().get(menuTshirts-1)); 
									produto:
										switch(opcao_produto) {

										case 1:
											if(!user.getCarrinho().contains(dataBase.getTshirts().get(menuTshirts-1))) user.addCarrinho(dataBase.getTshirts().get(menuTshirts-1));
										case 2:
											break produto;
										}
									break tshirts;
									}
								break;


							case 4: break;      // Adicionar Carrinho no utilizador!!!!!
							} 
						break;
					}
				case 3:

					if(user.getTipo() == 'v' || user.getTipo() == 'V') historico(1,user);
					else {
						opcao_menu2 = "";

						while(!opcao_menu2.equals("!q")) {
							menuCriarArtigo1 = menuCriarArtigo1(user);
							menuCriarArtigo2(user,menuCriarArtigo1);
							System.out.print("-Introduza '!q' para voltar atras ou qualquer texto para listar outro artigo.");
							sc.nextLine();
							opcao_menu2 = sc.next();
						}
					}
					break;
				case 4:
					historico(1,user);
					System.out.println("-Introduza qualquer texto para sair.");
					s = sc.next();
					break;

				case 5:
					historico(2,user);
					System.out.println("-Introduza qualquer texto para sair.");
					s = sc.next();
					break;

				}
				break;

			}
		}
	}



	// --------------------------------------------------------------------------------- //	
	// --------------------------------------------------------------------------------- //	



	// *MÉTODO DE ENTRADA*
	public static int entrada(){
		int i;
		System.out.println("-Bem vindo a nossa loja!\n");
		System.out.println("[0] Sair ");
		System.out.println("[1] Iniciar sessao");
		System.out.println("[2] Criar uma conta");

		i = sc.nextInt();
		while(i != 1 && i != 2 && i != 0){
			System.out.println("-Bem vindo a nossa loja!\n");
			System.out.println("[0] Sair ");
			System.out.println("[1] Iniciar sessao");
			System.out.println("[2] Criar uma conta");
			i = sc.nextInt();
		}
		return i;
	}


	// *MÉTODO DE LOGIN*

	public static String login() {
		System.out.print("-Introduza o email da sua conta ou apenas '!q' para sair");
		sc.nextLine();
		String email = sc.next();
		sc.nextLine();
		return email;
	}


	// *MÉTODO DE REGISTO*

	public static Utilizador registo() {
		System.out.print("-Introduza o seu email:");
		sc.nextLine();
		String email = sc.next();
		System.out.print("-Introduza o seu nome:");
		sc.nextLine();
		String nome = sc.next();
		System.out.print("-Introduza a sua morada:");
		sc.nextLine();
		String morada = sc.next();
		System.out.print("-Introduza o seu NIF:");
		sc.nextLine();
		int numeroFiscal = sc.nextInt();
		System.out.print("-Introduza o tipo de utilizador e submita (c = comprador, v = vendedor, a = ambos)");
		sc.nextLine();
		String tipo = sc.next();
		char tipo1 = tipo.charAt(0);

		while(tipo1 != 'c' && tipo1 != 'C' && tipo1 != 'v' && tipo1 != 'V' && tipo1 != 'A' && tipo1 != 'a') {
			System.out.println("-Tipo invalido, introduza novamente (c = comprador, v = vendedor, a = ambos): ");  // mudar para char (C ou c = comprador, V ou v = vendedor, A ou a = ambos)
			tipo = sc.next();
			tipo1 = tipo.charAt(0);
		}
		Utilizador utilizador = new Utilizador(email,nome,morada,numeroFiscal,tipo1);
		utilizador.setCode();
		return utilizador;
	}


	// *MÉTODO DO MAIN MENU DA LOJA*

	public static int mainMenu(Utilizador user){
		int r;
		if(user.getTipo() == 'c' || user.getTipo() == 'C') {
			System.out.println("[0] Sair da conta");
			System.out.println("[1] Ver perfil");
			System.out.println("[2] Ver artigos disponiveis");
			System.out.println("[3] Historico de compras");
			r = sc.nextInt();
			while ( r != 1 && r != 2 && r !=3 && r!= 0 ) {
				sc.nextLine();
				r = sc.nextInt();
			}
		} else if ( user.getTipo() == 'v' || user.getTipo()== 'V') {
			System.out.println("[0] Sair da conta");

			System.out.println("[1] Ver perfil");
			System.out.println("[2] Listar artigo(s) para venda");
			System.out.println("[3] Historico de vendas");
			r = sc.nextInt();
			while ( r != 1 && r != 2 && r !=3 && r!= 0 ) {
				sc.nextLine();
				r = sc.nextInt();
			}
		}else{
			System.out.println("[0] Sair da conta");
			System.out.println("[1] Ver perfil");
			System.out.println("[2] Ver artigos disponiveis");
			System.out.println("[3] Listar artigo(s) para venda");
			System.out.println("[4] Historico de compras");
			System.out.println("[5] Historico de vendas");
			r = sc.nextInt();
			while ( r != 1 && r != 2 && r != 3 && r != 4 && r != 5 && r!= 0) {
				sc.nextLine();
				r = sc.nextInt();
			}
			sc.nextLine();
		}
		return r;
	}

	// *MÉTODO DO PERFIL*
	public static int perfil(Utilizador user) {
		int r=0;
		System.out.print (user.toString());
		System.out.print("\n---------------\n");
		System.out.print("[1] Alterar e-mail da conta\n[2] Alterar morada\n[3] Carrinho de compras\n[4]Voltar ao menu\n");
		do {
			r = sc.nextInt();
			sc.nextLine();
		}while(r != 1 && r != 2 && r != 3 && r != 4);
		return r;

	}
	// *MÉTODO DOS ARTIGOS DISPONIVEIS*

	public static int menuArtigos() {
		int r;
		System.out.println("-Que artigos deseja ver?");
		System.out.print("[1] Malas\n[2] Sapatilhas\n[3] T-shirts\n[4] Voltar ao menu inicial\n");
		r=sc.nextInt();
		while (r != 1 && r != 2 && r != 3 && r != 4) {
			r = sc.nextInt();
		};
		return r;
	}

	// *MÉTODO DO MENU DAS MALAS*

	public static int menuMalas(Data_Base dataBase) {
		int r;
		System.out.println("[0] Voltar ao menu inicial");
		System.out.println("---------------");
		System.out.println(dataBase.malasToString());
		do {
			r = sc.nextInt();
		} while(r < 0 || r > dataBase.getMalas().size());
		return r;
	}

	// *MÉTODO DO MENU DAS SAPATILHAS*

	public static int menuSapatilhas(Data_Base dataBase) {
		int r;
		System.out.println("[0] Voltar ao menu inicial");
		System.out.println("---------------");
		System.out.println(dataBase.sapatilhasToString());
		do {
			r = sc.nextInt();
		} while(r < 0 || r > dataBase.getSapatilhas().size());
		return r;

	}

	// *MÉTODO DO MENU DAS TSHIRTS*

	public static int menuTshirts(Data_Base dataBase) {
		int r;
		System.out.println("[0] Voltar ao menu inicial");
		System.out.println("---------------");
		System.out.println(dataBase.tshirtsToString());
		do {
			r = sc.nextInt();
		} while(r < 0 || r > dataBase.getTshirts().size());
		return r;

	}

	// *MÉTODO DO MENU DE UM PRODUTO*

	public static int menuProduto(Artigos x) {
		int r;
		System.out.print(x.toString());
		sc.nextLine();
		System.out.println("---------------");
		System.out.println("[1] Adicionar ao carrinho");
		System.out.println("[2] Voltar atras");


		do {
			r = sc.nextInt();
		} while(r != 1 && r != 2);

		return r;
	}

	// *MÉTODO PARA LISTAR ARTIGO PARA VENDER* P.1

	public static int menuCriarArtigo1(Utilizador user){
		System.out.println("-Qual o tipo de artigo que ira adicionar?");
		System.out.println("[1] Tshirts");
		System.out.println("[2] Sapatilhas");
		System.out.println("[3] Malas");
		int r = sc.nextInt();
		return r;
	}

	// *MÉTODO DO MENU DE CHECKOUT*

	public static int checkout(Utilizador user) {
		Date datatual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = formato.format(datatual);
		System.out.println("-Data atual: " + dataFormatada);

		Encomendas e = new Encomendas();

		for (Artigos a : user.getCarrinho()) {
			e.adicionarArtigo(a);
			e.setDimensao(e.getDimensao()+1);
			a.minimalRep();
		}
		double precosBase = e.getArtigos().stream().mapToDouble(a -> a.getprecoBase()).sum();
		System.out.println("- Portes de envio: " +  String.format("%.2f", (e.precoFinal() - precosBase)));
		System.out.print("- Preco Total: " + String.format("%.2f", e.precoFinal()));
		sc.nextLine();
		System.out.println("- Quantidade de produtos: " + e.getDimensao());
		System.out.println("- Data: " + dataFormatada);
		System.out.println("--------------");
		System.out.println("[1] Confirmar Encomenda [2] Voltar atras");
		int a;
		do {
			a = sc.nextInt();}while(a != 1 && a != 2);
		ArrayList<Artigos> aux11 = new ArrayList<>();
		if (a == 1) {
			dataBase.add(e);
			e.realizarEncomenda();
			e.setEstado("confirmada");
			for(Artigos x : e.getArtigos()) aux11.add(x);
			for(Artigos x : aux11) user.addBought(x);
		}
		return a;
	}

	// *MÉTODO DO MENU DO CARRINHO*

	public static int menuCarrinho(Utilizador user) {
		int x;
		System.out.println(user.carrinhoToString());
		System.out.print("---------------\n"
				+ "[1]Checkout\n"
				+ "[2]Alterar encomenda\n"
				+ "[3]Voltar atras\n");
		do {
			x = sc.nextInt();
		} while(x != 1 && x != 2 && x != 3);		

		return x;
	}

	public static int alterarCarrinho(ArrayList<Artigos> l) {

		System.out.println("\n-Selecione o numero do artigo que deseja eliminar do carrinho ou '0' para sair.");
		int x = -1;
		do {
			x = sc.nextInt();
			if(x > l.size()) {
				System.out.println("\nValor invalido, introduza novamente:");
				x = sc.nextInt();
			}
		} while (x<0);

		return x;
	}
	// *MÉTODO DOs HISTORICOs DE COMPRAS E VENDAS*

	public static void historico(int i, Utilizador user) {
		String r = "";
		if (i == 1) {

			for (Artigos x: user.getBought()) {
				r+= ("[" + user.getBought().indexOf(x) + "] " + x.toString()) + "\n";
			}
		}
		else {
			for (Artigos x: user.getSold()) {

				r += ("[" + user.getSold().indexOf(x) + "] " + x.toString()) + "\n";
			}

		}

		System.out.println(r);
	}

	// *MÉTODO PARA LISTAR ARTIGO PARA VENDER* P.2

	public static void menuCriarArtigo2(Utilizador user, int r){
		float estado;
		int donos, r1 = 0;
		System.out.println("-O Artigo e novo ou usado? (true = novo, false = usado) ");
		boolean novo=sc.nextBoolean();
		if(novo){
			estado=100;
			donos=0;
		}
		else{
			System.out.println("-Estado do produto:");
			estado=sc.nextFloat();
			System.out.println("-Numero de donos");
			donos=sc.nextInt();
		}

		System.out.println("-Descricao do artigo:");
		String descricao=sc.next();
		System.out.println("-Marca do artigo:");
		String marca=sc.next();
		System.out.println("-Preco Base do artigo:");
		double precoBase=sc.nextDouble();
		System.out.println("-Desconto para o produto:");
		float desconto=sc.nextFloat();

		switch (r){
		case 1:
			System.out.println("-Qual o tamanho da Tshirt(S/M/L/XL)");
			Tshirts.Tamanho tamanhoT=Tshirts.Tamanho.valueOf(sc.next());
			System.out.println("-Qual o padrao da Tshirt(liso,riscas,palmeiras)");
			Tshirts.Padrao padrao=Tshirts.Padrao.valueOf((sc.next()));
			System.out.println("-Qual a transportadora desejada? (Introduza o nome em letras maiusculas)");
			System.out.println("-Transportadoras disponiveis: " + dataBase.getTransportadoras().toString());
			int tr = sc.nextInt();
			while(dataBase.getTransportadoras().get(tr-1) == null) {
				tr = sc.nextInt();
			}
			System.out.println("-Deseja guardar o artigo criado?");
			System.out.println("[1] - SIM\n[2]-NAO");
			r1 = sc.nextInt();
			while(r1 != 1 && r1 != 2) {
				r1 = sc.nextInt();
			}
			if (r1 == 1) {
				Tshirts tshirts=new Tshirts(novo,estado,donos,descricao,marca,precoBase,desconto,tamanhoT,padrao,dataBase.getTransportadoras().get(tr-1));
				dataBase.getTshirts().add(tshirts);
				user.addSelling(tshirts);
			}
			break;

		case 2:
			System.out.println("-As sapatilhas tem atacadores?");
			boolean atacadores=sc.nextBoolean();
			System.out.println("-Qual o tamanho das sapatilhas?");
			float tamanhoS=sc.nextFloat();
			System.out.println("-De que cor são as sapatilhas?");
			String cor=sc.next();
			System.out.println("-Quantos anos tem as sapatilhas");
			int dataS=sc.nextInt();
			System.out.println("-As sapatilhas são premium?");
			boolean premiumS=sc.nextBoolean();
			System.out.println("-Qual a transportadora desejada? (Introduza o nome em letras maiusculas)");
			System.out.println("-Transportadoras disponiveis: " + dataBase.getTransportadoras().toString());
			int tr1 = sc.nextInt();
			while(dataBase.getTransportadoras().get(tr1-1) == null) {
				tr1 = sc.nextInt();
			}
			System.out.println("-Deseja guardar o artigo criado?");
			System.out.println("[1] - SIM\n[2]-NAO");
			r1 = sc.nextInt();
			while(r1 != 1 && r1 != 2) {
				r1 = sc.nextInt();
			}

			if(r1==1) {
				Sapatilhas sapatilhas = new Sapatilhas(novo,estado,donos,descricao,marca,precoBase,desconto,tamanhoS,atacadores,cor,dataS,premiumS, dataBase.getTransportadoras().get(tr1-1));
				dataBase.getSapatilhas().add(sapatilhas);
				user.addSelling(sapatilhas);
			}
			break;

		case 3:
			System.out.println("-Qual a altura da mala");
			double altura=sc.nextDouble();
			System.out.println("-Qual a largura da mala");
			double largura=sc.nextDouble();
			System.out.println("-Qual o comprimento da mala");
			double comprimento=sc.nextDouble();
			System.out.println("-Qual o tipo de material da mala?");
			String material=sc.next();
			System.out.println("-As malas são premium?");
			boolean premiumM=sc.nextBoolean();
			System.out.println("-Ano de lacamento da mala?");
			int dataM=sc.nextInt();
			System.out.println("-Qual a transportadora desejada? (Introduza o nome em letras maiusculas)");
			System.out.println("-Transportadoras disponiveis:\n" + dataBase.transportadorasToString());
			int tr2 = sc.nextInt();
			while(dataBase.getTransportadoras().get(tr2-1) == null) {
				tr2 = sc.nextInt();
			}

			System.out.println("-Deseja guardar o artigo criado?");
			System.out.println("[1] - SIM\n[2]-NAO");
			r1 = sc.nextInt();
			while(r1 != 1 && r1 != 2) {
				r1 = sc.nextInt();
			}

			if (r1 == 1){
				Malas malas1=new Malas(novo,estado,donos,descricao,marca,precoBase,desconto,altura,comprimento,largura,material,dataM,premiumM,dataBase.getTransportadoras().get(tr2-1));
				dataBase.getMalas().add(malas1);
				user.addSelling(malas1);
				break;
			}
		}
	}
}
