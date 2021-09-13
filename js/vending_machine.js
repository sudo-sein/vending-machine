const TEA_PRICE = 45;
const COFFEE_PRICE = 125;

function VendingMachine(dispenser) {
    this.dispenser = dispenser;
    this.acceptedCoins = [5,20];
    this.insertedCoins = [0];
}

// Return a boolean to show if the coin was accepted.
VendingMachine.prototype.insertCoin = function(coinValue) {
    if (this.acceptedCoins.includes(coinValue)){
        this.insertedCoins.push(coinValue);
        return true;
    }
    return false;
};

// Call serveTea and return the total value of coins returned, or -1 if there are insufficient coins in the machine.
VendingMachine.prototype.pressTea = function() {
    const total = this.insertedCoins.reduce((acc, val) => acc+val);
    if (total > TEA_PRICE) {
        this.dispenser.serveTea();
        this.consumeCoins(TEA_PRICE);
        return this.pressReturn();
    }

    return -1;
};

// Call serveCoffee and return the total value of coins returned, or -1 if there are insufficient coins in the machine.
VendingMachine.prototype.pressCoffee = function() {
    const total = this.insertedCoins.reduce((acc, val) => acc+val);
    if (total > COFFEE_PRICE) {
        this.dispenser.serveCoffee();
        this.consumeCoins(COFFEE_PRICE);
        return this.pressReturn();
    }
    return -1;
};

VendingMachine.prototype.consumeCoins = function(amount) {
    var total = 0;
    this.insertedCoins = this.insertedCoins.map(value => {
        if (total < amount) {
            total = total + value;
            return 0;
        }
        return value;
    });
};

// Return the total value of coins in the machine.
VendingMachine.prototype.pressReturn = function() {
    return this.insertedCoins.reduce((acc, val) => acc+val);
};

module.exports = VendingMachine;


function DummyDispenser() {}

DummyDispenser.prototype.serveCoffee = function() {
    console.log("Coffee served");
};

DummyDispenser.prototype.serveTea = function() {
    console.log("Tea served");
};

if (require.main === module) {
    var machine = new VendingMachine(new DummyDispenser());
    console.log(machine.pressCoffee());
}
