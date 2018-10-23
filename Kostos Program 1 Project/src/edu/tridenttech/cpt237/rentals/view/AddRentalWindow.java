package edu.tridenttech.cpt237.rentals.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.tridenttech.cpt237.rentals.model.Company;
import edu.tridenttech.cpt237.rentals.model.Rental;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddRentalWindow implements EventHandler<ActionEvent>
{
	private Stage myStage;
	private Company company;
	TextField dateFld = new TextField();
	TextField periodFld = new TextField();
	ComboBox<String> rentalTypes = new ComboBox<>();
	Button okBtn = new Button("Get Rental");
	Button cancelBtn = new Button("Cancel");
	ConfirmRentalWindow confirmation;
	
	public AddRentalWindow()
	{
		myStage = new Stage();
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		Label typeLbl = new Label("Item");
		pane.add(typeLbl, 0, 0);
		pane.add(rentalTypes, 1, 0);
		myStage.setTitle("Rental Window");
		
		Label dateLbl = new Label("Desired Date");
		dateFld = new TextField();
		pane.add(dateLbl, 0, 1);
		pane.add(dateFld, 1, 1);

		Label periodLbl = new Label("Rental Period");
		periodFld = new TextField();
		pane.add(periodLbl, 0, 2);
		pane.add(periodFld, 1, 2);

		pane.add(okBtn, 0, 4);
		pane.add(cancelBtn, 2, 4);
		okBtn.setOnAction(this);
		cancelBtn.setOnAction(this);
		
		myStage.setScene(scene);
	}
	
	public void show(Company c)
	{
		company = c;
		rentalTypes.getItems().setAll(c.getSupportedRentals());
		rentalTypes.getSelectionModel().select(0);
		myStage.showAndWait();
	}

	@Override
	public void handle(ActionEvent event)
	{
		if (okBtn == event.getSource()) {
			SimpleDateFormat dateParser = new SimpleDateFormat("M/d/y");
			String item = rentalTypes.getValue();
			String periodStr = periodFld.getText();
			String dateStr = dateFld.getText();
			Date date;
			try {
				int rentalPeriod = Integer.parseInt(periodStr);
				date = dateParser.parse(dateStr);
				Rental r = company.createRental(item, date, rentalPeriod);
				if (getConfirmationWindow().confirmRental(r)) {
					company.addRental(r);
					myStage.close();
				}
			} catch (ParseException e) {
				new Alert(AlertType.ERROR,"Invalid date\nPlease enter a valid date (mm/dd/yyyy)").show();
				return;
			} catch (Exception e) {
				new Alert(AlertType.ERROR,"Invalid period.\nPlease enter a whole number.").show();
				return;
			}
		} else {
			myStage.close();
		}
	}
	
	private ConfirmRentalWindow getConfirmationWindow()
	{
		if (confirmation == null) {
			confirmation = new ConfirmRentalWindow();
		}
		return confirmation;
	}
}