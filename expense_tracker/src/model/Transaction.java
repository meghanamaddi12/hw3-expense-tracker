package model;

import controller.InputValidation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents an immutable financial transaction with an amount, category, and timestamp.
 * Validates input during construction and automatically generates a timestamp.
 */
public class Transaction {

  /**
   * Formatter used to generate a timestamp string in the format "dd-MM-yyyy HH:mm".
   */
  public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    
  //final means that the variable cannot be changed
  private final double amount;
  private final String category;
  private final String timestamp;

  /**
   * Constructs a new Transaction after validating the amount and category.
   * Automatically assigns the current timestamp.
   *
   * @param amount the monetary amount of the transaction
   * @param category the category of the transaction (e.g., food, travel)
   * @throws IllegalArgumentException if the amount or category is invalid
   */
  public Transaction(double amount, String category) {
    // Since this is a public constructor, perform input validation
    // to guarantee that the amount and category are both valid
    if (InputValidation.isValidAmount(amount) == false) {
	throw new IllegalArgumentException("The amount is not valid.");
    }
    if (InputValidation.isValidCategory(category) == false) {
	throw new IllegalArgumentException("The category is not valid.");
    }
      
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  /**
   * Returns the amount of the transaction.
   *
   * @return the transaction amount
   */
  public double getAmount() {
    return amount;
  }

  //setter method is removed because we want to make the Transaction immutable
  // public void setAmount(double amount) {
  //   this.amount = amount;
  // }

  /**
   * Returns the category of the transaction.
   *
   * @return the transaction category
   */
  public String getCategory() {
    return category;
  }

  // public void setCategory(String category) {
  //   this.category = category; 
  // }

  /**
   * Returns the timestamp when the transaction was created.
   *
   * @return the transaction timestamp as a formatted string
   */
  public String getTimestamp() {
    return timestamp;
  }
  //private helper method to generate timestamp
  /**
   * Generates a timestamp string based on the current date and time.
   *
   * @return the formatted timestamp string
   */
  private String generateTimestamp() {
     return dateFormatter.format(new Date());
  }

}
