package gym.view;

import org.apache.log4j.Logger;

import gym.GymProperties;
import gym.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MainMenuController extends Controller{	
	Logger log = Logger.getLogger(MainMenuController.class.getName());
	private Main mainApp;
	 
	@FXML
	private Button izlaz;
	@FXML
	private Button pregled;
	@FXML
	private Button korisnici;	
	 
	 public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;
	    }
	 public void homePage(){
		 log.info("Opening HomePage Form");
		 mainApp.setScreen("view/HomePage.fxml","gym.view.HomePageController",false,"center");
	 }
	 public void exit(){
		 log.info("Closing MainMenu Form");
		 mainApp.close();
	 }
	 public void Members(){
		 log.info("Opening Members Form");
		 mainApp.setScreen("view/Members.fxml", "",false,"center");
	 }


	public void initController() {
		izlaz.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "exit"));
		pregled.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "view"));
		korisnici.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "users"));
		
	}
	 
}
