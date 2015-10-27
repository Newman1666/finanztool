package de.newman.verwalter;

import java.util.ArrayList;

public class EntryList {
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	public EntryList() {	
	}
	
	public void addEntry(Entry newEntry) {
		entries.add(newEntry);
	}
	
	public Entry getEntryAt(int i) {
		return entries.get(i);
	}
	
	public int getSize() {
		return entries.size();
	}
}
