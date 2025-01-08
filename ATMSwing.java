import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

class Account {
    private double balance;
    private final int pin;
    private final ArrayList<String> transactionHistory;

    public Account(double initialBalance, int pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: " + initialBalance);
    }

    public boolean validatePin(int inputPin) {
        return inputPin == pin;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            return true;
        }
        return false;
    }
}

public class ATMSwing extends JFrame implements ActionListener {
    private final HashMap<Integer, Account> accounts;
    private Account currentAccount;
    private JTextField pinField, amountField;
    private JTextArea outputArea;

    public ATMSwing() {
        // Setup the accounts (PINs and balances)
        accounts = new HashMap<>();
        accounts.put(1234, new Account(1000.0, 1234));
        accounts.put(5678, new Account(5000.0, 5678));

        // Set up GUI
        setTitle("ATM Interface");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel pinLabel = new JLabel("Enter PIN:");
        pinField = new JTextField(10);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        add(pinLabel);
        add(pinField);
        add(loginButton);

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        JButton balanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton historyButton = new JButton("Transaction History");
        JButton exitButton = new JButton("Exit");

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        balanceButton.addActionListener(this);
        historyButton.addActionListener(this);
        exitButton.addActionListener(this);

        amountField = new JTextField(10);
        add(new JLabel("Amount:"));
        add(amountField);

        add(balanceButton);
        add(depositButton);
        add(withdrawButton);
        add(historyButton);
        add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("Login")) {
            int enteredPin = Integer.parseInt(pinField.getText());
            currentAccount = accounts.get(enteredPin);
            if (currentAccount != null) {
                outputArea.setText("Login successful!\n");
            } else {
                outputArea.setText("Invalid PIN!\n");
            }
        } else if (currentAccount != null) {
            if (command.equals("Check Balance")) {
                outputArea.append("Balance: " + currentAccount.getBalance() + "\n");
            } else if (command.equals("Deposit")) {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                outputArea.append("Deposited: " + amount + "\n");
            } else if (command.equals("Withdraw")) {
                double amount = Double.parseDouble(amountField.getText());
                if (currentAccount.withdraw(amount)) {
                    outputArea.append("Withdrew: " + amount + "\n");
                } else {
                    outputArea.append("Insufficient balance!\n");
                }
            } else if (command.equals("Transaction History")) {
                outputArea.append("Transaction History:\n");
                for (String entry : currentAccount.getTransactionHistory()) {
                    outputArea.append(entry + "\n");
                }
            } else if (command.equals("Exit")) {
                outputArea.append("Exiting...\n");
                currentAccount = null;
            }
        } else {
            outputArea.setText("Please login first!\n");
        }
    }

    public static void main(String[] args) {
        ATMSwing atmApp = new ATMSwing();
        atmApp.setVisible(true);
    }
}
