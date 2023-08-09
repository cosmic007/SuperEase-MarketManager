package com.shopeasemanager.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.Customer;
import com.shopeasemanager.service.CustomerServiceImpl;

public class CustomerUtility {

	public static void main(String[] args) {
		menu();

	}

	private static void menu() {

		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		do {
			System.out.println("Customer Menu\n");
			System.out.println("1.Add Customer\n2.Update Customer\n3.Display ALL Customers 4.Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				customerServiceImpl.addCustomer(addCustomer());
				break;
			case 2:
				System.out.println("Enter Customer ID:");
				scanner.nextLine();
				String customerID = scanner.nextLine();

				Customer selectedCustomer = customerServiceImpl.getCustomer(customerID);
				if (selectedCustomer != null) {
					selectedCustomer.setCustomerID(customerID);
					boolean continueUpdate = true;
					do {
						System.out.println(
								"UPDATE:\n1.Customer FirstName\n2.Customer Lastname\n3.Customer DOB\n4.Customer Email\n5.Customer Phno\n6.Customer Address\n7.Customer credit point\n8.Back");
						int selectedChoice = scanner.nextInt();
						switch (selectedChoice) {
						
						case 1:
							System.out.println("Enter new Customer FirstName:");
							scanner.nextLine();
							selectedCustomer.setCustomerFirstName(scanner.nextLine());
							customerServiceImpl.updateCustomer(selectedCustomer, "customer_firstname");
							break;
						case 2:
							System.out.println("Enter new Customer Lastname:");
							scanner.nextLine();
							selectedCustomer.setCustomerLastName(scanner.nextLine());
							customerServiceImpl.updateCustomer(selectedCustomer, "customer_lastname");
							break;
						case 3:
							System.out.println("Enter new Customer DOB:");
							scanner.nextLine();
							String dob = scanner.nextLine();
							LocalDate customerDOB = LocalDate.parse(dob, formatter);
							selectedCustomer.setCustomerDOB(customerDOB);
							customerServiceImpl.updateCustomer(selectedCustomer, "customer_dob");
							break;
						case 4:
							System.out.println("Enter new Customer Email:");
							scanner.nextLine();
							String customerEmail = scanner.nextLine();
							selectedCustomer.setCustomerEmail(customerEmail);
							customerServiceImpl.updateCustomer(selectedCustomer, "customer_email");
							break;
						case 5:
							System.out.println("Enter new Customer Phno:");
							scanner.nextLine();
							Long customerPhno = scanner.nextLong();
							selectedCustomer.setCustomerPhNo(customerPhno);
							customerServiceImpl.updateCustomer(selectedCustomer, "customer_phno");
							break;
						case 6:
							System.out.println("Enter new Customer Address:");
							scanner.nextLine();
							String customerAddress = scanner.nextLine();
							selectedCustomer.setCustomerAddress(customerAddress);
							customerServiceImpl.updateCustomer(selectedCustomer, "customer_address");

							break;
						case 7:
							System.out.println("Enter updated Customer Credit Points");
							scanner.nextLine();
							Long customerCreditPoints = scanner.nextLong();
							selectedCustomer.setCustomerCreditPoints(customerCreditPoints);
							customerServiceImpl.updateCustomer(selectedCustomer, "customer_credit_points");
							break;
						case 8:
							continueUpdate = false;

						default:
							System.out.println("Enter a valid option");
							break;

						}

					} while (continueUpdate);

				} else {
					System.out.println("Customer ID is invalid");
				}

				break;
			case 3:

				displayCustomer(customerServiceImpl.displayAllCustomers());

				break;
			case 4:
				next = false;
				break;
			default:
				break;

			}

		} while (next);

	}

	public static void displayCustomer(List<Customer> customerList) {
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-15s %-15s %-15s %-15s %-45s %-15s %-30s %-20s%n", "Customer ID", "First Name", "Last Name",
				"DOB", "Email", "Phone Number", "Address", "Credit Points");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Customer customer : customerList) {
			System.out.printf("%-15s %-15s %-15s %-15s %-45s %-15s %-30s %-20s%n", customer.getCustomerID(),
					customer.getCustomerFirstName(), customer.getCustomerLastName(),
					customer.getCustomerDOB().toString(), customer.getCustomerEmail(), customer.getCustomerPhNo(),
					customer.getCustomerAddress(), customer.getCustomerCreditPoints());

		}

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}

	private static Customer addCustomer() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer ID:");
		String customerID = scanner.nextLine();
		System.out.println("Enter Customer FirstName:");
		String customerFirstName = scanner.nextLine();
		System.out.println("Enter Customer LastName:");
		String customerLastName = scanner.nextLine();
		System.out.println("Enter customer DOB:");
		String DOB = scanner.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate customerDOB = LocalDate.parse(DOB, formatter);
		System.out.println("Enter customer email:");
		String customerEmail = scanner.nextLine();
		System.out.println("Enter Customer Phone Number:");
		Long customerPhno = scanner.nextLong();
		System.out.println("Enter Customer Address:");
		scanner.nextLine();
		String customerAddress = scanner.nextLine();
		System.out.println("Enter customer credit points");
		Long creditPoints = scanner.nextLong();
		Customer customer = new Customer(customerID, customerFirstName, customerLastName, customerDOB, customerEmail,
				customerPhno, customerAddress, creditPoints);

		return customer;
	}

}
