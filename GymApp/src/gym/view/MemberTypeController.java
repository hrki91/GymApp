package gym.view;

import gym.GymProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MemberTypeController extends DataController{
	
	@FXML
	Label naziv = new Label();
	@FXML
	Label opis = new Label();
	
	@FXML
	TextField t_name = new TextField();
	@FXML
	TextArea t_opis = new TextArea();
	

	@Override
	public void initController() {
		super.initController();
		naziv.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "name"));
		opis.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "active"));
	}

}
