package gym.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import gym.GymProperties;
import gym.db.Clan;
import gym.db.Clanarina;
import gym.db.Clanarina_clana;
import gym.db.DbOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class MembershipController extends DataController {

	@FXML
	Label naziv = new Label();
	@FXML
	Label aktivna = new Label();
	@FXML
	Label trajanje = new Label();
	@FXML
	Label br_treninga = new Label();
	@FXML
	Label cijena = new Label();

	@FXML
	TextField t_naziv = new TextField();
	@FXML
	CheckBox t_aktivna = new CheckBox();
	@FXML
	TextField t_trajanje = new TextField();
	@FXML
	TextField t_br_treninga = new TextField();
	@FXML
	TextField t_cijena = new TextField();
	@FXML
	TableView<Clanarina> tv = new TableView<Clanarina>();

	private Clanarina _clanarina;

	@Override
	public void initController() {
		super.initController();
		naziv.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "membership"));
		aktivna.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "active"));
		trajanje.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "duration"));
		br_treninga.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "trening_number"));
		cijena.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "price"));
		getData();
	}

	public void delete() {
		if (_clanarina != null && tv.getSelectionModel().getSelectedItem() != null) {
			if (!delete(_clanarina.getId(), _clanarina)) {
				showMessageBox(AlertType.ERROR, GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.title"),
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteMemberShip.error") + _clanarina.getNaziv(), 
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteMemberShip.message"),
						null);
			} else
				refreshTable(this, tv);
		} else
			showMessageBox(AlertType.WARNING,GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.title") ,
					GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.error"), "",null);
	}

	public void getData() {
		List<HashMap<String[], ?>> l = new ArrayList<>();
		List<Clanarina> clanarine = DbOperation.Instance().getValue(l, Clanarina.class);

		TableColumn id = new TableColumn("id");
		TableColumn naziv = new TableColumn("Naziv");
		TableColumn aktivna = new TableColumn("Aktivna");
		TableColumn trajanje_u_danima = new TableColumn("Trajanje u danima");
		TableColumn br_treninga_u_tjednu = new TableColumn("Trajanje u danima");
		TableColumn cijena = new TableColumn("Trajanje u danima");

		tv.getColumns().addAll(id, naziv, aktivna, trajanje_u_danima, br_treninga_u_tjednu, cijena);
		id.setCellValueFactory(new PropertyValueFactory<Clan, String>("id"));
		naziv.setCellValueFactory(new PropertyValueFactory<Clan, String>("naziv"));
		aktivna.setCellValueFactory(new PropertyValueFactory<Clan, String>("aktivna"));
		trajanje_u_danima.setCellValueFactory(new PropertyValueFactory<Clan, String>("trajanje_u_danima"));
		br_treninga_u_tjednu.setCellValueFactory(new PropertyValueFactory<Clan, String>("br_treninga_u_tjednu"));
		cijena.setCellValueFactory(new PropertyValueFactory<Clan, String>("cijena"));

		tv.setItems(FXCollections.observableArrayList(clanarine));
	}

	public void onClickListView(MouseEvent click) {
		_clanarina = tv.getSelectionModel().getSelectedItem();
		if (click.getClickCount() >= 2) {
			show();
			izmjena();
		}
	}

	public void izmjena() {
		if (_clanarina != null) {
			this.t_naziv.setText(_clanarina.getNaziv());
			this.t_aktivna.setSelected(_clanarina.getAktivna());
			this.t_br_treninga.setText(String.valueOf(_clanarina.getBr_treninga_u_tjednu()));
			this.t_cijena.setText(String.valueOf(_clanarina.getCijena()));
			this.t_trajanje.setText(String.valueOf(_clanarina.getTrajanje_u_danima()));
			update = false;
		} else {
			// TODO PORUKA POGREŠKE
		}

	}

	public void insert() {
		Clanarina c = new Clanarina();
		try {
			c.setAktivna(t_aktivna.isSelected());
			c.setBr_treninga_u_tjednu(Integer.valueOf(t_br_treninga.getText()));
			c.setCijena(Double.valueOf(t_cijena.getText()));
			c.setNaziv(t_naziv.getText());
			c.setTrajanje_u_danima(Integer.valueOf(t_trajanje.getText()));
		} catch (Exception e) {
			// TODO log
			e.printStackTrace();
			poruka.setText(GymProperties.getMessage(GymProperties.INFOMESSAGE, "unsuccessfulEntry"));
			poruka.setTextFill(Color.web(GymProperties.getPropertie("errorColor")));
			poruka.setVisible(true);
			return;
		}
		boolean unos = false;
		if (update) {
			c.setId(_clanarina.getId());
			unos = update(c);
		} else {
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

		t_br_treninga.clear();
		t_cijena.clear();
		t_naziv.clear();
		t_trajanje.clear();
		refreshTable(this, tv);
	}

	public void back() {
		super.back();
		update = false;
		t_br_treninga.clear();
		t_cijena.clear();
		t_naziv.clear();
		t_trajanje.clear();
	}
}
