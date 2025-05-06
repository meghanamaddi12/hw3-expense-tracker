package model.Filter;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import controller.InputValidation;

/**
 * Concrete strategy that filters transactions based on a category.
 * Implements the TransactionFilter interface using a specified category value.
 */
public class CategoryFilter implements TransactionFilter {
    private String categoryFilter;

    /**
     * Constructs a CategoryFilter with the given category.
     * Validates the input using the InputValidation utility.
     *
     * @param categoryFilter the category to filter by
     * @throws IllegalArgumentException if the category is invalid
     */
    public CategoryFilter(String categoryFilter) {
        // Since the CategoryFilter constructor is public,
        // the input validation needs to be performed again.
        if(!InputValidation.isValidCategory(categoryFilter)){
            throw new IllegalArgumentException("Invalid category filter");
        }else{
            this.categoryFilter = categoryFilter;
        }
    }

    /**
     * Filters the provided list of transactions, returning only those
     * whose category matches the specified category.
     *
     * @param transactions the list of transactions to filter
     * @return a list of transactions that match the filter category
     * @throws IllegalArgumentException if the input list is null
     */
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
	// Perform input validation
        if (transactions == null) {
            throw new IllegalArgumentException("The transactions list must be non-null.");
	}

        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equalsIgnoreCase(categoryFilter)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }
}
