import acme.IVendingMachine;
import acme.IDrinkDispenser;

import java.util.ArrayList;
import java.util.List;

/**
 * When using this example, keep in mind that not all methods and capabilities of Java
 * might be available in browser compiler, like lambdas and static methods.
 *
 */
public class VendingMachine implements IVendingMachine {

    final Integer TEA_PRICE = 32;
    final Integer COFFEE_PRICE = 42;
    List<Integer> acceptedCoins = List.of(5,2);
    List<Integer> coinGiven = new ArrayList<Integer>();
    IDrinkDispenser dispenser;

    public VendingMachine(IDrinkDispenser dispenser) {
        this.dispenser = dispenser;
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
        if (this.hasCoins(COFFEE_PRICE)) {
            this.dispenser.serveCoffee();
            this.consumeCoins(COFFEE_PRICE);
            return this.returnAllCoinsPressed();
        }
        return -1;
    }

    // If sufficient funds available call "serveTea()" on the dispenser and return the total of the coins returned to the user; otherwise -1.
    public int teaButtonPressed() {
        if (this.hasCoins(TEA_PRICE)) {
            this.dispenser.serveTea();
            this.consumeCoins(TEA_PRICE);
            return this.returnAllCoinsPressed();
        }
        return -1;
    }

    private void consumeCoins(Integer amount) {
        List<Integer> requiredCoins = this.requiredCoins(amount);
        for (int i = 0; i < requiredCoins.size(); i++) {
            for (int j = 0; j < requiredCoins.get(i); j++) {
                this.coinGiven.remove(this.acceptedCoins.get(i));
            }
        }
    }

    private List<Integer> requiredCoins(Integer amount) {
        List<Integer> finalresult = new ArrayList<>();
        Integer totalAmount = amount;
        for (Integer currentTypeValue : this.acceptedCoins) {
            Integer typeCount = Math.floorDiv(totalAmount, currentTypeValue);
            finalresult.add(typeCount);
            totalAmount -= (currentTypeValue * typeCount);
        }
        return finalresult;
    };

    private boolean hasCoins(Integer amount) {
        List<Integer> requiredCoins = this.requiredCoins(amount);
        List<Boolean> contains = new ArrayList<>();

        for (int i = 0; i < requiredCoins.size(); i++) {
            int finalI = i;
            long inserted = this.coinGiven.stream().filter(c -> this.acceptedCoins.get(finalI).equals(c)).count();
            contains.add(inserted >= requiredCoins.get(i));
        }
        return contains.stream().allMatch(isEnough -> isEnough);
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
