/*
 * Class that inherits Rental 
 * class and calculates weekly 
 * rental prices and discounts.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt237.rentals.model;

import java.util.Date;

public class Weekly extends Rental
{
	private final double COST;
	
	public Weekly(String item, Date date, int numPeriod, final double COST)
	{
		super(item, date, numPeriod);
		this.COST = COST;
	}
	
	public String getRentalPeriod()
	{
		rentalPeriod = "Weeks";
		return rentalPeriod;
	}
	
	public double getGrossCost()
	{
		double price = 0.0;
		
		if (super.getItemType().equals("Cabin"))
		{
			price = super.getNumPeriods() * COST;
		}
		
		return price;
	}
	
	public double getDiscount()
	{
		int numOfWeeks = 1;
		double discount = 0.0, priceCut = .25, weeksFree = 0.0;
		
		if (super.getNumPeriods() > numOfWeeks)
		{
			weeksFree = COST * priceCut;
			discount = (super.getNumPeriods() - numOfWeeks) * weeksFree;
		}
		
		return discount;
	}
}//END class Weekly
