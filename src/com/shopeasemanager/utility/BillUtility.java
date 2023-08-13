package com.shopeasemanager.utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.Bill;
import com.shopeasemanager.entity.BillDetails;
import com.shopeasemanager.entity.Customer;
import com.shopeasemanager.entity.Employee;
import com.shopeasemanager.entity.Freebies;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.entity.ProductOffer;
import com.shopeasemanager.pdf.PdfGenerator;
import com.shopeasemanager.service.BillServiceImpl;
import com.shopeasemanager.service.CreditPointServiceImpl;
import com.shopeasemanager.service.CustomerServiceImpl;
import com.shopeasemanager.service.DiscountServiceImpl;
import com.shopeasemanager.service.EmployeeScoreServiceImpl;
import com.shopeasemanager.service.EmployeeServiceImpl;
import com.shopeasemanager.service.FreebiesServiceImpl;
import com.shopeasemanager.service.ProductOfferServiceImpl;
import com.shopeasemanager.service.ProductServiceImpl;
import com.shopeasemanager.service.StockServiceImpl;

public class BillUtility {

	private static void menu() {
		BillServiceImpl billServiceImpl = new BillServiceImpl();

		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		do {
			System.out.println("Bill Menu\n");
			System.out.println("1.Generate Bill  2.View Bill  3.Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				Boolean status;

				Bill bill = createBill();
				System.out.println(bill.getDateOfPurchase());
				Long billNo = billServiceImpl.getBillNoByDOP(bill.getDateOfPurchase());
				bill.setBillNo(billNo);
				if (billNo != null) {
					status = generateBill(bill);
					if (status) {
						List<BillDetails> billDetailsListFromTable = billServiceImpl.getBillDetailsList(billNo);
						Boolean result = computeAndGenerateBill(billDetailsListFromTable);
						if (result) {
							System.out.println("Bill Generated Successfully");
							PdfGenerator.generatePdf(billDetailsListFromTable);

						} else {
							System.out.println("Failed");
						}
					} else {
						System.out.println("Bill generation failed");
					}
				} else {
					System.out.println("BillNo not found");
				}

				break;
			case 2:
				System.out.println("Enter Bill No:");
				Long billNumber = scanner.nextLong();
				List<BillDetails> billDetailsListFromDB = billServiceImpl.getBillDetailsList(billNumber);
				displayBillDetails(billDetailsListFromDB);
				FreebiesServiceImpl freebiesServiceImpl = new FreebiesServiceImpl();
				List<Freebies> freebiesList = freebiesServiceImpl.displayFreebies(billNumber);
				if (freebiesList != null) {
					for (Freebies freebies : freebiesList) {
						System.out
								.println(freebies.getBill().getBillNo() + "     " + freebies.getProduct().getProductID()
										+ "     " + freebies.getProduct().getProductName() + "      "
										+ freebies.getQuantity() + "     " + freebies.getCustomer().getCustomerID());
					}

				}

				break;
			case 3:
				next = false;
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
		} while (next);

	}

	private static void displayBillDetails(List<BillDetails> billDetailsListFromDB) {
		final String BILL_FORMAT = "----------------------------------\n" + "          INVOICE\n"
				+ "----------------------------------\n" + "Bill No: %d\n" + "Date: %s\n" + "Customer: %s\n"
				+ "Employee: %s\n" + "Discount Applied: %.2f\n" + "Credit Earned: %d\n" + "Credit Used: %d\n"
				+ "-------------------------------------------------------------------------------\n"
				+ "Description          |  Quantity  |  Unit Price  |  Discount  |  Amount\n"
				+ "-------------------------------------------------------------------------------\n";

		BillServiceImpl billServiceImpl = new BillServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();

		Long billNo = billDetailsListFromDB.get(0).getBill().getBillNo();
		Bill bill = billServiceImpl.getBillFromBillNo(billNo);
		Employee employee = new Employee();
		Customer customer = new Customer();

		System.out.printf(BILL_FORMAT, bill.getBillNo(), bill.getDateOfPurchase(),
				bill.getCustomer().getCustomerFirstName() + " " + bill.getCustomer().getCustomerLastName(),
				bill.getEmployee().getEmployeeFirstName() + " " + bill.getEmployee().getEmployeeLastName(),
				bill.getDiscountApplied(), bill.getCreditEarned(), bill.getCreditUsed());

		double totalAmount = 0.0;

		for (BillDetails billDetails : billDetailsListFromDB) {
			Product product = productServiceImpl.getProduct(billDetails.getProduct().getProductID());
			String description = product.getProductName();
			Long quantity = billDetails.getQuantity();
			Double unitPrice = product.getProductRate() + product.getProductGst();
			Double discount = billDetails.getDiscountEarned();
			double amount = (unitPrice * quantity) - discount;

			System.out.printf("%-20s |  %8d  |  %10.2f  |  %8.2f  |  %10.2f\n", description, quantity, unitPrice,
					discount, amount);

			totalAmount += amount;
		}

		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("Total: %.2f\n", bill.getTotalRate());
	}

	Employee employee = new Employee();
	Customer customer = new Customer();

	public static void main(String[] args) {
		menu();

	}

	private static Bill createBill() {
		BillServiceImpl billServiceImpl = new BillServiceImpl();
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

		Bill bill = null;
		try {

			LocalDateTime dateOfPurchase = LocalDateTime.now();
			System.out.println("Whether Registered Customer?  1.Yes  2.No");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			scanner.nextLine();
			String customerID;
			Customer customer = null;
			if (choice == 1) {
				System.out.println("Enter Customer ID:");
				customerID = scanner.nextLine();
			} else {
				customerID = "DEFAULTID";
			}
			if (customerServiceImpl.getCustomer(customerID) != null) {
				customer = customerServiceImpl.getCustomer(customerID);
				System.out.println("Enter Employee ID:");
				String employeeID = scanner.nextLine();
				if (employeeServiceImpl.getEmployee(employeeID) != null) {
					Employee employee = employeeServiceImpl.getEmployee(employeeID);
					bill = new Bill(dateOfPurchase, customer, employee);
					billServiceImpl.createBill(bill);

				} else {
					System.out.println("Invalid EmployeeID");
				}

			} else {
				System.out.println("Invalid Customer ID");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return bill;
	}

	private static Boolean generateBill(Bill bill) {
		Boolean status = false;

		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		DiscountServiceImpl discountServiceImpl = new DiscountServiceImpl();
		CreditPointServiceImpl creditPointServiceImpl = new CreditPointServiceImpl();
		StockServiceImpl stockServiceImpl = new StockServiceImpl();
		productServiceImpl.displayAllProducts();

		boolean addProduct = true;
		List<BillDetails> billDetailsList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("\n1.Add Product 2.Proceed");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				ProductUtility.displayProduct(productServiceImpl.displayAllProducts());
				System.out.println("Enter productID:");
				Long productID = scanner.nextLong();

				if (productServiceImpl.getProduct(productID) != null) {

					Product product = productServiceImpl.getProduct(productID);
					System.out.println("Enter quantity:");
					Long quantity = scanner.nextLong();
					if (quantity <= stockServiceImpl.getStockByID(productID)) {
						ProductOfferServiceImpl productOfferServiceImpl = new ProductOfferServiceImpl();
						ProductOffer productOffer = productOfferServiceImpl.getProductOffer(productID);
						if (productOffer != null) {
							if (productOffer.getOffer().getDescription().equalsIgnoreCase("BUY1GET1")
									&& productOffer.getStatus().equalsIgnoreCase("Active")) {
								Long stock = stockServiceImpl.getStockByID(productID);
								if (quantity * 2 <= stock) {
									Long free = quantity;
									Freebies freebies = new Freebies(bill, product, free, bill.getCustomer());

									FreebiesServiceImpl freebiesServiceImpl = new FreebiesServiceImpl();
									freebiesServiceImpl.sellFreeProducts(freebies);

								} else if (quantity * 2 > stock) {
									Long totalQ = quantity;
									Long remainingQuantity = quantity % 2;
									quantity = quantity / 2;
									quantity = quantity + remainingQuantity;
									Long freeQ = totalQ - quantity;
									Freebies freebies = new Freebies(bill, product, freeQ, bill.getCustomer());

									FreebiesServiceImpl freebiesServiceImpl = new FreebiesServiceImpl();
									freebiesServiceImpl.sellFreeProducts(freebies);

								}

							} else if (productOffer.getOffer().getDescription().equalsIgnoreCase("BUY1GET2")
									&& productOffer.getStatus().equalsIgnoreCase("Active")) {

								Long stock = stockServiceImpl.getStockByID(productID);
								if (quantity * 3 <= stock) {
									Long free = quantity;
									Freebies freebies = new Freebies(bill, product, free, bill.getCustomer());

									FreebiesServiceImpl freebiesServiceImpl = new FreebiesServiceImpl();
									freebiesServiceImpl.sellFreeProducts(freebies);

								} else if (quantity * 3 > stock) {
									Long totalQ = quantity;
									Long remainingQuantity = quantity % 2;
									quantity = quantity / 2;
									quantity = quantity + remainingQuantity;
									Long freeQ = totalQ - quantity;
									Freebies freebies = new Freebies(bill, product, freeQ, bill.getCustomer());

									FreebiesServiceImpl freebiesServiceImpl = new FreebiesServiceImpl();
									freebiesServiceImpl.sellFreeProducts(freebies);

								}
							}
						}
					}

					if (quantity <= stockServiceImpl.getStockByID(productID)) {

						Double discountEarned = discountServiceImpl.getDiscount(product);
						if (discountEarned != null) {
							discountEarned = discountEarned * quantity;
							Long creditEarned = creditPointServiceImpl.getCreditPoint(productID);
							if (creditEarned != null) {
								creditEarned = creditEarned * quantity;

								BillDetails billDetails = new BillDetails(bill, product, quantity, discountEarned,
										creditEarned);
								billDetailsList.add(billDetails);

							} else {
								System.out.println("CreditEarned is null");
							}

						} else {
							System.out.println("Discount is null");
						}
					} else {
						System.out.println("Stock not available for the selected quantity");
					}

				} else {
					System.out.println("Enter valid productID");
				}

				break;
			case 2:
				addProduct = false;
				break;
			default:
				System.out.println("Enter valid option");
				break;
			}

		} while (addProduct);
		if (!billDetailsList.isEmpty()) {
			BillServiceImpl billServiceImpl = new BillServiceImpl();
			status = billServiceImpl.generateBill(billDetailsList);

		}
		return status;

	}

	private static Boolean computeAndGenerateBill(List<BillDetails> billDetailsListFromTable) {
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		BillServiceImpl billServiceImpl = new BillServiceImpl();
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		EmployeeScoreServiceImpl employeeScoreServiceImpl = new EmployeeScoreServiceImpl();
		Scanner scanner = new Scanner(System.in);
		double discountApplied = 0;
		long creditEarned = 0;
		double rate = 0;

		for (BillDetails billDetails : billDetailsListFromTable) {
			discountApplied = discountApplied + billDetails.getDiscountEarned();
			creditEarned = creditEarned + billDetails.getCreditEarned();
			Product product = productServiceImpl.getProduct(billDetails.getProduct().getProductID());
			rate = rate + (product.getProductRate() + product.getProductGst()) * billDetails.getQuantity();

		}

		Bill bill = billDetailsListFromTable.get(0).getBill();
		double totalRate = rate - discountApplied;

		String customerID = billServiceImpl.getCustomerFromBill(bill.getBillNo());
		String employeeID = billServiceImpl.getEmployeeFromBill(bill.getBillNo());
		Customer customer = customerServiceImpl.getCustomer(customerID);
		Employee employee = employeeServiceImpl.getEmployee(employeeID);

		customerServiceImpl.updateCustomerCredit(creditEarned, customer.getCustomerID());

		Long customerCreditPoints = customer.getCustomerCreditPoints();
		Long chosenCreditPoints = (long) 0;
		if (customerCreditPoints > 1000) {
			System.out.println(
					"CreditPoints Available for " + customer.getCustomerFirstName() + ": " + customerCreditPoints);
			System.out.println("Use CreditPoints: 1.Yes 2.No");
			int choice = scanner.nextInt();
			if (choice == 1) {
				System.out.println("Enter desired creditpoint to redeem:");
				chosenCreditPoints = scanner.nextLong();
				if (chosenCreditPoints < customerCreditPoints) {

					totalRate = totalRate - chosenCreditPoints;
					customerServiceImpl.reduceCustomerCredit(chosenCreditPoints, customer.getCustomerID());
				} else {
					System.out.println("Exceeds the cureent amount");
				}
			} else if (choice == 2) {
				System.out.println("Adding creditpoint cancelled");

			}
		}

		long employeeScore = (long) ((5.0 / 100) * totalRate);

		employeeScoreServiceImpl.addScore(employee.getEmployeeID(), employeeScore);

		bill.setDiscountApplied(discountApplied);
		bill.setCreditEarned(creditEarned);
		bill.setCreditUsed(chosenCreditPoints);
		bill.setTotalRate(totalRate);

		boolean status = billServiceImpl.completeBillGeneration(bill);

		return status;
	}
}
