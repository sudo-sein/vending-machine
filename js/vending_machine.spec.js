import * as VendingMachine from "./vending_machine";


describe('Vending Machine', function () {
    let machine;

    beforeEach(() => {
        machine = new VendingMachine(new TestDispenser())
    });


    it('should accept 5 cents', function () {

    });

    it('should accept 20 cents', function () {

    });

    it('should reject 50 cents', function () {

    });

    it('should dispense coffee when there is enough money', function () {

    });

    it('should dispense tea when there is enough money', function () {

    });

    it('should return -1 for TEA if there is not enough money', function () {

    });

    it('should return -1 for COFFEE if there is not enough money', function () {

    });

    it('should return all the coins if no drink is dispensed', function () {

    });

    it('should return change when TEA is dispensed', function () {

    });

    it('should return change when COFFEE is dispensed', function () {

    });

    it('should track money correctly', function () {

    });


});


function TestDispenser() {}

TestDispenser.prototype.serveCoffee = function() {
    return "Coffee served";
};

TestDispenser.prototype.serveTea = function() {
    return "Tea served";
};