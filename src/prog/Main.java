package prog;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import gui.MainWindow;

public class Main {
	
	static ArrayList<Player> players = new ArrayList<Player>(); //list of all players
	static Timetable time = new Timetable(); 
	SimpleDateFormat par = new SimpleDateFormat("HH:mm");
	SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
	
		
	public static void main(String[] args){
		
		try{
				FileInputStream in = new FileInputStream("D:\\Programme\\Eclipse\\JavaProgramme\\Sascha\\Players.bin");
				ObjectInputStream is = new ObjectInputStream(in);
				while(true){
					players.add((Player) is.readObject());
				}
			}
		catch(FileNotFoundException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
		catch(ClassNotFoundException e){e.printStackTrace();}
		catch(Exception e){}
		finally{
			//is.close();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainWindow inst = new MainWindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.initGUI();
			}
		});	
		
		/*
		time.showTimetable();
		Slot s = new Slot("10:00", "12:00", "Dienstag");
		s.showSlot();
		time.addSlot(s);
		time.addSlot("Dienstag", "10:00");
		time.showTimetable();
		*/
	}
	
	//add a player to the list of players
	public static void addPlayer (String firstName, String lastName, int age, int amount){
		Player player = new Player(firstName, lastName, age, amount);
		players.add(player);
	}
	
	public static void addPlayer(Player player){
		players.add(player);
	}
	
	
	//opens a window for every player in the list
	public static void testShow(){
		
		for(int i=0;i<players.size();i++){
			players.get(i).showPlayer();
		}
		
	}
	
	//saves all players into a file
	public static void saveAllPlayers(){
		
		try{
			
			FileOutputStream out = new FileOutputStream("D:\\Programme\\Eclipse\\JavaProgramme\\Sascha\\Players.bin");
			ObjectOutputStream os = new ObjectOutputStream(out);
			
			for(int i=0;i<players.size();i++){
				os.writeObject(players.get(i));
			}
			
			os.close();
		
		} catch(Exception e) {e.printStackTrace();}
		
	}
	
	//Minimalize the amount of slots in a given list of slots by merging neighboured slots and deleting the remaining unnecessary slots
	public static void minimalize(ArrayList<Slot> slotList){
		boolean [] flags = new boolean [slotList.size()];
		for (int i = 0; i < slotList.size(); i++){
			for (int j = 0; j<slotList.size(); j++){
					if(slotList.get(i).mergeSlot(slotList.get(j))) flags[j]=true;
			}
		}
		
		for(int i = slotList.size()-1; i >=0; i--){
			if(flags[i]) slotList.remove(i);
		}
	}

}
