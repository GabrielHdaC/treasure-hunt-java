import java.util.Random;
import java.util.Scanner;

public class ProjetoCacaTesouro {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    int linhas, colunas, tentativasMax;
    char[][] mapa;
    int linhaTesouro, colunaTesouro;

    public static void main(String[] args) {
        new ProjetoCacaTesouro();
    }

    public ProjetoCacaTesouro() {
        inicializarJogo();
    }

    public void inicializarJogo() {
        while (true) {
            System.out.println("\n=== CAÇA AO TESOURO ===");
            System.out.println("[1] Iniciar");
            System.out.println("[2] Sair");
            System.out.print("\nEntrada: ");
            int escolha = sc.nextInt();

            if (escolha == 1) {
                definirDificuldade();
                break;
            } else if (escolha == 2) {
                animacaoSaida();
                break;
            } else {
                System.out.println("Opção inválida! Tente novamente.\n");
            }
        }
    }

    private void definirDificuldade() {
        while (true) {
            System.out.println("\nEscolha a dificuldade:");
            System.out.println("[1] Fácil   (5x5, 10 tentativas)");
            System.out.println("[2] Médio   (7x7, 8 tentativas)");
            System.out.println("[3] Difícil (10x10, 6 tentativas)");
            System.out.print("\nEntrada: ");
            int escolha = sc.nextInt();

            switch (escolha) {
                case 1 -> comecaJogo(5, 5, 10);
                case 2 -> comecaJogo(7, 7, 8);
                case 3 -> comecaJogo(10, 10, 6);
                default -> {
                    System.out.println("Opção inválida! Escolha novamente.\n");
                    continue;
                }
            }
            break;
        }
    }

    private void comecaJogo(int linhas, int colunas, int tentativasMax) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.tentativasMax = tentativasMax;

        mapa = new char[linhas][colunas];
        for (int i = 0; i < linhas; i++)
            for (int j = 0; j < colunas; j++)
                mapa[i][j] = '-';

        linhaTesouro = random.nextInt(linhas);
        colunaTesouro = random.nextInt(colunas);

        boolean encontrou = false;

        for (int tentativa = 1; tentativa <= tentativasMax; tentativa++) {
            System.out.println("\nTentativa " + tentativa + " de " + tentativasMax);
            mostrarMapa(false);

            System.out.print("Digite a linha (0 a " + (linhas - 1) + "): ");
            int linha = sc.nextInt();

            System.out.print("Digite a coluna (0 a " + (colunas - 1) + "): ");
            int coluna = sc.nextInt();

            if (linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
                System.out.println("Coordenadas inválidas! Tente novamente.");
                tentativa--;
                continue;
            }

            if (mapa[linha][coluna] == 'X') {
                System.out.println("Você já tentou aqui! Escolha outro local.");
                tentativa--;
                continue;
            }

            if (linha == linhaTesouro && coluna == colunaTesouro) {
                mapa[linha][coluna] = 'T';
                encontrou = true;
                break;
            } else {
                mapa[linha][coluna] = 'X';
                System.out.println("Nada aqui... tente novamente!");
            }
        }

        System.out.println();
        mostrarMapa(true);

        if (encontrou) {
            System.out.println("\nVOCÊ ENCONTROU O TESOURO!!!");
        } else {
            System.out.println("\nFim de jogo! Suas tentativas acabaram.");
            System.out.println("O tesouro estava em: (" + linhaTesouro + ", " + colunaTesouro + ")");
        }

        continuarJogo();
    }

    private void continuarJogo() {
        while (true) {
            System.out.println("\nDeseja jogar novamente?");
            System.out.println("[1] Sim");
            System.out.println("[2] Não");
            System.out.print("\nEntrada: ");
            int escolha = sc.nextInt();

            if (escolha == 1) {
                definirDificuldade();
                break;
            } else if (escolha == 2) {
                animacaoSaida();
                break;
            } else {
                System.out.println("Opção inválida! Digite novamente.\n");
            }
        }
    }

    private void mostrarMapa(boolean revelarTesouro) {
        System.out.println("\nMapa:");
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (revelarTesouro && i == linhaTesouro && j == colunaTesouro && mapa[i][j] != 'T')
                    System.out.print("T ");
                else
                    System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void animacaoSaida() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print("\rSaindo.  ");
                Thread.sleep(250);
                System.out.print("\rSaindo.. ");
                Thread.sleep(250);
                System.out.print("\rSaindo...");
                Thread.sleep(250);
                System.out.print("\rSaindo   ");
                Thread.sleep(250);
            }
            System.out.println("\nAté logo!");
        } catch (InterruptedException ignored) {}
    }
}
