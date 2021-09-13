import acme.IVendingMachine;
import acme.IDrinkDispenser;

import java.util.ArrayList;
import java.util.List;

/**
 * Not enough time to finish the task, and not all possibilities of Java available.
 *
 */
public class VendingMachine implements IVendingMachine {

    final Integer TEA_PRICE = 32;
    final Integer COFFEE_PRICE = 42;
    List<Integer> acceptedCoins = new ArrayList<Integer>();
    List<Integer> coinGiven = new ArrayList<Integer>();
    IDrinkDispenser dispenser;

    public VendingMachine(IDrinkDispenser dispenser) {
        this.dispenser = dispenser;
        this.acceptedCoins.add(2);
        this.acceptedCoins.add(5);
    }

    // Return true if the coin is accepted.
    public boolean coinInput(int value) {
        if (acceptedCoins.contains(value)) {
            coinGiven.add(value);
            return true;
        }
        return false;
    }

    // If sufficient funds available call "serveCoffee()" on the dispenser and return the total of the coins returned to the user; otherwise -1.
    public int coffeeButtonPressed() {
        Integer amountGiven = coinGiven.stream().reduce(0, Integer::sum);
        if (amountGiven > COFFEE_PRICE) {
            this.dispenser.serveCoffee();
            this.consumeCoins(COFFEE_PRICE);
            return this.returnAllCoinsPressed();
        }
        return -1;
    }

    // If sufficient funds available call "serveTea()" on the dispenser and return the total of the coins returned to the user; otherwise -1.
    public int teaButtonPressed() {
        Integer amountGiven = coinGiven.stream().reduce(0, Integer::sum);
        if (amountGiven > TEA_PRICE) {
            this.dispenser.serveCoffee();
            this.consumeCoins(TEA_PRICE);
            return this.returnAllCoinsPressed();
        }
        return -1;
    }

    private void consumeCoins(Integer amount) {
        final Integer[] total = {0};
        coinGiven.stream().map(integer -> {
            if (total[0] < amount) {
                total[0] = total[0] + integer;
                return 0;
            }
            return integer;
        });
    }

    // Return the total of the coins returned to the user.
    public int returnAllCoinsPressed() {
        return coinGiven.stream().reduce(0, Integer::sum);
    }

    static class DummyDispenser implements IDrinkDispenser {
        public void serveCoffee() {
            System.out.println("Coffee served");
        }

        public void serveTea() {
            System.out.println("Tea served");
        }
    }

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine(new DummyDispenser());
        System.out.println(machine.coffeeButtonPressed());
    }
}
