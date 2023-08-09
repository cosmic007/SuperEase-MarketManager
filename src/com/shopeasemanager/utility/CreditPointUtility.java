package com.shopeasemanager.utility;

import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.CreditPoint;
import com.shopeasemanager.service.CreditPointServiceImpl;


public class CreditPointUtility {

	public static void main(String[] args) {
		menu();

	}

	private static void menu() {
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		CreditPointServiceImpl creditPointServiceImpl = new CreditPointServiceImpl();
		do
		{
		System.out.println("CreditPoint Menu\n");
		System.out.println("1.Display CreditPoint    2.Update CreditPoint   3.Exit");
	
		int choice =scanner.nextInt();
		switch(choice)
		{
		case 1:
			List<CreditPoint> creditPointList =creditPointServiceImpl.displayCreditPoint();
			for(CreditPoint creditPoint:creditPointList)
			{
				System.out.println(creditPoint.getProductCategory().getCategoryID()+"     "+creditPoint.getCreditPoint());
			}
			
			break;
		case 2:
			System.out.println("Enter Category Code");
			scanner.nextLine();
			String categoryCode = scanner.nextLine();
			System.out.println("Enter New CreditPoint:");
			Long creditPoint=scanner.nextLong();
			creditPointServiceImpl.updateCreditPoint(categoryCode, creditPoint);
			break;
		case 3:
			next=false;
			break;
		default:
			break;
		}
		}while(next);
		
		
	}

}
