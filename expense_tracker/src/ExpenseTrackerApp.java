import javax.swing.JOptionPane;
import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Filter.AmountFilter;
import model.Filter.CategoryFilter;

/**
 * Entry point for the Expense Tracker application.
 * Sets up the MVC components, attaches event listeners, and launches the GUI.
 */
public class ExpenseTrackerApp {

    /**
     * The main method initializes the model, view, and controller,
     * sets up event listeners for UI actions, and starts the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);
    

    // Initialize view
    view.setVisible(true);



    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

        // Add action listener to the "Undo Last Transaction" button
        view.addUndoTransactionListener(e -> {
            controller.removeLastTransaction();
        });

        // Add action listener to the "Apply Category Filter" button
    view.addApplyCategoryFilterListener(e -> {
      try{
      String categoryFilterInput = view.getCategoryFilterInput();
      CategoryFilter categoryFilter = new CategoryFilter(categoryFilterInput);
      if (categoryFilterInput != null) {
          // controller.applyCategoryFilter(categoryFilterInput);
          controller.setFilter(categoryFilter);
          controller.applyFilter();
      }
     }catch(IllegalArgumentException exception) {
    JOptionPane.showMessageDialog(view, exception.getMessage());
    view.toFront();
   }});


    // Add action listener to the "Apply Amount Filter" button
    view.addApplyAmountFilterListener(e -> {
      try{
      double amountFilterInput = view.getAmountFilterInput();
      AmountFilter amountFilter = new AmountFilter(amountFilterInput);
      if (amountFilterInput != 0.0) {
          controller.setFilter(amountFilter);
          controller.applyFilter();
      }
    }catch(IllegalArgumentException exception) {
    JOptionPane.showMessageDialog(view,exception.getMessage());
    view.toFront();
   }});


   // Add action listener to the "Clear Filter" button
   view.addClearFilterListener(e -> {
     controller.setFilter(null);
     controller.applyFilter();
   });
    
  }
}
