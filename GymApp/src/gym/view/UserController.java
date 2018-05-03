package gym.view;

import gym.GymProperties;
import gym.db.Tip_treninga;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserController extends DataController{
	
	@FXML
	Label ime = new Label();
	@FXML
	Label aktivna = new Label();
	@FXML
	Label prezime = new Label();
	@FXML
	Label tip = new Label();
	@FXML
	Label kor_ime = new Label();
	@FXML
	Label zaporka = new Label();
	
	@FXML
	TextField t_ime = new TextField();
	@FXML
	CheckBox t_aktivna = new CheckBox();
	@FXML
	TextField t_prezime = new TextField();
	@FXML
	TextField t_kor_ime = new TextField();
	@FXML
	TextField t_zaporka = new TextField();
	@FXML
	ChoiceBox<Tip_treninga> t_tip = new ChoiceBox<Tip_treninga>();
		

	@Override
	public void initController() {
		super.initController();
		ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "name"));
		aktivna.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "active"));
		prezime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "surName"));
		tip.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "member_type"));
		kor_ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "user"));
		kor_ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "password"));
	}

}
