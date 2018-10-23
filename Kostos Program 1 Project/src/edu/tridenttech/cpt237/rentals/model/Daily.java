/*
 * Class that inherits Rental 
 * class and calculates daily 
 * rental prices and discounts.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt237.rentals.model;

import java.util.Date;

public class Daily extends Rental
{
	private final double COST;
	
	public Daily(String item, Date date, int numPeriod, final double COST)
	{
		super(item, date, numPeriod);
		this.COST = COST;
	}
	
	public String getRentalPeriod()
	{
		rentalPeriod = "Days";
		return rentalPeriod;
	}
	
	public double getGrossCost()
	{
		double price = 0.0;
		
		if (super.getItemType().equals("Bike"))
		{
			price = super.getNumPeriods() * COST;
		}
		else if (super.getItemType().equals("Umbrella"))
		{
			price = super.getNumPeriods() * COST;
		}
		
		return price;
	}
	
	public double getDiscount()
	{
		int numOfDays = 5, daysFree = 15;
		double discount = 0.0;
		
		if (super.getNumPeriods() >= numOfDays)
		{
			discount = super.getNumPeriods() / numOfDays * daysFree;
		}
		
		return discount;
	}
}//END class Daily