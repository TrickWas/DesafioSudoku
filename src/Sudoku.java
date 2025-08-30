import java.util.Scanner;

public class Sudoku {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        GeradorSudoku gerador = new GeradorSudoku(tabuleiro);

        gerador.preencherCompleto();
        gerador.gerarTabuleiroJogo(40); // remove 40 células mantendo unicidade

        boolean continuar = true;

        while (continuar) {
            tabuleiro.imprimir();

            // verifica se o Sudoku está completo
            boolean completo = true;
            for (int i = 0; i < 9 && completo; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tabuleiro.getTabuleiro()[i][j].getValor() == 0) {
                        completo = false;
                        break;
                    }
                }
            }

            if (completo) {
                System.out.println("Parabéns! Você completou o Sudoku!");
                break;
            }

            System.out.println("Digite linha (0-8), coluna (0-8) e número (1-9):");
            int linha = sc.nextInt();
            int coluna = sc.nextInt();
            int numero = sc.nextInt();

            if (tabuleiro.colocarNumero(linha, coluna, numero)) {
                System.out.println("Número colocado com sucesso!");
            } else {
                System.out.println("Movimento inválido!");
            }

            // Pergunta se quer continuar jogando, com verificação de entrada
            boolean opcaoValida = false;
            while (!opcaoValida) {
                System.out.println("Deseja continuar jogando? (1-Sim / 0-Não)");
                String entrada = sc.next(); // lê como String para evitar exceção de tipo
                if (entrada.equals("1")) {
                    opcaoValida = true;
                    continuar = true;
                } else if (entrada.equals("0")) {
                    opcaoValida = true;
                    continuar = false;
                } else {
                    System.out.println("Opção inválida! Digite 1 para Sim ou 0 para Não.");
                }
            }
        }

        sc.close();
        System.out.println("Jogo encerrado!");
    }
}
