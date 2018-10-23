package edu.tridenttech.cpt237.rentals.view;

import edu.tridenttech.cpt237.rentals.model.Company;
import edu.tridenttech.cpt237.rentals.model.Rental;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class StartWindow implements EventHandler<ActionEvent>
{
	private Stage myStage;
	private Company company;
	private Button addBtn = new Button("Add Rental");
	private Button showBtn = new Button("Display Rental");
	private Button listBtn = new Button("List All Rentals");
	private Button quitBtn = new Button("Quit");
	private AddRentalWindow rentalUI = new AddRentalWindow();
	private FindWindow findUI = new FindWindow();

	public StartWindow(Stage stage)
	{
		myStage = stage;
		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage.setScene(scene);
		pane.getChildren().add(addBtn);
		pane.getChildren().add(showBtn);
		pane.getChildren().add(listBtn);
		pane.getChildren().add(quitBtn);

		addBtn.setOnAction(this);
		showBtn.setOnAction(this);
		listBtn.setOnAction(this);
		quitBtn.setOnAction(this);
	}
	
	public void show(Company c)
	{
		company = c;
		myStage.show();
		myStage.setTitle(company.getName());
	}

	@Override
	public void handle(ActionEvent event)
	{
		Button btn = (Button)event.getSource();
		if (btn == addBtn) {
			rentalUI.show(company);
		} else if (btn == showBtn) {
			findUI.show(company);
			Rental r = findUI.getFoundRental();
			if (r != null) {
				ShowRentalWindow rentalWindow = new ShowRentalWindow();
				rentalWindow.show(company, r);
			}
		} else if (btn == listBtn) {
			company.showAllRentals();
		} else {
			myStage.close();
		}
		
	}
}
