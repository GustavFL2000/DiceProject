package opgave03;

import opgave01.RollTwoDice; // vi genbruger rollDice()

import java.util.Scanner;

public class PigsExtended {
    private static int[] playerScore = {0, 0};
    private static int currentPlayer = 0;
    private static int winningScore; // brugerdefineret slutscore
    private static int[] totalRolls = {0, 0}; // samlede kast pr. spiller
    private static int[] turns = {0, 0};      // antal ture pr. spiller

    public static void main(String[] args) {
        playPigs();
    }

    private static void switchPlayer() {
        turns[currentPlayer]++; // afslut en tur for nuværende spiller
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public static void playPigs() {
        Scanner scanner = new Scanner(System.in);

        // Bruger vælger slutscore
        System.out.print("Indtast slutscore (fx 100): ");
        winningScore = scanner.nextInt();
        scanner.nextLine(); // ryd buffer

        System.out.println("Velkommen til Pigs! Først til " + winningScore + " point vinder!\n");

        while (playerScore[0] < winningScore && playerScore[1] < winningScore) {
            int roundScore = 0;
            int rollCountThisTurn = 0;

            System.out.println("Spiller " + (currentPlayer + 1) + "s tur. Total: " + playerScore[currentPlayer]);

            while (true) {
                System.out.print("Rul to terninger? ('ja/nej'): ");
                String answer = scanner.nextLine();

                if (answer.equalsIgnoreCase("nej")) {
                    playerScore[currentPlayer] += roundScore;
                    System.out.println("Spiller " + (currentPlayer + 1) + " stopper med " + playerScore[currentPlayer] + " point.\n");

                    // Tjek om vundet efter "nej"
                    if (playerScore[currentPlayer] >= winningScore) {
                        System.out.println("\nSpiller " + (currentPlayer + 1) + " har vundet med "
                                + playerScore[currentPlayer] + " point!");
                        printStatistics();
                        scanner.close();
                        return;
                    }

                    switchPlayer();
                    break;
                }

                int[] dice = RollTwoDice.rollDice(); // vi bruger rollDice fra opgave01
                rollCountThisTurn += 2;
                System.out.println("Du slog: " + dice[0] + " og " + dice[1]);

                if (dice[0] == 1 && dice[1] == 1) {
                    System.out.println("Av! Du slog (1,1) og mistede ALLE dine point!\n");
                    playerScore[currentPlayer] = 0;
                    switchPlayer();
                    break;

                } else if (dice[0] == 1 || dice[1] == 1) {
                    System.out.println("Uheldigt! Du mistede alle runde-point.\n");
                    roundScore = 0;
                    switchPlayer();
                    break;

                } else {
                    roundScore += dice[0] + dice[1];
                    System.out.println("Runde-point: " + roundScore + " | Potentiel total: "
                            + (playerScore[currentPlayer] + roundScore));
                }

                // Vinder-check undervejs
                if (playerScore[currentPlayer] + roundScore >= winningScore) {
                    playerScore[currentPlayer] += roundScore;
                    System.out.println("\nSpiller " + (currentPlayer + 1) + " har vundet med "
                            + playerScore[currentPlayer] + " point!");
                    totalRolls[currentPlayer] += rollCountThisTurn;
                    turns[currentPlayer]++;
                    printStatistics();
                    scanner.close();
                    return;
                }
            }

            // Gem kast for denne tur
            totalRolls[currentPlayer] += rollCountThisTurn;
        }
    }

    private static void printStatistics() {
        System.out.println("\n--- Statistik ---");
        for (int i = 0; i < 2; i++) {
            double avgRolls = turns[i] > 0 ? (double) totalRolls[i] / turns[i] : 0;
            System.out.printf("Spiller %d: %d point, %d ture, %.2f kast i gennemsnit pr. tur\n",
                    i + 1, playerScore[i], turns[i], avgRolls);
        }
    }
}

