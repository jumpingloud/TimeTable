package prog;
import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

	private ArrayList<Player> players = new ArrayList<Player>();
	private Slot practiceTime;
	private boolean hasTime = false;	//true if the group has a practice time, false else

	public Group() {
	}

	public Group(ArrayList<Player> players) {
		this.players = players;
	}

	public Group(ArrayList<Player> players, Slot s) {
		this(players);
		this.practiceTime = s;
		this.hasTime = true;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public Slot getTime() {
		return this.practiceTime;
	}

	public void setTime(Slot s) {
		this.practiceTime = s;
		this.hasTime = true;
	}

	public boolean hasTime() {
		return hasTime;
	}

	//prints out information about the players in a group
	public void showGroup() {
		for (Player p : players) {
			p.showPlayer();
		}
		practiceTime.showSlot();
	}

}
