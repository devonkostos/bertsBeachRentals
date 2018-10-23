/*
 * Simulate a rental business 
 * application using multiple 
 * classes.
 * 
 */

package edu.tridenttech.cpt237.rentals;

import edu.tridenttech.cpt237.rentals.model.Company;
import edu.tridenttech.cpt237.rentals.view.StartWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Company c = Company.getInstance();
		c.loadRentals("RentalRecords.txt");
		StartWindow window = new StartWindow(primaryStage);
		window.show(c);
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}//END class MainApplication
