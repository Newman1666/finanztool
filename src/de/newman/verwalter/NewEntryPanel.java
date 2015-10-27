package de.newman.verwalter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewEntryPanel extends JPanel {
	private EntryList entries = new EntryList();
	JTextField name = new JTextField(10);
	private JTextField times = new JTextField(10);
	JTextField value = new JTextField(10);
	private JButton enterEntry = new JButton();
	private JButton deleteInputs = new JButton();
	JComboBox<Integer> day = new JComboBox<Integer>();
	JComboBox<Integer> month = new JComboBox<Integer>();
	JComboBox<Integer> year = new JComboBox<Integer>();
	JComboBox<String> intervall = new JComboBox<String>();
	JComboBox<String> willBeAdded = new JComboBox<String>();

	public NewEntryPanel(EntryList entries) {
		this.entries = entries;
		EntryPanelButtonListener entryPanelButtonListener = new EntryPanelButtonListener();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel("Name:"), c);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.add(name, c);

		willBeAdded.addItem("+");
		willBeAdded.addItem("-");
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		this.add(willBeAdded, c);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 0;
		this.add(new JLabel("Betrag:"), c);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 1;
		this.add(value, c);
		c.gridheight = 1;
		c.gridwidth = 3;
		c.gridx = 3;
		c.gridy = 0;
		this.add(new JLabel("Date:"), c);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 6;
		c.gridy = 0;
		this.add(new JLabel("Intervall:"), c);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 6;
		c.gridy = 1;
		intervall.addItem("Einmalig");
		intervall.addItem("Täglich");
		intervall.addItem("Wöchentlich");
		intervall.addItem("Monatlich");
		this.add(intervall, c);
		Calendar c1 = GregorianCalendar.getInstance();

		for (int i = 1; i < 13; i++) {
			month.addItem(i);
		}
		int thisYear = c1.get(Calendar.YEAR);
		for (int i = 0; i < 10; i++) {
			year.addItem(thisYear + i);
		}
		for (int i = 0; i < c1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			day.addItem(i + 1);
		}

		day.setSelectedItem(c1.get(Calendar.DAY_OF_MONTH));
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		this.add(day, c);
		month.setSelectedItem(c1.get(Calendar.MONTH) + 1);
		month.addActionListener(new DropDownChangeListener());
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 4;
		c.gridy = 1;
		this.add(month, c);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 5;
		c.gridy = 1;
		this.add(year, c);
		c.gridx = 7;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		this.add(new JLabel("Wie oft:"), c);
		c.gridx = 7;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		this.add(times, c);
		c.gridx = 8;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		deleteInputs.setText("Löschen");
		deleteInputs.addActionListener(entryPanelButtonListener);
		this.add(deleteInputs, c);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 8;
		c.gridy = 1;
		enterEntry.setText("Speichern");
		enterEntry.addActionListener(entryPanelButtonListener);
		this.add(enterEntry, c);
	}

	private class EntryPanelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == enterEntry) {
				Entry newEntry = new Entry();
				newEntry.setName(name.getText());
				if(value.getText().matches("-?\\d+(.\\d+)?"))newEntry.setMoney(Double.parseDouble(value.getText()));
				if (willBeAdded.getSelectedItem().equals("+"))
					newEntry.setIfWillBeAdded(true);
				else
					newEntry.setIfWillBeAdded(false);
				newEntry.setDay((int) day.getSelectedItem());
				newEntry.setMonth((int) month.getSelectedItem());
				newEntry.setYear((int) year.getSelectedItem());
				int tempTimes = times.getText().matches("-?\\d+(.\\d+)?") ? Integer.parseInt(times.getText())>1?Integer.parseInt(times.getText())-1 : 0 : 0;
				entries.addEntry(newEntry);
				switch (String.valueOf(intervall.getSelectedItem())) {
				case "Täglich":
					//i*z für 3 monatlich z=3
					for(int i=1; i<=tempTimes;i++) {
						Entry tempNewEntry = new Entry();
						tempNewEntry.setMoney(newEntry.getMoney());
						tempNewEntry.setIfWillBeAdded(newEntry.willItBeAdded());
						tempNewEntry.setName(newEntry.getName());
						tempNewEntry.setMonth(newEntry.getMonth());
						tempNewEntry.setYear(newEntry.getYear());
						tempNewEntry.setDay(newEntry.getDate().get(Calendar.DAY_OF_MONTH)+i);
						entries.addEntry(tempNewEntry);
					}
					break;
				case "Wöchentlich":
					for(int i=1; i<=tempTimes;i++) {
						Entry tempNewEntry = new Entry();
						tempNewEntry.setMoney(newEntry.getMoney());
						tempNewEntry.setIfWillBeAdded(newEntry.willItBeAdded());
						tempNewEntry.setMonth(newEntry.getMonth());
						tempNewEntry.setName(newEntry.getName());
						tempNewEntry.setYear(newEntry.getYear());
						tempNewEntry.setDay(newEntry.getDate().get(Calendar.DAY_OF_MONTH)+(7*i));
						entries.addEntry(tempNewEntry);
					}
					break;
				case "Monatlich":
					for(int i=1; i<=tempTimes;i++) {
						Entry tempNewEntry = new Entry();
						tempNewEntry.setMoney(newEntry.getMoney());
						tempNewEntry.setIfWillBeAdded(newEntry.willItBeAdded());
						tempNewEntry.setName(newEntry.getName());
						tempNewEntry.setYear(newEntry.getYear());
						int temp=(newEntry.getMonth()+i);
						int yearNo=0;
						while(temp>12) {
							temp-=12;
							yearNo++;
						}
						tempNewEntry.setMonth(temp);
						tempNewEntry.setYear(newEntry.getYear()+yearNo);
						if((int) day.getSelectedItem()>tempNewEntry.getDate().getActualMaximum(Calendar.DAY_OF_MONTH)) {
							tempNewEntry.setDay(tempNewEntry.getDate().getActualMaximum(Calendar.DAY_OF_MONTH));
						} else {
							tempNewEntry.setDay(newEntry.getDay());
						}
						entries.addEntry(tempNewEntry);
					}
					break;
				default:
					break;
				}
			}
			if (e.getSource() == deleteInputs) {
				Calendar c1 = GregorianCalendar.getInstance();
				name.setText("");
				value.setText("");
				willBeAdded.setSelectedItem("+");
				month.setSelectedItem(c1.get(Calendar.MONTH) + 1);
				day.setSelectedItem(c1.get(Calendar.DAY_OF_MONTH));
				year.setSelectedItem(c1.get(Calendar.YEAR));
				intervall.setSelectedItem("Einmalig");
				times.setText("");
			}
		}

	}

	private class DropDownChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == month) {
				Calendar c1 = GregorianCalendar.getInstance();
				c1.set(Calendar.MONTH, month.getSelectedIndex());
				day.removeAllItems();
				for (int i = 1; i <= c1.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
					day.addItem(i);
				}
			}
		}

	}
}
