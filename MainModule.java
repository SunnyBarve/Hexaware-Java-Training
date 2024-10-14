package com.hexaware.main;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;
import com.hexaware.controller.ReportGenerator;
import com.google.protobuf.TextFormat.ParseException;
import com.hexaware.controller.EmployeeService;
import com.hexaware.controller.FinancialRecordService;
import com.hexaware.dao.IFinancialRecordService;
import com.hexaware.dao.ITaxService;
import com.hexaware.controller.PayrollService;
import com.hexaware.controller.TaxService;
import com.hexaware.dao.IPayrollService;
import com.hexaware.dao.IEmployeeService;
import com.hexaware.entity.Employee;
import com.hexaware.entity.FinancialRecord;
import com.hexaware.entity.Payroll;
import com.hexaware.entity.Tax;

/**
 * Main Class.
 * This class serves as the entry point for the PayXPert application.
 * It provides a text-based menu for users to interact with different services based on their role.
 */
public class Main {

    static Scanner read = new Scanner(System.in);
    static String role; // Variable to store the user's role (Admin/User)

    /**
     * Main method to start the PayXPert application.
     *
     * @param args Command-line arguments.
     * @throws SQLIntegrityConstraintViolationException Exception for SQL integrity
     *                                                  constraint violation.
     */
    public static void main(String[] args) throws SQLIntegrityConstraintViolationException {
        System.out.println("Welcome to PayXPert");
        login(); // Call the login method to determine the role

        do {
            System.out.println("What do you want to do?");
            if (role.equalsIgnoreCase("Admin")) {
                // Admin can access all operations
                System.out.println("1. Employee Service\n2. Payroll Service\n"
                        + "3. Tax Service\n4. Financial Record Service\n5. Generate Report\n6. Log Out");
            } else if (role.equalsIgnoreCase("User")) {
                // User has restricted access
                System.out.println("1. View Employee Service\n2. View Payroll Service\n"
                        + "3. View Tax Service\n4. Generate Report\n5. Log Out");
            }

            int choice = read.nextInt();

            switch (choice) {
                case 1:
                    employeeService();
                    break;
                case 2:
                    payrollService();
                    break;
                case 3:
                    taxService();
                    break;
                case 4:
                    if (role.equalsIgnoreCase("Admin")) {
                        financialRecordService(); // Admin can access Financial Record Service
                    } else {
                        generateReport(); // Users can only generate reports
                    }
                    break;
                case 5:
                    if (role.equalsIgnoreCase("Admin")) {
                        generateReport(); // Admin can generate reports
                    } else {
                        System.out.println("Thank You");
                        System.exit(0);
                    }
                    break;
                case 6:
                    if (role.equalsIgnoreCase("Admin")) {
                        System.out.println("Thank You");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid Choice");
                    }
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        } while (true);
    }

    // Method for login to determine user role (Admin or User)
    private static void login() {
        System.out.println("Please enter your role (Admin/User):");
        role = read.next();
        if (!role.equalsIgnoreCase("Admin") && !role.equalsIgnoreCase("User")) {
            System.out.println("Invalid role. Please try again.");
            login();
        }
    }

    private static void employeeService() throws SQLIntegrityConstraintViolationException {
        IEmployeeService es = new EmployeeService();
        Employee emp = new Employee();

        if (role.equalsIgnoreCase("Admin")) {
            do {
                System.out.println("Choose from the below Employee Service");
                System.out.println("1. View all Employees\n2. View Employee by ID\n"
                        + "3. Add new Employee\n4. Remove an Employee\n5. Update Employee Details\n"
                        + "6. Main Menu\n7. LogOut");
                int empServiceNumber = read.nextInt();
                switch (empServiceNumber) {
                    case 1:
                        es.getAllEmployees();
                        break;
                    case 2:
                        es.getEmployeeById(emp.getEmployeeId());
                        break;
                    case 3:
                        es.addEmployee();
                        break;
                    case 4:
                        es.removeEmployee(emp.getEmployeeId());
                        break;
                    case 5:
                        es.updateEmployee();
                        break;
                    case 6:
                        System.out.println("Going back to the main menu");
                        return;
                    case 7:
                        System.out.println("Thank You");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (true);
        } else if (role.equalsIgnoreCase("User")) {
            System.out.println("You have permission to view employees only.");
            System.out.println("1. View all Employees\n2. View Employee by ID\n3. Main Menu\n4. LogOut");
            int empServiceNumber = read.nextInt();
            switch (empServiceNumber) {
                case 1:
                    es.getAllEmployees();
                    break;
                case 2:
                    es.getEmployeeById(emp.getEmployeeId());
                    break;
                case 3:
                    System.out.println("Going back to the main menu");
                    return;
                case 4:
                    System.out.println("Thank You");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static void payrollService() {
        IPayrollService ps = new PayrollService();
        Payroll pr = new Payroll();

        if (role.equalsIgnoreCase("Admin")) {
            do {
                System.out.println("Choose from the below payroll service");
                System.out.println("1. Generate payroll for Employee\n2. View Payroll by ID\n3. View payroll for Employee\n"
                        + "4. View payroll for a period\n5. Main menu\n6. LogOut");
                int payrollServiceNumber = read.nextInt();

                switch (payrollServiceNumber) {
                    case 1:
                        try {
                            ps.generatePayroll(pr.getEmployeeId(), pr.getPayPeriodStartDate(), pr.getPayPeriodEndDate());
                        } catch (ParseException | java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        ps.getPayrollById(pr.getPayrollId());
                        break;
                    case 3:
                        ps.getPayrollsForEmployee(pr.getEmployeeId());
                        break;
                    case 4:
                        ps.getPayrollsForPeriod(pr.getPayPeriodStartDate(), pr.getPayPeriodEndDate());
                        break;
                    case 5:
                        System.out.println("Going back to the main menu");
                        return;
                    case 6:
                        System.out.println("Thank You");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (true);
        } else if (role.equalsIgnoreCase("User")) {
            System.out.println("You can only view payroll details.");
            System.out.println("1. View Payroll by ID\n2. View payroll for Employee\n3. Main Menu\n4. LogOut");
            int payrollServiceNumber = read.nextInt();

            switch (payrollServiceNumber) {
                case 1:
                    ps.getPayrollById(pr.getPayrollId());
                    break;
                case 2:
                    ps.getPayrollsForEmployee(pr.getEmployeeId());
                    break;
                case 3:
                    System.out.println("Going back to the main menu");
                    return;
                case 4:
                    System.out.println("Thank You");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static void taxService() {
        ITaxService ts = new TaxService();
        Tax tx = new Tax();

        if (role.equalsIgnoreCase("Admin")) {
            do {
                System.out.println("Choose from the below tax services");
                System.out.println("1. Calculate tax for Employee\n2. View tax records by tax ID\n3. View tax records for Employee\n"
                        + "4. View tax for a year\n5. Main Menu\n6. LogOut");
                int taxServiceNumber = read.nextInt();

                switch (taxServiceNumber) {
                    case 1:
                        ts.taxCalculator(tx.getEmployeeId(), tx.getTaxYear());
                        break;
                    case 2:
                        ts.getTaxById(tx.getTaxId());
                        break;
                    case 3:
                        ts.getTaxesForEmployee(tx.getEmployeeId());
                        break;
                    case 4:
                        ts.getTaxesForYear(tx.getTaxYear());
                        break;
                    case 5:
                        System.out.println("Going back to the main menu");
                        return;
                    case 6:
                        System.out.println("Thank You");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (true);
        } else if (role.equalsIgnoreCase("User")) {
            System.out.println("You can only view tax records.");
            System.out.println("1. View tax records by tax ID\n2. View tax records for Employee\n3. Main Menu\n4. LogOut");
            int taxServiceNumber = read.nextInt();

            switch (taxServiceNumber) {
                case 1:
                    ts.getTaxById(tx.getTaxId());
                    break;
                case 2:
                    ts.getTaxesForEmployee(tx.getEmployeeId());
                    break;
                case 3:
                    System.out.println("Going back to the main menu");
                    return;
                case 4:
                    System.out.println("Thank You");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private static void financialRecordService() {
        IFinancialRecordService frs = new FinancialRecordService();
        FinancialRecord fr = new FinancialRecord();

        if (role.equalsIgnoreCase("Admin")) {
            do {
                System.out.println("Choose from the below Financial Record Service");
                System.out.println("1. Add a financial record\n2. Get financial record by ID\n3. Get financial record for employee\n"
                        + "4. Get financial record by date\n5. Main Menu\n6. LogOut");

                int financeServiceNumber = read.nextInt();
                switch (financeServiceNumber) {
                    case 1:
                        frs.addFinancialRecord(fr.getEmployeeId(), fr.getDescription(), fr.getAmount(), fr.getRecordType());
                        break;
                    case 2:
                        frs.getFinancialRecordById(fr.getRecordId());
                        break;
                    case 3:
                        frs.getFinancialRecordsForEmployee(fr.getEmployeeId());
                        break;
                    case 4:
                        frs.getFinancialRecordsForDate(fr.getRecordDate());
                        break;
                    case 5:
                        System.out.println("Going back to the main menu");
                        return;
                    case 6:
                        System.out.println("Thank You");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (true);
        } else {
            System.out.println("You do not have access to financial record services.");
        }
    }

    private static void generateReport() {
        ReportGenerator rg = new ReportGenerator();
        do {
            System.out.println("What report do you want to generate?");
            System.out.println("1. Total Salary Paid to  Employee by Year\n2. Total Salary Paid to  Employee by Month\n"
                    + "3. Salary Paid by Employee\n4. Main Menu\n5. LogOut");

            int reportNumber = read.nextInt();

            switch (reportNumber) {
                case 1:
                    rg.salaryPaidByYear();
                    break;
                case 2:
                    rg.salaryPaidByMonth();
                    break;
                case 3:
                    rg.salaryPaidByEmployee();
                    break;
                case 4:
                    System.out.println("Going back to the main menu");
                    return;
                case 5:
                    System.out.println("Thank You");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        } while (true);
    }
}
