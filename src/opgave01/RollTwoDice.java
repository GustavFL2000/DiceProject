package opgave01;

import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int sum = 0;
    private static int doublesCount = 0;
    private static int lowSum = 0;
    private static int highSum = 0;
    private static int[] count = new int[6];

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul to terninger.");
        System.out.println();

        playTwoDie();
        System.out.println();
        System.out.println("Tak for at spille, rul to terninger.");
    }


    private static void playTwoDie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul to terninger? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] dice = rollDice();
            System.out.println("Du rullede: " + dice[0] + " og " + dice[1]);
            System.out.println();
            updateStatistics(dice);
            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }
        printStatistics();
        scanner.close();
    }


    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static void updateStatistics(int[] dice) {
        rollCount = rollCount + 2;
        sum = sum + dice[0] + dice[1];
        lowSum = dice[0] + dice[1];

        if (lowSum > highSum) {
            highSum = lowSum;
        }
        if (dice[0] == dice[1]) {
            doublesCount++;
        }

        for (int i = 0; i < dice.length; i++) { // kører igennem terningerne (2x)
            count[dice[i] - 1]++; // opdaterer antallet af hver terninge værdi
        } // når terningen viser 1, opdateres count[0], når den viser 6, opdateres count[5]
    }

    // Print antallet af hver terninge værdi
    public static void printCountArray(int[] array) {
        for (int i = 0; i < array.length; i++) { // kører igennem arrayet
            System.out.println("Antal " + (i + 1) + " er: " + array[i]); // printer antallet af hver terninge værdi
        }
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal rul:", rollCount);
        System.out.printf("%17s %4d\n", "Sum af øjne:", sum);
        System.out.printf("%17s %4d\n", "Antal par:", doublesCount);
        System.out.printf("%17s %4d\n", "Højeste sum:", highSum);
        printCountArray(count);

    }

    public static int[] rollDice() {
        /*int die1 = rollDie();
        int die2 = rollDie();
        return new int[]{die1, die2};*/
        int[] die = new int[2];
        for (int i = 0; i < die.length; i++) {
            die[i] = rollDie();
        }
        return die;
    }
}
