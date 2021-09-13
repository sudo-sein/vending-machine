const VendingMachine = require("./vending_machine");


describe('Vending Machine', function () {
    let machine;

    beforeEach(() => {
        machine = new VendingMachine(new TestDispenser())
    });


    it('should accept 5 cents', function () {
        const result = machine.insertCoin(5);
        expect(result).toBeTruthy();
    });

    it('should accept 20 cents', function () {
        const result = machine.insertCoin(20);
        expect(result).toBeTruthy();
    });

    it('should reject other coins then 5 and 20', function () {
        const coins = [1,2,10,50];
        for (let i = 0; i < coins.length; i++) {
            const result = machine.insertCoin(coins[i]);
            expect(result).toBeFalsy();
        }
    });

    it('should return -1 for TEA if there is not enough money', function () {
        const result = machine.pressTea();
        expect(result).toBe(-1);
    });

    it('should return -1 for COFFEE if there is not enough money', function () {
        const result = machine.pressCoffee();
        expect(result).toBe(-1);
    });


    it('should return change when TEA is dispensed', function () {

        const coffeeCoins = [20,20,20,5,5];
        coffeeCoins.forEach(coin => machine.insertCoin(coin));
        const result = machine.pressTea();
        expect(result).toBe(25);
    });

    it('should return change when COFFEE is dispensed', function () {

        const coffeeCoins = [20,20,20,20,20,20,20,5,5];
        coffeeCoins.forEach(coin => machine.insertCoin(coin));
        const result = machine.pressCoffee();
        expect(result).toBe(25);
    });

    it('should dispense tea when there is enough money', function () {
        console.log = jest.fn();
        const teaCoins = [20,20,5];
        teaCoins.forEach(coin => machine.insertCoin(coin));
        const result = machine.pressTea();
        expect(result).toBe(0);
        expect(console.log).toHaveBeenCalledWith('Tea served');
    });

    it('should dispense coffee when there is enough money', function () {
        console.log = jest.fn();
        const coffeeCoins = [20,20,20,20,20,20,5];
        coffeeCoins.forEach(coin => machine.insertCoin(coin));
        const result = machine.pressCoffee();
        expect(result).toBe(0);
        expect(console.log).toHaveBeenCalledWith('Coffee served');
    });


    it('should return all the coins if no drink is dispensed', function () {
        const teaCoins = [20,20,5,5,5];
        teaCoins.forEach(coin => machine.insertCoin(coin));
        const result = machine.pressReturn();
        expect(result).toBe(55);
    });


    it('should track money correctly', function () {
        const teaCoins = [20,20,5];
        teaCoins.forEach(coin => machine.insertCoin(coin));
        const result = machine.pressReturn();
        expect(result).toBe(45)
    });

});


function TestDispenser() {}

TestDispenser.prototype.serveCoffee = function() {
    console.log("Coffee served");
};

TestDispenser.prototype.serveTea = function() {
    console.log("Tea served");
};