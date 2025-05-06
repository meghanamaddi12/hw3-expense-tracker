package model.Filter;

import java.util.List;

import model.Transaction;

/**
 * The TransactionFilter supports filtering the transaction list.
 *
 * NOTE) The Strategy design pattern is being applied. This is the Strategy interface.
 */
public interface TransactionFilter {

  /**
   * Filters the provided list of transactions based on a specific strategy.
   *
   * @param transactions the original list of transactions to be filtered
   * @return a list of transactions that match the filter criteria
   */
  public List<Transaction> filter(List<Transaction> transactions);

}
