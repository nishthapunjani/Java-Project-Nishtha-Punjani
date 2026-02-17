package Amazon.practise;

import java.util.Scanner;

//Exceptions
class StudentFeeException extends Exception {
    public StudentFeeException(String message) { super(message); }
}

class InvalidAmountException extends StudentFeeException {
    public InvalidAmountException(String message) { super(message); }
}

// Encapsulated Student Class
class Student {
    private String studentID;
    private String name;
    private double totalFee = -1; 
    private double paidAmount = 0;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    // Getters (Read-only access)
    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public double getTotalFee() { return totalFee; }
    public double getPaidAmount() { return paidAmount; }

    // Logic with Exception Handling instead of just if-else
    public void setTotalFee(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Fee amount must be positive. Provided: " + amount);
        }
        this.totalFee = amount;
        System.out.println("Total fee set successfully.");
    }

    public void payFee(double amount) throws StudentFeeException {
        if (totalFee == -1) {
            throw new StudentFeeException("Total fee has not been set yet!");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Payment amount must be positive.");
        }
        
        double balance = getBalance();
        if (amount > balance) {
            throw new StudentFeeException("Payment of " + amount + " exceeds remaining balance of " + balance);
        }

        paidAmount += amount;
        System.out.println("Payment successful! Remaining balance: " + getBalance());
    }

    public double getBalance() {
        return (totalFee == -1) ? 0 : (totalFee - paidAmount);
    }

    public void displayDetails() {
        System.out.println("\n--- Student Fee Details ---");
        System.out.println("ID: " + studentID + " | Name: " + name);
        System.out.println("Total Fee: " + (totalFee == -1 ? "Not Set" : totalFee));
        System.out.println("Paid: " + paidAmount + " | Balance: " + getBalance());
    }
}

//Main Application
public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Set Fee \n2. Pay Fee \n3. View Details \n4. Exit");
            System.out.print("Select: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter fee: ");
                        student.setTotalFee(Double.parseDouble(scanner.nextLine()));
                        break;
                    case 2:
                        System.out.print("Enter payment: ");
                        student.payFee(Double.parseDouble(scanner.nextLine()));
                        break;
                    case 3:
                        student.displayDetails();
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter numbers only.");
            } catch (StudentFeeException e) {
                // Catching custom business logic errors
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
        System.out.println("Exiting...Bye");
        scanner.close();
    }
}
