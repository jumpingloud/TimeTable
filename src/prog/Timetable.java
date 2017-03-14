package prog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Timetable implements Serializable {

	SimpleDateFormat par = new SimpleDateFormat("HH:mm");
	SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
	private ArrayList<ArrayList<Slot>> days = new ArrayList<ArrayList<Slot>>();
	private String[] listDays = { "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag" };
	private String coach; //name of the coach to which the timetable refers

	//Initialize a slot for each day
	public Timetable() {
		for (int i = 0; i < 6; i++) {
			days.add(i, new ArrayList<Slot>(50));
			days.get(i).add(new Slot("08:00", "21:00", listDays[i]));
		}
	}

	//returns the list of list of slots (which means, the days)
	public ArrayList<ArrayList<Slot>> getDays() {
		return this.days;
	}
	
	//returns the list of slots of a given day
	private ArrayList<Slot> getDay(int i) {
		return days.get(i);
	}
	
	public String getCoach(){
		return this.coach;
	}
	
	public void setCoach(String name){
		this.coach=name;
	}
	
	//splits the slot in which the given date is in two parts (at the date)
	public void addSlot(String day, String date) {
		for (int i = 0; i < 6; i++) {
			if (day == listDays[i]) {
				ArrayList<Slot> temp1 = new ArrayList<Slot>(getDay(i));
				Date temp = new Date();
				try {
					temp = par.parse(date);
				} catch (ParseException e) {

				}
				for (Slot slot : temp1) {
					if(!slot.isTaken()){
						if (slot.getStart().before(temp) && slot.getEnd().after(temp)) {
							getDay(i).add(i+1, new Slot(date, ft.format(slot.getEnd()), slot.getDay()));
							slot.setEnd(date);
						}
					}

				}
			}
		}
	}

	//adds a given slot into the list of slots by splitting the existing slot around the given one
	public void addSlot(Slot slot) {
		for (int i = 0; i < 6; i++) {
			if (slot.getDay() == listDays[i]) {
				ArrayList<Slot> temp = new ArrayList<Slot>(getDay(i));				
				
				for (Slot s : temp) {
					if(!s.isTaken()){
						if (s.getStart().before(slot.getStart()) && s.getEnd().after(slot.getEnd())) {
							getDay(i).add(i+1, slot);
							getDay(i).add(i+2, new Slot(ft.format(slot.getEnd()), ft.format(s.getEnd()), s.getDay()));
							s.setEnd(ft.format(slot.getStart()));
						}
					}	
				}
			}
		}	
	}

	//prints out all the slots of the timetable, for every day
	public void showTimetable() {
		for (int i = 0; i < 6; i++) {
			for (Slot slot : getDay(i)) {
				System.out.println(listDays[i] + ": ");
				slot.showSlot();
			}
			System.out.println("");
		}
		
	}
	
	public void saveTimetable(){
		
		try{
			FileOutputStream out = new FileOutputStream(new File("D:\\Programme\\Eclipse\\JavaProgramme\\Sascha\\timetable.ser"));
			ObjectOutputStream os = new ObjectOutputStream(out);
			os.writeObject(this);
			out.close();
		} catch(Exception e) {e.printStackTrace();}
		
	}

}
