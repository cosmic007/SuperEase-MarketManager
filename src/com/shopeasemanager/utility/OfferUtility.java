package com.shopeasemanager.utility;

import java.util.Scanner;

import com.shopeasemanager.entity.Offer;
import com.shopeasemanager.service.OfferServiceImpl;

public class OfferUtility {

	public static void main(String[] args) {
		
		menu();
		

	}

	private static void menu() {
		OfferServiceImpl offerServiceImpl = new OfferServiceImpl();
		boolean next=true;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Offer Menu");
		do
		{
			System.out.println("1.View Offer 2.Add Offer 3.Update Offer 4.");
			int choice = scanner.nextInt();
			switch(choice)
			{
			case 1: 
				for(Offer offer:offerServiceImpl.displayOffer())
				{
					System.out.println(offer.getOfferID()+"   "+offer.getDescription());
					
				}
				break;
			case 2:
				System.out.println("Enter Offer Description:");
				scanner.nextLine();
				String description =scanner.nextLine();
				boolean success=offerServiceImpl.addOfferDAO(description);
				if(success)
					System.out.println("Success");
				else
					System.out.println("Failed");
				break;
			case 3:
				System.out.println("Enter Offer ID:");
				Long offerID=scanner.nextLong();
				System.out.println("Enter description:");
				scanner.nextLine();
				String offerDescription=scanner.nextLine();
				
				Offer offer = new Offer(offerID,offerDescription);
				boolean result=offerServiceImpl.updateOffer(offer);
				if(result)
					System.out.println("Success");
				else
					System.out.println("Failed");
				break;
			
			default:
				System.out.println("Invalid Choice");
				break;

			}
			
		}
		while(next);
		
	}

}
