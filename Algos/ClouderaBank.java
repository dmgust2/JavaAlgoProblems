package Algos;
/**
 * Cloudera technical screen interview question
 * 
 * Support 4 operations:
 *   Deposit to a single account
 *   Withdraw from a single account
 *   Transfer money from one account to another
 *   Total money in the bank
 * Caveats:
 *   If Deposit or Transfer money to non-existing account, create the account
 *   Account should never have a negative balance -> Withdraw or Transfer goes below 0, throw exception
 *   Account should not have > 100,000,000 (10^8) -> Deposit or Transfer goes above 10^8, throw exception
 */


import java.util.*;


interface Bank {
	public final static double MAX_BALANCE = 100000000.00;


	@SuppressWarnings("serial")
	class MaxBalanceExceededException extends Exception
	{
	      public MaxBalanceExceededException() {}

	      public MaxBalanceExceededException(String message) { super(message); }

	      public MaxBalanceExceededException (Throwable cause) { super(cause); }

	      public MaxBalanceExceededException (String message, Throwable cause) { super(message, cause); }
	}

	@SuppressWarnings("serial")
	class InsufficientFundsException extends Exception
	{
	      public InsufficientFundsException() {}

	      public InsufficientFundsException(String message) { super(message); }

	      public InsufficientFundsException (Throwable cause) { super(cause); }

	      public InsufficientFundsException (String message, Throwable cause) { super(message, cause); }
	}

	@SuppressWarnings("serial")
	class AccountDoesNotExistException extends Exception
	{
	      public AccountDoesNotExistException() {}

	      public AccountDoesNotExistException(String message) { super(message); }

	      public AccountDoesNotExistException (Throwable cause) { super(cause); }

	      public AccountDoesNotExistException (String message, Throwable cause) { super(message, cause); }
	}

	@SuppressWarnings("serial")
	class ErrorAddingAccountException extends Exception
	{
	      public ErrorAddingAccountException() {}

	      public ErrorAddingAccountException(String message) { super(message); }

	      public ErrorAddingAccountException (Throwable cause) { super(cause); }

	      public ErrorAddingAccountException (String message, Throwable cause) { super(message, cause); }
	}

	class Account {
		int id;
		double balance;

		// CTOR
		public Account(int id) {
			this.id = id;
			this.balance = 0.00;
		}

		/**
		 * Returns the account balance for this account
		 * @return	Account balance
		 */
		double getBalance() {
			return balance;
		}

		/**
		 * Deposits money into this account
		 * @param amount
		 * @throws MaxBalanceExceededException
		 */
		void deposit(double amount) throws MaxBalanceExceededException {
			double tempBalance = this.balance;
			tempBalance += amount;

			if (tempBalance > MAX_BALANCE) {
				throw new MaxBalanceExceededException("Cannot process this deposit as account ID '" + this.id + "' will exceed the max balance");
			}
			else {
			    this.balance = tempBalance;
			}
		}

		/**
		 * Withdraws money from this account
		 * @param amount
		 * @throws InsufficientFundsException
		 */
		void withdraw(double amount) throws InsufficientFundsException {
			if (amount > this.balance) {
				throw new InsufficientFundsException("Cannot process this withdrawal from account ID '" + this.id + "' due to insufficient funds");
			}
			else {
				this.balance -= amount;
			}
		}
	}


	// NOTE: Using a Set makes it easier to iterate over (see getBankBalance), but harder (and less efficient) to simply retrieve a specific account (see getAccount)
	Set<Account> allAccountsSet = new HashSet<Account>();

	// NOTE: Maps are better for more efficiently retrieving accounts, but a little less straightforward to iterate over all items
	Map<Integer, Account> allAccounts = new HashMap<Integer, Account>();

	/**
	 * Adds a new account to the Bank
	 * @param newAccountId		Account ID
	 * @return					Account
	 * @throws ErrorAddingAccountException
	 */
	Account addAccount(int newAccountId);

	/**
	 * NOTE: This was previously used for a Set allAccountsSet above
	 * Adds a new account to the Bank
	 * @param newAccountId		Account ID
	 * @return					Account
	 * @throws ErrorAddingAccountException
	 */
	Account addAccountToSet(int newAccountId) throws ErrorAddingAccountException;

	/**
	 * Deposit money to a single account in the Bank
	 * @param toAccountId		Account ID
	 * @param amount			Money to deposit
	 * @throws ErrorAddingAccountException
	 * @throws MaxBalanceExceededException
	 */
	void deposit(int toAccountId, double amount) throws ErrorAddingAccountException, MaxBalanceExceededException;

	/**
	 * Withdraw money from a single account in the Bank
	 * @param fromAccountId		Account ID
	 * @param amount			Money to withdraw
	 * @throws AccountDoesNotExistException
	 * @throws InsufficientFundsException
	 */
	void withdraw(int fromAccountId, double amount) throws AccountDoesNotExistException, InsufficientFundsException;

	/**
	 * Transfer money from one account to another in the Bank
	 * @param fromAccountId		From Account ID
	 * @param toAccountId		To Account ID
	 * @param ammount			Money to transfer
	 * @throws AccountDoesNotExistException
	 * @throws InsufficientFundsException
	 * @throws ErrorAddingAccountException
	 * @throws MaxBalanceExceededException
	 */
	void transfer(int fromAccountId, int toAccountId, double ammount)
		throws AccountDoesNotExistException, InsufficientFundsException, ErrorAddingAccountException, MaxBalanceExceededException;

	/**
	 * Returns the total balance currently in the Bank (all accounts)
	 * @return			Total Balance
	 */
	double getBankBalance();
}


public class ClouderaBank implements Bank {

