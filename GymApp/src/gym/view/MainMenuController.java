package gym.view;

import org.apache.log4j.Logger;

import gym.Main;


public class MainMenuController extends Controller{	
	Logger log = Logger.getLogger(MainMenuController.class.getName());
	private Main mainApp;
	 
	 public MainMenuController() {
	    }
	 
	 public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;
	    }
	 public void homePage(){
		 log.info("Opening HomePage Form");
		 mainApp.setScreen("view/HomePage.fxml","gym.view.HomePageController",false,"lefts");
	 }
	 public void exit(){
		 log.info("Closing MainMenu Form");
		 mainApp.close();
	 }
	 public void Members(){
		 log.info("Opening Members Form");
		 mainApp.setScreen("view/Members.fxml", "",false,"left");
	 }

	public void initController() {}
	 
}
