package gym.view;

import gym.GymProperties;
import gym.db.Clanarina;
import gym.db.Trening;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MembersController extends DataController{
	
	@FXML
	Label ime = new Label();
	@FXML
	Label prezime = new Label();
	@FXML
	Label datum_rodenja = new Label();
	@FXML
	Label adresa = new Label();
	@FXML
	Label clanarina = new Label();
	@FXML
	Label trening = new Label();
	
	@FXML
	TextField t_ime = new TextField();
	@FXML
	TextField t_prezime = new TextField();
	@FXML
	DatePicker t_datum_rodenja = new DatePicker();
	@FXML
	TextField t_adresa = new TextField();
	@FXML
	ChoiceBox<Clanarina> t_clanarina = new ChoiceBox<Clanarina>();
	@FXML
	ChoiceBox<Trening> t_trening = new ChoiceBox<Trening>();
	
	
		
	
	@Override
	public void initController() {
		super.initController();
		ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "name"));
		prezime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "surName"));
		datum_rodenja.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "birth"));
		adresa.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "address"));
		clanarina.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "membership"));
		trening.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "renig"));
		dataPane.setVisible(false);
	}


}
