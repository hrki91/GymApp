package gym.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.mysql.fabric.xmlrpc.base.Data;

import gym.GymProperties;
import gym.db.Clan;
import gym.db.Clanarina;
import gym.db.DbOperation;
import gym.db.Tip_treninga;
import gym.db.Trening;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TreningController extends DataController {

	@FXML
	Label naziv = new Label();
	@FXML
	Label tip = new Label();
	@FXML
	Label tezina = new Label();

	@FXML
	TableView<Trening> tv = new TableView<Trening>();
	@FXML
	ChoiceBox<Integer> t_tezina = new ChoiceBox<Integer>();
	@FXML
	ChoiceBox<Tip_treninga> t_tip = new ChoiceBox<Tip_treninga>();
	@FXML
	TextField t_naziv = new TextField();

	private Trening _trenig;

	@Override
	public void initController() {
		super.initController();
		naziv.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "title"));
		tezina.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "level"));
		tip.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "trening"));
		List<Tip_treninga> tipoviTreninga = (List<Tip_treninga>) getAllData(Tip_treninga.class);
		t_tip.setItems(FXCollections.observableArrayList(tipoviTreninga));
		t_tezina.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
		getData();
	}

	public void delete() {
		if (_trenig != null && tv.getSelectionModel().getSelectedItem() != null)
			if (!delete(_trenig.getId(), _trenig)) {
				showMessageBox(AlertType.ERROR, GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.title"),
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteTrenig.error") + _trenig.getNaziv(), 
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteTrenig.message"));
			} else
				refreshTable(this, tv);
		else
			showMessageBox(AlertType.WARNING,GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.title") ,
					GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.error"), "");
	}

	public void getData() {
		List<HashMap<String[], ?>> l = new ArrayList<>();
		List<Trening> clanarine = DbOperation.Instance().getValue(l, Trening.class);

		TableColumn id = new TableColumn("id");
		TableColumn naziv = new TableColumn("Naziv");
		TableColumn tip = new TableColumn("Tip treninga");
		TableColumn tezina = new TableColumn("Tezina");

		tv.getColumns().addAll(id, naziv, tip, tezina);
		id.setCellValueFactory(new PropertyValueFactory<Trening, String>("id"));
		naziv.setCellValueFactory(new PropertyValueFactory<Trening, String>("naziv"));
		tip.setCellValueFactory(new PropertyValueFactory<Trening, String>("tip_treninga"));
		tezina.setCellValueFactory(new PropertyValueFactory<Trening, String>("tezina"));

		tv.setItems(FXCollections.observableArrayList(clanarine));

	}

	public void onClickListView(MouseEvent click) {
		_trenig = tv.getSelectionModel().getSelectedItem();
		if (click.getClickCount() >= 2) {
			show();
			izmjena();
		}
	}
	

	public void izmjena() {
		if (_trenig != null) {
			this.t_naziv.setText(_trenig.getNaziv());
			this.t_tezina.getSelectionModel().select(Integer.valueOf(_trenig.getTezina()));
			this.t_tip.getSelectionModel().select(_trenig.getTip_treninga());
			update = true;
		} else {
			// TODO PORUKA POGRE�KE
		}

	}

	public void show() {
		super.show();
		update = false;
		t_naziv.clear();
		t_tip.getSelectionModel().select(0);
	}

	public void insert() {
		Trening c = new Trening();
		// t_aktivna
		try {
			c.setNaziv(t_naziv.getText());
			c.setTezina(t_tezina.getSelectionModel().getSelectedItem());
			c.setTip_treninga(t_tip.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			e.printStackTrace();
			poruka.setText(GymProperties.getMessage(GymProperties.INFOMESSAGE, "unsuccessfulEntry"));
			poruka.setTextFill(Color.web(GymProperties.getPropertie("errorColor")));
			poruka.setVisible(true);
			return;
		}
		boolean unos = false;
		if (update) {
			c.setId(_trenig.getId());
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

		t_naziv.clear();
		refreshTable(this, tv);
	}

	public void back() {
		super.back();
		update = false;
		t_naziv.clear();
		t_tip.getSelectionModel().select(0);
	}
}
