package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import prog.Main;
import prog.Player;


public class PlayerWindow extends JFrame{
	
	JPanel playPan = new JPanel();
	
	public PlayerWindow(String title) {
		super(title);
		setSize(600, 800);
		setVisible(true);	
	}
	
	public void initAdd(){
		
		playPan.setLayout(new GridLayout(0,1));
		
		JLabel jLName = new JLabel("Name:");
		JTextField jTFName = new JTextField();
		jTFName.setPreferredSize(new Dimension(200, 20));
		JLabel jLFirstName = new JLabel("Vorname: ");
		JTextField jTFFirstName = new JTextField();
		jTFFirstName.setPreferredSize(new Dimension(200, 20));
		JLabel jLAge = new JLabel("Alter: ");
		JTextField jTFAge = new JTextField();
		jTFAge.setPreferredSize(new Dimension(100, 20));
		JButton jBtnAdd = new JButton("Spieler erstellen!");
		jBtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Main.addPlayer(new Player(jTFFirstName.getText(),jTFName.getText(),Integer.parseInt(jTFAge.getText())));
				jTFName.setText("");
				jTFFirstName.setText("");
				jTFAge.setText("");
				jTFName.requestFocusInWindow();
			}
		});
		this.add(playPan);
		playPan.add(jLName);
		playPan.add(jTFName);
		playPan.add(jLFirstName);
		playPan.add(jTFFirstName);
		playPan.add(jLAge);
		playPan.add(jTFAge);
		playPan.add(jBtnAdd);
		
	}
	
	public void initShow(String firstName, String lastName, int age){
		
		this.setSize(300, 200);
		playPan.setLayout(new GridLayout(0,1));
		
		JLabel jLName = new JLabel(firstName + " " + lastName);
		JLabel jLAge = new JLabel(Integer.toString(age));
		
		this.add(playPan);
		playPan.add(jLName);
		playPan.add(jLAge);
		
	}

}
