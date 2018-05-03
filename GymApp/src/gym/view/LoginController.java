package gym.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import gym.GymProperties;
import gym.Main;
import gym.db.DbOperation;
import gym.db.Korisnik;

public class LoginController extends Controller {
	@FXML
	private PasswordField zaporka;
	@FXML
	private TextField korisnicko_ime;
	@FXML
	private Label error_label;
	@FXML
	private Label lbl_korisnicko_ime;
	@FXML
	private Label lbl_zaporka;
	// Reference to the main application.
	private Main mainApp;

	private final Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void initController() {
		// gymProperties = new GymProperties();
		lbl_korisnicko_ime.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "user"));
		lbl_zaporka.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "password"));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

	}

	/**
	 * Method for login If username or password-s lenght is 0 app show error. In
	 * every other cases, object is created, Object that contains username, and
	 * password, and connect to database
	 */
	public void login(ActionEvent event) {
		if (korisnicko_ime.getLength() == 0 || zaporka.getLength() == 0) {
			error_label.setText(GymProperties.getMessage(GymProperties.ERRMESSAGE, "empty_field"));
			log.info("Empty 'korisniko_ime' or 'zaporka' while trying login");
		} else {
			HashMap<String[],String> tmp = new HashMap<>();
			String restriction = "eq;kor_ime";
			tmp.put(restriction.split(";"),korisnicko_ime.getText());
			List<HashMap<String[],?>> l = new ArrayList<>();
			l.add(tmp);
			@SuppressWarnings("unchecked")
			List<Korisnik> korisnik =DbOperation.Instance().getValue(l,Korisnik.class);
			if (korisnik.isEmpty()) {
				error_label.setText(GymProperties.getMessage(GymProperties.ERRMESSAGE, "wrong_username"));
				log.info("Wrong user name " + korisnicko_ime.getText());
				return;
			}
			if (korisnik.size() > 1) {
				error_label.setText(GymProperties.getMessage(GymProperties.ERRMESSAGE, "to_many_values"));
				log.info("To many resaults for: " + korisnicko_ime.getText());
				return;
			}

			String hashZaporka = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.reset();
				md.update(StandardCharsets.UTF_8.encode(zaporka.getText()));
				hashZaporka = String.format("%032x", new BigInteger(1, md.digest()));
			} catch (NoSuchAlgorithmException e) {
				log.error("No such algorithm ", e);
				error_label.setText(GymProperties.getMessage(GymProperties.ERRMESSAGE, "internal_error"));
				return;
			}
			for (Korisnik korisnik2 : korisnik) {
				if (korisnik2.getZaporka().equals(hashZaporka)) {
					mainApp.setScreen("view/MainMenu.fxml", "gym.view.MainMenuController", true,"right");
					mainApp.setScreen("view/HomePage.fxml", "gym.view.HomePageController",false,"center");
				}

				else
					error_label.setText(GymProperties.getMessage(GymProperties.ERRMESSAGE, "wrong_pass"));
			}
		}
	}

	public void cancel() {
		log.info("Gašenje aplikacije");
		System.exit(0);
	}

	public void keyPress(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.ENTER) {
			login(null);
		} else if (keyEvent.getCode() == KeyCode.ESCAPE) {
			cancel();
		} else if (keyEvent.getCode() == KeyCode.DELETE) {
			zaporka.clear();
			korisnicko_ime.clear();
			korisnicko_ime.requestFocus();
		}

	}

}