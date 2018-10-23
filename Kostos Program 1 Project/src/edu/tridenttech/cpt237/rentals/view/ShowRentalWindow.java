package edu.tridenttech.cpt237.rentals.view;

import java.text.ParseException;
import java.util.Date;

import edu.tridenttech.cpt237.rentals.model.Company;
import edu.tridenttech.cpt237.rentals.model.Rental;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowRentalWindow implements EventHandler<ActionEvent>
{
	Stage myStage;
	Company company;
	Button updateBtn = new Button("Update");
	Button modifyBtn = new Button("Edit");
	Button deleteBtn = new Button("Delete");
	Button cancelBtn = new Button("Cancel");
	DisplayRentalPane rentalPane;
	Rental rental;

	public ShowRentalWindow()
	{
		GridPane pane = new GridPane();
		myStage = new Stage();
		Scene scene = new Scene(pane);
		myStage.setScene(scene);
		rentalPane = new DisplayRentalPane();
		myStage.setTitle("Find Rental");
		pane.add(rentalPane, 0, 0, 3, 1);
		pane.add(modifyBtn, 0, 2);
		pane.add(updateBtn, 1, 2);
		pane.add(deleteBtn, 2, 2);
		pane.add(cancelBtn, 4, 2);
		
		modifyBtn.setOnAction(this);
		modifyBtn.setDisable(true);
		updateBtn.setOnAction(this);
		deleteBtn.setOnAction(this);
		cancelBtn.setOnAction(this);

	}
	
	public void show(Company c, Rental rent)
	{
		company = c;
		rental = rent;
		modifyBtn.setDisable(false);
		updateBtn.setDisable(true);
		rentalPane.showRental(rental, false);
		myStage.showAndWait();
	}

	@Override
	public void handle(ActionEvent event)
	{
		if (modifyBtn == event.getSource()) {
			rentalPane.allowEdits(true);
			updateBtn.setDisable(false);
			modifyBtn.setDisable(true);
		} else if (updateBtn == event.getSource()) {
			Date newdate;
			try {
				newdate = rentalPane.getDesiredDate();
				int newPeriods = rentalPane.getNumPeriods();
				company.updateRental(rental, newdate, newPeriods);
				updateBtn.setDisable(true);
				modifyBtn.setDisable(false);
				rentalPane.showRental(rental, false);
			} catch (ParseException e) {
				new Alert(AlertType.ERROR, "You have entered an invalid Date.");
			} catch (NumberFormatException e) {
				new Alert(AlertType.ERROR, "You have entered an invalid number for the rental period.");
			}
		} else if (deleteBtn == event.getSource()) {
			company.removeRental(rental);
			myStage.close();
		} else {
			myStage.close();
		}
	}
}
