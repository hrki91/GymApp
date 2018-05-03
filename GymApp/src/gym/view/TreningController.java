package gym.view;

import gym.GymProperties;
import gym.db.Tip_treninga;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TreningController extends DataController{
	
	@FXML
	Label naziv = new Label();
	@FXML
	Label tip = new Label();
	@FXML
	Label tezina = new Label();
		
	@FXML
	TextField t_ime = new TextField();
	@FXML
	ChoiceBox<Integer> t_tezina = new ChoiceBox<Integer>();
	@FXML
	ChoiceBox<Tip_treninga> t_tip = new ChoiceBox<Tip_treninga>();
		

	@Override
	public void initController() {
		super.initController();
		naziv.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "name"));
		tezina.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "level"));
		tip.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "member_type"));
	}

}
