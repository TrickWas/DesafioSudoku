public class ValidadorSudoku {
    private Tabuleiro tabuleiro;

    public ValidadorSudoku(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public boolean unicaSolucao() {
        return contarSolucoes(0,0,0) == 1;
    }

    private int contarSolucoes(int linha, int coluna, int count) {
        if (linha == 9) return count + 1;
        Celula[][] celulas = tabuleiro.getTabuleiro();
        if (celulas[linha][coluna].getValor() != 0) {
            int nextCol = (coluna + 1) % 9;
            int nextRow = (coluna + 1) % 9 == 0 ? linha + 1 : linha;
            return contarSolucoes(nextRow, nextCol, count);
        }

        for (int num = 1; num <= 9; num++) {
            if (tabuleiro.validarMovimento(linha, coluna, num)) {
                celulas[linha][coluna].setValor(num);
                int nextCol = (coluna + 1) % 9;
                int nextRow = (coluna + 1) % 9 == 0 ? linha + 1 : linha;
                count = contarSolucoes(nextRow, nextCol, count);
                if (count > 1) {
                    celulas[linha][coluna].setValor(0);
                    return count;
                }
                celulas[linha][coluna].setValor(0);
            }
        }
        return count;
    }
}
