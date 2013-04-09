package com.example.agenda;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Calandar extends Activity {

	EditText dy;
	EditText mz;
	EditText yr;
	EditText dat;
	Button conv;
	Spinner caltype;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convert_calendar);
		populate();

	}

	public void populate() {
		dy = (EditText) findViewById(R.id.day_ethio);
		mz = (EditText) findViewById(R.id.month_ethio);
		yr = (EditText) findViewById(R.id.year_ethio);
		dat = (EditText) findViewById(R.id.dat_con);
		conv = (Button) findViewById(R.id.convert);
		caltype = (Spinner) findViewById(R.id.spinner1);
		conv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!dy.getText().toString().equalsIgnoreCase("") && !mz.getText().toString().equalsIgnoreCase("") && !yr.getText().toString().equalsIgnoreCase(""))
				{
				dat.setText(Convert(Integer.parseInt(dy.getText().toString()),
						Integer.parseInt(mz.getText().toString()), Integer
								.parseInt(yr.getText().toString()), caltype
								.getSelectedItem().toString()));
				}
				else
				{
					dat.setText("please enter the correct value");
				}
			}
		});
	}

	public String Convert(int day, int mon, int yr, String calendartype) {
		if (calendartype.equalsIgnoreCase("Ethiopian") ||calendartype.equalsIgnoreCase("ኢትዮጰያን")) {
			return toEthiopian(this,day, mon, yr);
		} else if (calendartype.equalsIgnoreCase("Gregorian") ||calendartype.equalsIgnoreCase("ግሪጎርያን")) {
			return toGregorian(this,day, mon, yr);
		} else {
			return null;
		}
	}

	public String toGregorian(Context context,int day, int month, int year) {
		int day_G = 0;
		int month_G = 0;
		int year_G = 0;
		String date = null;
		if (year % 4 != 0) {
			if (month == 1 && (day >= 1 && day <= 20)) {
				day_G = day + 10;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 1 && (day >= 21 && day <= 30)) {
				day_G = day - 20;
				month_G = month + 9;
				year_G = year + 7;
			} else if (month == 2 && (day >= 1 && day <= 21)) {
				day_G = day + 10;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 2 && (day >= 22 && day <= 30)) {
				day_G = day - 21;
				month_G = month + 9;
				year_G = year + 7;
			} else if (month == 3 && (day >= 1 && day <= 21)) {
				day_G = day + 9;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 3 && (day >= 22 && day <= 30)) {
				day_G = day - 21;
				month_G = month + 9;
				year_G = year + 7;
			} else if (month == 4 && (day >= 1 && day <= 22)) {
				day_G = day + 9;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 4 && (day >= 23 && day <= 30)) {
				day_G = day - 22;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 5 && (day >= 1 && day <= 23)) {
				day_G = day + 8;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 5 && (day >= 24 && day <= 30)) {
				day_G = day - 23;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 6 && (day >= 1 && day <= 21)) {
				day_G = day + 7;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 6 && (day >= 22 && day <= 30)) {
				day_G = day - 21;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 7 && (day >= 1 && day <= 22)) {
				day_G = day + 9;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 7 && (day >= 23 && day <= 30)) {
				day_G = day - 22;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 8 && (day >= 1 && day <= 22)) {
				day_G = day + 8;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 8 && (day >= 23 && day <= 30)) {
				day_G = day - 22;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 9 && (day >= 1 && day <= 23)) {
				day_G = day + 8;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 9 && (day >= 24 && day <= 30)) {
				day_G = day - 23;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 10 && (day >= 1 && day <= 23)) {
				day_G = day + 7;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 10 && (day >= 24 && day <= 30)) {
				day_G = day - 23;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 11 && (day >= 1 && day <= 24)) {
				day_G = day + 7;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 11 && (day >= 25 && day <= 30)) {
				day_G = day - 24;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 12 && (day >= 1 && day <= 25)) {
				day_G = day + 6;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 12 && (day >= 26 && day <= 30)) {
				day_G = day - 25;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 13 && (year + 1) % 4 == 0
					&& (day >= 1 && day <= 6)) {
				day_G = day + 5;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 13 && (year + 1) % 4 != 0
					&& (day >= 1 && day <= 5)) {
				day_G = day + 5;
				month_G = month - 4;
				year_G = year + 8;
			} else {
				Toast.makeText(context, "Please enter the correct date",
						Toast.LENGTH_LONG).show();
			}

		} else if (year % 4 == 0) {
			if (month == 1 && (day >= 1 && day <= 19)) {
				day_G = day + 11;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 1 && (day >= 20 && day <= 30)) {
				day_G = day - 19;
				month_G = month + 9;
				year_G = year + 7;
			} else if (month == 2 && (day >= 1 && day <= 20)) {
				day_G = day + 11;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 2 && (day >= 21 && day <= 30)) {
				day_G = day - 20;
				month_G = month + 9;
				year_G = year + 7;
			} else if (month == 3 && (day >= 1 && day <= 20)) {
				day_G = day + 10;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 3 && (day >= 21 && day <= 30)) {
				day_G = day - 20;
				month_G = month + 9;
				year_G = year + 7;
			} else if (month == 4 && (day >= 1 && day <= 21)) {
				day_G = day + 10;
				month_G = month + 8;
				year_G = year + 7;
			} else if (month == 4 && (day >= 22 && day <= 30)) {
				day_G = day - 21;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 5 && (day >= 1 && day <= 22)) {
				day_G = day + 9;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 5 && (day >= 23 && day <= 30)) {
				day_G = day - 22;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 6 && (day >= 1 && day <= 21)) {
				day_G = day + 8;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 6 && (day >= 22 && day <= 30)) {
				day_G = day - 21;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 7 && (day >= 1 && day <= 22)) {
				day_G = day + 9;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 7 && (day >= 23 && day <= 30)) {
				day_G = day - 22;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 8 && (day >= 1 && day <= 22)) {
				day_G = day + 8;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 8 && (day >= 23 && day <= 30)) {
				day_G = day - 22;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 9 && (day >= 1 && day <= 23)) {
				day_G = day + 8;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 9 && (day >= 24 && day <= 30)) {
				day_G = day - 23;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 10 && (day >= 1 && day <= 23)) {
				day_G = day + 7;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 10 && (day >= 24 && day <= 30)) {
				day_G = day - 23;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 11 && (day >= 1 && day <= 24)) {
				day_G = day + 7;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 11 && (day >= 25 && day <= 30)) {
				day_G = day - 24;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 12 && (day >= 1 && day <= 25)) {
				day_G = day + 6;
				month_G = month - 4;
				year_G = year + 8;
			} else if (month == 12 && (day >= 26 && day <= 30)) {
				day_G = day - 25;
				month_G = month - 3;
				year_G = year + 8;
			} else if (month == 13 && (day >= 1 && day <= 5)) {
				day_G = day + 5;
				month_G = month - 4;
				year_G = year + 8;
			} else {
				Toast.makeText(context, "Please enter the correct date",
						Toast.LENGTH_LONG).show();
			}

		}
		date = day_G + "/" + month_G + "/" + year_G;
		return date;
	}

	public String toEthiopian(Context context,int day, int month, int year) {
		int day_E = 0;
		int month_E = 0;
		int year_E = 0;
		String date = null;
		if (year % 4 != 0) {
			if (month == 1 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 1 && (day >= 9 && day <= 31)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 2 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 2 && (day >= 8 && day <= 28)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 3 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 3 && (day >= 10 && day <= 31)) {
				day_E = day - 9;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 4 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 4 && (day >= 9 && day <= 30)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 5 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 5 && (day >= 9 && day <= 31)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 6 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 6 && (day >= 8 && day <= 30)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 7 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 7 && (day >= 8 && day <= 31)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 8 && (day >= 1 && day <= 6)) {
				day_E = day + 24;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 8 && (day >= 7 && day <= 31)) {
				day_E = day - 6;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 9 && (day >= 1 && day <= 5)) {
				day_E = day + 25;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 9 && (day >= 6 && day <= 10)) {
				day_E = day - 5;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 9 && (day >= 11 && day <= 30)) {
				day_E = day - 10;
				month_E = month - 8;
				year_E = year - 7;
			} else if (month == 10 && (day >= 1 && day <= 10)) {
				day_E = day + 20;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 10 && (day >= 11 && day <= 31)) {
				day_E = day - 10;
				month_E = month - 8;
				year_E = year - 8;
			} else if (month == 11 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 11 && (day >= 10 && day <= 30)) {
				day_E = day - 9;
				month_E = month - 8;
				year_E = year - 7;
			} else if (month == 12 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 12 && (day >= 10 && day <= 31)) {
				day_E = day - 9;
				month_E = month - 8;
				year_E = year - 7;
			} else {
				Toast.makeText(context, "Please enter the correct date",
						Toast.LENGTH_LONG).show();
			}
		}
		if ((year + 1) % 4 == 0) {
			if (month == 1 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 1 && (day >= 9 && day <= 31)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 2 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 2 && (day >= 8 && day <= 28)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 3 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 3 && (day >= 10 && day <= 31)) {
				day_E = day - 9;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 4 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 4 && (day >= 9 && day <= 30)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 5 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 5 && (day >= 9 && day <= 31)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 6 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 6 && (day >= 8 && day <= 30)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 7 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 7 && (day >= 8 && day <= 31)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 8 && (day >= 1 && day <= 6)) {
				day_E = day + 24;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 8 && (day >= 7 && day <= 31)) {
				day_E = day - 6;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 9 && (day >= 1 && day <= 5)) {
				day_E = day + 25;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 9 && (day >= 6 && day <= 11)) {
				day_E = day - 5;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 9 && (day >= 12 && day <= 30)) {
				day_E = day - 11;
				month_E = month - 8;
				year_E = year - 7;
			} else if (month == 10 && (day >= 1 && day <= 11)) {
				day_E = day + 19;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 10 && (day >= 12 && day <= 31)) {
				day_E = day - 11;
				month_E = month - 8;
				year_E = year - 8;
			} else if (month == 11 && (day >= 1 && day <= 10)) {
				day_E = day + 20;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 11 && (day >= 11 && day <= 30)) {
				day_E = day - 10;
				month_E = month - 8;
				year_E = year - 7;
			} else if (month == 12 && (day >= 1 && day <= 10)) {
				day_E = day + 20;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 12 && (day >= 11 && day <= 31)) {
				day_E = day - 10;
				month_E = month - 8;
				year_E = year - 7;
			} else {
				Toast.makeText(context, "Please enter the correct date",
						Toast.LENGTH_LONG).show();
			}
		} else if (year % 4 == 0) {
			if (month == 1 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 1 && (day >= 10 && day <= 31)) {
				day_E = day - 9;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 2 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 2 && (day >= 9 && day <= 29)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 3 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 3 && (day >= 10 && day <= 31)) {
				day_E = day - 9;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 4 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 4 && (day >= 9 && day <= 30)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 5 && (day >= 1 && day <= 8)) {
				day_E = day + 22;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 5 && (day >= 9 && day <= 31)) {
				day_E = day - 8;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 6 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 6 && (day >= 8 && day <= 30)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 7 && (day >= 1 && day <= 7)) {
				day_E = day + 23;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 7 && (day >= 8 && day <= 31)) {
				day_E = day - 7;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 8 && (day >= 1 && day <= 6)) {
				day_E = day + 24;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 8 && (day >= 7 && day <= 31)) {
				day_E = day - 6;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 9 && (day >= 1 && day <= 5)) {
				day_E = day + 25;
				month_E = month + 3;
				year_E = year - 8;
			} else if (month == 9 && (day >= 6 && day <= 10)) {
				day_E = day - 5;
				month_E = month + 4;
				year_E = year - 8;
			} else if (month == 9 && (day >= 11 && day <= 30)) {
				day_E = day - 10;
				month_E = month - 8;
				year_E = year - 7;
			} else if (month == 10 && (day >= 1 && day <= 10)) {
				day_E = day + 20;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 10 && (day >= 11 && day <= 31)) {
				day_E = day - 10;
				month_E = month - 8;
				year_E = year - 8;
			} else if (month == 11 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 11 && (day >= 10 && day <= 30)) {
				day_E = day - 9;
				month_E = month - 8;
				year_E = year - 7;
			} else if (month == 12 && (day >= 1 && day <= 9)) {
				day_E = day + 21;
				month_E = month - 9;
				year_E = year - 7;
			} else if (month == 12 && (day >= 10 && day <= 31)) {
				day_E = day - 9;
				month_E = month - 8;
				year_E = year - 7;
			} else {
				Toast.makeText(context, "Please enter the correct date",
						Toast.LENGTH_LONG).show();
			}

		}
		date = day_E + "/" + month_E + "/" + year_E;
		return date;
	}

}
