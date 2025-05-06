package controller;

import java.util.Arrays;
/**
 * Utility class providing static methods to validate user input
 * such as transaction amount and category.
 */
public class InputValidation {
  /**
   * Private constructor to prevent instantiation of utility class.
   */
  private InputValidation() {
    // Prevent instantiation
  }

  /**
   * Checks whether the given amount is a valid transaction value.
   *
   * @param amount the transaction amount to validate
   * @return true if the amount is greater than 0 and less than or equal to 1000; false otherwise
   */
  public static boolean isValidAmount(double amount) {
    
    // Check range
    if(amount >1000) {
      return false;
    }
    if (amount < 0){
      return false;
    }
    if (amount == 0){
      return false;
    }
    return true;
  }

  /**
   * Validates the category string to ensure it is non-empty, alphabetical,
   * and matches one of the allowed categories: food, travel, bills, entertainment, other.
   *
   * @param category the transaction category to validate
   * @return true if the category is valid; false otherwise
   */
  public static boolean isValidCategory(String category) {

    if(category == null) {
      return false; 
    }
  
    if(category.trim().isEmpty()) {
      return false;
    }

    if(!category.matches("[a-zA-Z]+")) {
      return false;
    }

    String[] validWords = {"food", "travel", "bills", "entertainment", "other"};

    if(!Arrays.asList(validWords).contains(category.toLowerCase())) {
      // invalid word  
      return false;
    }
  
    return true;
  
  }

}