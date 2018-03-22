package gym.view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import gym.db.Clan;
import gym.db.DbOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HomePageController extends Controller{
	Logger log = Logger.getLogger(HomePageController.class.getName());

	@FXML
	ListView<String> members = new ListView<String>();;
	@FXML
	ListView<String> membersTrain = new ListView<String>();
	@FXML
	Label l= new Label();
	@FXML
	TextField tf = new TextField();
	
public HomePageController(){		
	}

	public void initController(){
		getMembers();	 
		setMembers();
	}
	private void setMembers(){
		if(membersList != null){
			ObservableList<String> options =  FXCollections.observableArrayList(membersList);
			members.setItems(options);
		}
		if(membersWhoTrain != null){
			ObservableList<String> options =  FXCollections.observableArrayList(membersWhoTrain);
			membersTrain.setItems(options);
		}
		
	}
	/**
	 * LIST WHICH CONTAINS ALL MEMBERS.
	 * METHOD getMembers fill list with data
	 * */
	private ArrayList <String> membersList = new ArrayList<String>();
	private ArrayList <String> membersWhoTrain = new ArrayList<String>();
	
	
	
	@SuppressWarnings("unchecked")
	private void getMembers(){
		log.debug("GETTING MEMBERS WHO CAN TRAIN TODAY ");
		/*
		 * HashMap<String, String[]> tmp = new HashMap<>();
			String restriction = "eq;"+korisnicko_ime.getText();
			tmp.put("kor_ime",restriction.split(";"));
			@SuppressWarnings("unchecked")
			List<Korisnik> korisnik =DbOperation.Instance().getValue(tmp,Korisnik.class);
		 * 
		 * */
		HashMap<String, String[]> tmp = new HashMap<>();
		//String restriction = "eq;"+korisnicko_ime.getText();
		List<Clan> clan = DbOperation.Instance().getValue(tmp, Clan.class);
		if (!clan.isEmpty()) {
			for (Clan clan2 : clan) {
				membersList.add(clan2.getId()+". "+clan2.getIme()+" "+clan2.getPrezime());
			}
		}
		else
			log.debug("No members for today trening");
//		
//		ArrayList<String> temp = new ArrayList<String>();		
//		temp=sq.executeQuerry("MembersWhoCanTrain", "id,Name,Last_Name");
//		
//		if(temp != null){
//			membersList=sm.getToList(temp);
//		}
//		else{
//			log.info(sq.getError(8)+" gym.getMembers");
//		}
//		log.info("GETTING MEMBERS WHO TRAIN TODAY ");
//		temp.clear();
//		temp=sq.executeQuerry("MembersWhoTrain", "id,Name,Last_Name");
//		if(temp != null){
//			membersWhoTrain = sm.getToList(temp);
//		}
//		else{
//			//TODO rijesiti
//			log.info("test");
//		}
//		
	}
	/**
	 * METHOD THAT MOVE MEMBER FROM TABLE MEMBERS TO TABLE MEMBERS WHO WAS IN GYM 
	 * THAT DAY.
	 * */
	public void moveMember(){
//		int index;
//		String member;
//		member= members.getSelectionModel().getSelectedItem();
//		index= members.getSelectionModel().getSelectedIndex();
//		if(this.index==-1){//AKO SE ITEM U LISTVIEWU PRITISNE JEDANPUT
//			this.index=index;
//		}
//		else if(index==this.index){//AKO SE ITEM U LISTVIEWU PRITISNE DVAPUT
//			membersTrain.getItems().add(member);
//			GetDate gd = new GetDate();
//			if(sq.insert("arivals","id,Datum,Time",member.substring(0,Integer.valueOf(pr.properties.get("id")))+",'"+gd.getDate(null, null, null)+"','" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()+"'") ==false){
//				AlertMessage.alertMessage(sq.getError(9), "GRESKA PRI UNOSU");
//				log.error(sq.getError(9));
//				log.info("Data for insert: "+"arivals , id,Datum , Time" + member.substring(0,Integer.valueOf(pr.properties.get("id")))+",'"+gd.getDate(null, null, null)+"',"+LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
//			}
//			members.getItems().remove(index);
//			this.index=-1;
//		}
//		else{//AKO SE PRITISNE RAZLIÈITI ITEM U LISTVIEWU
//			this.index=index;
//		}
//		new Thread(new TimeOut(500,this,index)).start();
	}
	private int index=-1;
	
	public void resetIndex(){
		this.index=-1;
	}
	public boolean isIndexSame(int i){
		if(i == index) return true;
		return false;
	}
	
	public void followMouse(){
		//System.out.println("test");		
	}
	/**
	 * METHOD THAT CALLED WHEN USER SET MOUSE ON MEMBERSTRAIN LISTBOX
	 * */
	public void membersTrainFocus(){
		setFocus(membersTrain);
	}
	public void membersFocus(){
		setFocus(members);
	}
	
	private void setFocus(ListView<String> lw){
		if(!lw.isFocused()){
			lw.requestFocus();
		}
	}
}
