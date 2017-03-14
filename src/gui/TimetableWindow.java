package gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import prog.Slot;

public class TimetableWindow extends JFrame {
	
	JPanel panel = new JPanel();
	
	private ArrayList<ArrayList<Component>> listOfSlotComponents = new ArrayList<ArrayList<Component>>();
	
	
	
	public TimetableWindow(String title) {
		super(title);
		setSize(1500, 1000);
		setVisible(true);
		
		for (int i = 0; i<6; i++){
			listOfSlotComponents.add(i, new ArrayList<Component>());
		}
		
	}
	
	public void initWindow(){
		
		panel.setLayout(new GridBagLayout());
		add(panel);
		GridBagConstraints c = new GridBagConstraints();
		JPanel slotPanel = new JPanel();
		
		JLabel jLDay = new JLabel("Montag");
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		slotPanel.add(jLDay);
		slotPanel.setPreferredSize(new Dimension(120,25));
		slotPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(slotPanel, c);
		
		jLDay = new JLabel("Dienstag");
		c.gridx = 2;
		slotPanel = new JPanel();
		slotPanel.add(jLDay);
		slotPanel.setPreferredSize(new Dimension(120,25));
		slotPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(slotPanel, c);
		
		jLDay = new JLabel("Mittwoch");
		c.gridx = 3;
		slotPanel = new JPanel();
		slotPanel.add(jLDay);
		slotPanel.setPreferredSize(new Dimension(120,25));
		slotPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(slotPanel, c);
		
		jLDay = new JLabel("Donnerstag");
		c.gridx = 4;
		slotPanel = new JPanel();
		slotPanel.add(jLDay);
		slotPanel.setPreferredSize(new Dimension(120,25));
		slotPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(slotPanel, c);
		
		jLDay = new JLabel("Freitag");
		c.gridx = 5;
		slotPanel = new JPanel();
		slotPanel.add(jLDay);
		slotPanel.setPreferredSize(new Dimension(120,25));
		slotPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(slotPanel, c);
		
		jLDay = new JLabel("Samstag");
		c.gridx = 6;
		slotPanel = new JPanel();
		slotPanel.add(jLDay);
		slotPanel.setPreferredSize(new Dimension(120,25));
		slotPanel.setBackground(Color.LIGHT_GRAY);
		panel.add(slotPanel, c);
		
		jLDay = new JLabel("Uhrzeit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weightx = 0;
		c.weighty = 0;
		slotPanel = new JPanel();
		slotPanel.add(jLDay);
		slotPanel.setPreferredSize(new Dimension(50,25));
		slotPanel.setBackground(Color.DARK_GRAY);
		panel.add(slotPanel, c);		
		
		for(int i = 1; i <31; i++){
			
			String time = "";
			int hours = (int) (7 + 0.5*i);
			if (hours<10) time += "0";
			time += String.valueOf(hours);
			if(i%2==0)	time += ":00";
			else time += ":30";			
			c.anchor = GridBagConstraints.LINE_START;
			c.gridx = 0;
			c.gridy = i;
			c.fill = GridBagConstraints.VERTICAL;
			c.weighty = 1;
			slotPanel = new JPanel();
			jLDay = new JLabel(time);
			slotPanel.setPreferredSize(new Dimension(50,25));
			if(i%2==0) slotPanel.setBackground(Color.WHITE);
			else slotPanel.setBackground(Color.YELLOW);
			slotPanel.add(jLDay);
			panel.add(slotPanel, c);
						
		}
		
		for(int i = 1; i<7; i++){
			
			JPanel slotLayerPanel = new JPanel();
			GridBagConstraints cons = new GridBagConstraints();
			cons.gridx = i;
			cons.gridy = 1;
			cons.weightx = 1;
			cons.weighty = 1;
			cons.gridwidth = 1;
			cons.fill = GridBagConstraints.BOTH;
			cons.gridheight = GridBagConstraints.REMAINDER;
			slotLayerPanel.setBackground(Color.GREEN);
			panel.add(slotLayerPanel, cons);
			listOfSlotComponents.get(i-1).add(slotPanel);
			
		}
		
		ArrayList<Slot> list = new ArrayList<Slot>();
		list.add(new Slot("10:30", "12:00", "Dienstag"));
		showSlot(list);
		System.out.println("Bla!");
		
	}

	public void showSlot(ArrayList<Slot> slotList){
		
		String [] listOfDays = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};
		
		for (int i = 1; i<7; i++){
			for(Slot slot: slotList){			
				if ((slot.getDay().equals(listOfDays[i-1]) && (!listOfSlotComponents.get(i-1).isEmpty()))){
					for(Component comp :listOfSlotComponents.get(i-1)){
						panel.remove(comp);
						//listOfSlotComponents.get(i-1).remove(comp);
					}
				}
			}	
		}
		
		
		for (Slot slot: slotList){
			for(int i = 1; i<7; i++){
				
				if(slot.getDay().equals(listOfDays[i-1])){
				
					JPanel slotPanel = new JPanel();
					JLabel label = new JLabel("Gruppenname");
					GridBagConstraints c = new GridBagConstraints();
					c.gridx = i;
					c.gridy = (slot.getStart().getHours()-8)*2+Math.max(0, slot.getStart().getMinutes()-30);
					c.fill = GridBagConstraints.BOTH;
					c.gridheight = slot.getLength();
					c.gridwidth = 1;
					c.weightx = 1;
					c.weighty = 1;
					slotPanel.setBackground(Color.RED);
					
					slotPanel.add(label);
					panel.add(slotPanel, c);
					listOfSlotComponents.get(i-1).add(slotPanel);
				}					
					
			}	
		}
		
		repaint();
		revalidate();
		
	}
	
}
