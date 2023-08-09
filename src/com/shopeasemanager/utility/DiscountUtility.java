package com.shopeasemanager.utility;

import java.util.Scanner;

import com.shopeasemanager.entity.Discount;
import com.shopeasemanager.service.DiscountServiceImpl;
import com.shopeasemanager.service.ProductServiceImpl;



public class DiscountUtility {

	public static void main(String[] args) {
		menu();

	}

	private static void menu() {
		DiscountServiceImpl discountServiceImpl = new DiscountServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		do {
			System.out.println("Discount Menu\n");
			System.out.println("1.View ALL Discount   2.Update Discount   3.Exit");

			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				for(Discount discount:discountServiceImpl.displayAllDiscount())
				{
					System.out.println(discount.getProduct().getProductID()+"       "+discount.getDiscountAmount());
					
				}
				break;
			case 2:
				System.out.println("Enter the productID:");
				Long productID=scanner.nextLong();
				if(productServiceImpl.getProduct(productID)!=null)
				{
					System.out.println("Enter the discount:");
					double discountAmount = scanner.nextDouble();
					discountServiceImpl.updateDiscount(productID, discountAmount);
					
				}
				else
				{
					System.out.println("Invalid Product ID");
				}
				
				
				
				break;
			case 3:
				next=false;
				break;
			default:
				System.out.println("Invalid Option");
				break;

			}

		} while (next);

	}
}
