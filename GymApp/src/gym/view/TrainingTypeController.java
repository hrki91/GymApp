package gym.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gym.GymProperties;
import gym.db.Clan;
import gym.db.Clanarina;
import gym.db.DbOperation;
import gym.db.Tip_treninga;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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

public class TrainingTypeController extends DataController {

	@FXML
	Label ime = new Label();
	@FXML
	TextField t_ime = new TextField();
	@FXML
	TableView<Tip_treninga> tv = new TableView<Tip_treninga>();

	private Tip_treninga tp;

	@Override
	public void initController() {
		super.initController();
		ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "name"));
		dataPane.setVisible(false);
		getData();
	}

	public void delete() {
		if (tp != null && tv.getSelectionModel().getSelectedItem() != null) {
			if (!delete(tp.getId(), tp)) {
				showMessageBox(AlertType.ERROR, GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.title"),
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteTrainingType.error") + tp.getNaziv(), 
						GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDeleteTrainingType.message"),
						null);
			} else
				refreshTable(this, tv);
		} else
			showMessageBox(AlertType.WARNING,GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.title") ,
					GymProperties.getMessage(GymProperties.ERRMESSAGE, "onDelete.noValue.error"), "",null);
	}

	public void getData() {
		List<HashMap<String[], ?>> l = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Tip_treninga> trenizi = DbOperation.Instance().getValue(l, Tip_treninga.class);

		TableColumn id = new TableColumn("id");
		TableColumn naziv = new TableColumn("Naziv");

		tv.getColumns().addAll(id, naziv);
		id.setCellValueFactory(new PropertyValueFactory<Tip_treninga, String>("id"));
		naziv.setCellValueFactory(new PropertyValueFactory<Tip_treninga, String>("naziv"));

		tv.setItems(FXCollections.observableArrayList(trenizi));
	}

	public void onClickListView(MouseEvent click) {
		tp = tv.getSelectionModel().getSelectedItem();
		if (click.getClickCount() >= 2) {
			show();
			izmjena();
		}
	}

	public void izmjena() {
		if (tp != null) {
			this.t_ime.setText(tp.getNaziv());
			update = true;
		}
	}

	public void insert() {
		Tip_treninga c = null;
		if (tp != null)
			c = tp;
		else
			c = new Tip_treninga();
		try {
			c.setNaziv(t_ime.getText());

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
			c.setId(tp.getId());
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

		t_ime.clear();
		refreshTable(this, tv);
	}

	public void back() {
		super.back();
		update = false;
		t_ime.clear();

	}
}
