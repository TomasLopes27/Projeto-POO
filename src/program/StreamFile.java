package program;

import java.io.*;

public class StreamFile {
   private String nomeArquivo;
    private Object conteudo;
    private File arquivo;

    public StreamFile(String nomeArquivo, Object conteudo) {
        this.nomeArquivo = nomeArquivo;
        this.conteudo = conteudo;
        if (nomeArquivo != null) {
            this.arquivo = new File(nomeArquivo);
            verificaSeArquivoExiste();
        }
    }
 public void verificaSeArquivoExiste() {
     try {
         if (!arquivo.exists()) {
             arquivo.createNewFile();
         }
     } catch (IOException e) {
         System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
     }
 }


    public void gravarArquivo() {
        String caminhoArquivo = "C:\\Users\\Ze Carlos\\Desktop\\Uni\\POO" + nomeArquivo;

        try {
            // Cria um novo arquivo usando a classe FileOutputStream
            FileOutputStream streamArquivo = new FileOutputStream(caminhoArquivo);

            // Cria um objeto ObjectOutputStream, passando o objeto FileOutputStream como parâmetro
            ObjectOutputStream streamObjeto = new ObjectOutputStream(streamArquivo);

            // Grava o objeto no arquivo usando o método writeObject()
            streamObjeto.writeObject(conteudo);

            // Fecha o stream de objetos
            streamObjeto.close();

            // Fecha o stream de arquivo
            streamArquivo.close();

            System.out.println("Arquivo criado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void substituirConteudo(Object novoConteudo) {
        String caminhoArquivo = "C:\\Users\\Ze Carlos\\Desktop\\Uni\\POO" + nomeArquivo;

        try {
            // Abre o arquivo em modo de escrita
            FileOutputStream streamArquivo = new FileOutputStream(caminhoArquivo, false);

            // Cria um objeto ObjectOutputStream, passando o objeto FileOutputStream como parâmetro
            ObjectOutputStream streamObjeto = new ObjectOutputStream(streamArquivo);

            // Grava o objeto no arquivo usando o método writeObject()
            streamObjeto.writeObject(novoConteudo);

            // Fecha o stream de objetos
            streamObjeto.close();

            // Fecha o stream de arquivo
            streamArquivo.close();

            System.out.println("Conteúdo substituído com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object lerConteudo() {
        String caminhoArquivo = "C:\\Users\\Ze Carlos\\Desktop\\Uni\\POO" + nomeArquivo;
        Object conteudoLido = null;

        try {
            // Cria um novo arquivo usando a classe FileInputStream
            FileInputStream streamArquivo = new FileInputStream(caminhoArquivo);

            // Cria um objeto ObjectInputStream, passando o objeto FileInputStream como parâmetro
            ObjectInputStream streamObjeto = new ObjectInputStream(streamArquivo);

            // Lê o objeto do arquivo usando o método readObject()
            conteudoLido = streamObjeto.readObject();

            // Fecha o stream de objetos
            streamObjeto.close();

            // Fecha o stream de arquivo
            streamArquivo.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conteudoLido;
    }

    public void removerConteudo() {
        String caminhoArquivo = "C:\\Users\\Ze Carlos\\Desktop\\Uni\\POO" + nomeArquivo;

        try {
            // Abre o arquivo em modo de escrita, o que apaga todo o seu conteúdo
            FileOutputStream streamArquivo = new FileOutputStream(caminhoArquivo, false);

            // Fecha o stream de arquivo
            streamArquivo.close();

            System.out.println("Conteúdo removido com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

