package com.shopeasemanager.utility;

import java.util.Scanner;

import com.shopeasemanager.entity.Employee;
import com.shopeasemanager.entity.Salary;
import com.shopeasemanager.service.EmployeeServiceImpl;
import com.shopeasemanager.service.SalaryServiceImpl;



public class SalaryUtility {

	public static void main(String[] args) {
		menu();
		

	}

	private static void menu() {
		
		SalaryServiceImpl salaryServiceImpl = new SalaryServiceImpl();
		EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
		
		Scanner scanner = new Scanner(System.in);
		boolean next = true;
		do
		{
		System.out.println("Salary Menu\n");
		System.out.println("1.Diplay Salary Details   2.Update Salary by ID   3.Update Bonus by ID  4.Add new Salary 5.Exit");
		int choice =scanner.nextInt();
		switch(choice)
		{
		case 1:
			
			
			for(Salary salary:salaryServiceImpl.displaySalaryDetails())
			{
				System.out.println(salary.getEmployee().getEmployeeID()+"      "+salary.getSalaryAmount()+"       "+salary.getBonus());
				
			}
			break;
		case 2:
			System.out.println("Enter employeeID:");
			scanner.nextLine();
			String employeeID=scanner.nextLine();
			
			
			if(employeeServiceImpl.getEmployee(employeeID)!=null)
			{
				System.out.println("Enter new Salary:");
				double newSalary=scanner.nextDouble();
				salaryServiceImpl.updateSalary(employeeID, newSalary);
			}
			else
			{
				System.out.println("EmployeeID is invalid");
			}
			break;
		case 3:
			System.out.println("Enter employeeID:");
			scanner.nextLine();
			String employeeId=scanner.nextLine();
		
			
			if(employeeServiceImpl.getEmployee(employeeId)!=null)
			{
				System.out.println("Enter new Bonus:");
				double newBonus=scanner.nextDouble();
				salaryServiceImpl.updateBonus(employeeId, newBonus);
			}
			else
			{
				System.out.println("EmployeeID is invalid");
			}
			
			
			break;
		case 4:
			Employee employee=null;
			System.out.println("Enter employeeID");
			scanner.nextLine();
			String employeeid=scanner.nextLine();
			if(employeeServiceImpl.getEmployee(employeeid)!=null)
			{
				employee=employeeServiceImpl.getEmployee(employeeid);
				System.out.println("Enter Salary:");
				double salaryAmount = scanner.nextDouble();
				System.out.println("Enter Bonus:");
				double bonus=scanner.nextDouble();
				Salary salary = new Salary(employee,salaryAmount,bonus);
				salaryServiceImpl.addNewSalary(salary);
				
			}
			break;
		case 5:
			next=false;
			break;
		default:
			System.out.println("Invalid Option");
			break;
			
		}
		
		}while(next);
		
	}

}
