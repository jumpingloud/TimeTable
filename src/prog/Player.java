package prog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import gui.PlayerWindow;

public class Player implements Serializable {
	private static final long serialVersionUID = -7249989690362858923L;
	
	private static final String SEPARATOR = ";";
	private String firstName;
	private String lastName;
	private int age;
	private ArrayList<Player> linkedPlayers = new ArrayList<Player>();	//candidates for a group with this player
	private ArrayList<Slot> practiceTimes = new ArrayList<Slot>();		//practice times of this player
	private ArrayList<Group> practiceGroups = new ArrayList<Group>();	//groups in which the player has practice
	private ArrayList<Slot> availableTimes = new ArrayList<Slot>();		//free time of the player, able to take practice in this times
	private int amount; // amount of wanted practice sessions per week
	private String coach;

	public Player(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Player(String firstName, String lastName, int age, int amount) {
		this(firstName, lastName, age);
		this.amount = amount;
	}
	
	public Player(String firstName, String lastName, int age, int amount, ArrayList<Slot> times){
		this(firstName, lastName, age, amount);
		this.availableTimes = times;
	}
	
	public Player(String firstName, String lastName, int age, int amount, ArrayList<Slot> times, ArrayList<Group> groups){
		this(firstName, lastName, age, amount, times);
		this.practiceGroups = groups;
	}

	public void setName(String firstName, String LastName) {
		setFirstName(firstName);
		setLastName(LastName);
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	//adds a player to the linked players-list
	public void addLinkedPlayer(Player player) {
		this.linkedPlayers.add(player);
	}

	//adds several players to the linked players-list
	public void addLinkedPlayers(List<Player> players) {
		for (Player p : players) {
			addLinkedPlayer(p);
		}
	}

	public ArrayList<Player> getLinkedPlayers() {
		return this.linkedPlayers;
	}

	public void addGroup(Group group) {
		this.practiceGroups.add(group);
	}

	public void addGroups(List<Group> groups) {
		for (Group g : groups) {
			addGroup(g);
		}
	}

	public ArrayList<Group> getGroups() {
		return this.practiceGroups;
	}

	public void setAmount(int i) {
		this.amount = i;
	}

	public int getAmount() {
		return this.amount;
	}
	
	public ArrayList<Slot> getAvailableTimes(){
		return this.availableTimes;
	}
	
	public void addFreeTime(Slot slot){
		this.availableTimes.add(slot);
	}
	
	public void addFreeTime(ArrayList<Slot> times){
		for(Slot slot: times){
			this.availableTimes.add(slot);
		}
		Main.minimalize(this.availableTimes);
	}

	//prints out information about a playerS
	public void showPlayer() {
		
		String firstName = this.getFirstName();
		String lastName = this.getLastName();
		int age = this.getAge();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PlayerWindow playerWind = new PlayerWindow(firstName + " " + lastName);
				playerWind.setLocationRelativeTo(null);
				playerWind.setVisible(true);
				playerWind.initShow(firstName, lastName, age);
			}
		});

	}
	
	public String toData() {
		return firstName + SEPARATOR + lastName + SEPARATOR + age;
	}
	
	public static Player fromData(String data) {
		String[] split = data.split(SEPARATOR);
		
		Player player = new Player(split[0], split[1], Integer.parseInt(split[2]));
		
		return player;
	}
	
}
