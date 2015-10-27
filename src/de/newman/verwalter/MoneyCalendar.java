package de.newman.verwalter;

import java.util.ArrayList;

import javax.swing.JPanel;

public class MoneyCalendar extends JPanel{
	private EntryList entries = new EntryList();
	public MoneyCalendar(EntryList entries) {
		this.entries=entries;
	}
}
