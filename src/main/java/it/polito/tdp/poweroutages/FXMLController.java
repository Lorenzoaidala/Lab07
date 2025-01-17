/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import javax.management.RuntimeErrorException;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="cmbNerc"
	private ComboBox<Nerc> cmbNerc; // Value injected by FXMLLoader

	@FXML // fx:id="txtYears"
	private TextField txtYears; // Value injected by FXMLLoader

	@FXML // fx:id="txtHours"
	private TextField txtHours; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

	@FXML
	void doRun(ActionEvent event) {
		txtResult.clear();
		try {
			Nerc selected_nerc = cmbNerc.getSelectionModel().getSelectedItem();
			if(selected_nerc == null) {
				txtResult.setText("Choose a valid NERC");
				return;
			}
			
			int maxOre = Integer.parseInt(txtHours.getText());
			if(maxOre<=0) {
				txtResult.setText("Hours number must be > 0");
				return;
			}
			
			int maxYears = Integer.parseInt(txtYears.getText());
			if(maxYears<=0) {
				txtResult.setText("Years number must be > 0");
				return;
			}
			
			List<PowerOutages> result = model.getWorstCase(selected_nerc, maxYears, maxOre);
			
			txtResult.setText(String.format("Computing analysis for max %d years and max %d hours duration", maxYears, maxOre));
			txtResult.clear();
			
			txtResult.appendText(String.format("Total people affected : %d\n", model.sommaPersoneCoinvolte(result)));
			txtResult.appendText(String.format("Total duration of the outage: %d\n", model.checkMaxHours(result)));
			for(PowerOutages po : result) {
			txtResult.appendText(String.format("%d %s %s %d %d", po.getYear(), po.getStart(), po.getEnd(), po.getDuration(), po.getAffectedPeople()));
			txtResult.appendText("\n");
			}
			
		} catch(NumberFormatException nfe) {
			throw new RuntimeException("Invalid format data", nfe);
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert cmbNerc != null : "fx:id=\"cmbNerc\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

		// Utilizzare questo font per incolonnare correttamente i dati;
		txtResult.setStyle("-fx-font-family: monospace");
	}

	public void setModel(Model model) {
		this.model = model;
		cmbNerc.getItems().addAll(model.getNercList());
	}
}
