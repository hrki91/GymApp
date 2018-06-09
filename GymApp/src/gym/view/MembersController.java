package gym.view;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import gym.GymProperties;
import gym.db.Clan;
import gym.db.Clanarina;
import gym.db.DbOperation;
import gym.db.Tip_treninga;
import gym.db.Trening;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class MembersController extends DataController {

	@FXML
	Label ime = new Label();
	@FXML
	Label prezime = new Label();
	@FXML
	Label datum_rodenja = new Label();
	@FXML
	Label adresa = new Label();
	@FXML
	Label clanarina = new Label();
	@FXML
	Label trening = new Label();

	@FXML
	TextField t_ime = new TextField();
	@FXML
	TextField t_prezime = new TextField();
	@FXML
	DatePicker t_datum_rodenja = new DatePicker();
	@FXML
	TextField t_adresa = new TextField();
	@FXML
	ChoiceBox<Clanarina> t_clanarina = new ChoiceBox<Clanarina>();
	@FXML
	ChoiceBox<Trening> t_trening = new ChoiceBox<Trening>();
	@FXML
	TableView<Clan> tv = new TableView<Clan>();

	private Clan cl;

	@SuppressWarnings("unchecked")
	@Override
	public void initController() {
		super.initController();
		ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "name"));
		prezime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "surName"));
		datum_rodenja.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "birth"));
		adresa.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "address"));
		clanarina.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "membership"));
		trening.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "trening"));
		dataPane.setVisible(false);
		// checkBoxConverter(t_trening, "getNaziv");
		getData();
		List<Clanarina> tipoviTreninga = (List<Clanarina>) getAllData(Clanarina.class);
		this.t_clanarina.setItems(FXCollections.observableArrayList(tipoviTreninga));
		List<Trening> tmp_ = (List<Trening>) getAllData(Trening.class);
		this.t_trening.setItems(FXCollections.observableArrayList(tmp_));
	}

	public void delete() {
		if (cl != null && tv.getSelectionModel().getSelectedItem() != null)
			if (!delete(cl.getId(), cl)) {
				showMessageBox(AlertType.ERROR, GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.title"),
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelteMember.error") + cl.getIme(), 
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelteMember.message"),
						null);
			} else
				refreshTable(this, tv);
		else
			showMessageBox(AlertType.WARNING,GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.title") ,
					GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.error"), "",null);
	}

	public void getData() {
		List<HashMap<String[], ?>> l = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Clan> clan = DbOperation.Instance().getValue(l, Clan.class);

		tv.setEditable(true);

		TableColumn id = new TableColumn("id");
		TableColumn ime = new TableColumn("Ime");
		TableColumn prezime = new TableColumn("Prezime");
		TableColumn datum_rodjenja = new TableColumn("Datum rodenja");
		TableColumn adresa = new TableColumn("Adresa");
		TableColumn clanarina = new TableColumn("Clanarina");
		TableColumn trening = new TableColumn("Trening");

		tv.getColumns().addAll(id, ime, prezime, datum_rodjenja, adresa, clanarina, trening);
		id.setCellValueFactory(new PropertyValueFactory<Clan, String>("id"));
		ime.setCellValueFactory(new PropertyValueFactory<Clan, String>("ime"));
		prezime.setCellValueFactory(new PropertyValueFactory<Clan, String>("prezime"));
		datum_rodjenja.setCellValueFactory(new PropertyValueFactory<Clan, String>("datum_rodenja"));
		adresa.setCellValueFactory(new PropertyValueFactory<Clan, String>("adresa"));
		clanarina.setCellValueFactory(new PropertyValueFactory<Clan, String>("clanarina"));
		trening.setCellValueFactory(new PropertyValueFactory<Clan, String>("trening"));

		tv.setItems(FXCollections.observableArrayList(clan));

	}

	public void onClickListView(MouseEvent click) {
		this.cl = tv.getSelectionModel().getSelectedItem();
		if (click.getClickCount() >= 2) {
			izmjena();
			show();
		}
	}

	public void izmjena() {
		if (cl != null) {
			this.t_ime.setText(cl.getIme());
			this.t_prezime.setText(cl.getPrezime());
			this.t_adresa.setText(cl.getAdresa());
			LocalDate ld = cl.getDatum_rodenja().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.t_datum_rodenja.setValue(ld);
			this.t_clanarina.getSelectionModel().select(cl.getClanarina());
			this.t_trening.getSelectionModel().select(cl.getTrening());
			update = true;
		}
	}

	public void insert() {
		Clan c = null;
		if (cl != null)
			c = cl;
		else
			c = new Clan();
		try {
			c.setAdresa(t_adresa.getText());
			c.setClanarina(t_clanarina.getSelectionModel().getSelectedItem());
			LocalDate ld = t_datum_rodenja.getValue();
			Calendar calendar = Calendar.getInstance();
			calendar.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
			c.setDatum_rodenja(calendar.getTime());
			c.setIme(t_ime.getText());
			c.setPrezime(t_prezime.getText());
			c.setTrening(t_trening.getSelectionModel().getSelectedItem());

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
			c.setId(cl.getId());
			unos = update(c);
		} else {
			unos = DbOperation.Instance().insertValue(c);
		}

		refreshTable(this, tv);
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
		t_adresa.clear();
		t_datum_rodenja.setValue(null);
		t_trening.getSelectionModel().clearSelection();
		t_clanarina.getSelectionModel().clearSelection();
		refreshTable(this, tv);
	}

	public void back() {
		super.back();
		t_ime.clear();
		t_prezime.clear();
		t_adresa.clear();
		t_datum_rodenja.setValue(null);
		t_trening.getSelectionModel().clearSelection();
		t_clanarina.getSelectionModel().clearSelection();
		update = false;
	}
}
