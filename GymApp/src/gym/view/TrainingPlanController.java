package gym.view;

import gym.GymProperties;
import gym.db.Tip_treninga;
import gym.db.Vjezba;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TrainingPlanController extends DataController{
	
	@FXML
	Label trening = new Label();
	@FXML
	Label vjezba = new Label();
	@FXML
	Label tezina = new Label();
	@FXML
	Label ponavljanja = new Label();
	@FXML
	Label dan = new Label();
		
	@FXML
	TextField t_ime = new TextField();
	@FXML
	ChoiceBox<Integer> t_tezina = new ChoiceBox<Integer>();
	@FXML
	ChoiceBox<Tip_treninga> t_trening = new ChoiceBox<Tip_treninga>();
	@FXML
	ChoiceBox<Vjezba> t_vjezba = new ChoiceBox<Vjezba>();	
	@FXML
	TextField t_ponavljanja = new TextField();
	@FXML
	TextField t_dan = new TextField();

	@Override
	public void initController() {
		super.initController();
		trening.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "trenig"));
		vjezba.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "exercise"));
		ponavljanja.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "repeats"));
		tezina.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "level"));
		dan.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "day"));
	}

}
