package acme;

public interface IVendingMachine {

  //user enters a new coin into the machine with "value".
  public boolean coinInput(int value);

  //If sufficient funds available call "serveCoffee()" and the sum total value of the coins to be returned to the user; otherwise return -1. No coins will remain in the machine after a drink is served.
  public int coffeeButtonPressed();

  //If sufficient funds available call "serveTea()" and the sum total value of the coins to be returned to the user; otherwise return -1. No coins will remain in the machine after a drink is served.
  public int teaButtonPressed();

  //Return the sum total value of all coins entered. After this method is called, you may assume coins have been returned.
  public int returnAllCoinsPressed();
}
