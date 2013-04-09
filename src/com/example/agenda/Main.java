package com.example.agenda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.agenda.view.viewgroup.DataBaseAdapter;
import com.example.agenda.view.viewgroup.SlidingLayout;

@SuppressLint("DefaultLocale")
public class Main extends Activity {
	SlidingLayout root;
	myListAdapter madapter;
	private DataBaseAdapter db_bill;
	private DataBaseAdapter db_celle;
	private DataBaseAdapter db_meet;
	private DataBaseAdapter db_edu;
	private DataBaseAdapter db_enter;
	private DataBaseAdapter db_travel;
	private DataBaseAdapter db_other;
	private Calandar cal_type;
	
	// for data base
	ArrayList<Elements> myElements = new ArrayList<Elements>();
	ArrayList<Elements> myElements_week = new ArrayList<Elements>();
	ArrayList<Elements> myElements_month = new ArrayList<Elements>();
	String[] myItems = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.root = (SlidingLayout) this.getLayoutInflater().inflate(
				R.layout.home, null);
		this.setContentView(root);
		addItemstoList();
		PopulatelistView();
		getData();
		ListView menu_layout = (ListView) findViewById(R.id.ItemList);
		registerForContextMenu(menu_layout);
		// Toogle_button
		animate_button();

		//
		populate_tab();

