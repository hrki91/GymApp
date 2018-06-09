package gym.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gym.GymProperties;
import gym.db.DbOperation;
import gym.db.Skupina_tijela;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class BodyGroupController extends DataController{
	
	@FXML
	Label naziv = new Label();
	@FXML
	Label opis = new Label();
		
	@FXML
	TextField t_naziv = new TextField();
	@FXML
	TextField t_opis = new TextField();
	@FXML
	ListView<Skupina_tijela> contentTable = new ListView<Skupina_tijela>();
	
	private Skupina_tijela sk;

	@Override
	public void initController() {
		super.initController();
		naziv.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "title"));
		opis.setText(GymProperties.getMessage(GymProperties.LBLMESSAGE, "description"));
		dataPane.setVisible(false);
		getData();
	}

	public void getData() {
		List<HashMap<String[], ?>> l = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Skupina_tijela> clan = DbOperation.Instance().getValue(l, Skupina_tijela.class);
		System.out.println(clan);
		contentTable.setItems(FXCollections.observableArrayList(clan));
		contentTable.setCellFactory(new Callback<ListView<Skupina_tijela>, ListCell<Skupina_tijela>>(){
 
            @Override
            public ListCell<Skupina_tijela> call(ListView<Skupina_tijela> p) {
                 
                ListCell<Skupina_tijela> cell = new ListCell<Skupina_tijela>(){
 
                    @Override
                    protected void updateItem(Skupina_tijela t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getNaziv()+" - " + t.getOpis());
                        }
                    }
 
                };
                 
                return cell;
            }
        });
	}
	public void onClickListView(MouseEvent click) {
		if(click.getClickCount() >= 2) {
			izmjena();
			show();
		}
	}
	
	public void izmjena()
	{
		if(contentTable != null) {
		this.sk = contentTable.getSelectionModel().getSelectedItem();
		this.t_naziv.setText(sk.getNaziv());
		this.t_opis.setText(sk.getOpis());
		}
	}
	
	public void insert() {
		Skupina_tijela c = null;
		if(sk != null)
			c = sk;
		else
			c = new Skupina_tijela();
		try {
			c.setNaziv(t_naziv.getText());
			c.setOpis(t_opis.getText());
			
		} catch (Exception e) {
			//TODO log
			e.printStackTrace();
			poruka.setText(GymProperties.getMessage(GymProperties.INFOMESSAGE, "unsuccessfulEntry"));
			poruka.setTextFill(Color.web(GymProperties.getPropertie("errorColor")));
			poruka.setVisible(true);
			return;
		}
		boolean unos = DbOperation.Instance().insertValue(c);
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
		t_opis.clear();

	}
}
