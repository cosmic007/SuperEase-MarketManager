package com.shopeasemanager.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.Customer;
import com.shopeasemanager.entity.Employee;
import com.shopeasemanager.service.EmployeeServiceImpl;

public class EmployeeUtility {

	public static void main(String[] args) {
		menu();

	}

	private static void menu() {

		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		do {
			System.out.println("Employee Menu\n");
			System.out.println("1.Add Employee\n2.Update Employee\n3.Display ALL Employee 4.Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				employeeServiceImpl.addEmployee(addEmployee());
				break;
			case 2:
				System.out.println("Enter Employee ID:");
				scanner.nextLine();
				String employeeID = scanner.nextLine();

				Employee selectedEmployee = employeeServiceImpl.getEmployee(employeeID);
				if (selectedEmployee != null) {
					selectedEmployee.setEmployeeID(employeeID);
					boolean continueUpdate = true;
					do {
						System.out.println(
								"UPDATE:\n1.Employee FirstName\n2.Employee Lastname\n3.Employee DOB\n4.Employee Phno\n5.Employee Address\n6.Back");
						int selectedChoice = scanner.nextInt();
						switch (selectedChoice) {
						case 1:
							System.out.println("Enter new Employee FirstName:");
							scanner.nextLine();
							selectedEmployee.setEmployeeFirstName(scanner.nextLine());
							employeeServiceImpl.updateEmployee(selectedEmployee, "employee_firstname");
							break;
						case 2:
							System.out.println("Enter new Employee Lastname:");
							scanner.nextLine();
							selectedEmployee.setEmployeeLastName(scanner.nextLine());
							employeeServiceImpl.updateEmployee(selectedEmployee, "employee_lastname");
							break;
						case 3:
							System.out.println("Enter new Employee DOB:");
							scanner.nextLine();
							String dob = scanner.nextLine();
							LocalDate employeeDOB = LocalDate.parse(dob, formatter);
							selectedEmployee.setEmployeeDOB(employeeDOB);
							employeeServiceImpl.updateEmployee(selectedEmployee, "employee_dob");
							break;
						case 4:
							System.out.println("Enter new Employee Phno:");
							scanner.nextLine();
							Long employeePhno = scanner.nextLong();
							selectedEmployee.setEmployeePhNo(employeePhno);
							employeeServiceImpl.updateEmployee(selectedEmployee, "employee_phno");
							break;
						case 5:
							System.out.println("Enter new Employee Address:");
							scanner.nextLine();
							String employeeAddress = scanner.nextLine();
							selectedEmployee.setEmployeeAddress(employeeAddress);
							employeeServiceImpl.updateEmployee(selectedEmployee, "employee_address");

							break;
					
						case 6:
							continueUpdate = false;
							break;

						default:
							System.out.println("Enter a valid option");
							break;

						}

					} while (continueUpdate);

				} else {
					System.out.println("Employee ID is invalid");
				}

				break;
			case 3:

				displayEmployee(employeeServiceImpl.displayAllEmployees());

				break;
			case 4:
				next = false;
				break;
			default:
				break;

			}

		} while (next);

	}

	public static void displayEmployee(List<Employee> employeeList) {
	    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	    System.out.printf("%-15s %-15s %-15s %-15s %-25s %-35s%n", "Employee ID", "First Name", "Last Name",
	            "DOB", "Phone Number", "Address");
	    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

	    for (Employee employee : employeeList) {
	        System.out.printf("%-15s %-15s %-15s %-15s %-25s %-35s%n", employee.getEmployeeID(),
	                employee.getEmployeeFirstName(), employee.getEmployeeLastName(),
	                employee.getEmployeeDOB().toString(), employee.getEmployeePhNo(),
	                employee.getEmployeeAddress());
	    }

	    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	}




	private static Employee addEmployee() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Employee ID:");
		String employeeID = scanner.nextLine();
		System.out.println("Enter Employee FirstName:");
		String employeeFirstName = scanner.nextLine();
		System.out.println("Enter Employee LastName:");
		String employeeLastName = scanner.nextLine();
		System.out.println("Enter Employee DOB:");
		String DOB = scanner.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate employeeDOB = LocalDate.parse(DOB, formatter);
		System.out.println("Enter Employee Phone Number:");
		Long employeePhno = scanner.nextLong();
		System.out.println("Enter Employee Address:");
		scanner.nextLine();
		String employeeAddress = scanner.nextLine();
		Employee employee= new Employee(employeeID,employeeFirstName,employeeLastName,employeeDOB,employeePhno,employeeAddress);
		
		

		return employee;
	}

}
