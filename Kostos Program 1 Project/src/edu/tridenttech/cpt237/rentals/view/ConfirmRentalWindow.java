package edu.tridenttech.cpt237.rentals.view;

import edu.tridenttech.cpt237.rentals.model.Rental;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConfirmRentalWindow implements EventHandler<ActionEvent>
{
	private Stage myStage;
	private Button okBtn = new Button("Accept");
	private Button cancelBtn = new Button("Cancel");
	private DisplayRentalPane rentalPane;

	private Rental rental;
	private Boolean accepted;

	public ConfirmRentalWindow()
	{
		myStage = new Stage();
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		myStage.setScene(scene);
		rentalPane = new DisplayRentalPane();
		myStage.setTitle("Find Rental");
		pane.add(rentalPane, 0, 0);
		pane.add(okBtn, 0, 2);
		pane.add(cancelBtn, 1, 2);
		
		okBtn.setOnAction(this);
		cancelBtn.setOnAction(this);
	}
	
	public boolean confirmRental(Rental r)
	{
		accepted = false;
		rental = r;
		rentalPane.showRental(rental, false);
		myStage.showAndWait();
		return accepted;
	}

	@Override
	public void handle(ActionEvent event)
	{
		if (okBtn == event.getSource()) {
			accepted = true;
		}
		myStage.close();
	}
}
