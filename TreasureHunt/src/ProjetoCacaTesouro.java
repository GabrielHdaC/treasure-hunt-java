import java.util.Random;
import java.util.Scanner;

public class ProjetoCacaTesouro {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    int nivel;
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

        int escolha;
        System.out.println("Bem-vindo(a) ao caça ao Tesouro");
        System.out.println("[1] Iniciar" +
                "\n[2] Sair");
        System.out.printf("\nEntrada: ");
        escolha = sc.nextInt();

        if (escolha == 2) {
            animacaoSaida();
        } else if (escolha == 1) {
            definirDificuldade();
        } else {
            System.out.println("Opção inválida! Digite novamente\n");
            inicializarJogo();
        }
    }

    public void definirDificuldade() {

        int escolhaDificuldade;
        System.out.println("\nEscolha a dificuldade do jogo: " +
                "\n[1] Fácil   (10 tentativas)" +
                "\n[2] Médio   (8 tentativas)" +
                "\n[3] Difícil (6 tentativas)");
        System.out.printf("\nEntrada: ");
        escolhaDificuldade = sc.nextInt();

        switch (escolhaDificuldade) {
            case 1:
                comecaJogo(5, 5, 10);
                break;

            case 2:
                comecaJogo(7, 7, 8);
                break;

            case 3:
                comecaJogo(10, 10, 6);
                break;

            default:
                System.out.println("Opção Inválida! Escolha novamente a opção");
                definirDificuldade();
                break;
        }
    }

    private void comecaJogo(int linhas, int colunas, int tentativasMax) {

        mapa = new char[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                mapa[i][j] = '-';
            }
        }

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

            // valida coordenadas
            if (linha < 0 || linha >= linhas || coluna < 0 || coluna >= colunas) {
                System.out.println("Coordenadas inválidas! Tente novamente.");
                tentativa--; // não conta tentativa errada
                continue;
            }

            // verifica se achou o tesouro
            if (linha == linhaTesouro && coluna == colunaTesouro) {
                mapa[linha][coluna] = 'T';
                encontrou = true;
                break;
            } else {
                mapa[linha][coluna] = 'X';
                System.out.println("Nada aqui... tente novamente!");
            }
        }

        // resultado final do jogo
        if (encontrou) {
            System.out.println("\nVocê encontrou o tesouro!");
        } else {
            System.out.println("\nFim de jogo! Suas tentativas acabaram.");
        }

        System.out.println("O tesouro estava em: (" + linhaTesouro + ", " + colunaTesouro + ")");
        mostrarMapa(true);
        continuarJogo(); // aqui é onde o jogo realmente para ou recomeça
    }

    private void continuarJogo() {

        int entradaContinuacao;
        System.out.println("\nDeseja continuar o jogo?" +
                "\n[1] Sim" +
                "\n[2] Não");
        System.out.printf("\nEntrada: ");
        entradaContinuacao = sc.nextInt();

        if (entradaContinuacao == 1) {
            definirDificuldade();
        } else if (entradaContinuacao == 2) {
            animacaoSaida();
        } else {
            System.out.println("Opção inválida! Digite novamente.\n");
            continuarJogo();
        }
    }

    private void mostrarMapa(boolean revelarTesouro) {
        System.out.println("\nMapa:");
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (revelarTesouro && i == linhaTesouro && j == colunaTesouro)
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
                Thread.sleep(300);
                System.out.print("\rSaindo.. ");
                Thread.sleep(300);
                System.out.print("\rSaindo...");
                Thread.sleep(300);
                System.out.print("\rSaindo   ");
                Thread.sleep(300);
            }
            System.out.println("\nAté logo!");
        } catch (InterruptedException ignored) {
        }
    }
}
