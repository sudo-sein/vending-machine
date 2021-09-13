import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class VendingMachineTest {

    VendingMachine vendingMachine;

    @BeforeEach
    void "setup"() {
        vendingMachine = new VendingMachine(new VendingMachine.DummyDispenser());
    }

    @Test
    void "it should accept 2 cents"() {

    }

    @Test
    void "should accept 5 cents"() {

    }

    @Test
    void "should reject 10 cents"() {

    }

    @Test
    void "should dispense coffee when there is enough money"() {

    }

    @Test
    void "should dispense tea when there is enough money"() {

    }

    @Test
    void "should return -1 for TEA if there is not enough money"() {

    }

    @Test
    void "should return -1 for COFFEE if there is not enough money"() {

    }

    @Test
    void "should return all the coins if no drink is dispensed"() {

    }

    @Test
    void "should return change when TEA is dispensed"() {

    }

    @Test
    void "should return change when COFFEE is dispensed"() {

    }

    @Test
    void "should track money correctly"() {

    }

}
