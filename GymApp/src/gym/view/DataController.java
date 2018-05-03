package gym.view;

import gym.GymProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


/**
 * SVE FORME ZA UNOS,BRISANJE,IZMJENU PODATAKA SU NA ISTU FORU I SADRŽE ISTE BUTTONE I JOŠ NEKE ELEMENTE.
 * OVM ABSTRAKTNOM KLASOM SMANJUJEM KOD
 * */
public abstract class DataController extends Controller{

	
	@FXML
	Button b_obrisi = new Button();
	@FXML
	Button b_produziClanarinu = new Button();
	@FXML
	Button b_ponisti = new Button();
	@FXML
	Button b_potvrda = new Button();
	@FXML
	Button b_izmjeni = new Button();
	@FXML
	Button b_novo = new Button();
	
	@FXML
	Pane dataPane = new Pane();
	
	@Override
	public void initController() {
		b_obrisi.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "delete"));
		b_produziClanarinu.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "extendMembership"));
		b_ponisti.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "cancel"));
		b_potvrda.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "confirm"));
		b_izmjeni.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "change"));
		b_novo.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "new"));
		dataPane.setVisible(false);
	}
	public void change() {
		dataPane.setVisible(true);
	}
	public void insert() {
		dataPane.setVisible(true);
	}
	public void delete() {
		//TODO 
	}
	

}
