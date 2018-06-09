package gym.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import gym.GymProperties;
import gym.db.Clan;
import gym.db.Clanarina;
import gym.db.DbOperation;
import gym.db.Korisnik;
import gym.db.Tip_korisnika;
import gym.db.Tip_treninga;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
	TextField t_ime = new TextField();
	@FXML
	CheckBox t_aktivna = new CheckBox();
	@FXML
	TextField t_prezime = new TextField();
	@FXML
	TextField t_kor_ime = new TextField();
	@FXML
	ChoiceBox<Tip_korisnika> t_tip = new ChoiceBox<Tip_korisnika>();
	@FXML 
	TableView<Korisnik> tv = new TableView<Korisnik>();
	private Korisnik _korisnik;

	@Override
	public void initController() {
		super.initController();
		ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "name"));
		aktivna.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "active"));
		prezime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "surName"));
		tip.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "member_type"));
		kor_ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "user"));
		getData();
		List<HashMap<String[], ?>> l = new ArrayList<>();
		List<Tip_korisnika> tipoviKorisnika = DbOperation.Instance().getValue(l, Tip_korisnika.class);
		t_tip.setItems(FXCollections.observableArrayList(tipoviKorisnika));
	}
	
	@SuppressWarnings("rawtypes")
	public void getData() {
		List<HashMap<String[], ?>> l = new ArrayList<>();
		List<Korisnik> clanarine = DbOperation.Instance().getValue(l, Korisnik.class);
	
		
		TableColumn id = new TableColumn("id");
        TableColumn ime = new TableColumn("Ime");
        TableColumn prezime = new TableColumn("Prezime");
        TableColumn korIme = new TableColumn("Korisničko ime");
        TableColumn tip = new TableColumn("Tip korisnika");
        TableColumn aktivna = new TableColumn("Status");
        
        tv.getColumns().addAll(id,ime,prezime,korIme,tip,aktivna);
        id.setCellValueFactory(
        	    new PropertyValueFactory<Clan,String>("id")
        	);
        ime.setCellValueFactory(
        	    new PropertyValueFactory<Clan,String>("ime")
        	);
        prezime.setCellValueFactory(
        	    new PropertyValueFactory<Clan,String>("prezime")
        	);
        korIme.setCellValueFactory(
        	    new PropertyValueFactory<Clan,String>("kor_ime")
        	);
        tip.setCellValueFactory(
        	    new PropertyValueFactory<Clan,String>("tip_korisnika")
        	);
        aktivna.setCellValueFactory(
        	    new PropertyValueFactory<Clan,String>("aktivan")
        	);
        
        tv.setItems(FXCollections.observableArrayList(clanarine));
	}
	
	public void onClickListView(MouseEvent click) {
		_korisnik = tv.getSelectionModel().getSelectedItem();
		if(click.getClickCount() >= 2) {
			show();
			izmjena();
		}
	}
	
	public void delete() {
		if(_korisnik != null && tv.getSelectionModel().getSelectedItem() != null)
			if(! delete(_korisnik.getId(), _korisnik)) {
				showMessageBox(AlertType.ERROR, GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.title"),
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteUser.error") + _korisnik.getKor_ime(), 
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteUser.message"),
						null);
				}
			else
				refreshTable(this, tv);
		else
			showMessageBox(AlertType.WARNING,GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.title") ,
					GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.error"), "",null);
	}
	
	public void izmjena()
	{
		if(_korisnik != null) {
			this.t_ime.setText(_korisnik.getIme());
			this.t_prezime.setText(_korisnik.getPrezime());
			this.t_kor_ime.setText(_korisnik.getKor_ime());
			this.t_aktivna.setSelected(_korisnik.isAktivan());
			this.t_tip.getSelectionModel().select(_korisnik.getTip_korisnika());
			update = true;
		}
		else {
			//TODO PORUKA POGREŠKE
		}
			
	}

	public void insert() {
		Korisnik c = new Korisnik();
		// t_aktivna
		try {
			c.setIme(t_ime.getText());
			c.setPrezime(t_prezime.getText());
			c.setKor_ime(t_kor_ime.getText());
			c.setAktivan(t_aktivna.isSelected());
			c.setZaporka("12345");
			c.setTip_korisnika(t_tip.getSelectionModel().getSelectedItem());
			
		} catch (Exception e) {
			//TODO log
			e.printStackTrace();
			poruka.setText(GymProperties.getMessage(GymProperties.INFOMESSAGE, "unsuccessfulEntry"));
			poruka.setTextFill(Color.web(GymProperties.getPropertie("errorColor")));
			poruka.setVisible(true);
			return;
		}
		boolean unos = false;
		if(update) {
			c.setId(_korisnik.getId());
			update(c);
		}
		else {
			unos = DbOperation.Instance().insertValue(c);
		}
		if (unos) {
			poruka.setText(GymProperties.getMessage(GymProperties.INFOMESSAGE, "successfulEntry"));
			poruka.setTextFill(Color.web(GymProperties.getPropertie("infoColor")));
			poruka.setVisible(true);
		} else {
			poruka.setText(GymProperties.getMessage(GymProperties.INFOMESSAGE, "unsuccessfulEntry"));
			poruka.setTextFill(Color.web(GymProperties.getPropertie("errorColor")));
			poruka.setVisible(true);
		}
		update = false;
		t_ime.clear();
		t_prezime.clear();
		t_kor_ime.clear();
		t_tip.getSelectionModel().select(0);
		refreshTable(this, tv);
	}
	public void back() {
		super.back();
		update = false;
		t_ime.clear();
		t_prezime.clear();
		t_kor_ime.clear();
		t_tip.getSelectionModel().select(0);		
	}

}
