package com.shopeasemanager.utility;

import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.PCMapping;
import com.shopeasemanager.service.PCMappingServiceImpl;

public class PCMappingUtility {
	public static void main(String[] args)
	{
		menu();
	}

	private static void menu() {
		
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		do
		{
		System.out.println("ProductCategory Menu\n");
		System.out.println("1.View All 2.Exit");
		int choice =scanner.nextInt();
		switch(choice)
		{
		case 1:
			PCMappingServiceImpl pcMappingService = new PCMappingServiceImpl();
			displayItems(pcMappingService.displayAllItems());
			break;
		case 2:
			next=false;
			break;
		default:
			break;
		}
		}
		while(next);
		
	}

	private static void displayItems(List <PCMapping> pcMappingList) {
		
		System.out.println("----------------------------------------");
		System.out.printf("%-12s %-12s %-15s%n", "pcmapping_id", "product_id", "category_code");
		System.out.println("----------------------------------------");

		for (PCMapping pcMapping : pcMappingList) {
		    String mappingID = String.valueOf(pcMapping.getMappingID());
		    String productID = String.valueOf(pcMapping.getProduct().getProductID());
		    String categoryID = String.valueOf(pcMapping.getProductCategory().getCategoryID());

		    // Add padding to align the values in columns
		    String formattedOutput = String.format("%-12s %-12s %-15s", mappingID, productID, categoryID);

		    System.out.println(formattedOutput);
		}

		System.out.println("------------------------------------------");

		
	}

}
