import java.util.Scanner;
class BankAccount {
    private final String Name;
    private double balance;
    private String password;

    public BankAccount(String Name, double Balance) {
        this.Name = Name;
        this.balance = Balance;
        this.password = "";
    }

    public String getHolderName() {
        return Name;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return password;
    }

    public void setPin(String password) {
        this.password = password;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount added successfully.Your Current balance: $" + balance);
        } else {
            System.out.println("Unable to process deposit.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.Your Current balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Transaction declined due to insufficient balance.");
        } else {
            System.out.println("Invalid amount entered.");
        }
    }
}

// Class to represent the ATM machine
class ATM {
    private final BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public boolean verifyPin(String inputPin) {
        return account.getPin().equals(inputPin);
    }

    public boolean isPinSet() {
        return !account.getPin().isEmpty();
    }

    public void setPin(String newPin) {
        if (newPin.matches("\\d{4}")) {
            account.setPin(newPin);
            System.out.println("Your PIN has been set successfully.");
        } else {
            System.out.println("Error: Invalid PIN format. Please enter a 4-digit number.");
        }
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void checkBalance() {
        System.out.println("Available balance $" + account.getBalance());
    }

    public void showAccountDetails() {
        System.out.println("Account Owner: " + account.getHolderName());
    }

    public void show() {
        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scan.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scan.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Exiting ATM.");
                    break;
                default:
                    System.out.println("Error: Please choose a valid option.");
            }
        } while (choice != 4);

        scan.close();
    }

    public void startATM() {
        Scanner scan = new Scanner(System.in);

        if (!isPinSet()) {
            System.out.print("You don't have a PIN set. Enter a new 4-digit PIN: ");
            String newPin = scan.nextLine();
            setPin(newPin);
        }

        int attempts = 0;
        boolean accessGranted = false;

        while (attempts < 2 && !accessGranted) {
            System.out.print("Enter PIN: ");
            String inputPin = scan.nextLine();

            if (verifyPin(inputPin)) {
                accessGranted = true;
                System.out.println("Login successful.");
                showAccountDetails();
                show();
            } else {
                attempts++;
                if (attempts < 2) {
                    System.out.println("Error: Invalid PIN.");
                } else {
                    System.out.println("Error: Access denied due to invalid PIN.");
                }
            }
        }

        scan.close();
    }

    // Main method to start the ATM application
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Palak Goyal", 5000.00); 
        ATM atm = new ATM(account);

        atm.startATM(); 
    }
}
