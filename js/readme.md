# Vending Machine JS

This is an example project for blueoptima test.

An example solution with tests written in jest, 
alongside mutation tests with stryker.

## Install

To run the script packages are not required, 
only to test the solution. This will install Jest and Stryker.

```shell
$ npm install
```

## Run

This script runs as node module.

```shell
$ npm run
```

## Test

Runs jest tests with coverage report, and stryker 
mutation tests. There are some mutations possible,
as tests that are written only test exercise requirements.

```shell
$ npm test
```


## Exercise

**Vending Machine** 

Constraints:
- Machine accepts only 2 and 5 coins
- Cost of a coffee is 42 dollars
- Cost of a tea is 32 dollars

The machine has three buttons: 
1. Request Coffee 
2. Request Tea 
3. Return all coins added 

The machine should only return whole coins which are not used to pay (even in part) for a product. 
For example, no change is returned if coffee is bought with seven 5 cent coins 
(because the user has not entered any 2 cent coins that could be returned to them), 
however 2 cents is returned if the tea button is pressed after the user has entered 
eight 5 cent coins and two 2 cent coins. If there is not enough money in the machine 
to pay for the selected drink, return -1 for the amount of change to indicate this. 

Partial credit is given for solutions which does one or more of the following: 

* Accepts 2 and 5 cent coins correctly 
* Rejects other coins 
* Dispenses tea and coffee when the buttons are pressed if there is sufficient money in the machine 
* Returns -1 when there is insufficient money in the machine 
* Returns the correct change after a button press 
* Keeps track of the money in the machine correctly 

Full credit is awarded for a solution which correctly adheres to all task requirements. 

Important Information: 
1. Do not modify names or signatures of any classes or methods provided as skeleton implementation 
