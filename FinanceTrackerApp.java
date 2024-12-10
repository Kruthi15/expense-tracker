import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Abstract class for transactions
abstract class Transaction {
    private String transactionId;
    private double amount;
    private String date;

    public Transaction(String transactionId, double amount, String date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
    }

    public abstract String viewDetails();

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionId() {
        return transactionId;
    }
}

// Income class extending Transaction
class Income extends Transaction {
    private String source;

    public Income(String transactionId, double amount, String date, String source) {
        super(transactionId, amount, date);
        this.source = source;
    }

    @Override
    public String viewDetails() {
        return "Income: $" + getAmount() + " from " + source + " on " + getDate();
    }
}

// Expense class extending Transaction
class Expense extends Transaction {
    private String category;

    public Expense(String transactionId, double amount, String date, String category) {
        super(transactionId, amount, date);
        this.category = category;
    }

    @Override
    public String viewDetails() {
        return "Expense: $" + getAmount() + " on " + category + " on " + getDate();
    }
}

// Budget class
class Budget {
    private double budgetAmount;
    private double remainingAmount;
    private double emergencyFund;

    public Budget(double budgetAmount, double emergencyFund) {
        this.budgetAmount = budgetAmount;
        this.remainingAmount = budgetAmount;
        this.emergencyFund = emergencyFund;
    }

    public void trackSpending(double amount) {
        remainingAmount -= amount;
    }

    public void addIncome(double amount) {
        remainingAmount += amount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    // Emergency fund methods
    public double getEmergencyFund() {
        return emergencyFund;
    }

    public void withdrawEmergencyFund(double amount) {
        if (amount <= emergencyFund) {
            emergencyFund -= amount;
            remainingAmount += amount; // Emergency funds are added to the available balance
        } else {
            System.out.println("Insufficient emergency funds!");
        }
    }

    public void addEmergencyFund(double amount) {
        emergencyFund += amount;
    }
}

// TransactionManager class
class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

// Main Application class with GUI
public class FinanceTrackerApp extends JFrame implements ActionListener {
    private TransactionManager transactionManager = new TransactionManager();
    private Budget budget;

    private JTextArea transactionArea;
    private JButton addIncomeBtn, addExpenseBtn, viewBudgetBtn, refreshBtn, profileBtn, viewEmergencyFundsBtn, withdrawEmergencyFundsBtn, viewProfileBtn;

    // Profile fields
    private String profileName = "";
    private String profileAge = "";
    private String profileEmail = "";

    public FinanceTrackerApp() {
        // Prompt user to enter initial budget and emergency fund
        double initialBudget = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter your initial budget for the week:", "Initial Budget", JOptionPane.PLAIN_MESSAGE));
        double emergencyFund = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter the amount for emergency funds:", "Emergency Fund", JOptionPane.PLAIN_MESSAGE));
        budget = new Budget(initialBudget, emergencyFund);

        // Frame setup
        setTitle("Personal Finance Tracker");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(true);
        setLocationRelativeTo(null);

        // Set background color to Dusty Rose
        getContentPane().setBackground(Color.decode("#eed6d3"));

// Transaction area
transactionArea = new JTextArea(15, 50);
transactionArea.setEditable(false);
transactionArea.setBackground(Color.WHITE); // Set background to white
transactionArea.setForeground(Color.decode("#67595e")); // Set text color to Coffee Pot
JScrollPane scrollPane = new JScrollPane(transactionArea);
add(scrollPane);


        // Buttons setup with new colors
        addIncomeBtn = createButton("Add Income");
        addExpenseBtn = createButton("Add Expense");
        viewBudgetBtn = createButton("View Budget");
        refreshBtn = createButton("Refresh");
        profileBtn = createButton("Profile");
        viewEmergencyFundsBtn = createButton("View Emergency Funds");
        withdrawEmergencyFundsBtn = createButton("Withdraw Emergency Funds");
        viewProfileBtn = createButton("View Profile");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 3, 10, 10));
        buttonPanel.setBackground(Color.decode("#eed6d3"));
        buttonPanel.add(addIncomeBtn);
        buttonPanel.add(addExpenseBtn);
        buttonPanel.add(viewBudgetBtn);
        buttonPanel.add(refreshBtn);
        buttonPanel.add(profileBtn);
        buttonPanel.add(viewEmergencyFundsBtn);
        buttonPanel.add(withdrawEmergencyFundsBtn);
        buttonPanel.add(viewProfileBtn);

        add(buttonPanel);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#a49393"));
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIncomeBtn) {
            handleAddIncome();
        } else if (e.getSource() == addExpenseBtn) {
            handleAddExpense();
        } else if (e.getSource() == viewBudgetBtn) {
            handleViewBudget();
        } else if (e.getSource() == refreshBtn) {
            refreshTransactionArea();
        } else if (e.getSource() == profileBtn) {
            handleProfile();
        } else if (e.getSource() == viewEmergencyFundsBtn) {
            handleViewEmergencyFunds();
        } else if (e.getSource() == withdrawEmergencyFundsBtn) {
            handleWithdrawEmergencyFunds();
        } else if (e.getSource() == viewProfileBtn) {
            handleViewProfile();
        }
    }

    private void handleAddIncome() {
        String source = JOptionPane.showInputDialog("Enter income source:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter income amount:"));
        String date = JOptionPane.showInputDialog("Enter date (YYYY-MM-DD):");
        transactionManager.addTransaction(new Income("INC" + (transactionManager.getTransactions().size() + 1), amount, date, source));
        budget.addIncome(amount);
        refreshTransactionArea();
    }

    private void handleAddExpense() {
        String category = JOptionPane.showInputDialog("Enter expense category:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter expense amount:"));
        String date = JOptionPane.showInputDialog("Enter date (YYYY-MM-DD):");
        transactionManager.addTransaction(new Expense("EXP" + (transactionManager.getTransactions().size() + 1), amount, date, category));
        budget.trackSpending(amount);
        refreshTransactionArea();
    }

    private void handleViewBudget() {
        JOptionPane.showMessageDialog(this, "Remaining Budget: $" + budget.getRemainingAmount());
    }

    private void handleProfile() {
        profileName = JOptionPane.showInputDialog("Enter your name:");
        profileAge = JOptionPane.showInputDialog("Enter your age:");
        profileEmail = JOptionPane.showInputDialog("Enter your email:");
    }

    private void handleViewEmergencyFunds() {
        JOptionPane.showMessageDialog(this, "Emergency Funds: $" + budget.getEmergencyFund());
    }

    private void handleWithdrawEmergencyFunds() {
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter withdrawal amount:"));
        budget.withdrawEmergencyFund(amount);
        refreshTransactionArea();
    }

    private void handleViewProfile() {
        JOptionPane.showMessageDialog(this, "Name: " + profileName + "\nAge: " + profileAge + "\nEmail: " + profileEmail);
    }

    private void refreshTransactionArea() {
        transactionArea.setText("");
        for (Transaction transaction : transactionManager.getTransactions()) {
            transactionArea.append(transaction.viewDetails() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow login = new LoginWindow();
            login.setVisible(true);
        });
    }
}

// Login Window Class
class LoginWindow extends JFrame {
    public LoginWindow() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set background color
        getContentPane().setBackground(Color.decode("#eed6d3"));

        // Username and Password Fields
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.decode("#a49393"));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            if ("admin".equals(username) && "password123".equals(new String(password))) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                FinanceTrackerApp app = new FinanceTrackerApp();
                app.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials, please try again.");
            }
        });

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);
    }
}
