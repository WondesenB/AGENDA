package com.example.agenda;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.agenda.view.viewgroup.DataBaseAdapter;

public class Meeting extends Activity {
	private Spinner cat_spin;
	private Spinner fre_spin;
	private Spinner caltype_spin;
	private EditText title_edit;
	private EditText startdate_edit;
	private EditText starttime_edit;
	private Spinner Timr1_spin;
	private EditText endedate_edit;
	private EditText endetime_edit;
	private Spinner Timr2_spin;
	private EditText note_edit;
	private Button add_btn;
	private DataBaseAdapter db;
	//
	String [] li=null;
	String [] Ti=null;
	String [] dt=null;
	String [] fr=null;
	
	Spinner sp1, sp2, sp3, sp4;
	//
	private int num;
	private String tit_;
	private String categ_;
	private String sDate_;
	private String sTime_;
	private String sTclass_;
	private String eDate_;
	private String eTime_;
	private String eTclass_;

	private String freq_;
	private String nott_;
    private String caltype_;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		//
		Intent b = getIntent();
		num = b.getIntExtra("num", 0);
		tit_ = b.getStringExtra("title");
		categ_ = b.getStringExtra("category");
		sDate_ = b.getStringExtra("startingDate");
		sTime_ = b.getStringExtra("startingTime");
		sTclass_ = b.getStringExtra("startingTime_class");
		eDate_ = b.getStringExtra("endingDate");
		eTime_ = b.getStringExtra("endingTime");
		eTclass_ = b.getStringExtra("endingTime_class");
		freq_ = b.getStringExtra("frequency");
		nott_ = b.getStringExtra("note");
		caltype_ = b.getStringExtra("caltype");
		//
		addItem();
		call();
		add_items();
	}

	// ++++++++++++++++++++++++++++++++++++++
	private void add_items() {
		// TODO Auto-generated method stub
		add_btn = (Button) findViewById(R.id.bill_add_button);
		title_edit = (EditText) findViewById(R.id.bill_edit_title);
		startdate_edit = (EditText) findViewById(R.id.bill_edit_startdate);
		starttime_edit = (EditText) findViewById(R.id.bill_edit_starttime);
		endedate_edit = (EditText) findViewById(R.id.bill_edit_enddate);
		endetime_edit = (EditText) findViewById(R.id.bill_edit_endtime);
		note_edit = (EditText) findViewById(R.id.bill_edit_note);
		cat_spin = (Spinner) findViewById(R.id.bill_cat_spin);
		fre_spin = (Spinner) findViewById(R.id.bill_fre_spin);
		Timr1_spin = (Spinner) findViewById(R.id.bill_Timr1_spin);
		Timr2_spin = (Spinner) findViewById(R.id.bill_Time2_spin);
		caltype_spin = (Spinner) findViewById(R.id.bill_caleType_spin);
		if (num == 2) {
			title_edit.setText(tit_);
			@SuppressWarnings("unchecked")
			ArrayAdapter<String> myad = (ArrayAdapter<String>) cat_spin
					.getAdapter();
			int p = myad.getPosition(categ_);
			cat_spin.setSelection(p);
			startdate_edit.setText(sDate_);
			starttime_edit.setText(sTime_);
			@SuppressWarnings("unchecked")
			ArrayAdapter<String> myad1 = (ArrayAdapter<String>) Timr1_spin
					.getAdapter();
			int p1 = myad1.getPosition(sTclass_);
			Timr1_spin.setSelection(p1);
			endedate_edit.setText(eDate_);
			endetime_edit.setText(eTime_);
			@SuppressWarnings("unchecked")
			ArrayAdapter<String> myad2 = (ArrayAdapter<String>) Timr2_spin
					.getAdapter();
			int p2 = myad2.getPosition(eTclass_);
			Timr2_spin.setSelection(p2);
			@SuppressWarnings("unchecked")
			ArrayAdapter<String> myad3 = (ArrayAdapter<String>) fre_spin
					.getAdapter();
			int p3 = myad3.getPosition(freq_);
			fre_spin.setSelection(p3);
			note_edit.setText(nott_);
			@SuppressWarnings("unchecked")
			ArrayAdapter<String> myad4 = (ArrayAdapter<String>) caltype_spin
					.getAdapter();
			int p4 = myad4.getPosition(caltype_);
			caltype_spin.setSelection(p4);
			add_btn.setText(getResources().getString(R.string.update));
		}
		add_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (num == 1) {
					submitData();
				}

				if (num == 2) {
					updateData();
					finish();
				}

			}
		});
	}

	protected void submitData() {
		String ti = title_edit.getText().toString();
		String cat = cat_spin.getSelectedItem().toString();
		String sdate = startdate_edit.getText().toString();
		String stime = starttime_edit.getText().toString();
		String stime_class = Timr1_spin.getSelectedItem().toString();
		String edate = endedate_edit.getText().toString();
		String etime = endetime_edit.getText().toString();
		String etime_class = Timr2_spin.getSelectedItem().toString();
		String fr = fre_spin.getSelectedItem().toString();
		String note = note_edit.getText().toString();
        String caltype=caltype_spin.getSelectedItem().toString();
		db = new DataBaseAdapter(this);
		long num;
		try {
			db.open_meet();
			num = db.addmeet_info(ti, cat, sdate, stime, stime_class, edate,
					etime, etime_class, fr, note,caltype);
			db.close_meet();
		} catch (SQLException e) {
			num = -5;
		} finally {
			db.close_meet();
		}

		if (num > 0) {
			Toast.makeText(this,getResources().getString(R.string.meet_event_added) , 2000).show();
			title_edit.setText("");
			startdate_edit.setText("");
			starttime_edit.setText("");
			endedate_edit.setText("");
			endetime_edit.setText("");
			note_edit.setText("");
		}

		else if (num == -1)
			Toast.makeText(this, getResources().getString(R.string.error_dublicat), 4000).show();
		else
			Toast.makeText(this, getResources().getString(R.string.error_insert), 2000).show();

	}
	private void updateData() {
		String ti = title_edit.getText().toString();
		String cat = cat_spin.getSelectedItem().toString();
		String sdate = startdate_edit.getText().toString();
		String stime = starttime_edit.getText().toString();
		String stime_class = Timr1_spin.getSelectedItem().toString();
		String edate = endedate_edit.getText().toString();
		String etime = endetime_edit.getText().toString();
		String etime_class = Timr2_spin.getSelectedItem().toString();
		String fr = fre_spin.getSelectedItem().toString();
		String note = note_edit.getText().toString();
		String caltype=caltype_spin.getSelectedItem().toString();
		db = new DataBaseAdapter(this);
		try {
			db.open_meet();
			db.updateRow_meet(ti, cat, sdate, stime, stime_class, edate,
					etime, etime_class, fr, note,caltype, tit_, categ_, sDate_, sTime_, sTclass_,
					eDate_, eTime_, eTclass_, freq_, nott_);
			db.close_meet();
		} catch (SQLException e) {
			num = -5;
		} finally {
			db.close_meet();
			Toast.makeText(this, getResources().getString(R.string.updated), 2000).show();
		}

	}
	// ++++++++++++++++++++++++++++++++++++++
	private void call() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, li);
		final ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, Ti);
		ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, fr);
		final ArrayAdapter<String> adp3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, dt);
		adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		adp1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		adp2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		adp3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp1.setAdapter(adp);
		caltype_spin = (Spinner) findViewById(R.id.bill_caleType_spin);
		sp2.setAdapter(adp3);
		sp3.setAdapter(adp3);
		 caltype_spin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					if(caltype_spin.getSelectedItem().toString().equalsIgnoreCase("Ethiopian")
							||caltype_spin.getSelectedItem().toString().equalsIgnoreCase("ኢትዮጰያን"))
					{
						sp2.setAdapter(adp3);
						sp3.setAdapter(adp3);
					}
					if(caltype_spin.getSelectedItem().toString().equalsIgnoreCase("Gregorian")
							||caltype_spin.getSelectedItem().toString().equalsIgnoreCase("ግሪጎርያን"))
					{
						sp2.setAdapter(adp1);
						sp3.setAdapter(adp1);
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		sp4.setAdapter(adp2);
	}

	private void addItem() {
		// TODO Auto-generated method stub
		li = getResources().getStringArray(R.array.meet_categroy);
		Ti = getResources().getStringArray(R.array.am_pm);
		dt = getResources().getStringArray(R.array.daytime);
		fr = getResources().getStringArray(R.array.frequency);
	
		sp1 = (Spinner) findViewById(R.id.bill_cat_spin);
		sp2 = (Spinner) findViewById(R.id.bill_Timr1_spin);
		sp3 = (Spinner) findViewById(R.id.bill_Time2_spin);
		sp4 = (Spinner) findViewById(R.id.bill_fre_spin);
		
	}

}
