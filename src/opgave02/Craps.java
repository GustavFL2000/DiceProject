package opgave02;
import opgave01.RollTwoDice;

public class Craps {
    public static void main(String[] args) {
        playCraps();
    }

    // Spil det første kast i craps.
    public static void playCraps() {
        int[] dice = RollTwoDice.rollDice();
        int sum = dice[0] + dice[1];
        System.out.println("Du rullede: " + dice[0] + " og " + dice[1]);

        if (sum == 7 || sum == 11) {
            System.out.println("Du vandt!");
        } else if (sum == 2 || sum == 3 || sum == 12) {
            System.out.println("Du tabte!");
        } else {
            System.out.println("Dit point er: " + sum);
            boolean won = rollforPoint(sum);
            if (won) {
                System.out.println("Du vandt ved at slå dit point igen!");
            } else {
                System.out.println("Du tabte ved at slå 7."); //hvis 7 bliver rullet på andre slag end det første taber man
            }
        }
    }

    // Roll the 2 dice until point or 7 is rolled.
    // Return true, if point is rolled.
    public static boolean rollforPoint(int point) {
        while (true) {
            int[] dice = RollTwoDice.rollDice();
            int sum = dice[0] + dice[1];
            System.out.println("Du rullede: " + dice[0] + " og " + dice[1]);
            if (sum == point) {
                return true;
            } else if (sum == 7) {
                return false;
            }
        }
    }
}
