public abstract class Artigos {

    private static long contador = 0;
    private boolean novo;
    private float estado;
    private int nrDonos;
    private String descricao;
    private String marca;
    private final long codigo;
    private double precoBase;
    private float desconto;

    public Artigos(){
        this.novo = true;
        this.estado = 100;
        this.nrDonos = 0;
        this.descricao = "";
        this.marca = "";
        this.codigo = contador++;
        this.precoBase = 0;
        this.desconto = 0;
    }

    public Artigos(boolean novo,float estado, int nrDonos, String descricao, String marca, double precoBase, float desconto){
        this.novo = novo;
        this.estado = estado;
        this.nrDonos = nrDonos;
        this.descricao = descricao;
        this.marca = marca;
        this.codigo = contador++;
        this.precoBase = precoBase;
        this.desconto = desconto;
    }

    public Artigos (Artigos artigo){
        this.novo = artigo.isnovo();
        this.estado = artigo.getestado();
        this.nrDonos = artigo.getnrDonos();
        this.descricao = artigo.getdescricao();
        this.marca = artigo.getmarca();
        this.codigo = contador++;
        this.precoBase = artigo.getprecoBase();
        this.desconto = artigo.getdesconto();
    }

    public void setnovo(boolean novo) {
        this.novo = novo;
    }

    public boolean isnovo() {
        return this.novo;
    }

    public float getestado() {
        return this.estado;
    }

    public void setestado(float estado) {
        this.estado = estado;
    }

    public int getnrDonos() {
        return this.nrDonos;
    }

    public void setnrDonos(int nrDonos) {
        this.nrDonos = nrDonos;
    }

    public void setdescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getdescricao() {
        return this.descricao;
    }

    public String getmarca() {
        return this.marca;
    }

    public void setmarca(String marca) {
        this.marca = marca;
    }

    public void setprecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getprecoBase() {
        return this.precoBase;
    }

    public void setdesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getdesconto() {
        return this.desconto;
    }

    public abstract boolean equals(Object o);

    public abstract Artigos clone();

    public abstract String toString();

}
