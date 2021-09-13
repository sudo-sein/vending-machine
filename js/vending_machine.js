const TEA_PRICE = 45;
const COFFEE_PRICE = 125;

function VendingMachine(dispenser) {
    this.dispenser = dispenser;
    this.acceptedCoins = [20,5];
    this.insertedCoins = [];
}

// Return a boolean to show if the coin was accepted.
VendingMachine.prototype.insertCoin = function (coinValue) {
    if (this.acceptedCoins.includes(coinValue)) {
        this.insertedCoins.push(coinValue);
        return true;
    }
    return false;
};

// Call serveTea and return the total value of coins returned, or -1 if there are insufficient coins in the machine.
VendingMachine.prototype.pressTea = function () {
    // const total = this.insertedCoins.reduce((acc, val) => acc + val);
    if (this.hasCoins(TEA_PRICE)) {
        this.dispenser.serveTea();
        this.consumeCoins(TEA_PRICE);
        return this.pressReturn();
    }

    return -1;
};

// Call serveCoffee and return the total value of coins returned, or -1 if there are insufficient coins in the machine.
VendingMachine.prototype.pressCoffee = function () {
    const total = this.insertedCoins.length > 0 ? this.insertedCoins.reduce((acc, val) => acc + val) : 0;

    if (this.hasCoins(COFFEE_PRICE)) {
        this.dispenser.serveCoffee();
        this.consumeCoins(COFFEE_PRICE);
        return this.pressReturn();
    }
    return -1;
};

VendingMachine.prototype.hasCoins = function (amount) {
    const requiredCoins = this.requiredCoins(amount);
    const contains = [];
    for (let i = 0; i < requiredCoins.length; i++) {
        const amountInserted = this.insertedCoins.filter(coin => this.acceptedCoins[i] === coin).length;
        contains.push(amountInserted >= requiredCoins[i]);
    }
    return contains.every(isEnough => isEnough);
}

VendingMachine.prototype.consumeCoins = function (amount) {
    const requiredCoins = this.requiredCoins(amount);
    for (let i = 0; i < requiredCoins.length; i++) {
        for (let j = 0; j < requiredCoins[i]; j++) {
            const index = this.insertedCoins.indexOf(this.acceptedCoins[i])
            this.insertedCoins.splice(index,1)
        }
    }
}

VendingMachine.prototype.requiredCoins = function (amount) {
    var finalresult = [],
        totalAmount = amount;
    for (var i = 0; i < this.acceptedCoins.length; i++) {
        var currentTypeValue = this.acceptedCoins[i],
            typeCount = Math.floor(totalAmount / currentTypeValue);
        finalresult.push(typeCount);
        totalAmount -= (currentTypeValue * typeCount);
    }
    return finalresult;
};

// Return the total value of coins in the machine.
VendingMachine.prototype.pressReturn = function () {
    const returnAmount = this.insertedCoins.length > 0 ? this.insertedCoins.reduce((acc, val) => acc + val) : 0;
    this.insertedCoins = [];
    return returnAmount;
};

module.exports = VendingMachine;


function DummyDispenser() {
}

DummyDispenser.prototype.serveCoffee = function () {
    console.log("Coffee served");
};

DummyDispenser.prototype.serveTea = function () {
    console.log("Tea served");
};

if (require.main === module) {
    var machine = new VendingMachine(new DummyDispenser());
    console.log(machine.requiredCoins(125));
}
