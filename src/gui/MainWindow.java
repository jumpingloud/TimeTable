package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import prog.Main;


public class MainWindow extends JFrame {	
	
	JPanel jPAddPlayer;
	
	public MainWindow() {
		super("SPTool");
		setSize(1024, 768);
		setVisible(true);		
	}
	
	public void initGUI(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    		Main.saveAllPlayers();
		            System.exit(0);
		        }
		    }
		);
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints cons = new GridBagConstraints();
		JButton jBtnAddPlayer = new JButton("Spieler hinzufügen");
		jBtnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						PlayerWindow inst = new PlayerWindow("text");
						inst.setLocationRelativeTo(null);
						inst.setVisible(true);
						inst.initAdd();
					}
				});
			}
		});
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 0;
		cons.gridy = 0;		
		this.add(jBtnAddPlayer, cons);
		
		JButton jBtnShowPlayers = new JButton("Spieler anzeigen");
		jBtnShowPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Main.testShow();
			}
		});
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 1;
		cons.gridy = 1;		
		this.add(jBtnShowPlayers, cons);
		
		JButton jBtnShowTT = new JButton("Plan anzeigen");
		jBtnShowTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						TimetableWindow inst = new TimetableWindow("Stundenplan");
						inst.setLocationRelativeTo(null);
						inst.setVisible(true);
						inst.initWindow();
						inst.pack();
					}
				});
			}
		});
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 0;
		cons.gridy = 1;		
		this.add(jBtnShowTT, cons);
		
		
		jBtnAddPlayer = new JButton("Gruppe erstellen");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridx = 1;
		cons.gridy = 0;
		this.add(jBtnAddPlayer, cons);
		
		
		
	}
}