	public Account addAccount(int newAccountId) {
		Account newAccount = new Account(newAccountId);
		allAccounts.put(newAccountId, newAccount);

		return newAccount;
	}

	// NOTE: This was previously used for a Set allAccountsSet above
	@Override
	public Account addAccountToSet(int newAccountId) throws ErrorAddingAccountException {
		Account newAccount = new Account(newAccountId);
		boolean success = allAccountsSet.add(newAccount);

		if (success) {
			return newAccount;
		}
		else {
			throw new ErrorAddingAccountException("Error adding this new account to the Bank!");
		}
	}

	/**
	 * NOTE: This was previously used for a Set allAccountsSet above
	 * Retrieves the Cloudera Bank account from the passed account ID
	 * @param id	Account ID
	 * @return		Account
	 */
	public Account getAccount(int id) {
		for (Account account : allAccountsSet) {
			if (account.id == id) {
				return account;
			}
		}

		return null;
	}


	public void deposit(int toAccountId, double amount) throws ErrorAddingAccountException, MaxBalanceExceededException {
		//Account toAccount = getAccount(toAccountId);
		Account toAccount = allAccounts.get(toAccountId);
		if (toAccount != null) {
			// If the account exists, deposit the passed amount
			toAccount.deposit(amount);
		}
		else {
			// Create and add this new account to the bank
			//Account newAccount = this.addAccountToSet(toAccountId);
			Account newAccount = this.addAccount(toAccountId);

			// Deposit the passed amount
			newAccount.deposit(amount);
		}
	}

	public void withdraw(int fromAccountId, double amount) throws AccountDoesNotExistException, InsufficientFundsException {
		//Account fromAccount = getAccount(fromAccountId);
		Account fromAccount = allAccounts.get(fromAccountId);
		if (fromAccount == null) {
			throw new AccountDoesNotExistException("Account ID '" + fromAccountId + "' does not exist!");
		}
		else {
			// Withdraw the passed amount if there are sufficient funds
			fromAccount.withdraw(amount);
		}
	}

	public void transfer(int fromAccountId, int toAccountId, double amount)
		throws AccountDoesNotExistException, InsufficientFundsException, ErrorAddingAccountException, MaxBalanceExceededException {
		// First withdraw the money from the fromAccount
		withdraw(fromAccountId, amount);

		// Now deposit the money in the toAccount
		deposit(toAccountId, amount);
	}

	/**
	 * Get the total balance for the entire Cloudera Bank
	 */
	public double getBankBalance() {
		double totalBalance = 0;

		// Using a Set
		//for (Account account : allAccountsSet) {
		//	totalBalance += account.getBalance();
		//}

		// DEBUG using new style (Java 8)
		allAccounts.forEach((k,v) -> System.out.println("Account ID: " + k + ", Account balance: " + v.balance));

		for (Map.Entry<Integer, Account> entry : allAccounts.entrySet()) {
            totalBalance += entry.getValue().getBalance();
        }

		return totalBalance;
	}


	/**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Create a new bank
			ClouderaBank cloudBank = new ClouderaBank();

			// 1: Deposit test: Deposit to non-existent account (100)
			cloudBank.deposit(1, 100.00);
			System.out.println("Deposit test 1, total Bank balance: " + cloudBank.getBankBalance());

			// 2: Deposit to existing account (10100)
			cloudBank.deposit(1, 10000.00);
			System.out.println("Deposit test 2, total Bank balance: " + cloudBank.getBankBalance());

			// 3: Deposit over the limit new account test (10100)
			// ERROR: MaxBalanceExceededException
			//cloudBank.deposit(2, 100000001.00);
			//System.out.println("Deposit test 3, total Bank balance: " + cloudBank.getBankBalance());

			// 4: Withdrawal test (10050)
			cloudBank.withdraw(1, 50);
			System.out.println("Withdrawal test 4, total Bank balance: " + cloudBank.getBankBalance());

			// 5: Withdrawal test, overdrawn (10050)
			// ERROR: InsufficientFundsException
			//cloudBank.withdraw(1, 11000);
			//System.out.println("Withdrawal test 5, total Bank balance: " + cloudBank.getBankBalance());

			// 6: Transfer test to non-existent account (10050)
			cloudBank.transfer(1, 2, 1000);
			System.out.println("Transfer test 6, total Bank balance: " + cloudBank.getBankBalance());

			// 7: Transfer test (10050)
			cloudBank.transfer(1, 2, 5000);
			System.out.println("Transfer test 7, total Bank balance: " + cloudBank.getBankBalance());

			// 8: Transfer test, overdrawn From account (10050)
			// ERROR: InsufficientFundsException
			//cloudBank.transfer(2, 1, 10000);
			//System.out.println("Transfer test 8, total Bank balance: " + cloudBank.getBankBalance());

			// 9: Transfer test, From account doesn't exist (10050)
			// ERROR: AccountDoesNotExistException
			//cloudBank.transfer(3, 1, 100);
			//System.out.println("Transfer test 9, total Bank balance: " + cloudBank.getBankBalance());

			// 10: Withdrawal test (0)
			cloudBank.withdraw(1, 4050);
			cloudBank.withdraw(2, 6000);
			System.out.println("Withdrawal test 10, total Bank balance: " + cloudBank.getBankBalance());
		}
		catch (ErrorAddingAccountException ex) {
			System.out.println("ERROR: " + ex);
		}
		catch (MaxBalanceExceededException ex) {
			System.out.println("ERROR: " + ex);
		}
		catch (AccountDoesNotExistException ex) {
			System.out.println("ERROR: " + ex);
		}
		catch (InsufficientFundsException ex) {
			System.out.println("ERROR: " + ex);
		}
	}
}
