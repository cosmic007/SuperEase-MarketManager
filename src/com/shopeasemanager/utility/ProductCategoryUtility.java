package com.shopeasemanager.utility;

import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.ProductCategory;
import com.shopeasemanager.service.ProductCategoryServiceImpl;

public class ProductCategoryUtility {

	public static void main(String[] args) {
		menu();

	}

	private static void menu() {
		ProductCategoryServiceImpl productCategoryServiceImpl = new ProductCategoryServiceImpl();
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		do {
			System.out.println("Stock Menu\n");
			System.out.println("1.Add Product Category     2.Update Product Category     3.Display Product Category      4.Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the Category Code");
				scanner.nextLine();
				String categoryCode =scanner.nextLine();
				System.out.println("Enter the Category Name:");
				String categoryName=scanner.nextLine();
				ProductCategory productCategory = new ProductCategory(categoryCode,categoryName);
				productCategoryServiceImpl.addProductCategory(productCategory);
				
				break;
			case 2:
				System.out.println("Enter the Category Code");
				scanner.nextLine();
				String selectedCategoryCode =scanner.nextLine();
				System.out.println("Enter the Category Name:");
				String newCategoryName=scanner.nextLine();
				productCategoryServiceImpl.updateProductCategory(selectedCategoryCode, newCategoryName);
				break;
			case 3:
				List <ProductCategory> categoryList=productCategoryServiceImpl.displayProductCatgeory();
				for(ProductCategory category:categoryList)
				{
					System.out.println(category.getCategoryID()+"     "+category.getCategoryName());
				}
				
				break;
			case 4:
				next=false;
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}

		} while (next);

	}

}
