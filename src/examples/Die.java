package lektion9.Die;

import java.util.Random;

public class Die {
    private int sides;
    private int value;
    private Random random;

    public Die(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    public int roll() {
        value = random.nextInt(sides) + 1;
        return value;
    }

    public int getSides() {
        return sides;
    }


    @Override
    public String toString() {
        return "Die{" +
                "sides=" + sides +
                ", value=" + value +
                ", random=" + random +
                '}';
    }

}
