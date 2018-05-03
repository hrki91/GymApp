package gym.view;

import gym.GymProperties;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MembershipController extends DataController{
	
	@FXML
	Label naziv = new Label();
	@FXML
	Label aktivna = new Label();
	@FXML
	Label trajanje = new Label();
	@FXML
	Label br_treninga = new Label();
	@FXML
	Label cijena = new Label();
	
	@FXML
	TextField t_naziv = new TextField();
	@FXML
	CheckBox t_aktivna = new CheckBox();
	@FXML
	TextField t_trajanje = new TextField();
	@FXML
	TextField t_br_treninga = new TextField();
	@FXML
	TextField t_cijena = new TextField();
		

	@Override
	public void initController() {
		super.initController();
		naziv.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "membership"));
		aktivna.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "active"));
		trajanje.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "duration"));
		br_treninga.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "trening_number"));
		cijena.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "price"));

	}

}
