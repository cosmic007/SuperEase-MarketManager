package com.shopeasemanager.utility;

import java.util.List;
import java.util.Scanner;

import com.shopeasemanager.entity.Stock;
import com.shopeasemanager.service.ProductServiceImpl;
import com.shopeasemanager.service.StockServiceImpl;

public class StockUtility {

	public static void main(String[] args) {
		
		
		menu();
	

	}

	private static void menu() {
		
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		do
		{
		System.out.println("Stock Menu\n");
		System.out.println("1.View Stock By ID   2.Set Stock By ID   3.Empty Products  4.Display StockList 5.Decrement Stock 6.Exit");
		StockServiceImpl stockServiceImpl = new StockServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		int choice =scanner.nextInt();
		switch(choice)
		{
		case 1:
			System.out.println("Enter Product ID:");
			Long productID =scanner.nextLong();
			
			if(productServiceImpl.getProduct(productID)!=null)
			{
				Long stockCount = stockServiceImpl.getStockByID(productID);
				System.out.println(stockCount);
				
			}
			else
			{
				System.out.println("Product ID is invalid");
			}
			
			
			break;
		case 2:
			System.out.println("Enter Product ID to Add Stock:");
			Long productId =scanner.nextLong();
			if(productServiceImpl.getProduct(productId)!=null)
			{
				System.out.println("Enter Added Stocks:");
				Long addedStock = scanner.nextLong();
				stockServiceImpl.setStockByID(productId, addedStock);
				
			}
			else
			{
				System.out.println("Product ID is invalid");
			}
			
			
		
			break;
			
		case 3:
			List <Stock> emptyStockList=stockServiceImpl.getEmptyStockList();
			if(emptyStockList.isEmpty())
			{
				System.out.println("Nothing is Empty");
			}
			for(Stock stock:emptyStockList)
			{
				System.out.println(stock.getProduct().getProductID()+"     "+stock.getAvailableStock());
			}
			
			break;
		case 4:
			List <Stock> stockList=stockServiceImpl.getStockList();
			for(Stock stock:stockList)
			{
				System.out.println(stock.getProduct().getProductID()+"     "+stock.getAvailableStock());
			}
			break;
			
			
		case 5:
			
			System.out.println("Enter Product ID:");
			Long productid=scanner.nextLong();
			if(productServiceImpl.getProduct(productid)!=null)
			{
				System.out.println("Enter Stock Count to reduce:");
				Long reducedCount = scanner.nextLong();
				stockServiceImpl.decrementStockbyId(productid, reducedCount);
				
			}
			else
			{
				System.out.println("Product ID is invalid");
			}
			
			break;
		case 6:
			next=false;
			break;
			
		default:
			break;
		}
		}
		while(next);
		
	}

}
