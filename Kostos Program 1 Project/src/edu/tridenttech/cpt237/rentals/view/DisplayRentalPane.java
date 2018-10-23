package edu.tridenttech.cpt237.rentals.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.tridenttech.cpt237.rentals.model.Rental;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DisplayRentalPane extends GridPane
{
	private Rental rental;

	private TextField rentalIdField = new TextField();
	private TextField itemNameField = new TextField();
	private TextField dateField = new TextField();
	private TextField rentalPeriodField = new TextField();
	private TextField numPeriodsField = new TextField();
	private TextField grossCostField = new TextField();
	private TextField discountField = new TextField();

	// constructor
	public DisplayRentalPane()
	{
		super();
		Label rentalIdLbl = new Label("Rental ID:");
		Label itemNameLbl = new Label("Item:");
		Label dateLbl = new Label("Date: ");
		Label rentalPeriodLbl = new Label("Period:");
		Label numPeriodsLbl = new Label("Period:");
		Label grossCostLbl = new Label("Cost:");
		Label discountLbl = new Label("Discount");
		
		super.add(rentalIdLbl, 0, 0);
		super.add(rentalIdField, 0, 0);
		super.add(itemNameLbl, 0, 1);
		super.add(itemNameField, 1, 1);
		super.add(dateLbl, 0, 2);
		super.add(dateField, 1, 2);
		super.add(rentalPeriodLbl, 0, 3);
		super.add(rentalPeriodField, 1, 3);
		super.add(numPeriodsLbl, 0, 4);
		super.add(numPeriodsField, 1, 4);
		super.add(grossCostLbl, 0, 5);
		super.add(grossCostField, 1, 5);
		super.add(discountLbl, 0, 6);
		super.add(discountField, 1, 6);

		rentalIdField.setEditable(false);
		itemNameField.setEditable(false);
		dateField.setEditable(false);
		rentalPeriodField.setEditable(false);
		numPeriodsField.setEditable(false);
		grossCostField.setEditable(false);
		discountField.setEditable(false);
	}
	
	public void showRental(Rental r, boolean editable)
	{
		if (r != null) {
			rental = r;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			rentalIdField.setText(rental.getRentalId());
			itemNameField.setText(rental.getItemType());
			dateField.setText(sdf.format(rental.getDate()));
			rentalPeriodField.setText(rental.getRentalPeriod());
			numPeriodsField.setText(Integer.toString(rental.getNumPeriods()));
			grossCostField.setText(String.format("%.2f", rental.getGrossCost()));
			discountField.setText(String.format("%.2f", rental.getDiscount()));
			
			allowEdits(editable);
		}
	}
	
	public void allowEdits(boolean canEditable)
	{
			dateField.setEditable(canEditable);
			numPeriodsField.setEditable(canEditable);
	}
	
	public Date getDesiredDate() throws ParseException
	{
		SimpleDateFormat parser = new SimpleDateFormat("M/d/y");
		return parser.parse(dateField.getText());
	}
	
	public int getNumPeriods() throws NumberFormatException
	{
		return Integer.parseInt(numPeriodsField.getText());
	}
}
