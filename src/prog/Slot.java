package prog;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Slot implements Serializable {

	private static final long serialVersionUID = 1028455469126878802L;
	SimpleDateFormat par = new SimpleDateFormat("HH:mm");
	SimpleDateFormat ft = new SimpleDateFormat("HH:mm");

	private Date startingDate;
	private Date endDate;
	private String day;
	private int duration; // length of the slot in minutes
	private Group groupOfPlayers;
	private boolean taken=false;


	public Slot() {
	}

	public Slot(String start, String end, String day) {
		try {
			this.startingDate = par.parse(start);
			this.endDate = par.parse(end);
		} catch (ParseException e) {
			System.out.println("Illegal Date");
		}
		this.day = day;
		duration = (this.endDate.getHours() - this.startingDate.getHours()) * 60;
		duration += this.endDate.getMinutes() - this.startingDate.getMinutes();
	}

	public Slot(String start, String end, String day, Group group) {
		this(start, end, day);
		this.groupOfPlayers = group;
	}

	public void setStart(String s) {
		try {
			this.startingDate = par.parse(s);
		} catch (ParseException e) {
			System.out.println("Illegal Date");
		}

		if (this.startingDate != null || this.endDate != null) {
			duration = (this.endDate.getHours() - this.startingDate.getHours()) * 60;
			duration += this.endDate.getMinutes() - this.startingDate.getMinutes();
		}
	}
	
	public void setStart(Date date){
		this.startingDate=date;
		this.setLength();
	}

	public Date getStart() {
		return this.startingDate;
	}

	public void setEnd(String s) {
		try {
			this.endDate = par.parse(s);
		} catch (ParseException e) {
			System.out.println("Illegal Date");
		}

		if (this.startingDate != null && this.endDate != null) {
			duration = (this.endDate.getHours() - this.startingDate.getHours()) * 60;
			duration += this.endDate.getMinutes() - this.startingDate.getMinutes();
		}
	}
	
	public void setEnd(Date date) {
		this.endDate=date;
		this.setLength();
	}

	public Date getEnd() {
		return this.endDate;
	}

	public void setDay(String s) {
		this.day = s;
	}

	public String getDay() {
		return this.day;
	}

	public int getLength() {
		return this.duration;
	}
	
	public void setLength(){
		if (this.startingDate != null || this.endDate != null) {
			duration = (this.endDate.getHours() - this.startingDate.getHours()) * 60;
			duration += this.endDate.getMinutes() - this.startingDate.getMinutes();
		}
	}

	public void setGroup(Group g) {
		this.groupOfPlayers = g;
	}

	public Group getGroup() {
		return this.groupOfPlayers;
	}
	
	public boolean isTaken(){
		return this.taken;
	}
	
	public void setTaken(boolean taken){
		this.taken=taken;
	}

	//prints out information about a slot
	public void showSlot() {
		System.out.println("Start: " + ft.format(startingDate));
		System.out.println("Ende: " + ft.format(endDate));
		System.out.println("Tag: " + day);
		System.out.println("Länge: " + duration + "\n");
		//groupOfPlayers.showGroup();
	}
	
	//returns true if the given slot lies inside the invoking slot
	public boolean isInSlot(Slot slot){
		if(this.getStart().before(slot.getStart()) && this.getEnd().after(slot.getEnd())){
			return true;
		}
		return false;
	}
	
	public boolean mergeSlot(Slot s){
		if(this.getDay().equals(s.getDay())){
			if(this.getStart().equals(s.getEnd())){
				this.setStart(s.getStart());
				return true;
			}
			
			else if(this.getEnd().equals(s.getStart())){
				this.setEnd(s.getEnd());
				return true;
			}
		}
		
		return false;
	}
	
	
	/*
	public void splitSlot(String date){
		new Slot(date, ft.format(this.endDate), this.day);
		try{
			this.endDate = par.parse(date);
		} catch(ParseException e){
			
		}
	}
	
	public void splitSlot(Slot slot){
		new Slot(ft.format(slot.getEnd()),ft.format(this.endDate), this.day);
		this.endDate = slot.getStart();
	}
	*/
}
