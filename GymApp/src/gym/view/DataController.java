package gym.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import gym.GymProperties;
import gym.db.DbOperation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * SVE FORME ZA UNOS,BRISANJE,IZMJENU PODATAKA SU NA ISTU FORU I SADRŽE ISTE
 * BUTTONE I JOŠ NEKE ELEMENTE. OVM ABSTRAKTNOM KLASOM SMANJUJEM KOD
 */
public abstract class DataController extends Controller {

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
	Label poruka = new Label();

	@FXML
	Pane dataPane = new Pane();

	public boolean update;

	@Override
	public void initController() {
		b_obrisi.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "delete"));
		b_produziClanarinu.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "extendMembership"));
		b_ponisti.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "cancel"));
		b_potvrda.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "confirm"));
		b_izmjeni.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "change"));
		b_novo.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "new"));
		poruka.setVisible(false);
		dataPane.setVisible(false);
	}

	public void change() {
		dataPane.setVisible(true);
	}

	public void insert() {
	}

	public void show() {
		dataPane.setVisible(true);
	}

	public boolean delete(int id, Object klasa) {
		return DbOperation.Instance().deleteByPrimaryKey(id, klasa);
	}

	public boolean update(Object klasa) {
		return DbOperation.Instance().insertValue(klasa);
	}

	public void back() {
		dataPane.setVisible(false);
	}

	/**
	 * METODA KOJA VRAĆA LISTU SA SVIM OBJEKTIMA IZ BAZE
	 */
	public List<?> getAllData(Class<?> klasa) {

		HashMap<String[], Date> tmp = new HashMap<>();
		// String restriction = "gt;end_date";
		// tmp.put(restriction.split(";"),new java.sql.Date(curentDay.getTime()));
		List<HashMap<String[], ?>> l = new ArrayList<>();
		l.add(tmp);
		List<?> clanarine = DbOperation.Instance().getValue(l, klasa);
		return clanarine;
	}

	public void showMessageBox(AlertType at, String naslov, String zaglavlje, String sadrzaj) {
		Alert alert = new Alert(at);
		alert.setTitle(naslov);
		alert.setHeaderText(zaglavlje);
		alert.setContentText(sadrzaj);
		alert.showAndWait().ifPresent(rs -> {
			if (rs == ButtonType.OK) {
				// todo execute method
				System.out.println("Pressed OK.");
			}
		});
	}

	/**
	 * METODA ZA OSVJEŽAVANJE TABLE VIEW-a
	 */
	public void refreshTable(Object o, TableView<?> tv) {
		tv.getItems().clear();
		tv.getColumns().clear();
		tv.refresh();
		Method m = null;
		try {
			m = o.getClass().getMethod("getData");
			m.invoke(o);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
