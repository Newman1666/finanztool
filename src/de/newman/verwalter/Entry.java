package de.newman.verwalter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Entry {
	private String name;
	private double money;
	private Calendar date;
	private boolean willBeAdded;

	public Entry() {
		date = GregorianCalendar.getInstance();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double d) {
		this.money = d;
	}

	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date=date;
	}

	public void setDay(int day) {
		this.date.set(Calendar.DAY_OF_MONTH, day);
	}
	public int getDay() {
		return this.date.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Sets the month of this entry
	 * 
	 * @param month
	 *            1=January 12=December
	 */
	public void setMonth(int month) {
		if (month >= 1 && month <= 12) {
			this.date.set(Calendar.MONTH, month-1);
		}
	}
	
	public int getMonth() {
		return this.date.get(Calendar.MONTH)+1;
	}

	public void setYear(int year) {
		this.date.set(Calendar.YEAR, year);
	}

	public int getYear() {
		return this.date.get(Calendar.YEAR);
	}

	public boolean willItBeAdded() {
		return willBeAdded;
	}

	public void setIfWillBeAdded(boolean willBeAdded) {
		this.willBeAdded = willBeAdded;
	}
}
