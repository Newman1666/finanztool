package de.newman.verwalter;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame{

	private JTabbedPane tabs = new JTabbedPane();
	private EntryPanel entryPanel;
	
	public static void main(String[] args) {
		MainFrame menuFrame = new MainFrame();
		

	}
	
	public MainFrame() {
		tabs.addChangeListener(new TabsChangeListener());
		EntryList newEntryList = new EntryList();
		
		entryPanel  = new EntryPanel(newEntryList);
		tabs.add("Einträge", entryPanel);
		
		NewEntryPanel newEntryPanel = new NewEntryPanel(newEntryList);
		tabs.add("Neuer Eintrag", newEntryPanel);
		
		MoneyCalendar moneyCalendarPanel = new MoneyCalendar(newEntryList);
		tabs.addTab("Kalendar", moneyCalendarPanel);
		this.add(tabs);
		this.setSize(800, 200);
		this.setTitle("Geld Verwalter");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2));
		this.setVisible(true);
	}
	
	private class TabsChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if(tabs.getSelectedIndex()==0) {
				entryPanel.drawEntries();
			}
		}
		
	}
}
