package model.Filter;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import controller.InputValidation;

/**
 * Concrete strategy that filters transactions based on an exact amount.
 * Implements the TransactionFilter interface using a specified amount value.
 */
public class AmountFilter implements TransactionFilter{
    private double amountFilter;

    /**
     * Constructs an AmountFilter with the given amount.
     * Validates the input using the InputValidation utility.
     *
     * @param amountFilter the exact amount to filter by
     * @throws IllegalArgumentException if the amount is invalid
     */
    public AmountFilter(double amountFilter){
        // Since the AmountFilter constructor is public, 
        // the input validation needs to be performed again.
        if(!InputValidation.isValidAmount(amountFilter)){
            throw new IllegalArgumentException("Invalid amount filter");
        } else {
            this.amountFilter = amountFilter;
        }
    }
    /**
     * Filters the provided list of transactions, returning only those
     * whose amount matches the specified amount.
     *
     * @param transactions the list of transactions to filter
     * @return a list of transactions that match the filter amount
     * @throws IllegalArgumentException if the input list is null
     */
    @Override
    public List<Transaction> filter(List<Transaction> transactions){
	// Perform input validation
	if (transactions == null) {
	    throw new IllegalArgumentException("The transactions list must be non-null.");
	}
        List<Transaction> filteredTransactions = new ArrayList<>();
        for(Transaction transaction : transactions){
            // Your solution could use a different comparison here.
            if(transaction.getAmount() == amountFilter){
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }
    
}
