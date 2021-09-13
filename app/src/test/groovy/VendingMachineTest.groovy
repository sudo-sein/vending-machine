import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*

class VendingMachineTest {

    VendingMachine vendingMachine;

    @BeforeEach
    void "setup"() {
        vendingMachine = new VendingMachine(new VendingMachine.DummyDispenser());
    }

    @Test
    void "it should accept 2 cents"() {
        boolean result = this.vendingMachine.coinInput(2);
        assertTrue(result);
    }

    @Test
    void "should accept 5 cents"() {
        boolean result = this.vendingMachine.coinInput(5);
        assertTrue(result);
    }

    @Test
    void "should reject other coins"() {
        List<Integer> coins = List.of(1,7,10,15);
        coins.forEach({ coin ->
            boolean result = this.vendingMachine.coinInput(coin)
            assertFalse(result);
        });
    }

    @Test
    void "should return -1 for TEA if there is not enough money"() {
        Integer result = this.vendingMachine.teaButtonPressed();
        assertEquals(-1, result);
    }

    @Test
    void "should return -1 for COFFEE if there is not enough money"() {
        Integer result = this.vendingMachine.coffeeButtonPressed();
        assertEquals(-1, result);
    }

    @Test
    void "should return all the coins if no drink is dispensed"() {
        List<Integer> coins = List.of(5,2,2,5);
        coins.forEach({coin -> this.vendingMachine.coinInput(coin)});
        Integer result = this.vendingMachine.returnAllCoinsPressed();
        assertEquals(14, result);
    }

    @Test
    void "should dispense coffee when there is enough money"() {
        List<Integer> coins = List.of(5,5,5,5,5,5,5,5,2);
        coins.forEach({coin -> this.vendingMachine.coinInput(coin)});
        Integer result = this.vendingMachine.coffeeButtonPressed();
        assertEquals(0, result);
    }

    @Test
    void "should dispense tea when there is enough money"() {
        List<Integer> coins = List.of(5,5,5,5,5,5,2);
        coins.forEach({coin -> this.vendingMachine.coinInput(coin)});
        Integer result = this.vendingMachine.teaButtonPressed();
        assertEquals(0, result);
    }

    @Test
    void "should return change when TEA is dispensed"() {
        List<Integer> coins = List.of(5,5,2, 5,5,5,5,2,5);
        coins.forEach({coin -> this.vendingMachine.coinInput(coin)});
        Integer result = this.vendingMachine.teaButtonPressed();
        assertEquals(7, result);
    }

    @Test
    void "should return change when COFFEE is dispensed"() {
        List<Integer> coins = List.of(5,5,5,2,5,5,5,5,5,2,5);
        coins.forEach({coin -> this.vendingMachine.coinInput(coin)});
        Integer result = this.vendingMachine.coffeeButtonPressed();
        assertEquals(7, result);
    }

}
