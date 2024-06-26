package exceptions;

public class SavingsAccount {
	public int balance;
	public String name;

	public SavingsAccount(int initialBalance, String name) {
		this.balance = initialBalance;
		this.name = name;
		System.out.println("Created a savings account with balance = " + initialBalance);
	}

	public void debit(int amountToDebit) {
		// debit business logic
	}

	public void credit(int amountToCredit) {
		// credit business logic

	}

	public void sendNotification(NotificationMedium medium) {
		if (medium == NotificationMedium.SMS) {
			// Send SMS here 
			} else if (medium == NotificationMedium.EMAIL) {
				// Send Email
			}
		
	}}

