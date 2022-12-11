// Ola professor, consegui fazer grande parte do jogo, porem não consegui
// fazer a comparação das cartelas dos jogadores com as sorteadas.
// mesmo assim, acredito que o jogo esteja completo o suficiente para jogas
// do mesmo jeito que jogamos na ultima aula! Obrigado pelas aulas.

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] jogadores = getPlayers();
        int[][] cartelasBingo = new int[jogadores.length][5];
        int[][] cartelasMarcadas = new int[jogadores.length][5];


        for ( int i = 0; i < jogadores.length; i++){
            switch (manualAutomatico()) {
                case 1:
                    cartelasBingo[i] = gerarCartela();
                    break;
                case 2:
                    cartelasBingo[i] = bingoManual();
                    break;
                default:
                manualAutomatico();
                break;
            }
        }

        jogo(jogadores, cartelasBingo, cartelasMarcadas);
        int[] sorteio = gerarCartela();

        boolean resp = true;
        while ( resp ) {
            System.out.println("Deseja continuar ? 1 para sim e 2 para sair");
            int resp1 = sc.nextInt();
            if (resp1 == 1) {
                resp = true;
                System.out.println("A cartela sorteada foi --> " + Arrays.toString(gerarCartela()));

            }
            else if (resp1 == 2) {
                System.out.println("Saindo.... Volte sempre!!");
                break;
            }
        }

    }

    public static String[] getPlayers() {
        Scanner sc = new Scanner(System.in);

        System.out.println("------- BEM VINDO AO BINGO ------");
        System.out.println("------- BOA SORTE !!!!!!!! ------");
        System.out.println("---------------------------------");

        System.out.println("Digite o apelido dos jogadores separados por '-':  ");
        String nome = sc.nextLine();

        String[] apelidos = nome.split("-");

        return apelidos;

    }

    public static int manualAutomatico() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DESEJA O MODO AUTOMATUCO OU MANUAL ?");
        System.out.println("DIGITE 1 PARA --> AUTOMATICO");
        System.out.println("DIGITE 2 PARA --> MANUAL");
        return scanner.nextInt();
    }




    public static int[] bingoManual() {
        Scanner sc = new Scanner(System.in);
        System.out.println("DIGITE O NUMERO DA CARTELA: ");
        String cartela = sc.nextLine();
        String[] numbers = cartela.split(",");
        int[] bingoCard = new int[5];
        for (int i = 0; i < 5; i++) {
            bingoCard[i] = Integer.parseInt(numbers[i]);
        }
        return bingoCard;

    }

    public static int[] gerarCartela(){
        int[] valores = new int[5];
        Random random = new Random();

        int i = 0;
        while(i < valores.length){
            valores[i] = random.nextInt(20);
            boolean colide = false;

            for(int j = 0; j < i; j++){
                if(valores[i] == valores[j]){
                    colide = true;
                    break;
                }
            }
            if(!colide){
                i++;
            }
        }
        return valores;
    }

    public static void jogo (String[] apelidos, int[][] cartelasBingo, int[][] cartelasMarcadas){
        int[] sorteio = gerarCartela();
        System.out.println("------------JOGADORES: ------------");
        System.out.println(" ");

        for(int i = 0; i < apelidos.length; i++){
            System.out.printf("Jogador %d: %s\n", i+1, apelidos[i]);
            System.out.printf("cartela: ");

            for(int j = 0; j < 5; j++){
                System.out.printf("[%d]",cartelasBingo[i][j]);
            }
            System.out.println(" ");
            System.out.printf("cartela marcada: ");
            for(int j = 0; j < 5; j++){
                if (sorteio[j] == cartelasBingo[i][j]) {
                    cartelasMarcadas[i][j] = 1;
                }
                System.out.printf("[%d]",cartelasMarcadas[i][j]);
            }
            System.out.println(" ");
            System.out.println("-----------------------");

        }
        System.out.println("A cartela sorteada foi --> " + Arrays.toString(sorteio));
    }

}