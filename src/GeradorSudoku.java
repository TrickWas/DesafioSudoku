import java.util.Random;

public class GeradorSudoku {
    private Tabuleiro tabuleiro;
    private Random rand = new Random();

    public GeradorSudoku(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void preencherCompleto() {
        preencher(0,0);
    }

    private boolean preencher(int linha, int coluna) {
        if (linha == 9) return true;
        int nextCol = (coluna + 1) % 9;
        int nextRow = (coluna + 1) % 9 == 0 ? linha + 1 : linha;
        if (tabuleiro.getTabuleiro()[linha][coluna].getValor() != 0) {
            return preencher(nextRow, nextCol);
        }
        int[] numeros = embaralhar();
        for (int num : numeros) {
            if (tabuleiro.validarMovimento(linha, coluna, num)) {
                tabuleiro.getTabuleiro()[linha][coluna].setValor(num);
                if (preencher(nextRow, nextCol)) return true;
                tabuleiro.getTabuleiro()[linha][coluna].setValor(0);
            }
        }
        return false;
    }

    private int[] embaralhar() {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        for (int i = nums.length-1; i > 0; i--) {
            int j = rand.nextInt(i+1);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    public void gerarTabuleiroJogo(int removals) {
        ValidadorSudoku validador = new ValidadorSudoku(tabuleiro);
        while (removals > 0) {
            int i = rand.nextInt(9);
            int j = rand.nextInt(9);
            if (tabuleiro.getTabuleiro()[i][j].getValor() != 0) {
                int backup = tabuleiro.getTabuleiro()[i][j].getValor();
                tabuleiro.getTabuleiro()[i][j].setValor(0);
                if (!validador.unicaSolucao()) {
                    tabuleiro.getTabuleiro()[i][j].setValor(backup);
                } else {
                    removals--;
                }
            }
        }
    }
}
