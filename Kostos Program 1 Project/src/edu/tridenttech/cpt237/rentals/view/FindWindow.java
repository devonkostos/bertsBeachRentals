package edu.tridenttech.cpt237.rentals.view;

import edu.tridenttech.cpt237.rentals.model.Company;
import edu.tridenttech.cpt237.rentals.model.Rental;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FindWindow implements EventHandler<ActionEvent>
{

	private Stage myStage;
	private Company company;
	private TextField idFld = new TextField();
	private Button findBtn = new Button("Find");
	private Button cancelBtn = new Button("Cancel");
	private Rental foundRental;

	public FindWindow()
	{
		myStage = new Stage();
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		myStage.setScene(scene);
		Label idLbl = new Label("Rental ID");
		myStage.setTitle("Find Rental");
		pane.add(idLbl, 0, 0);
		pane.add(idFld, 1, 0);

		pane.add(findBtn, 0, 2);
		pane.add(cancelBtn, 1, 2);
		
		findBtn.setOnAction(this);
		cancelBtn.setOnAction(this);
	}
	
	public void show(Company c)
	{
		idFld.clear();
		foundRental = null;
		company = c;
		myStage.showAndWait();
	}

	public Rental getFoundRental()
	{
		return foundRental;
	}

	@Override
	public void handle(ActionEvent event)
	{
		if (findBtn == event.getSource()) {
			String idStr = idFld.getText();
			if (!idStr.isEmpty()) {
				foundRental = company.findRentalById(idStr);
				if (foundRental == null) {
					Alert err =  new Alert(AlertType.ERROR, "Invalid Rental Id\nPlease enter a valid id.");
					err.showAndWait();
				} else {
					myStage.close();
				}
			} else {
				Alert err =  new Alert(AlertType.ERROR, "Empty Rental Id\nPlease enter a valid id.");
				err.showAndWait();
			}
		} else {
			myStage.close();
		}
	}
}
