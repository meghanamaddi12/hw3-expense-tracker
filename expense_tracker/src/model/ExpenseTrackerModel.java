package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * The model component in the MVC architecture.
 * Manages the list of transactions and provides methods to modify or access them.
 * Ensures data integrity through encapsulation.
 */
public class ExpenseTrackerModel {

  //encapsulation - data integrity
  private List<Transaction> transactions;
  /**
   * Stack to hold recently removed transactions for undo functionality.
   */
  private Stack<Transaction> undoStack = new Stack<>();


  /**
   * Constructs an ExpenseTrackerModel with an empty list of transactions.
   */
  public ExpenseTrackerModel() {
    transactions = new ArrayList<>();
  }

  /**
   * Adds a non-null transaction to the model.
   *
   * @param t the Transaction to be added
   * @throws IllegalArgumentException if the transaction is null
   */
  public void addTransaction(Transaction t) {
    // Perform input validation to guarantee that all transactions added are non-null.
    if (t == null) {
      throw new IllegalArgumentException("The new transaction must be non-null.");
    }
    transactions.add(t);
  }

  /**
   * Removes the specified transaction from the model.
   *
   * @param t the Transaction to be removed
   */
  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }
  /**
   * Removes the last transaction and pushes it to the undo stack.
   * @return the removed transaction or null if list is empty
   */
  public Transaction removeLastTransaction() {
    if (transactions.isEmpty()) {
      return null;
    }
    Transaction removed = transactions.remove(transactions.size() - 1);
    undoStack.push(removed);
    return removed;
  }


  /**
   * Returns an unmodifiable copy of the current list of transactions.
   * This maintains encapsulation and prevents external modification.
   *
   * @return list of transactions
   */
  public List<Transaction> getTransactions() {
    //encapsulation - data integrity
    return Collections.unmodifiableList(new ArrayList<>(transactions));
  }

}
