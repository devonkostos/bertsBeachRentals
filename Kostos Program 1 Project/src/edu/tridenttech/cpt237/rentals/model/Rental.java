/*
 * Class to store and 
 * modify rental data.
 * 
 */

package edu.tridenttech.cpt237.rentals.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rental extends Company
{
	private String itemName;
	private Date date; 
	protected String rentalPeriod = "Years";
	private int numPeriods;
	private String rentalId;

	/**
	 * Create a new rental 
	 * @param  Type of item 
	 * @param item Item rented
	 * @param date  Date rented
	 * @param numPeriods Number of 'periods' rented; * definition of period depends on type of rental
	 * @param grossCost Cost without any discounts
	 */
	public Rental(String item, Date date, int numPeriods)
	{
		this.itemName = item;
		this.date = date;
		this.numPeriods = numPeriods;
	}

	public String getItemType()
	{
		return itemName;
	}

	public String getRentalPeriod()
	{
		return rentalPeriod;
	}

	public Date getDate()
	{
		return date;
	}

	public int getNumPeriods()
	{
		return numPeriods;
	}

	public double getGrossCost()
	{
		return 0;
	}

	public double getDiscount()
	{
		return 0;
	}
	
	public String getRentalId()
	{
		return rentalId;
	}

	public void setRentalId(String id)
	{
		rentalId = id;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public void setNewDate(Date newDate)
	{
		this.date = newDate;
	}
	
	public void setNewDuration(int newDuration)
	{
		this.numPeriods = newDuration;
	}
	
	public void setName(String newName)
	{
		this.itemName = newName;
	}
	
	public void setNewId(String newId)
	{
		this.rentalId = newId;
	} 

	@Override
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return String.format(
				"%-12s%-15s%-10s%5d  %-8s%12.2f%10.2f%10.2f",
				sdf.format(date), rentalId, itemName, numPeriods, rentalPeriod, getGrossCost(), getDiscount(), getGrossCost() - getDiscount());
	}
}
