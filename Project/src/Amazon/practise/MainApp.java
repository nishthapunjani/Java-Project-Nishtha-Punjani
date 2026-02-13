package Amazon.practise;

import java.util.Scanner;

// Student Class
class Student {
    private String studentID;
    private String name;
    private double totalFee = -1; 
    private double paidAmount = 0;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }
    
//fees set
    
    public void setTotalFee(double amount) {
        if (amount > 0) {
            this.totalFee = amount;
            System.out.println("Total fee set to: " + totalFee);
        } else {
            System.out.println("Error: Fee amount must be positive.");
        }
    }

    public void payFee(double amount) {
        if (totalFee == -1) {
            System.out.println("Warning: Please set the total fee first!");
            return;
        }
        double balance = getBalance();
        if (amount > balance) {
            System.out.println("Error: Payment exceeds remaining balance of " + balance);
        } else {
            paidAmount += amount;
            System.out.println("Payment successful! Amount paid: " + amount);
            if (getBalance() == 0) {
                System.out.println("Fee paid completely.");
            }
        }
    }

    public double getBalance() {
        return totalFee - paidAmount;
    }
    
 //student details

    public void displayDetails() {
        System.out.println("\n--- Student Fee Details ---");
        System.out.println("Student ID: " + studentID);
        System.out.println(" Student Name: " + name);
        System.out.println("Total Fee: " + (totalFee == -1 ? "Not Set" : totalFee));
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Balance: " + (totalFee == -1 ? "N/A" : getBalance()));
    }
}

// Main class 
public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Student Fee Management System ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Set Total Fee\n2. Pay Fee\n3. View Fee Details\n4. Exit");
            System.out.print("Select an option: ");
            
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);
                
 // Using Classic Switch Syntax for maximum compatibility
                switch (choice) {
                    case 1:
                        System.out.print("Enter total fee amount: ");
                        double fee = Double.parseDouble(scanner.nextLine());
                        student.setTotalFee(fee);
                        break;
                    case 2:
                        System.out.print("Enter payment amount: ");
                        double payment = Double.parseDouble(scanner.nextLine());
                        student.payFee(payment);
                        break;
                    case 3:
                        student.displayDetails();
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {    //Exception
                System.out.println("Error: Please enter a valid number.");
            }
        }
        System.out.println("Exiting... Bye Bye!");
        scanner.close();
    }
}
