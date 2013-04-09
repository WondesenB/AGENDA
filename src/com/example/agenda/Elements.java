package com.example.agenda;

public class Elements {
	private String title;
	private String category;
	private String Big_category;
	private String startingDate;
	private String startingTime;
	private String startingTime_class;
	private String endingDate;
	private String endingTime;
	private String endingTime_class;

	private String s_place;
	private String des_place;
	private String frequency;
	private String note;
	private String caltype;
	private String from;
	private String to;

	public Elements(String title, String category, String big_category,
			String startingDate, String startingTime,
			String startingTime_class, String endingDate, String endingTime,
			String endingTime_class, String s_place, String des_place,
			String frequency, String note, String caltype,String from, String to) {
		super();
		this.title = title;
		this.category = category;
		this.Big_category = big_category;
		this.startingDate = startingDate;
		this.startingTime = startingTime;
		this.startingTime_class = startingTime_class;
		this.endingDate = endingDate;
		this.endingTime = endingTime;
		this.endingTime_class = endingTime_class;
        this.caltype=caltype;
		this.s_place = s_place;
		this.des_place = des_place;
		this.frequency = frequency;
		this.note = note;
		this.from = from;
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBig_category() {
		return Big_category;
	}

	public void setBig_category(String big_category) {
		Big_category = big_category;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public String getStartingTime_class() {
		return startingTime_class;
	}

	public void setStartingTime_class(String startingTime_class) {
		this.startingTime_class = startingTime_class;
	}

	public String getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}

	public String getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}

	public String getEndingTime_class() {
		return endingTime_class;
	}

	public void setEndingTime_class(String endingTime_class) {
		this.endingTime_class = endingTime_class;
	}

	public String getS_place() {
		return s_place;
	}

	public void setS_place(String s_place) {
		this.s_place = s_place;
	}

	public String getDes_place() {
		return des_place;
	}

	public void setDes_place(String des_place) {
		this.des_place = des_place;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public String getcaltype() {
		return caltype;
	}

	public void setcaltype(String caltype) {
		this.caltype = caltype;
	}
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
