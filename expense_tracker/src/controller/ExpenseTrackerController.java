package controller;

import view.ExpenseTrackerView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.ExpenseTrackerModel;
import model.Transaction;
import model.Filter.TransactionFilter;
/**
 * The controller component in the MVC architecture.
 * Handles user interactions, updates the model, and refreshes the view.
 * Applies the Strategy design pattern for filtering transactions.
 */

public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  /** 
   * The Controller is applying the Strategy design pattern.
   * This is the has-a relationship with the Strategy class 
   * being used in the applyFilter method.
   */
  private TransactionFilter filter;
  /**
   * Constructs an ExpenseTrackerController with the given model and view.
   *
   * @param model the data model for managing transactions
   * @param view the graphical user interface for the user
   */
  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Sets the filter strategy used for filtering transactions.
   *
   * @param filter the TransactionFilter implementation to apply
   */
  public void setFilter(TransactionFilter filter) {
    // Sets the Strategy class being used in the applyFilter method.
    this.filter = filter;
  }
  /**
   * Refreshes the table in the view with the current transactions from the model.
   */
  public void refresh() {
    List<Transaction> transactions = model.getTransactions();
    view.refreshTable(transactions);
  }

  /**
   * Adds a transaction to the model if valid and updates the view.
   *
   * @param amount the transaction amount
   * @param category the transaction category
   * @return true if the transaction was valid and added; false otherwise
   */
  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }

  /**
   * Applies the current filter to the transaction list and updates the view
   * with the filtered transactions.
   */
  public void applyFilter() {
    List<Transaction> filteredTransactions;
    // If no filter is specified, show all transactions.
    if (filter == null) {
      filteredTransactions = model.getTransactions();
    }
    // If a filter is specified, show only the transactions accepted by that filter.
    else {
      // Use the Strategy class to perform the desired filtering
      List<Transaction> transactions = model.getTransactions();
      filteredTransactions = filter.filter(transactions);
    }
    view.displayFilteredTransactions(filteredTransactions);
  }
  /**
   * Removes the last transaction using model and refreshes the table.
   */
  public void removeLastTransaction() {
    model.removeLastTransaction();
    refresh();
  }
}
