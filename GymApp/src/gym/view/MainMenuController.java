package gym.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import gym.GymProperties;
import gym.Main;
import gym.db.DbOperation;
import gym.db.Glavni_meni;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class MainMenuController extends Controller{	
	
	private List<Glavni_meni> glavniMeni;
	Logger log = Logger.getLogger(MainMenuController.class.getName());
	private Main mainApp;
	 
	@FXML
	private Button izlaz;
	@FXML
	private Button pregled;
	@FXML
	private Button korisnici;	
	 
	 @SuppressWarnings("unchecked")
	public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;
	        HashMap<String[],Boolean> tmp = new HashMap<>();
	        String restriction = "eq;aktivna";
	        tmp.put(restriction.split(";"), true);	        
	        log.info("Ucitavanje glavnog menija");
			List<HashMap<String[],?>> l = new ArrayList<>();
			HashMap<String[],Integer> tmp_ = new HashMap<>();
			restriction = "gt;korisnik";
			int tip = Integer.valueOf(System.getProperty("user.type"))-1;
			tmp_.put(restriction.split(";"), tip);
			l.add(tmp);l.add(tmp_);
			glavniMeni = DbOperation.Instance().getValue(l, Glavni_meni.class);
			int startCordinatesX = Integer.valueOf(GymProperties.getPropertie("startCordinatesX"));
			int startCordinatesY= Integer.valueOf(GymProperties.getPropertie("startCordinatesY"));
			Integer space = Integer.valueOf(GymProperties.getPropertie("spaceBetweenButtons"));
			
			
			for (Glavni_meni meni : this.glavniMeni) {
				Button b = new Button();
				b.setText(meni.getNaslov());
				b.getStyleClass().add("menuButton");
				b.setLayoutX(startCordinatesX);
				b.setLayoutY(startCordinatesY);
				if(meni.getController() == null) {
					b.setOnAction((event) -> {
						exit();
					});
				}else {
					b.setOnAction((event) -> {
						setPage(meni);
					});
				}
				this.mainApp.getAnchorPane().getChildren().add(b);
				this.mainApp.getAnchorPane().applyCss();
				startCordinatesY += b.getHeight()+space;
				}
	    }
	 //METODA KOJA SE POZIVA PRILIKOM KLIKA NA GUMB
	 public void setPage(Glavni_meni meni) {
		log.info("Otvaram formu"+meni.getNaslov());
		mainApp.setScreen(meni.getFxml(),meni.getController(),false,"center");		 
	 }
	 
	 public void exit(){
		 log.info("Closing MainMenu Form");
		 mainApp.close();
	 }


	public void initController() {
	}
	
	 
}
