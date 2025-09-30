package opgave03;

import java.util.Scanner;

import examples.RollOneDie;

public class Pigs {
    public static int[] playerScore = {95, 0};
    public static int currentPlayer = 0;

    public static void main(String[] args) {
        playPigs();
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % 2;
    }


    public static void playPigs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Velkommen til Pigs!\n");

        while (playerScore[0] < 100 && playerScore[1] < 100) {// spillet fortsætter indtil en spiller rammer 100 point
            int roundScore = 0;
            System.out.println("Spiller " + (currentPlayer + 1) + "s tur. Total: " + playerScore[currentPlayer]);
            while (true) {// spillerens tur
                System.out.print("Rul en terning? ('ja/nej'): ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("nej")) { //Kan vvælge at slutte turen og beholdesine point
                    playerScore[currentPlayer] += roundScore; //sumere tergmimge værdier slået i denne runde op og gemmer det i playerScore
                    System.out.println("Spiller " + (currentPlayer + 1) + " stopper med " + playerScore[currentPlayer] + " point.\n");
                    switchPlayer(); //Skifter spiller
                    break; // afslutter turen, men ikke hele spillet
                }

                int roll = RollOneDie.rollDie(); //
                System.out.println("Du slog: " + roll);
                if (roll == 1) { //slår en så får man ingen point denne runde
                    System.out.println("Uheldigt! Du mistede alle runde-point.\n");
                    roundScore = 0;
                    switchPlayer(); //Skifter spiller
                    break; // afslutter turen
                } else {
                    roundScore += roll; // hvis ikke 1 sumere vi det til round score
                    System.out.println("Runde-point: " + roundScore + " Potentiel total: " + (playerScore[currentPlayer] + roundScore));
                }
                if (playerScore[currentPlayer] + roundScore >= 100) {
                    playerScore[currentPlayer] += roundScore; // Tilføj runde-point til totalscoren
                    System.out.println("\nSpiller " + (currentPlayer + 1) + " har vundet med " + playerScore[currentPlayer] + " point!");
                    scanner.close();
                    return; // Afslut spillet
                }
            }
        }
        scanner.close();
    }
}
