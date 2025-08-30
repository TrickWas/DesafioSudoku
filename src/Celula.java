public class Celula {
    private int valor;
    private boolean fixa;

    public Celula(int valor, boolean fixa) {
        this.valor = valor;
        this.fixa = fixa;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (!fixa) {
            this.valor = valor;
        }
    }

    public boolean isFixa() {
        return fixa;
    }
}
