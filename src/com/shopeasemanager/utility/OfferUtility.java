package com.shopeasemanager.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.LeastSellingProduct;
import com.shopeasemanager.entity.Offer;
import com.shopeasemanager.entity.Product;
import com.shopeasemanager.entity.ProductCategory;
import com.shopeasemanager.entity.ProductOffer;
import com.shopeasemanager.service.OfferServiceImpl;
import com.shopeasemanager.service.ProductCategoryServiceImpl;
import com.shopeasemanager.service.ProductOfferServiceImpl;
import com.shopeasemanager.service.ProductServiceImpl;
import com.shopeasemanager.service.PurchaseStatsServiceImpl;

public class OfferUtility {

	public static void main(String[] args) {

		menu();

	}

	private static void menu() {
		OfferServiceImpl offerServiceImpl = new OfferServiceImpl();
		PurchaseStatsServiceImpl purchaseStatsServiceImpl = new PurchaseStatsServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		
		ProductOfferServiceImpl productOfferServiceImpl = new ProductOfferServiceImpl();
		boolean next = true;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Offer Menu");
		do {
			System.out.println("\n1.View Offer \n2.Add Offer \n3.Update Offer \n4.ViewLeastSellingProduct \n5.Add Offer to Product  \n6.Update Offer in product \n7.Display Product Offers \n8.Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				for (Offer offer : offerServiceImpl.displayOffer()) {
					System.out.println(offer.getOfferID() + "   " + offer.getDescription());

				}
				break;
			case 2:
				System.out.println("Enter Offer Description:");
				scanner.nextLine();
				String description = scanner.nextLine();
				boolean success = offerServiceImpl.addOfferDAO(description);
				if (success)
					System.out.println("Success");
				else
					System.out.println("Failed");
				break;
			case 3:
				for (Offer offer : offerServiceImpl.displayOffer()) {
					System.out.println(offer.getOfferID() + "   " + offer.getDescription());

				}
				
				System.out.println("Enter Offer ID:");
				Long offerID = scanner.nextLong();
				System.out.println("Enter description:");
				scanner.nextLine();
				String offerDescription = scanner.nextLine();

				Offer offer = new Offer(offerID, offerDescription);
				boolean result = offerServiceImpl.updateOffer(offer);
				if (result)
					System.out.println("Success");
				else
					System.out.println("Failed");
				break;

			case 4:
				ProductCategoryServiceImpl productCategoryServiceImpl = new ProductCategoryServiceImpl();
				List<ProductCategory> categoryList = productCategoryServiceImpl.displayProductCatgeory();
				System.out.println("Category ID     Category Name");
				System.out.println("--------------------------------");

				for (ProductCategory category : categoryList) {
				    System.out.printf("%-15s %-30s%n",
				                      category.getCategoryID(),
				                      category.getCategoryName());
				}

				System.out.println("Enter Category ID:");
				scanner.nextLine();
				String categoryCode = scanner.nextLine();
				System.out.println("Enter Limit:");
				int limit = scanner.nextInt();
				System.out.printf("%-20s %-30s %-15s %-20s %-10s%n", "Product ID", "Product Name", "Purchase Count",
						"Category Name", "Product Rate");

				for (LeastSellingProduct product : purchaseStatsServiceImpl.displayLeastSellingByCateory(categoryCode,
						limit)) {
					System.out.printf("%-20s %-30s %-15s %-20s %-10s%n", product.getProductID(),
							product.getProductName(), product.getPurchaseCount(), product.getCategoryName(),
							product.getProductRate());
				}

				break;
				
				
			case 5:
				ProductUtility.displayProduct(productServiceImpl.displayAllProducts());
				
				System.out.println("Enter product ID:");
				Long productId = scanner.nextLong();
				if(productServiceImpl.getProduct(productId)!=null)
				{
					Product product =productServiceImpl.getProduct(productId);
					for (Offer productOffer : offerServiceImpl.displayOffer()) {
						System.out.println(productOffer.getOfferID() + "   " + productOffer.getDescription());

					}
					
					Long offerId=scanner.nextLong();
					
					Offer newOffer = new Offer(offerId,offerServiceImpl.getOffer(offerId));
					
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					System.out.println("Enter new StartDate:");
					scanner.nextLine();
					String startD=scanner.nextLine();
					LocalDate startDate = LocalDate.parse(startD, formatter);
					System.out.println("Enter new EndDate:");
					String endD=scanner.nextLine();
					LocalDate endDate=LocalDate.parse(endD, formatter);
					
					
					
					
					ProductOffer productOffer = new ProductOffer(product,newOffer,startDate,endDate);
					
		
					boolean value=productOfferServiceImpl.addProductOffer(productOffer);
					if(value)
					{
						System.out.println("Successfully Added");
					}
					else
					{
						System.out.println("Failed");
					}
				}
				
				
				break;
			case 6:
				System.out.println("Enter product ID:");
				Long productid = scanner.nextLong();
				if(productServiceImpl.getProduct(productid)!=null)
				{
					Product product =productServiceImpl.getProduct(productid);
					for (Offer productOffer : offerServiceImpl.displayOffer()) {
						System.out.println(productOffer.getOfferID() + "   " + productOffer.getDescription());

					}
					
					Long offerId=scanner.nextLong();
					
					Offer newOffer = new Offer(offerId,offerServiceImpl.getOffer(offerId));
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					System.out.println("Enter new StartDate:");
					scanner.nextLine();
					String startD=scanner.nextLine();
					LocalDate startDate = LocalDate.parse(startD, formatter);
					System.out.println("Enter new EndDate:");
					String endD=scanner.nextLine();
					LocalDate endDate=LocalDate.parse(endD, formatter);
					
					
					
					
					ProductOffer productOffer = new ProductOffer(product,newOffer,startDate,endDate);
					
		
					boolean value=productOfferServiceImpl.updateProductOffer(productOffer);
					if(value)
					{
						System.out.println("Successfully Updated");
					}
					else
					{
						System.out.println("Failed");
					}
				}
				
				
				
				
				
				
				break;
			case 7:
				if(productOfferServiceImpl.displayProductOffer()!=null)
				{
				
				for(ProductOffer productOffer:productOfferServiceImpl.displayProductOffer())
				{
					System.out.println(productOffer.getProduct().getProductID()+"    "+productOffer.getProduct().getProductName()+"    "+productOffer.getOffer().getDescription()+"     "+productOffer.getStatus());
				}
				}
				else
				{
					System.out.println("List Not found");
				}
				
				
				break;
				
			case 8:
				next=false;
				break;

			default:
				System.out.println("Invalid Choice");
				break;

			}

		} while (next);

	}

}
