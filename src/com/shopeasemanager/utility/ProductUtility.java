package com.shopeasemanager.utility;

import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.Product;
import com.shopeasemanager.entity.ProductCategory;
import com.shopeasemanager.service.ProductServiceImpl;

public class ProductUtility {

	public static void main(String[] args) {

		menu();

	}

	private static void menu() {
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		do {
			System.out.println("Product Menu\n");
			System.out.println("1.Add Product\n2.Update Product\n3.Display ALL Products 4.Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				productServiceImpl.addProduct(getProduct());
				break;
			case 2:
				System.out.println("Enter Product ID:");
				Long selectedProductID = scanner.nextLong();
				Product selectedProduct=productServiceImpl.getProduct(selectedProductID);
				if(selectedProduct!=null)
				{
					selectedProduct.setProductID(selectedProductID);
					boolean continueUpdate = true;
					do {
						System.out.println(
								"UPDATE:\n1.Product Name\n2.Product Brand\n3.Product Rate\n4.Product GST Rate\n5.Back");
						int selectedChoice = scanner.nextInt();
						switch (selectedChoice) {
						case 1:
							System.out.println("Enter new Product Name:");
							scanner.nextLine();
							selectedProduct.setProductName(scanner.nextLine());
							productServiceImpl.updateProduct(selectedProduct, "product_name");
							break;
						case 2:
							System.out.println("Enter new Product brand:");
							scanner.nextLine();
							selectedProduct.setProductName(scanner.nextLine());
							productServiceImpl.updateProduct(selectedProduct, "product_brand");
							break;
						case 3:
							System.out.println("Enter new Product Rate:");
							scanner.nextLine();
							selectedProduct.setProductRate(scanner.nextDouble());
							productServiceImpl.updateProduct(selectedProduct, "product_rate");
							break;
						case 4:
							System.out.println("Enter new Product Gst:");
							scanner.nextLine();
							selectedProduct.setProductGst(scanner.nextDouble());
							productServiceImpl.updateProduct(selectedProduct, "product_gst");
							break;
						case 5:
							continueUpdate = false;

						default:
							System.out.println("Enter a valid option");
							break;

						}

					} while (continueUpdate);
					
				}
				else
				{
					System.out.println("Product ID is invalid");
				}
				

				break;
			case 3:

				displayProduct(productServiceImpl.displayAllProducts());

				break;
			case 4:
				next = false;
				break;
			default:
				break;

			}

		} while (next);

	}

	

	public static void displayProduct(List<Product> productList) {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("%-10s %-20s %-20s %-16s %-10s%n", "Product ID", "Product Name", "Product Brand",
				"Product Rate", "Product GST");
		System.out.println("----------------------------------------------------------------------------------");

		for (Product product : productList) {
			System.out.printf("%-10s %-20s %-20s %-16.2f %-10.2f%n", product.getProductID(), product.getProductName(),
					product.getProductBrand(), product.getProductRate(), product.getProductGst());
		}

		System.out.println("----------------------------------------------------------------------------------");

	}

	private static Product getProduct() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Product ID:");
		Long productID = scanner.nextLong();
		System.out.println("Enter Product Name:");
		scanner.nextLine();
		String productName = scanner.nextLine();
		System.out.println("Enter Product Brand:");
		String productBrand = scanner.nextLine();
		System.out.println("Enter Product Rate:");
		Double productRate = scanner.nextDouble();
		System.out.println("Enter Product GST Rate:");
		Double productGST = scanner.nextDouble();
		Product product = new Product(productID, productName, productBrand, productRate, productGST);
		return product;
	}

}
