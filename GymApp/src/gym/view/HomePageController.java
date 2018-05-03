package gym.view;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

import gym.GymProperties;
import gym.db.Clan;
import gym.db.Clanarina_clana;
import gym.db.DbOperation;
import gym.db.Dolasci;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
		try {
			df=new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df_= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.curentDay = (java.util.Date) this.df.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			this.curentDayTime = (java.util.Date) df_.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			
			getMembers();			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
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
	private DateFormat df;
	private java.util.Date curentDay;
	private java.util.Date curentDayTime;
	
	
	@SuppressWarnings("unchecked")
	private void getMembers() throws ParseException{
		log.debug("GETTING MEMBERS WHO CAN TRAIN TODAY ");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		String startOfWeek= new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		
		
		HashMap<String[], Date> tmp = new HashMap<>();
		//java.util.Date utilDay = (java.util.Date) df.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		String restriction = "gt;end_date";
		tmp.put(restriction.split(";"),new java.sql.Date(curentDay.getTime()));
		List<HashMap<String[],?>> l = new ArrayList<>();
		l.add(tmp);
		List<Clanarina_clana> clanarina_clana = DbOperation.Instance().getValue(l, Clanarina_clana.class);
		if (! clanarina_clana.isEmpty()) {
			for (Clanarina_clana clan2 : clanarina_clana) {
				tmp.clear();
				String restriction_ = "gt;dolazak";
				tmp.put(restriction_.split(";"), new java.sql.Date(((java.util.Date) df.parse(startOfWeek)).getTime()));
				restriction_="eq;clan.id";
				HashMap<String[],Integer> clan = new HashMap<>();
				clan.put(restriction_.split(";"),clan2.getId());
				l.add(tmp);l.add(clan);
				
				List<Dolasci> dolasci = DbOperation.Instance().getValue(l, Dolasci.class);
				boolean danasnjiDolazak = false;
				for (Dolasci dolasci2 : dolasci) {
					System.out.println(df.format(dolasci2.getDolazak()));
					if(df.format(dolasci2.getDolazak()) == df.format(this.curentDay) && dolasci2.getOdlazak() == null)
						danasnjiDolazak = true;
				}
				//if(dolasci.contains(""/*new Dolasci().setDolazak(new java.sql.Date(utilDay.getTime()))*/));
				if(!danasnjiDolazak && dolasci.size() < clan2.getClan().getClanarina().getBr_treninga_u_tjednu()) {				
					membersList.add(clan2.getClan().getId()+". "+clan2.getClan().getIme()+" "+clan2.getClan().getPrezime());
				}
				else if(danasnjiDolazak) {
					membersWhoTrain.add(clan2.getClan().getId()+". "+clan2.getClan().getIme()+" "+clan2.getClan().getPrezime());
				}
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
	 * METODA KOJA PREBACUJE CLANOVE IZ LISTE CLANOVA KOJI MOGU TRENIRATI U LISTU CLANOVA KOJI SU TRENIRALI DANAS
	 * */
	public void registerMember(MouseEvent click){
		String member= members.getSelectionModel().getSelectedItem();
		if(click.getClickCount() >= 2 && this.index == members.getSelectionModel().getSelectedIndex()) {
			
			Dolasci d = new Dolasci();
			
			Clan clan = new Clan();
			clan.setId(Integer.valueOf(member.substring(0,Integer.valueOf(GymProperties.getPropertie("id")))));
			
			d.setClan(clan);
			d.setDolazak(new java.sql.Date(curentDayTime.getTime()));
			DbOperation.Instance().insertValue(d);
			membersTrain.getItems().add(member);
			members.getItems().remove(index);
			this.index=-1;
		}else			
			this.index= members.getSelectionModel().getSelectedIndex();

//			GetDate gd = new GetDate();
//			if(sq.insert("arivals","id,Datum,Time",member.substring(0,Integer.valueOf(pr.properties.get("id")))+",'"+gd.getDate(null, null, null)+"','" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond()+"'") ==false){
//				AlertMessage.alertMessage(sq.getError(9), "GRESKA PRI UNOSU");
//				log.error(sq.getError(9));
//				log.info("Data for insert: "+"arivals , id,Datum , Time" + member.substring(0,Integer.valueOf(pr.properties.get("id")))+",'"+gd.getDate(null, null, null)+"',"+LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
//			}
//			
//		}
//		else{//AKO SE PRITISNE RAZLIÈITI ITEM U LISTVIEWU
//			this.index=index;
//		}
//		new Thread(new TimeOut(500,this,index)).start();
	}
	
	
	
	private int index=-1;
	
//	public void resetClick(){
//		this.click=-1;
//	}
//	public boolean isDoubleClick(int i){
//		if(i == click) return true;
//		return false;
//	}
	
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
