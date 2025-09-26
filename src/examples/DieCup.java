package lektion9.DieCup;

import lektion9.Die.Die;
import java.util.ArrayList;
import java.util.List;

public class DieCup {
    private List<Die> dice;

    public DieCup() {
        dice = new ArrayList<>();
    }

    public DieCup(int numberOfDice, int sidesPerDie) {
        dice = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            dice.add(new Die(sidesPerDie));
        }
    }

    public void addDie(Die die) {
        dice.add(die);
    }

    public List<Integer> rollAll() {
        List<Integer> results = new ArrayList<>();
        for (Die die : dice) {
            results.add(die.roll());
        }
        return results;
    }

    public int getTotal() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.roll();
        }
        return sum;
    }

    public List<Die> getDice() {
        return dice;
    }

    @Override
    public String toString() {
        return "DieCup{" +
                "dice=" + dice +
                ", total=" + getTotal() +
                '}';
    }
}

