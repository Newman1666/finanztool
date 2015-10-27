package de.newman.verwalter;

import java.awt.Component;
import java.util.Calendar;
import java.util.Comparator;

import javax.swing.DefaultRowSorter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class EntryPanel extends JPanel{
	private EntryList entries = new EntryList();
	public EntryPanel(EntryList entries) {
		this.entries=entries;
		JPanel tablePanel = new JPanel();
		JTable table=new JTable();
		tablePanel.add(table.getTableHeader());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		tablePanel.add(scrollPane);
		table.setDefaultRenderer(String.class, new EntryTableCellRenderer());
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new EntryTableModel(entries));
		
//		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.setRowHeight(30);
		
		table.setAutoCreateRowSorter(true);
		final DefaultRowSorter<?, ?> rowSorter = (DefaultRowSorter<?,?>) table.getRowSorter();
		for(int i=0;i<table.getModel().getColumnCount(); i++) {
			rowSorter.setComparator(i, new EntryRowComparator());
		}
//		rowSorter.toggleSortOrder(0);

//		updateTable(table);
		drawEntries();
	}
	public void updateTable(JTable table) {
		table.setModel(new EntryTableModel(entries));
	}
	
	public void drawEntries() {
		this.removeAll();
		for(int i=0; i<entries.getSize();i++) {
			this.add(new JLabel(entries.getEntryAt(i).getName()));
			this.add(new JLabel(String.valueOf(entries.getEntryAt(i).getMoney())));
			this.add(new JLabel(String.valueOf(entries.getEntryAt(i).getDate().get(Calendar.DAY_OF_MONTH))));
			this.add(new JLabel(String.valueOf(entries.getEntryAt(i).getMonth())));
		}
	}
	
	// Kann jede zelle formatieren
	private class EntryTableCellRenderer implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private class EntryTableModel extends DefaultTableModel{
		
		private EntryList entries;
		
		public EntryTableModel(EntryList entries) {
			super(new Object[] {"test","test2"}, entries.getSize());
			this.entries=entries;
		}
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public Object getValueAt(int row, int column) {
			Entry rowValue = entries.getEntryAt(row);
			switch(column){
			case 0:
				return rowValue.getName();
			case 1:
				return rowValue.willItBeAdded() ? "+" : "-";
			case 2:
				return rowValue.getMoney();
			case 3:
				return rowValue.getDate();
			default:
				System.out.println("Error");
				break;
			}
			return null;
		}

		@Override
		public boolean isCellEditable(int arg0, int arg1) {
			return false;
		}		
	}
	private class EntryRowComparator implements Comparator<Object>{

		public EntryRowComparator() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
