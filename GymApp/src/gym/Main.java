package gym;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import gym.db.DbOperation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	static final Logger log = Logger.getLogger(Main.class);
	private Stage primaryStage;
	private BorderPane rootLayout;
	private Scene scene;
	private AnchorPane ap;
	
	public Scene getScene() {
		return this.scene;
	}
	
	public AnchorPane getAnchorPane() {
		return this.ap;
	}
	

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			rootLayout.setMinSize(200, 200);

			scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			setScreen("view/Login.fxml", "gym.view.LoginController",true,"center");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure(args[2]);
		// INICIJALIZACIJA PROPERTY FILE-OVA, VRIJEDNOSTI SE DOBIVAJU IZ POZIVA JAVE
		log.info("Pocetak rada");
		new GymProperties().initProperties(args[1], args[0]);
		DbOperation.Instance();
		launch(args);
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * POVRATAK NA LOGIN
	 */
	public void close() {
		rootLayout.setLeft(null);
		rootLayout.setCenter(null);
		rootLayout.setRight(null);
		setScreen("view/Login.fxml", "gym.view.LoginController",true,"center");
	}

	/**
	 * SET'S SCREEN, METHOD REQUIRE URL TO .fxml .
	 */
	public void setScreen(String fxml, String klasa,boolean setMainApp,String position) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(fxml));
			ap = loader.load();
			if(position.equals("center"))
				rootLayout.setCenter(ap);
			else if (position.equals("left"))	
				rootLayout.setLeft(ap);
			else if (position.equals("right"))
				rootLayout.setRight(ap);
			else {
				log.error("Wrong position");
				return;
			}
				
			try {
				Class<?> noparams[] = {};
				Class<?> controller = Class.forName(klasa);
				Object clsInstance = controller.newInstance().getClass();
				clsInstance = loader.getController();
				controller.getDeclaredMethod("initController", noparams).invoke(clsInstance);
				Class<?> params = Main.class;
				if (setMainApp)
					controller.getDeclaredMethod("setMainApp", params).invoke(clsInstance, this);
			} catch (ClassNotFoundException e) {
				log.error("Class "+ klasa +" not found",e);
			} catch (IllegalAccessException e) {
				log.error("Illegal acces",e);
			} catch (IllegalArgumentException e) {
				log.error("Illegal Argument",e);
			} catch (InvocationTargetException e) {
				log.error("",e);
			} catch (NoSuchMethodException e) {
				log.error("No such method",e);
			} catch (SecurityException e) {
				log.error("",e);
			} catch (InstantiationException e) {
				log.error("",e);
			}
		} catch (IOException e) {
			log.error("IOException",e);
			e.printStackTrace();
		}
	}
}
