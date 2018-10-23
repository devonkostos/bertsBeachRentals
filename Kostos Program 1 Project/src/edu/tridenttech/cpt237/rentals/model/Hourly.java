/*
 * Class that inherits Rental 
 * class and calculates hourly 
 * rental prices and discounts.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt237.rentals.model;

import java.util.Date;

public class Hourly extends Rental
{
	private final double COST;
	
	public Hourly(String item, Date date, int numPeriod, final double COST)
	{
		super(item, date, numPeriod);
		this.COST = COST;
	}
	
	public String getRentalPeriod()
	{
		rentalPeriod = "Hours";
		return rentalPeriod;
	}
	
	public double getGrossCost()
	{
		double price = 0.0;
		
		if (super.getItemType().equals("SeaDoo"))
		{
			price = super.getNumPeriods() * COST;
		}
		else if (super.getItemType().equals("Getaway"))
		{
			price = super.getNumPeriods() * COST;
		}
		
		return price;
	}
	
	public double getDiscount()
	{
		int numOfHours = 3, hoursFree = 5;
		double discount = 0.0;
		
		if (super.getNumPeriods() >= numOfHours)
		{
			discount = super.getNumPeriods() * hoursFree;
		}
		
		return discount;
	}
}//END class Hourly
