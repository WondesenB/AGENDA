package com.example.agenda;

public class Note_Item {
	private String date;
	private String title;
	private String note;
	public Note_Item(String date, String title, String note) {
		super();
		this.date = date;
		this.title = title;
		this.note = note;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
