/*
 * Class to display, add, 
 * remove and modify rentals 
 * using the Rental class.
 * 
 */

package edu.tridenttech.cpt237.rentals.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Company
{
	private static Company instance;
	private String name = "Bert's Beach Rentals";
	protected ArrayList<Rental> hourlyRentals = new ArrayList<>();
	protected ArrayList<Rental> dailyRentals = new ArrayList<>();
	protected ArrayList<Rental> weeklyRentals = new ArrayList<>();
	protected ArrayList<Rental> rentalList = new ArrayList<>();
	private String[] supportedRentals = {"Bike", "Umbrella", "Getaway", "SeaDoo", "Cabin"};
	private int nextId = 1001;
	
	protected Company()
	{
		//Singleton constructor
	}
	
	public static Company getInstance()
	{
		if (instance == null) 
		{
			instance = new Company();
		}
		
		return instance;
	}
	
	public String getName()
	{
		return name;
	}

	public List<String> getSupportedRentals()
	{
		return Collections.unmodifiableList(Arrays.asList(supportedRentals));
	}

	private String getNextId(Rental r)
	{
		String id = String.format("%s-%d", r.getItemType().toUpperCase(), nextId);
		nextId++;
		return id;
	}

	public void addRental(Rental r)
	{
		if (r.getRentalPeriod().equals("Hours"))
		{
			hourlyRentals.add(r);
		}
		else if (r.getRentalPeriod().equals("Days"))
		{
			dailyRentals.add(r);
		}
		else if (r.getRentalPeriod().equals("Weeks"))
		{
			weeklyRentals.add(r);
		}

		r.setRentalId(getNextId(r));
		rentalList.add(r);
	}
	
	public void updateRental(Rental r, Date newDate, int newDuration)
	{
		if (r.getItemType().equals("SeaDoo"))
		{
			r.setNewDate(newDate);
			r.setNewDuration(newDuration);
		}
		else if (r.getItemType().equals("Getaway"))
		{
			r.setNewDate(newDate);
			r.setNewDuration(newDuration);
		}
		else if (r.getItemType().equals("Bike"))
		{
			r.setNewDate(newDate);
			r.setNewDuration(newDuration);
		}
		else if (r.getItemType().equals("Umbrella"))
		{
			r.setNewDate(newDate);
			r.setNewDuration(newDuration);
		}
		else if (r.getItemType().equals("Cabin"))
		{
			r.setNewDate(newDate);
			r.setNewDuration(newDuration);
		}
	}
	
	public void removeRental(Rental r)
	{
		if (r.getItemType().equals("SeaDoo"))
		{
			hourlyRentals.remove(r);
		}
		else if (r.getItemType().equals("Getaway"))
		{
			hourlyRentals.remove(r);
		}
		else if (r.getItemType().equals("Bike"))
		{
			dailyRentals.remove(r);
		}
		else if (r.getItemType().equals("Umbrella"))
		{
			dailyRentals.remove(r);
		}
		else if (r.getItemType().equals("Cabin"))
		{
			weeklyRentals.remove(r);
		}
		
		rentalList.remove(r);
	}

	public Rental createRental(String type, Date date, int rentalPeriod)
	{
		final double COST;
		
		if (type.equals("SeaDoo"))
		{
			COST = 50;
			Rental r = new Hourly(type, date, rentalPeriod, COST);
			return r;
		}
		else if (type.equals("Getaway"))
		{
			COST = 35;
			Rental r = new Hourly(type, date, rentalPeriod, COST);
			return r;
		}
		else if (type.equals("Bike"))
		{
			COST = 5;
			Rental r = new Daily(type, date, rentalPeriod, COST);
			return r;
		}
		else if (type.equals("Umbrella"))
		{
			COST = 15;
			Rental r = new Daily(type, date, rentalPeriod, COST);
			return r;
		}
		else if (type.equals("Cabin"))
		{
			COST = 800;
			Rental r = new Weekly(type, date, rentalPeriod, COST);
			return r;
		}

		return null;
	}
	
	public Rental findRentalById(String idStr)
	{
		String findStr = idStr.toUpperCase();
		
		for (Rental r : rentalList)
		{
			if (findStr.equals(r.getRentalId()))
			{
				return r;
			}
		}

		return null;	
	}
	
	public List<Rental> getRentalsByType(String typeName)
	{
		// TODO -- return the correct list based on the typeName
		// Beware of using == with strings; it doesn't work
		
		if (typeName.equals("SeaDoo"))
		{
			return Collections.unmodifiableList(hourlyRentals);
		}
		else if (typeName.equals("Getaway"))
		{
			return Collections.unmodifiableList(hourlyRentals);
		}
		else if (typeName.equals("Bike"))
		{
			return Collections.unmodifiableList(dailyRentals);
		}
		else if (typeName.equals("Umbrella"))
		{
			return Collections.unmodifiableList(dailyRentals);
		}
		else if (typeName.equals("Cabin"))
		{
			return Collections.unmodifiableList(weeklyRentals);
		}
		
		return Collections.unmodifiableList(rentalList);
	}

	public void loadRentals(String filename) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String fileRead = br.readLine();
			
			while (fileRead != null)
			{
				String[] fields = fileRead.split(",");

				Date rentalDate = sdf.parse(fields[0]);
				String rentalId = fields[1];
				String itemName = fields[2];
				int rentalDuration = Integer.parseInt(fields[3]);
				
				final double COST;
				
				if (itemName.equals("SeaDoo"))
				{
					COST = 50;
					Rental r = new Hourly(rentalId, rentalDate, rentalDuration, COST);
					r.getRentalPeriod().equals("Hours");
					r.setNewId(rentalId);
					r.setName(itemName);
					hourlyRentals.add(r);
				}
				else if (itemName.equals("Getaway"))
				{
					COST = 35;
					Rental r = new Hourly(rentalId, rentalDate, rentalDuration, COST);
					r.getRentalPeriod().equals("Hours");
					r.setNewId(rentalId);
					r.setName(itemName);
					hourlyRentals.add(r);
				}
				else if (itemName.equals("Bike"))
				{
					COST = 5;
					Rental r = new Daily(rentalId, rentalDate, rentalDuration, COST);
					r.getRentalPeriod().equals("Days");
					r.setNewId(rentalId);
					r.setName(itemName);
					dailyRentals.add(r);
				}
				else if (itemName.equals("Umbrella"))
				{
					COST = 15;
					Rental r = new Daily(rentalId, rentalDate, rentalDuration, COST);
					r.getRentalPeriod().equals("Days");
					r.setNewId(rentalId);
					r.setName(itemName);
					dailyRentals.add(r);
				}
				else if (itemName.equals("Cabin"))
				{
					COST = 800;
					Rental r = new Weekly(rentalId, rentalDate, rentalDuration, COST);
					r.getRentalPeriod().equals("Weeks");
					r.setNewId(rentalId);
					r.setName(itemName);
					weeklyRentals.add(r);
				}
				
				fileRead = br.readLine();
			}
			
			br.close();
		}
		
		catch(IOException | ParseException ex)
		{
			ex.printStackTrace();
		} 
	}

	public void showAllRentals()
	{
		int hourCount = 0, dayCount = 0, weekCount = 0;
		double hourGross = 0.0, dayGross = 0.0, weekGross = 0.0;
		double hourDiscount = 0.0, dayDiscount = 0.0, weekDiscount = 0.0;

		
		System.out.printf("Hourly:\n%-12s%-15s%-10s%5s  %-8s%12s%10s%10s\n",
						  "Date", "RID", "Item Name", "NUM", "PRD", "Orig. Price", "Discount", "Actual");
		
		for (Rental r : hourlyRentals)
		{
			System.out.println(r);
			hourCount++;
			hourGross += r.getGrossCost();
			hourDiscount += r.getDiscount();

		}
		
		System.out.printf("Rentals: %d\nOrig. Price: $%.2f\nSales: $%.2f", 
						  hourCount, hourGross, hourGross - hourDiscount);
		
		
		System.out.printf("\n\nDaily:\n%-12s%-15s%-10s%5s  %-8s%12s%10s%10s\n",
						  "Date", "RID", "Item Name", "NUM", "PRD", "Orig. Price", "Discount", "Actual");
		
		for (Rental r : dailyRentals)
		{
			System.out.println(r);
			dayCount++;
			dayGross += r.getGrossCost();
			dayDiscount += r.getDiscount();
		}
		
		System.out.printf("Rentals: %d\nOrig. Price: $%.2f\nSales: $%.2f", 
						  dayCount, dayGross, dayGross - dayDiscount);
		
		
		System.out.printf("\n\nWeekly:\n%-12s%-15s%-10s%5s  %-8s%12s%10s%10s\n",
						  "Date", "RID", "Item Name", "NUM", "PRD", "Orig. Price", "Discount", "Actual");
		
		for (Rental r : weeklyRentals)
		{
			System.out.println(r);
			weekCount++;
			weekGross += r.getGrossCost();
			weekDiscount += r.getDiscount();
		}
		
		System.out.printf("Rentals: %d\nOrig. Price: $%.2f\nSales: $%.2f\n\n", 
						  weekCount, weekGross, weekGross - weekDiscount);
	}

	public static void main(String[] args)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		Company c = new Company();
		c.loadRentals("RentalRecords.txt");
		c.showAllRentals();
		Rental r = c.findRentalById("NONE-0000");
		System.out.printf("%15s: %10s\n", "Item ID:", sdf.format(r.getDate()));
		System.out.printf("%15s: %10d\n", "Rental Period:", r.getNumPeriods());
		System.out.printf("%15s: %10.2f\n", "Gross Rental:", r.getGrossCost());
		System.out.printf("%15s: %10.2f\n", "Discount:", r.getDiscount());
	}
}//END class Company
