package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;
import java.awt.event.ActionListener;


/**
 * The view component of the MVC architecture for the Expense Tracker.
 * Builds and manages the Swing-based GUI, handles user inputs, and updates the table.
 */
public class ExpenseTrackerView extends JFrame {

  /** Table displaying all transactions. */
  private JTable transactionsTable;
  /** Button to trigger removing the last transaction */
  private JButton undoTransactionBtn;
  /** Button to trigger adding a transaction. */
  private JButton addTransactionBtn;
  /** Field for entering the transaction amount. */
  private JFormattedTextField amountField;
  /** Field for entering the transaction category. */
  private JTextField categoryField;
  /** Table model that holds the data for transactions. */
  private DefaultTableModel model;

  /** Field for entering category filter value. */
  private JTextField categoryFilterField;
  /** Button to trigger category filter. */
  private JButton categoryFilterBtn;

  /** Field for entering amount filter value. */
  private JTextField amountFilterField;
  /** Button to trigger amount filter. */
  private JButton amountFilterBtn;

  /** Button to clear active filters. */
  private JButton clearFilterBtn;

  /** List of currently displayed transactions in the table. */
  private List<Transaction> displayedTransactions = new ArrayList<>(); // ✅ Moved here

  /**
   * Constructs the Expense Tracker GUI, initializes all UI components,
   * and sets up layout and event wiring.
   */
  public ExpenseTrackerView() {
    setTitle("Expense Tracker");
    setSize(600, 400);

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    transactionsTable = new JTable(model);
    undoTransactionBtn = new JButton("Undo Last Transaction");
    undoTransactionBtn.setEnabled(false); // Initially disabled until a row is selected
    addTransactionBtn = new JButton("Add Transaction");

    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();
    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    JLabel categoryFilterLabel = new JLabel("Filter by Category:");
    categoryFilterField = new JTextField(10);
    categoryFilterBtn = new JButton("Filter by Category");

    JLabel amountFilterLabel = new JLabel("Filter by Amount:");
    amountFilterField = new JTextField(10);
    amountFilterBtn = new JButton("Filter by Amount");

    clearFilterBtn = new JButton("Clear Filter");
    
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);



    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.add(amountFilterBtn);
    buttonPanel.add(categoryFilterBtn);
    buttonPanel.add(clearFilterBtn);
    buttonPanel.add(undoTransactionBtn); //  Adds the Remove Last Transaction button to UI


    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);

    // Enable the Remove button only when a row is selected
    transactionsTable.getSelectionModel().addListSelectionListener(e -> {
      // Prevent firing twice (once for mouse press, once for release)
      if (!e.getValueIsAdjusting()) {
        int selectedRow = transactionsTable.getSelectedRow();
        undoTransactionBtn.setEnabled(selectedRow >= 0 && selectedRow < model.getRowCount() - 1);
      }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  /**
   * Returns the table model used for displaying transactions.
   *
   * @return the DefaultTableModel of the transactions table
   */
  public DefaultTableModel getTableModel() {
    return model;
  }

  /**
   * Returns the JTable component used to show transactions.
   *
   * @return the transactions JTable
   */
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  /**
   * Retrieves the amount entered by the user from the input field.
   *
   * @return the entered amount as a double; 0 if the field is empty
   */
  public double getAmountField() {
    if (amountField.getText().isEmpty()) {
      return 0;
    } else {
      return Double.parseDouble(amountField.getText());
    }
  }

  /**
   * Sets the amount input field reference (used mainly for testing or configuration).
   *
   * @param amountField the formatted amount field to set
   */
  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  /**
   * Retrieves the category entered by the user.
   *
   * @return the text in the category input field
   */
  public String getCategoryField() {
    return categoryField.getText();
  }

  /**
   * Sets the category input field reference (used mainly for testing or configuration).
   *
   * @param categoryField the category input field to set
   */
  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  /**
   * Adds an ActionListener for the "Filter by Category" button.
   *
   * @param listener the ActionListener to attach
   */
  public void addApplyCategoryFilterListener(ActionListener listener) {
    categoryFilterBtn.addActionListener(listener);
  }

  /**
   * Prompts the user to input a category for filtering.
   *
   * @return the category entered by the user
   */
  public String getCategoryFilterInput() {
    return JOptionPane.showInputDialog(this, "Enter Category Filter:");
  }

  /**
   * Adds an ActionListener for the "Filter by Amount" button.
   *
   * @param listener the ActionListener to attach
   */
  public void addApplyAmountFilterListener(ActionListener listener) {
    amountFilterBtn.addActionListener(listener);
  }

  /**
   * Prompts the user to input an amount for filtering.
   *
   * @return the entered amount, or 0.0 if input is invalid
   */
  public double getAmountFilterInput() {
    String input = JOptionPane.showInputDialog(this, "Enter Amount Filter:");
    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      return 0.0;
    }
  }

  /**
   * Adds an ActionListener for the "Clear Filter" button.
   *
   * @param listener the ActionListener to attach
   */
  public void addClearFilterListener(ActionListener listener) {
    clearFilterBtn.addActionListener(listener);
  }

  /**
   * Refreshes the transactions table with the given list.
   * Also calculates and displays the total transaction amount.
   *
   * @param transactions the list of transactions to display
   */
  public void refreshTable(List<Transaction> transactions) {
    model.setRowCount(0);
    this.displayedTransactions = transactions; // ✅ Track displayed transactions

    int rowNum = model.getRowCount();
    double totalCost = 0;

    for (Transaction t : transactions) {
      totalCost += t.getAmount();
    }

    for (Transaction t : transactions) {
      model.addRow(new Object[]{++rowNum, t.getAmount(), t.getCategory(), t.getTimestamp()}); 
    }

    model.addRow(new Object[]{"Total", null, null, totalCost});
    transactionsTable.updateUI();
  }

  /**
   * Returns the button used to add a transaction.
   *
   * @return the add transaction JButton
   */
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  /**
   * Displays the filtered transactions in the table.
   *
   * @param filteredTransactions the list of filtered transactions
   */
  public void displayFilteredTransactions(List<Transaction> filteredTransactions) {
    refreshTable(filteredTransactions);
  }

  /**
   * Returns the list of transactions currently shown in the table.
   *
   * @return the displayed transactions list
   */
  public List<Transaction> getDisplayedTransactions() {
    return displayedTransactions;
  }

  // Optional: remove if no longer needed
  // public void highlightRows(List<Integer> rowIndexes) { ... }

  // public void highlightRows(List<Integer> rowIndexes) {
  //     // The row indices are being used as hashcodes for the transactions.
  //     // The row index directly maps to the the transaction index in the list.
  //     transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
  //         @Override
  //         public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
  //                                                       boolean hasFocus, int row, int column) {
  //             Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
  //             if (rowIndexes.contains(row)) {
  //                 c.setBackground(new Color(173, 255, 168)); // Light green
  //             } else {
  //                 c.setBackground(table.getBackground());
  //             }
  //             return c;
  //         }
  //     });

  //     transactionsTable.repaint();
  // }
  /**
   * Provides access to the remove button so a controller can register a listener.
   * @return JButton removeTransactionBtn
   */
  public JButton getUndoTransactionBtn() {
    return undoTransactionBtn;
  }
  /**
   * Registers an ActionListener for the "Remove Last Transaction" button.
   *
   * @param listener the ActionListener to trigger when the button is clicked
   */
  public void addUndoTransactionListener(ActionListener listener) {
    undoTransactionBtn.addActionListener(listener);
  }

}
