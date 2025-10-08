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
        System.out.printf("\nEscolha: ");
        escolha = sc.nextInt();

        if (escolha == 2) {
            animacaoSaida();
        } else {
            definirDificuldade();
        }
        if (escolha != 1 && escolha != 2) {
            System.out.println("Opção Invalida! Digite novamente\n");
            inicializarJogo();
        }
    }

    public void definirDificuldade() {

        int escolhaDificuldade;
        System.out.println("\nEscolha a dificuldade do jogo: " +
                "\n[1] Fácil (10 tentativas)" +
                "\n[2] Médio (8 tentativas)" +
                "\n[3] Difícil (6 tentativas)");
        System.out.printf("\nEscolha: ");
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
                System.out.print("\rSaindo   "); // apaga os pontos
                Thread.sleep(300);
            }
        } catch (InterruptedException ignored) {
            // ignora
        }
    }
}