		//
	}

	private void populate_tab() {
		// TODO Auto-generated method stub
		TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
		tabHost.setup();
		String tod = getResources().getString(R.string.today_event);
		String week = getResources().getString(R.string.week_event);
		String mon = getResources().getString(R.string.month_event);
		TabSpec spec1 = tabHost.newTabSpec("Today");
		spec1.setContent(R.id.todayevent_tab);
		spec1.setIndicator(tod);

		TabSpec spec2 = tabHost.newTabSpec("week");
		spec2.setIndicator(week);
		spec2.setContent(R.id.thisweekevent_tab);

		TabSpec spec3 = tabHost.newTabSpec("month");
		spec3.setIndicator(mon);
		spec3.setContent(R.id.thismonthevent_tab);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
	}

	private void animate_button() {
		// TODO Auto-generated method stub
		final Animation animation = new AlphaAnimation(0, 1);
		animation.setDuration(1000);
		animation.setInterpolator(new LinearInterpolator());
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		final ImageButton toggle_btn = (ImageButton) findViewById(R.id.Toogle_button);
		toggle_btn.startAnimation(animation);
	}

	private void addItemstoList() {
		myItems = getResources().getStringArray(R.array.menu_item);

	}

	private void PopulatelistView() {

		// configure
		ArrayAdapter<String> adapter = new myListAdapter();
		ListView mylist = (ListView) findViewById(R.id.ItemList);

		mylist.setAdapter(adapter);

		mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> Parent, View viewChecked,
					int position, long id) { // TODO Auto-generated method
				// stub
				ListView menu_layout = (ListView) findViewById(R.id.ItemList);
				registerForContextMenu(menu_layout);
				viewChecked.showContextMenu();

			}

		});

	}

	// ++++++++++++++++++++++++++++++++
	// Data Base related

	public void getData() {

		getToday_event();
		getWeek_event();
		getMonth_event();
		setDataIntoList();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SimpleDateFormat")
	private void getToday_event() {
		myElements.clear();
		final Calendar c = Calendar.getInstance();
		final Calendar c1 = Calendar.getInstance();
		final Calendar c2 = Calendar.getInstance();
		final Calendar c11 = Calendar.getInstance();
		final Calendar c12 = Calendar.getInstance();
		SimpleDateFormat formater = new java.text.SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		String day = "" + c.get(Calendar.DAY_OF_MONTH);
		String month = "" + df1.format(c.getTime());
		String year = "" + c.get(Calendar.YEAR);
		db_bill = new DataBaseAdapter(this);
		db_celle = new DataBaseAdapter(this);
		db_meet = new DataBaseAdapter(this);
		db_edu = new DataBaseAdapter(this);
		db_enter = new DataBaseAdapter(this);
		db_travel = new DataBaseAdapter(this);
		db_other = new DataBaseAdapter(this);
		try {
			db_bill.open();
			db_celle.open_celle();
			db_meet.open_meet();
			db_edu.open_edu();
			db_enter.open_entr();
			db_travel.open_travel();
			db_other.open_others();

			Cursor cur = db_bill.getAllTitles_bill();
			Cursor cur_celle = db_celle.getAllTitles_celle();
			Cursor cur_meet = db_meet.getAllTitles_meet();
			Cursor cur_edu = db_edu.getAllTitles_edu();
			Cursor cur_enter = db_enter.getAllTitles_enter();
			Cursor cur_travel = db_travel.getAllTitles_travel();
			Cursor cur_other = db_other.getAllTitles_others();

			while (cur.moveToNext()) {
				cal_type = new Calandar();
				Date now = formater.parse(day + "/" + month + "/" + year);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);

				if (cur.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur.getString(3));
					Date date2 = formater.parse(cur.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);

					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur.getString(11).equalsIgnoreCase("Gregorian")
						|| cur.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur.getString(3));
					Date date2 = formater.parse(cur.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (now.equals(c1.getTime())
						|| now.equals(c2.getTime())
						|| (now.after(c1.getTime()) && now.before(c2.getTime()))) {
					String valueoftitle = cur.getString(1);
					String valueofcat = cur.getString(2);
					String valueofsdate = cur.getString(3);
					String valueofstime = cur.getString(4);
					String valueofstime_class = cur.getString(5);
					String valueofedate = cur.getString(6);
					String valueofetime = cur.getString(7);
					String valueofetime_class = cur.getString(8);
					String valueoffreq = cur.getString(9);
					String valueofnote = cur.getString(10);
					String valueofcaltype = cur.getString(11);
					//
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.billing_),
								formater.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.billing_),
								valueofsdate, valueofstime, valueofstime_class,
								valueofedate, valueofetime, valueofetime_class,
								"", "", valueoffreq, valueofnote,
								valueofcaltype, "", ""));
					}

				}
			}
			while (cur_celle.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_celle.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_celle.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_celle.getString(3));
					Date date2 = formater.parse(cur_celle.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur_celle.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_celle.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_celle.getString(3));
					Date date2 = formater.parse(cur_celle.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (now.equals(c1.getTime())
						|| now.equals(c2.getTime())
						|| (now.after(c1.getTime()) && now.before(c2.getTime()))) {
					String valueoftitle = cur_celle.getString(1);
					String valueofcat = cur_celle.getString(2);
					String valueofsdate = cur_celle.getString(3);
					String valueofstime = cur_celle.getString(4);
					String valueofstime_class = cur_celle.getString(5);
					String valueofedate = cur_celle.getString(6);
					String valueofetime = cur_celle.getString(7);
					String valueofetime_class = cur_celle.getString(8);
					String valueoffreq = cur_celle.getString(9);
					String valueofnote = cur_celle.getString(10);
					String valueofcaltype = cur_celle.getString(11);
					//
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources()
										.getString(R.string.cellebration_),
								formater.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources()
										.getString(R.string.cellebration_),
								valueofsdate, valueofstime, valueofstime_class,
								valueofedate, valueofetime, valueofetime_class,
								"", "", valueoffreq, valueofnote,
								valueofcaltype, "", ""));
					}

				}
			}
			while (cur_meet.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_meet.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_meet.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_meet.getString(3));
					Date date2 = formater.parse(cur_meet.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur_meet.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_meet.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_meet.getString(3));
					Date date2 = formater.parse(cur_meet.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (now.equals(c1.getTime())
						|| now.equals(c2.getTime())
						|| (now.after(c1.getTime()) && now.before(c2.getTime()))) {
					String valueoftitle = cur_meet.getString(1);
					String valueofcat = cur_meet.getString(2);
					String valueofsdate = cur_meet.getString(3);
					String valueofstime = cur_meet.getString(4);
					String valueofstime_class = cur_meet.getString(5);
					String valueofedate = cur_meet.getString(6);
					String valueofetime = cur_meet.getString(7);
					String valueofetime_class = cur_meet.getString(8);
					String valueoffreq = cur_meet.getString(9);
					String valueofnote = cur_meet.getString(10);
					String valueofcaltype = cur_meet.getString(11);
					//
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.meeting_),
								formater.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.meeting_),
								valueofsdate, valueofstime, valueofstime_class,
								valueofedate, valueofetime, valueofetime_class,
								"", "", valueoffreq, valueofnote,
								valueofcaltype, "", ""));
					}

				}
			}
			while (cur_edu.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_edu.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_edu.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_edu.getString(3));
					Date date2 = formater.parse(cur_edu.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur_edu.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_edu.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_edu.getString(3));
					Date date2 = formater.parse(cur_edu.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (now.equals(c1.getTime())
						|| now.equals(c2.getTime())
						|| (now.after(c1.getTime()) && now.before(c2.getTime()))) {
					String valueoftitle = cur_edu.getString(1);
					String valueofcat = cur_edu.getString(2);
					String valueofsdate = cur_edu.getString(3);
					String valueofstime = cur_edu.getString(4);
					String valueofstime_class = cur_edu.getString(5);
					String valueofedate = cur_edu.getString(6);
					String valueofetime = cur_edu.getString(7);
					String valueofetime_class = cur_edu.getString(8);
					String valueoffreq = cur_edu.getString(9);
					String valueofnote = cur_edu.getString(10);
					String valueofcaltype = cur_edu.getString(11);
					//
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.education_),
								formater.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.education_),
								valueofsdate, valueofstime, valueofstime_class,
								valueofedate, valueofetime, valueofetime_class,
								"", "", valueoffreq, valueofnote,
								valueofcaltype, "", ""));
					}

				}
			}
			while (cur_enter.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_enter.getString(12).equalsIgnoreCase("Ethiopian")
						|| cur_enter.getString(12).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_enter.getString(3));
					Date date2 = formater.parse(cur_enter.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur_enter.getString(12).equalsIgnoreCase("Gregorian")
						|| cur_enter.getString(12).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_enter.getString(3));
					Date date2 = formater.parse(cur_enter.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (now.equals(c1.getTime())
						|| now.equals(c2.getTime())
						|| (now.after(c1.getTime()) && now.before(c2.getTime()))) {
					String valueoftitle = cur_enter.getString(1);
					String valueofcat = cur_enter.getString(2);
					String valueofsdate = cur_enter.getString(3);
					String valueofstime = cur_enter.getString(4);
					String valueofstime_class = cur_enter.getString(5);
					String valueofedate = cur_enter.getString(6);
					String valueofetime = cur_enter.getString(7);
					String valueofetime_class = cur_enter.getString(8);
					String valueofplace = cur_enter.getString(9);
					String valueoffreq = cur_enter.getString(10);
					String valueofnote = cur_enter.getString(11);
					String valueofcaltype = cur_enter.getString(12);
					//
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(
										R.string.entertainment_), formater
										.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, valueofplace, "",
								valueoffreq, valueofnote, valueofcaltype,
								getResources().getString(R.string.At), ""));
					} else {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(
										R.string.entertainment_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, valueofplace,
								"", valueoffreq, valueofnote, valueofcaltype,
								getResources().getString(R.string.At), ""));
					}

				}
			}
			while (cur_travel.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_travel.getString(12).equalsIgnoreCase("Ethiopian")
						|| cur_travel.getString(12).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_travel.getString(3));
					Date date2 = formater.parse(cur_travel.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur_travel.getString(13).equalsIgnoreCase("Gregorian")
						|| cur_travel.getString(13).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_travel.getString(3));
					Date date2 = formater.parse(cur_travel.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (now.equals(c1.getTime())
						|| now.equals(c2.getTime())
						|| (now.after(c1.getTime()) && now.before(c2.getTime()))) {
					String valueoftitle = cur_travel.getString(1);
					String valueofcat = cur_travel.getString(2);
					String valueofsdate = cur_travel.getString(3);
					String valueofstime = cur_travel.getString(4);
					String valueofstime_class = cur_travel.getString(5);
					String valueofedate = cur_travel.getString(6);
					String valueofetime = cur_travel.getString(7);
					String valueofetime_class = cur_travel.getString(8);
					String valueofsplace = cur_travel.getString(9);
					String valueofdesplace = cur_travel.getString(10);
					String valueoffreq = cur_travel.getString(11);
					String valueofnote = cur_travel.getString(12);
					String valueofcaltype = cur_travel.getString(13);
					//
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.travel_),
								formater.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, valueofsplace,
								valueofdesplace, valueoffreq, valueofnote,
								valueofcaltype, getResources().getString(
										R.string.from), getResources()
										.getString(R.string.To)));
					} else {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.travel_),
								valueofsdate, valueofstime, valueofstime_class,
								valueofedate, valueofetime, valueofetime_class,
								valueofsplace, valueofdesplace, valueoffreq,
								valueofnote, valueofcaltype, getResources()
										.getString(R.string.from),
								getResources().getString(R.string.To)));
					}

				}
			}
			while (cur_other.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_other.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_other.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_other.getString(3));
					Date date2 = formater.parse(cur_other.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur_other.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_other.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_other.getString(3));
					Date date2 = formater.parse(cur_other.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (now.equals(c1.getTime())
						|| now.equals(c2.getTime())
						|| (now.after(c1.getTime()) && now.before(c2.getTime()))) {
					String valueoftitle = cur_other.getString(1);
					String valueofcat = cur_other.getString(2);
					String valueofsdate = cur_other.getString(3);
					String valueofstime = cur_other.getString(4);
					String valueofstime_class = cur_other.getString(5);
					String valueofedate = cur_other.getString(6);
					String valueofetime = cur_other.getString(7);
					String valueofetime_class = cur_other.getString(8);
					String valueoffreq = cur_other.getString(9);
					String valueofnote = cur_other.getString(10);
					String valueofcaltype = cur_other.getString(11);
					//
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.others_),
								formater.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements.add(new Elements(valueoftitle, valueofcat,
								getResources().getString(R.string.others_),
								valueofsdate, valueofstime, valueofstime_class,
								valueofedate, valueofetime, valueofetime_class,
								"", "", valueoffreq, valueofnote,
								valueofcaltype, "", ""));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db_bill.close();
			db_celle.close_celle();
			db_meet.close_meet();
			db_edu.close_edu();
			db_enter.close_entr();
			db_travel.close_travel();
			db_other.close_others();

		}
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	@SuppressLint("SimpleDateFormat")
	private void getWeek_event() {
		myElements_week.clear();
		final Calendar c = Calendar.getInstance();
		final Calendar c1 = Calendar.getInstance();
		final Calendar c2 = Calendar.getInstance();
		final Calendar c11 = Calendar.getInstance();
		final Calendar c12 = Calendar.getInstance();
		SimpleDateFormat formater = new java.text.SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		String day = "" + c.get(Calendar.DAY_OF_MONTH);
		String month = "" + df1.format(c.getTime());
		String year = "" + c.get(Calendar.YEAR);
		db_bill = new DataBaseAdapter(this);
		db_celle = new DataBaseAdapter(this);
		db_meet = new DataBaseAdapter(this);
		db_edu = new DataBaseAdapter(this);
		db_enter = new DataBaseAdapter(this);
		db_travel = new DataBaseAdapter(this);
		db_other = new DataBaseAdapter(this);
		try {
			db_bill.open();
			db_celle.open_celle();
			db_meet.open_meet();
			db_edu.open_edu();
			db_enter.open_entr();
			db_travel.open_travel();
			db_other.open_others();

			Cursor cur = db_bill.getAllTitles_bill();
			Cursor cur_celle = db_celle.getAllTitles_celle();
			Cursor cur_meet = db_meet.getAllTitles_meet();
			Cursor cur_edu = db_edu.getAllTitles_edu();
			Cursor cur_enter = db_enter.getAllTitles_enter();
			Cursor cur_travel = db_travel.getAllTitles_travel();
			Cursor cur_other = db_other.getAllTitles_others();

			while (cur.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur.getString(3));
					Date date2 = formater.parse(cur.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (cur.getString(11).equalsIgnoreCase("Gregorian")
						|| cur.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur.getString(3));
					Date date2 = formater.parse(cur.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}
				if (c.get(c.WEEK_OF_YEAR) == c1.get(c1.WEEK_OF_YEAR)
						|| c.get(c.WEEK_OF_YEAR) == c2.get(c2.WEEK_OF_YEAR)
						|| (c.get(c.WEEK_OF_YEAR) > c1.get(c1.WEEK_OF_YEAR) && c
								.get(c.WEEK_OF_YEAR) < c2.get(c2.WEEK_OF_YEAR))) {
					String valueoftitle = cur.getString(1);
					String valueofcat = cur.getString(2);
					String valueofsdate = cur.getString(3);
					String valueofstime = cur.getString(4);
					String valueofstime_class = cur.getString(5);
					String valueofedate = cur.getString(6);
					String valueofetime = cur.getString(7);
					String valueofetime_class = cur.getString(8);
					String valueoffreq = cur.getString(9);
					String valueofnote = cur.getString(10);
					String valueofcaltype = cur.getString(11);
					//
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.billing_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.billing_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
			while (cur_celle.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_celle.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_celle.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_celle.getString(3));
					Date date2 = formater.parse(cur_celle.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_celle.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_celle.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_celle.getString(3));
					Date date2 = formater.parse(cur_celle.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(c.WEEK_OF_YEAR) == c1.get(c1.WEEK_OF_YEAR)
						|| c.get(c.WEEK_OF_YEAR) == c2.get(c2.WEEK_OF_YEAR)
						|| (c.get(c.WEEK_OF_YEAR) > c1.get(c1.WEEK_OF_YEAR) && c
								.get(c.WEEK_OF_YEAR) < c2.get(c2.WEEK_OF_YEAR))) {
					String valueoftitle = cur_celle.getString(1);
					String valueofcat = cur_celle.getString(2);
					String valueofsdate = cur_celle.getString(3);
					String valueofstime = cur_celle.getString(4);
					String valueofstime_class = cur_celle.getString(5);
					String valueofedate = cur_celle.getString(6);
					String valueofetime = cur_celle.getString(7);
					String valueofetime_class = cur_celle.getString(8);
					String valueoffreq = cur_celle.getString(9);
					String valueofnote = cur_celle.getString(10);
					String valueofcaltype = cur_celle.getString(11);
					//
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.cellebration_), formater
										.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.cellebration_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
			while (cur_meet.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_meet.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_meet.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_meet.getString(3));
					Date date2 = formater.parse(cur_meet.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_meet.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_meet.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_meet.getString(3));
					Date date2 = formater.parse(cur_meet.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(c.WEEK_OF_YEAR) == c1.get(c1.WEEK_OF_YEAR)
						|| c.get(c.WEEK_OF_YEAR) == c2.get(c2.WEEK_OF_YEAR)
						|| (c.get(c.WEEK_OF_YEAR) > c1.get(c1.WEEK_OF_YEAR) && c
								.get(c.WEEK_OF_YEAR) < c2.get(c2.WEEK_OF_YEAR))) {
					String valueoftitle = cur_meet.getString(1);
					String valueofcat = cur_meet.getString(2);
					String valueofsdate = cur_meet.getString(3);
					String valueofstime = cur_meet.getString(4);
					String valueofstime_class = cur_meet.getString(5);
					String valueofedate = cur_meet.getString(6);
					String valueofetime = cur_meet.getString(7);
					String valueofetime_class = cur_meet.getString(8);
					String valueoffreq = cur_meet.getString(9);
					String valueofnote = cur_meet.getString(10);
					String valueofcaltype = cur_meet.getString(11);
					//
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.meeting_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.meeting_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
			while (cur_edu.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_edu.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_edu.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_edu.getString(3));
					Date date2 = formater.parse(cur_edu.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_edu.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_edu.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_edu.getString(3));
					Date date2 = formater.parse(cur_edu.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(c.WEEK_OF_YEAR) == c1.get(c1.WEEK_OF_YEAR)
						|| c.get(c.WEEK_OF_YEAR) == c2.get(c2.WEEK_OF_YEAR)
						|| (c.get(c.WEEK_OF_YEAR) > c1.get(c1.WEEK_OF_YEAR) && c
								.get(c.WEEK_OF_YEAR) < c2.get(c2.WEEK_OF_YEAR))) {
					String valueoftitle = cur_edu.getString(1);
					String valueofcat = cur_edu.getString(2);
					String valueofsdate = cur_edu.getString(3);
					String valueofstime = cur_edu.getString(4);
					String valueofstime_class = cur_edu.getString(5);
					String valueofedate = cur_edu.getString(6);
					String valueofetime = cur_edu.getString(7);
					String valueofetime_class = cur_edu.getString(8);
					String valueoffreq = cur_edu.getString(9);
					String valueofnote = cur_edu.getString(10);
					String valueofcaltype = cur_edu.getString(11);
					//
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.education_), formater
										.format(c1.getTime()), valueofstime,
								valueofstime_class, formater.format(c2
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.education_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
			while (cur_enter.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_enter.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_enter.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_enter.getString(3));
					Date date2 = formater.parse(cur_enter.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_enter.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_enter.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_enter.getString(3));
					Date date2 = formater.parse(cur_enter.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(c.WEEK_OF_YEAR) == c1.get(c1.WEEK_OF_YEAR)
						|| c.get(c.WEEK_OF_YEAR) == c2.get(c2.WEEK_OF_YEAR)
						|| (c.get(c.WEEK_OF_YEAR) > c1.get(c1.WEEK_OF_YEAR) && c
								.get(c.WEEK_OF_YEAR) < c2.get(c2.WEEK_OF_YEAR))) {
					String valueoftitle = cur_enter.getString(1);
					String valueofcat = cur_enter.getString(2);
					String valueofsdate = cur_enter.getString(3);
					String valueofstime = cur_enter.getString(4);
					String valueofstime_class = cur_enter.getString(5);
					String valueofedate = cur_enter.getString(6);
					String valueofetime = cur_enter.getString(7);
					String valueofetime_class = cur_enter.getString(8);
					String valueofplace = cur_enter.getString(9);
					String valueoffreq = cur_enter.getString(10);
					String valueofnote = cur_enter.getString(11);
					String valueofcaltype = cur_enter.getString(12);
					//
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.entertainment_), formater
										.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, valueofplace, "",
								valueoffreq, valueofnote, valueofcaltype,
								getResources().getString(R.string.At), ""));
					} else {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.entertainment_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, valueofplace,
								"", valueoffreq, valueofnote, valueofcaltype,
								getResources().getString(R.string.At), ""));
					}

				}
			}
			while (cur_travel.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_travel.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_travel.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_travel.getString(3));
					Date date2 = formater.parse(cur_travel.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_travel.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_travel.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_travel.getString(3));
					Date date2 = formater.parse(cur_travel.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(c.WEEK_OF_YEAR) == c1.get(c1.WEEK_OF_YEAR)
						|| c.get(c.WEEK_OF_YEAR) == c2.get(c2.WEEK_OF_YEAR)
						|| (c.get(c.WEEK_OF_YEAR) > c1.get(c1.WEEK_OF_YEAR) && c
								.get(c.WEEK_OF_YEAR) < c2.get(c2.WEEK_OF_YEAR))) {
					String valueoftitle = cur_travel.getString(1);
					String valueofcat = cur_travel.getString(2);
					String valueofsdate = cur_travel.getString(3);
					String valueofstime = cur_travel.getString(4);
					String valueofstime_class = cur_travel.getString(5);
					String valueofedate = cur_travel.getString(6);
					String valueofetime = cur_travel.getString(7);
					String valueofetime_class = cur_travel.getString(8);
					String valueofsplace = cur_travel.getString(9);
					String valueofdesplace = cur_travel.getString(10);
					String valueoffreq = cur_travel.getString(11);
					String valueofnote = cur_travel.getString(12);
					String valueofcaltype = cur_travel.getString(13);
					//
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.travel_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, valueofsplace,
								valueofdesplace, valueoffreq, valueofnote,
								valueofcaltype, getResources().getString(
										R.string.from), getResources()
										.getString(R.string.To)));
					} else {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.travel_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class,
								valueofsplace, valueofdesplace, valueoffreq,
								valueofnote, valueofcaltype, getResources()
										.getString(R.string.from),
								getResources().getString(R.string.To)));
					}

				}
			}
			while (cur_other.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_other.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_other.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_other.getString(3));
					Date date2 = formater.parse(cur_other.getString(6));

					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_other.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_other.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_other.getString(3));
					Date date2 = formater.parse(cur_other.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(c.WEEK_OF_YEAR) == c1.get(c1.WEEK_OF_YEAR)
						|| c.get(c.WEEK_OF_YEAR) == c2.get(c2.WEEK_OF_YEAR)
						|| (c.get(c.WEEK_OF_YEAR) > c1.get(c1.WEEK_OF_YEAR) && c
								.get(c.WEEK_OF_YEAR) < c2.get(c2.WEEK_OF_YEAR))) {
					String valueoftitle = cur_other.getString(1);
					String valueofcat = cur_other.getString(2);
					String valueofsdate = cur_other.getString(3);
					String valueofstime = cur_other.getString(4);
					String valueofstime_class = cur_other.getString(5);
					String valueofedate = cur_other.getString(6);
					String valueofetime = cur_other.getString(7);
					String valueofetime_class = cur_other.getString(8);
					String valueoffreq = cur_other.getString(9);
					String valueofnote = cur_other.getString(10);
					String valueofcaltype = cur_other.getString(11);
					//
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.others_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_week.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.others_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db_bill.close();
			db_celle.close_celle();
			db_meet.close_meet();
			db_edu.close_edu();
			db_enter.close_entr();
			db_travel.close_travel();
			db_other.close_others();

		}
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SimpleDateFormat")
	private void getMonth_event() {

		myElements_month.clear();
		final Calendar c = Calendar.getInstance();
		final Calendar c1 = Calendar.getInstance();
		final Calendar c2 = Calendar.getInstance();
		final Calendar c11 = Calendar.getInstance();
		final Calendar c12 = Calendar.getInstance();
		SimpleDateFormat formater = new java.text.SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		String day = "" + c.get(Calendar.DAY_OF_MONTH);
		String month = "" + df1.format(c.getTime());
		String year = "" + c.get(Calendar.YEAR);
		db_bill = new DataBaseAdapter(this);
		db_celle = new DataBaseAdapter(this);
		db_meet = new DataBaseAdapter(this);
		db_edu = new DataBaseAdapter(this);
		db_enter = new DataBaseAdapter(this);
		db_travel = new DataBaseAdapter(this);
		db_other = new DataBaseAdapter(this);
		try {
			db_bill.open();
			db_celle.open_celle();
			db_meet.open_meet();
			db_edu.open_edu();
			db_enter.open_entr();
			db_travel.open_travel();
			db_other.open_others();

			Cursor cur = db_bill.getAllTitles_bill();
			Cursor cur_celle = db_celle.getAllTitles_celle();
			Cursor cur_meet = db_meet.getAllTitles_meet();
			Cursor cur_edu = db_edu.getAllTitles_edu();
			Cursor cur_enter = db_enter.getAllTitles_enter();
			Cursor cur_travel = db_travel.getAllTitles_travel();
			Cursor cur_other = db_other.getAllTitles_others();

			while (cur.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur.getString(3));
					Date date2 = formater.parse(cur.getString(6));
					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur.getString(11).equalsIgnoreCase("Gregorian")
						|| cur.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur.getString(3));
					Date date2 = formater.parse(cur.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						|| c.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
					String valueoftitle = cur.getString(1);
					String valueofcat = cur.getString(2);
					String valueofsdate = cur.getString(3);
					String valueofstime = cur.getString(4);
					String valueofstime_class = cur.getString(5);
					String valueofedate = cur.getString(6);
					String valueofetime = cur.getString(7);
					String valueofetime_class = cur.getString(8);
					String valueoffreq = cur.getString(9);
					String valueofnote = cur.getString(10);
					String valueofcaltype = cur.getString(11);
					//
					if (cur.getString(9).equalsIgnoreCase("Per Month")
							|| cur.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.billing_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.billing_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}

			}
			while (cur_celle.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_celle.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_celle.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_celle.getString(3));
					Date date2 = formater.parse(cur_celle.getString(6));
					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_celle.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_celle.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_celle.getString(3));
					Date date2 = formater.parse(cur_celle.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						|| c.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
					String valueoftitle = cur_celle.getString(1);
					String valueofcat = cur_celle.getString(2);
					String valueofsdate = cur_celle.getString(3);
					String valueofstime = cur_celle.getString(4);
					String valueofstime_class = cur_celle.getString(5);
					String valueofedate = cur_celle.getString(6);
					String valueofetime = cur_celle.getString(7);
					String valueofetime_class = cur_celle.getString(8);
					String valueoffreq = cur_celle.getString(9);
					String valueofnote = cur_celle.getString(10);
					String valueofcaltype = cur_celle.getString(11);
					//
					if (cur_celle.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.cellebration_), formater
										.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.cellebration_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}

			}
			while (cur_meet.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_meet.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_meet.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_meet.getString(3));
					Date date2 = formater.parse(cur_meet.getString(6));
					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_meet.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_meet.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_meet.getString(3));
					Date date2 = formater.parse(cur_meet.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						|| c.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
					String valueoftitle = cur_meet.getString(1);
					String valueofcat = cur_meet.getString(2);
					String valueofsdate = cur_meet.getString(3);
					String valueofstime = cur_meet.getString(4);
					String valueofstime_class = cur_meet.getString(5);
					String valueofedate = cur_meet.getString(6);
					String valueofetime = cur_meet.getString(7);
					String valueofetime_class = cur_meet.getString(8);
					String valueoffreq = cur_meet.getString(9);
					String valueofnote = cur_meet.getString(10);
					String valueofcaltype = cur_meet.getString(11);
					//
					if (cur_meet.getString(9).equalsIgnoreCase("Per Month")
							|| cur_meet.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.meeting_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.meeting_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
			while (cur_edu.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_edu.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_edu.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_edu.getString(3));
					Date date2 = formater.parse(cur_edu.getString(6));
					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_edu.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_edu.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_edu.getString(3));
					Date date2 = formater.parse(cur_edu.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						|| c.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
					String valueoftitle = cur_edu.getString(1);
					String valueofcat = cur_edu.getString(2);
					String valueofsdate = cur_edu.getString(3);
					String valueofstime = cur_edu.getString(4);
					String valueofstime_class = cur_edu.getString(5);
					String valueofedate = cur_edu.getString(6);
					String valueofetime = cur_edu.getString(7);
					String valueofetime_class = cur_edu.getString(8);
					String valueoffreq = cur_edu.getString(9);
					String valueofnote = cur_edu.getString(10);
					String valueofcaltype = cur_edu.getString(11);
					//
					if (cur_edu.getString(9).equalsIgnoreCase("Per Month")
							|| cur_edu.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.education_), formater
										.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.education_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
			while (cur_enter.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_enter.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_enter.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_enter.getString(3));
					Date date2 = formater.parse(cur_enter.getString(6));
					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_enter.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_enter.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_enter.getString(3));
					Date date2 = formater.parse(cur_enter.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						|| c.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
					String valueoftitle = cur_enter.getString(1);
					String valueofcat = cur_enter.getString(2);
					String valueofsdate = cur_enter.getString(3);
					String valueofstime = cur_enter.getString(4);
					String valueofstime_class = cur_enter.getString(5);
					String valueofedate = cur_enter.getString(6);
					String valueofetime = cur_enter.getString(7);
					String valueofetime_class = cur_enter.getString(8);
					String valueofplace = cur_enter.getString(9);
					String valueoffreq = cur_enter.getString(10);
					String valueofnote = cur_enter.getString(11);
					String valueofcaltype = cur_enter.getString(12);
					//
					if (cur_enter.getString(10).equalsIgnoreCase("Per Month")
							|| cur_enter.getString(10).equalsIgnoreCase("በየወሩ")) {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.entertainment_), formater
										.format(c11.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, valueofplace, "",
								valueoffreq, valueofnote, valueofcaltype,
								getResources().getString(R.string.At), ""));
					} else {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.entertainment_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, valueofplace,
								"", valueoffreq, valueofnote, valueofcaltype,
								getResources().getString(R.string.At), ""));
					}

				}
			}
			while (cur_travel.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_travel.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_travel.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_travel.getString(3));
					Date date2 = formater.parse(cur_travel.getString(6));
					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_travel.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_travel.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_travel.getString(3));
					Date date2 = formater.parse(cur_travel.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						|| c.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
					String valueoftitle = cur_travel.getString(1);
					String valueofcat = cur_travel.getString(2);
					String valueofsdate = cur_travel.getString(3);
					String valueofstime = cur_travel.getString(4);
					String valueofstime_class = cur_travel.getString(5);
					String valueofedate = cur_travel.getString(6);
					String valueofetime = cur_travel.getString(7);
					String valueofetime_class = cur_travel.getString(8);
					String valueofsplace = cur_travel.getString(9);
					String valueofdesplace = cur_travel.getString(10);
					String valueoffreq = cur_travel.getString(11);
					String valueofnote = cur_travel.getString(12);
					String valueofcaltype = cur_travel.getString(13);
					//
					if (cur_travel.getString(11).equalsIgnoreCase("Per Month")
							|| cur_travel.getString(11)
									.equalsIgnoreCase("በየወሩ")) {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.travel_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, valueofsplace,
								valueofdesplace, valueoffreq, valueofnote,
								valueofcaltype, getResources().getString(
										R.string.from), getResources()
										.getString(R.string.To)));
					} else {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.travel_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class,
								valueofsplace, valueofdesplace, valueoffreq,
								valueofnote, valueofcaltype, getResources()
										.getString(R.string.from),
								getResources().getString(R.string.To)));
					}

				}
			}
			while (cur_other.moveToNext()) {
				Date now = formater.parse(day + "/" + month + "/" + year);
				c.setTime(now);
				String noweth = cal_type.toEthiopian(this,
						c.get(Calendar.DAY_OF_MONTH),
						Integer.parseInt(df1.format(c.getTime())),
						c.get(Calendar.YEAR));
				Date eth = formater.parse(noweth);
				if (cur_other.getString(11).equalsIgnoreCase("Ethiopian")
						|| cur_other.getString(11).equalsIgnoreCase("ኢትዮጰያን")) {
					Date date1 = formater.parse(cur_other.getString(3));
					Date date2 = formater.parse(cur_other.getString(6));
					c11.setTime(date1);
					c12.setTime(date2);
					cal_type = new Calandar();
					int d = c11.get(Calendar.DAY_OF_MONTH);
					int m = c11.get(Calendar.MONTH);
					int y = c11.get(Calendar.YEAR);
					int d1 = c12.get(Calendar.DAY_OF_MONTH);
					int m1 = c12.get(Calendar.MONTH);
					int y1 = c12.get(Calendar.YEAR);
					String sdate = cal_type.toGregorian(this, d, m + 1, y);
					String edate = cal_type.toGregorian(this, d1, m1 + 1, y1);
					Date sdate1 = formater.parse(sdate);
					Date edate2 = formater.parse(edate);
					c1.setTime(sdate1);
					c2.setTime(edate2);
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (sdate1.getMonth() == edate2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
							c11.set(Calendar.MONTH, eth.getMonth());
							c12.set(Calendar.MONTH, eth.getMonth() + 1);
						}
					}
				}
				if (cur_other.getString(11).equalsIgnoreCase("Gregorian")
						|| cur_other.getString(11).equalsIgnoreCase("ግሪጎርያን")) {
					Date date1 = formater.parse(cur_other.getString(3));
					Date date2 = formater.parse(cur_other.getString(6));
					c1.setTime(date1);
					c2.setTime(date2);
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_celle.getString(9).equalsIgnoreCase("በየወሩ")) {
						if (date1.getMonth() == date2.getMonth()) {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth());
						} else {
							c1.set(Calendar.MONTH, now.getMonth());
							c2.set(Calendar.MONTH, now.getMonth() + 1);
						}
					}
				}

				if (c.get(Calendar.MONTH) == c1.get(Calendar.MONTH)
						|| c.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {
					String valueoftitle = cur_other.getString(1);
					String valueofcat = cur_other.getString(2);
					String valueofsdate = cur_other.getString(3);
					String valueofstime = cur_other.getString(4);
					String valueofstime_class = cur_other.getString(5);
					String valueofedate = cur_other.getString(6);
					String valueofetime = cur_other.getString(7);
					String valueofetime_class = cur_other.getString(8);
					String valueoffreq = cur_other.getString(9);
					String valueofnote = cur_other.getString(10);
					String valueofcaltype = cur_other.getString(11);
					//
					if (cur_other.getString(9).equalsIgnoreCase("Per Month")
							|| cur_other.getString(9).equalsIgnoreCase("በየወሩ")) {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.others_), formater.format(c11
										.getTime()), valueofstime,
								valueofstime_class, formater.format(c12
										.getTime()), valueofetime,
								valueofetime_class, "", "", valueoffreq,
								valueofnote, valueofcaltype, "", ""));
					} else {
						myElements_month.add(new Elements(valueoftitle,
								valueofcat, getResources().getString(
										R.string.others_), valueofsdate,
								valueofstime, valueofstime_class, valueofedate,
								valueofetime, valueofetime_class, "", "",
								valueoffreq, valueofnote, valueofcaltype, "",
								""));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db_bill.close();
			db_celle.close_celle();
			db_meet.close_meet();
			db_edu.close_edu();
			db_enter.close_entr();
			db_travel.close_travel();
			db_other.close_others();

		}

	}

	private void setDataIntoList() {

		// today list adapter
		ArrayAdapter<Elements> adapter_today = new ElementAdapter();
		ListView eventList_today = (ListView) findViewById(R.id.todaylistview);
		eventList_today.setAdapter(adapter_today);

		// week list adapter
		ArrayAdapter<Elements> adapter_week = new ElementAdapter_week();
		ListView eventList_week = (ListView) findViewById(R.id.thisweekevent);
		eventList_week.setAdapter(adapter_week);
		// month list adapter
		ArrayAdapter<Elements> adapter_month = new ElementAdapter_mon();
		ListView eventList_month = (ListView) findViewById(R.id.thismonthevent);
		eventList_month.setAdapter(adapter_month);
	}

	private class ElementAdapter extends ArrayAdapter<Elements> {

		public ElementAdapter() {
			super(Main.this, R.layout.eventlist_view, myElements);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View elementView = convertView;
			if (elementView == null) {
				elementView = getLayoutInflater().inflate(
						R.layout.eventlist_view, parent, false);
			}
			Elements currentElement = myElements.get(position);
			TextView CatText = (TextView) elementView
					.findViewById(R.id.textView1);
			CatText.setText(currentElement.getCategory());
			TextView BingCatText = (TextView) elementView
					.findViewById(R.id.textView15);
			BingCatText.setText(currentElement.getBig_category());
			TextView TitleText = (TextView) elementView
					.findViewById(R.id.textView2);
			TitleText.setText(currentElement.getTitle() + "  ");
			TextView sDateText = (TextView) elementView
					.findViewById(R.id.textView3);
			sDateText.setText(currentElement.getStartingDate());
			TextView eDateText = (TextView) elementView
					.findViewById(R.id.textView4);
			eDateText.setText(currentElement.getEndingDate());
			TextView sTimeText = (TextView) elementView
					.findViewById(R.id.textView6);
			sTimeText.setText(currentElement.getStartingTime() + "  "
					+ currentElement.getStartingTime_class());
			TextView eTimeText = (TextView) elementView
					.findViewById(R.id.textView7);
			eTimeText.setText(currentElement.getEndingTime() + "  "
					+ currentElement.getEndingTime_class());
			TextView fromText = (TextView) elementView
					.findViewById(R.id.textView_from);
			fromText.setText(currentElement.getFrom());
			TextView fromplace = (TextView) elementView
					.findViewById(R.id.from_place);
			fromplace.setText(currentElement.getS_place());
			TextView toText = (TextView) elementView
					.findViewById(R.id.textView_to);
			toText.setText(currentElement.getTo());
			TextView desplace = (TextView) elementView
					.findViewById(R.id.to_place);
			desplace.setText(currentElement.getDes_place());
			return elementView;
		}

	}

	private class ElementAdapter_mon extends ArrayAdapter<Elements> {

		public ElementAdapter_mon() {
			super(Main.this, R.layout.eventlist_view, myElements_month);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View elementView = convertView;
			if (elementView == null) {
				elementView = getLayoutInflater().inflate(
						R.layout.eventlist_view, parent, false);
			}
			Elements currentElement = myElements_month.get(position);
			TextView CatText = (TextView) elementView
					.findViewById(R.id.textView1);
			CatText.setText(currentElement.getCategory());
			TextView BingCatText = (TextView) elementView
					.findViewById(R.id.textView15);
			BingCatText.setText(currentElement.getBig_category());
			TextView TitleText = (TextView) elementView
					.findViewById(R.id.textView2);
			TitleText.setText(currentElement.getTitle() + "  ");
			TextView sDateText = (TextView) elementView
					.findViewById(R.id.textView3);
			sDateText.setText(currentElement.getStartingDate());
			TextView eDateText = (TextView) elementView
					.findViewById(R.id.textView4);
			eDateText.setText(currentElement.getEndingDate());
			TextView sTimeText = (TextView) elementView
					.findViewById(R.id.textView6);
			sTimeText.setText(currentElement.getStartingTime() + "  "
					+ currentElement.getStartingTime_class());
			TextView eTimeText = (TextView) elementView
					.findViewById(R.id.textView7);
			eTimeText.setText(currentElement.getEndingTime() + "  "
					+ currentElement.getEndingTime_class());
			TextView fromText = (TextView) elementView
					.findViewById(R.id.textView_from);
			fromText.setText(currentElement.getFrom());
			TextView fromplace = (TextView) elementView
					.findViewById(R.id.from_place);
			fromplace.setText(currentElement.getS_place());
			TextView toText = (TextView) elementView
					.findViewById(R.id.textView_to);
			toText.setText(currentElement.getTo());
			TextView desplace = (TextView) elementView
					.findViewById(R.id.to_place);
			desplace.setText(currentElement.getDes_place());
			return elementView;
		}

	}

	private class ElementAdapter_week extends ArrayAdapter<Elements> {

		public ElementAdapter_week() {
			super(Main.this, R.layout.eventlist_view, myElements_week);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View elementView = convertView;
			if (elementView == null) {
				elementView = getLayoutInflater().inflate(
						R.layout.eventlist_view, parent, false);
			}
			Elements currentElement = myElements_week.get(position);
			TextView CatText = (TextView) elementView
					.findViewById(R.id.textView1);
			CatText.setText(currentElement.getCategory());
			TextView BingCatText = (TextView) elementView
					.findViewById(R.id.textView15);
			BingCatText.setText(currentElement.getBig_category());
			TextView TitleText = (TextView) elementView
					.findViewById(R.id.textView2);
			TitleText.setText(currentElement.getTitle() + "  ");
			TextView sDateText = (TextView) elementView
					.findViewById(R.id.textView3);
			sDateText.setText(currentElement.getStartingDate());
			TextView eDateText = (TextView) elementView
					.findViewById(R.id.textView4);
			eDateText.setText(currentElement.getEndingDate());
			TextView sTimeText = (TextView) elementView
					.findViewById(R.id.textView6);
			sTimeText.setText(currentElement.getStartingTime() + "  "
					+ currentElement.getStartingTime_class());
			TextView eTimeText = (TextView) elementView
					.findViewById(R.id.textView7);
			eTimeText.setText(currentElement.getEndingTime() + "  "
					+ currentElement.getEndingTime_class());
			TextView fromText = (TextView) elementView
					.findViewById(R.id.textView_from);
			fromText.setText(currentElement.getFrom());
			TextView fromplace = (TextView) elementView
					.findViewById(R.id.from_place);
			fromplace.setText(currentElement.getS_place());
			TextView toText = (TextView) elementView
					.findViewById(R.id.textView_to);
			toText.setText(currentElement.getTo());
			TextView desplace = (TextView) elementView
					.findViewById(R.id.to_place);
			desplace.setText(currentElement.getDes_place());
			return elementView;
		}

	}

	// ++++++++++++++++++++++++++++++++
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sample, menu);
		return true;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Resources res = getResources();
		String current = res.getConfiguration().locale.getCountry();
		String localeString = new String(current);
		switch (item.getItemId()) {
		case R.id.Quit:
			// Single menu item is selected do something
			// Ex: launching new activity/screen or show alert message
			finish();
			return true;

		case R.id.About:
			// Toast.makeText(Main.this, "about is Selected",
			// Toast.LENGTH_SHORT)
			// .show();
			Intent int_about_ = new Intent(Main.this, About.class);
			startActivity(int_about_);
			return true;

		case R.id.Help:

			/*Intent int_help_ = new Intent(Main.this, Help.class);
			startActivity(int_help_);*/
			Intent int_help_ = new Intent(Main.this, Help.class);
			startActivity(int_help_);
			return true;

		case R.id.english:
			localeString = "en";
			if (!current.equalsIgnoreCase(localeString)
					&& localeString.length() > 0) {
				// Change locale settings in the app.
				DisplayMetrics dm = res.getDisplayMetrics();
				android.content.res.Configuration conf = res.getConfiguration();
				conf.locale = new Locale(localeString.toLowerCase(Locale
						.getDefault()));
				res.updateConfiguration(conf, dm);
				reload(root);
				// refresh menu
				/*
				 * setGridView(); //added this line to get refreshed listener
				 * changeLanguageListener();
				 */
			}
			return true;

		case R.id.amharic:
			localeString = "am";
			if (!current.equalsIgnoreCase(localeString)
					&& localeString.length() > 0) {
				// Change locale settings in the app.
				DisplayMetrics dm = res.getDisplayMetrics();
				android.content.res.Configuration conf = res.getConfiguration();
				conf.locale = new Locale(localeString.toLowerCase(Locale
						.getDefault()));
				res.updateConfiguration(conf, dm);
				reload(root);
				// refresh menu
				/*
				 * setGridView(); //added this line to get refreshed listener
				 * changeLanguageListener();
				 */
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

		String selectedItemText = ((TextView) info.targetView).getText()
				.toString();
		if (selectedItemText.equalsIgnoreCase("Report")
				|| selectedItemText.equalsIgnoreCase("ሪፖርት")) {
			new MenuInflater(this).inflate(R.menu.context, menu);
			MenuItem myLocationMenuItem = menu.findItem(R.id.add);
			myLocationMenuItem.setVisible(false);
			MenuItem myLocationMenuItem1 = menu.findItem(R.id.date_convert);
			myLocationMenuItem1.setVisible(false);
		} else if (selectedItemText.equalsIgnoreCase("Calander")
				|| selectedItemText.equalsIgnoreCase("ጊዜ መቁጠሪያ")) {
			new MenuInflater(this).inflate(R.menu.context, menu);
			MenuItem myLocationMenuItem = menu.findItem(R.id.add);
			myLocationMenuItem.setVisible(false);
		} else {

			new MenuInflater(this).inflate(R.menu.context, menu);
			MenuItem myLocationMenuItem2 = menu.findItem(R.id.date_convert);
			myLocationMenuItem2.setVisible(false);
		}

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.add:
			int pos = info.position;
			switch (pos) {
			case 1:
				Intent int4 = new Intent(Main.this, Billing.class);
				int4.putExtra("num", 1);
				startActivity(int4);

				break;
			case 2:
				Intent int3 = new Intent(Main.this, Meeting.class);
				int3.putExtra("num", 1);
				startActivity(int3);

				break;
			case 3:
				Intent int1 = new Intent(Main.this, Cellebration.class);
				int1.putExtra("num", 1);
				startActivity(int1);

				break;
			case 4:
				Intent int2 = new Intent(Main.this, Education.class);
				int2.putExtra("num", 1);
				startActivity(int2);

				break;
			case 5:
				Intent int5 = new Intent(Main.this, Entertainment.class);
				int5.putExtra("num", 1);
				startActivity(int5);

				break;
			case 6:
				Intent int6 = new Intent(Main.this, Travel.class);
				int6.putExtra("num", 1);
				startActivity(int6);

				break;
			case 7:
				Intent int7 = new Intent(Main.this, Others.class);
				int7.putExtra("num", 1);
				startActivity(int7);

				break;
			case 9:
				Intent int9 = new Intent(Main.this, Budget.class);
				startActivity(int9);
				break;
			case 10:
				Intent int10 = new Intent(Main.this, Income.class);
				startActivity(int10);
				break;
			case 11:
				Intent int11 = new Intent(Main.this, Expense.class);
				startActivity(int11);
				break;
			case 15:
				Intent int15 = new Intent(Main.this, Note.class);
				Bundle b2 = new Bundle();
				b2.putInt("key", 1);
				int15.putExtras(b2);
				startActivity(int15);
				break;

			}

			return (true);

		case R.id.show:
			int pos1 = info.position;
			switch (pos1) {
			case 1:
				Intent int1 = new Intent(Main.this, Display.class);
				Bundle b = new Bundle();
				b.putInt("key", pos1);
				int1.putExtras(b);
				startActivity(int1);
				break;
			case 2:
				Intent int2 = new Intent(Main.this, Display.class);
				Bundle b2 = new Bundle();
				b2.putInt("key", pos1);
				int2.putExtras(b2);
				startActivity(int2);
				break;
			case 3:
				Intent int3 = new Intent(Main.this, Display.class);
				Bundle b3 = new Bundle();
				b3.putInt("key", pos1);
				int3.putExtras(b3);
				startActivity(int3);
				break;
			case 4:
				Intent int4 = new Intent(Main.this, Display.class);
				Bundle b4 = new Bundle();
				b4.putInt("key", pos1);
				int4.putExtras(b4);
				startActivity(int4);
				break;
			case 5:
				Intent int5 = new Intent(Main.this, Display.class);
				Bundle b5 = new Bundle();
				b5.putInt("key", pos1);
				int5.putExtras(b5);
				startActivity(int5);
				break;
			case 6:
				Intent int6 = new Intent(Main.this, Display.class);
				Bundle b6 = new Bundle();
				b6.putInt("key", pos1);
				int6.putExtras(b6);
				startActivity(int6);
				break;
			case 7:
				Intent int7 = new Intent(Main.this, Display.class);
				Bundle b7 = new Bundle();
				b7.putInt("key", pos1);
				int7.putExtras(b7);
				startActivity(int7);
				break;
			case 9:
				Intent int9 = new Intent(Main.this, Budget_Show.class);
				startActivity(int9);
				break;
			case 10:
				Intent int10 = new Intent(Main.this, Income_show.class);
				startActivity(int10);
				break;
			case 11:
				Intent int11 = new Intent(Main.this, Expense_show.class);
				startActivity(int11);
				break;
			case 12:
				Intent int12 = new Intent(Main.this, Report.class);
				startActivity(int12);
				break;
			case 14:
				Intent intent = getPackageManager().getLaunchIntentForPackage(
						"com.android.calendar");

				startActivity(intent);
				break;
			case 15:
				Intent int15 = new Intent(Main.this, Note_Show.class);
				startActivity(int15);
				break;
			}

			return (true);
		case R.id.date_convert:
			int pos3 = info.position;
			switch (pos3) {
			case 14:
				Intent intcal = new Intent(Main.this,
						com.example.agenda.Calandar.class);
				startActivity(intcal);
				break;
			}
			return (true);
		}

		return (super.onContextItemSelected(item));
	}

	public void toggleMenu(View v) {
		v.clearAnimation();
		this.root.toggleMenu();
	}

	public void reload(View v) {
		Intent refresh = getIntent();
		finish();
		startActivity(refresh);
	}

	private class myListAdapter extends ArrayAdapter<String> {
		public myListAdapter() {
			super(Main.this, R.layout.item_list, myItems);
		}

		private static final int TYPE_TITLE = 0;
		private static final int TYPE_IITLE_1 = 1;
		private static final int TYPE_ITEM = 2;
		private static final int TYPE_IITLE_2 = 3;

		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			if (myItems[position].equalsIgnoreCase("Finance")
					|| myItems[position].equalsIgnoreCase("ፋይናንስ")) {
				return TYPE_TITLE;
			} else if (myItems[position].equalsIgnoreCase("Tools")
					|| myItems[position].equalsIgnoreCase("አጋዠ መሳርያ")) {
				return TYPE_IITLE_1;
			} else if (myItems[position].equalsIgnoreCase("General")
					|| myItems[position].equalsIgnoreCase("አጠቃላይ")) {
				return TYPE_IITLE_2;
			} else {
				return TYPE_ITEM;
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View Itemview = convertView;
			ViewHolder holder = null;
			int type = getItemViewType(position);
			// if (Itemview == null) {
			holder = new ViewHolder();
			switch (type) {
			case TYPE_IITLE_2:
				Itemview = getLayoutInflater().inflate(R.layout.title_2,
						parent, false);
				holder.textView = (TextView) Itemview
						.findViewById(R.id.title_2);
				holder.textView.setText(myItems[position]);
				break;
			case TYPE_TITLE:
				Itemview = getLayoutInflater().inflate(R.layout.title, parent,
						false);
				holder.textView = (TextView) Itemview.findViewById(R.id.title);
				holder.textView.setText(myItems[position]);
				break;
			case TYPE_IITLE_1:
				Itemview = getLayoutInflater().inflate(R.layout.title_1,
						parent, false);
				holder.textView = (TextView) Itemview
						.findViewById(R.id.title_1);
				holder.textView.setText(myItems[position]);
				break;
			case TYPE_ITEM:
				Itemview = getLayoutInflater().inflate(R.layout.item_list,
						parent, false);
				holder.textView = (TextView) Itemview
						.findViewById(R.id.item_list);
				holder.textView.setText(myItems[position]);
				break;
			}

			Itemview.setTag(holder);

			return Itemview;
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myItems.length;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public static class ViewHolder {
		public TextView textView;
	}
}
