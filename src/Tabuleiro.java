public class Tabuleiro {
    private Celula[][] tabuleiro = new Celula[9][9];

    public Tabuleiro() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tabuleiro[i][j] = new Celula(0, false);
            }
        }
    }

    public Celula[][] getTabuleiro() {
        return tabuleiro;
    }

    public void imprimir() {
        System.out.println("=== Sudoku ===");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) System.out.println("------+-------+------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) System.out.print("| ");
                int val = tabuleiro[i][j].getValor();
                System.out.print(val == 0 ? ". " : val + " ");
            }
            System.out.println();
        }
    }

    public boolean validarMovimento(int linha, int coluna, int numero) {
        for (int i = 0; i < 9; i++) {
            if (tabuleiro[linha][i].getValor() == numero) return false;
            if (tabuleiro[i][coluna].getValor() == numero) return false;
        }
        int startRow = linha - linha % 3;
        int startCol = coluna - coluna % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (tabuleiro[i][j].getValor() == numero) return false;
            }
        }
        return true;
    }

    public boolean colocarNumero(int linha, int coluna, int numero) {
        if (tabuleiro[linha][coluna].isFixa()) return false;
        if (validarMovimento(linha, coluna, numero)) {
            tabuleiro[linha][coluna].setValor(numero);
            return true;
        }
        return false;
    }
}
