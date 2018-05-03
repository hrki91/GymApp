package gym.view;

import gym.GymProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BodyGroupController extends DataController{
	
	@FXML
	Label naziv = new Label();
	@FXML
	Label opis = new Label();
		
	@FXML
	TextField t_naziv = new TextField();
	@FXML
	TextField t_opis = new TextField();

	@Override
	public void initController() {
		super.initController();
		naziv.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "title"));
		opis.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "description"));
		}

}
