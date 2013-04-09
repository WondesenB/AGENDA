package com.example.agenda;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.Finance_DatabaseAdapter;

public class Expense_show extends Activity {
	private Spinner month_spin;
	private Spinner date_spin;
	private EditText food_edit;
	private EditText transport_edit;
	private EditText family_edit;
	private EditText housing_edit;
	private EditText billing_edit;
	private EditText education_edit;
	private EditText entrmnt_edit;
	private EditText health_edit;
	private EditText others_edit;
	private EditText total_edit;

	private Button save_btn;
	private Button delete_btn;
	private Finance_DatabaseAdapter db;

	String [] mon_day=null;
	String mon= "";
	String dat="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_show);
		show();
	}

	private void show() {
		// TODO Auto-generated method stub
		month_spin = (Spinner) findViewById(R.id.expense_show_monthspin);
		date_spin = (Spinner) findViewById(R.id.expense_show_datespin);
		food_edit = (EditText) findViewById(R.id.expense_show_food_edit);
		food_edit.setFocusable(false);
		transport_edit = (EditText) findViewById(R.id.expense_show_transport_edit);
		transport_edit.setFocusable(false);
		family_edit = (EditText) findViewById(R.id.expense_show_family_edit);
		family_edit.setFocusable(false);
		housing_edit = (EditText) findViewById(R.id.expense_show_home_edit);
		housing_edit.setFocusable(false);
		billing_edit = (EditText) findViewById(R.id.expense_show_billing_edit);
		billing_edit.setFocusable(false);
		education_edit = (EditText) findViewById(R.id.expense_show_education_edit);
		education_edit.setFocusable(false);
		entrmnt_edit = (EditText) findViewById(R.id.expense_show_entertainment_edit);
		entrmnt_edit.setFocusable(false);
		health_edit = (EditText) findViewById(R.id.expense_show_health_edit);
		health_edit.setFocusable(false);
		others_edit = (EditText) findViewById(R.id.expense_show_other_edit);
		others_edit.setFocusable(false);
		total_edit = (EditText) findViewById(R.id.expense_show_total_edit);
		total_edit.setFocusable(false);
		save_btn = (Button) findViewById(R.id.expense_show_save_btn);
		save_btn.setText(getResources().getString(R.string.Ok));
		delete_btn = (Button) findViewById(R.id.expense_show_delete_btn);
		save_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		delete_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 AlertDialog.Builder builder = new AlertDialog.Builder(Expense_show.this);
                 builder.setTitle(getResources().getString(R.string.caution));
                 builder.setMessage(getResources().getString(R.string.warning_msg));
                 builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                    	 final Calendar c = Calendar.getInstance();
         				String yr = ""+ c.get(Calendar.YEAR);
         				String mon= "";
         				String dat = date_spin.getSelectedItem().toString();
         				String mon_select = month_spin.getSelectedItem().toString();
         				if (mon_select.equalsIgnoreCase("January")||mon_select.equalsIgnoreCase("ጥር")) {
         					mon = "01";
         				} else if (mon_select.equalsIgnoreCase("February")||mon_select.equalsIgnoreCase("የካቲት")) {
         					mon = "02";
         				} else if (mon_select.equalsIgnoreCase("March")||mon_select.equalsIgnoreCase("መጋቢት")) {
         					mon = "03";
         				} else if (mon_select.equalsIgnoreCase("April")||mon_select.equalsIgnoreCase("ሚያዚያ")) {
         					mon = "04";
         				} else if (mon_select.equalsIgnoreCase("May")||mon_select.equalsIgnoreCase("ግንቦት")) {
         					mon = "05";
         				} else if (mon_select.equalsIgnoreCase("June")||mon_select.equalsIgnoreCase("ሰኔ")) {
         					mon = "06";
         				} else if (mon_select.equalsIgnoreCase("July")||mon_select.equalsIgnoreCase("ሀምሌ")) {
         					mon = "07";
         				} else if (mon_select.equalsIgnoreCase("August")||mon_select.equalsIgnoreCase("ነሀሴ")) {
         					mon = "08";
         				} else if (mon_select.equalsIgnoreCase("September")||mon_select.equalsIgnoreCase("መስከረም")) {
         					mon = "09";
         				} else if (mon_select.equalsIgnoreCase("October")||mon_select.equalsIgnoreCase("ጥቅምት")) {
         					mon = "10";
         				} else if (mon_select.equalsIgnoreCase("November")||mon_select.equalsIgnoreCase("ህዳር")) {
         					mon = "11";
         				} else if (mon_select.equalsIgnoreCase("December")||mon_select.equalsIgnoreCase("ታህሳስ")) {
         					mon = "12";
         				}
         				String Date =dat+"/"+mon+"/"+yr;
         				db = new Finance_DatabaseAdapter(Expense_show.this);
         				try{
         					db.open_expense();
         					db.delete_expense(Date);
         					Toast.makeText(Expense_show.this,getResources().getString(R.string.expense_deleted), 4000).show();
         					finish();
         				}
         				catch (Exception e) {
         					e.printStackTrace();
         				} finally {
         					db.close_expense();

         				}
                     }

                 });
                 builder.setNegativeButton(getResources().getString(R.string.No), new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                    	 dialog.cancel();
                     }
                 });
                 Dialog dd= builder.create();
                 dd.show();
				
			}
		});
		date_spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				display();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		month_spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				
				String mon_select = month_spin.getSelectedItem().toString();
				if (mon_select.equalsIgnoreCase("January")||mon_select.equalsIgnoreCase("ጥር")) {
					mon = "01";
					mon_day = getResources().getStringArray(R.array.month_31);
				} else if (mon_select.equalsIgnoreCase("February")||mon_select.equalsIgnoreCase("የካቲት")) {
					mon = "02";
					mon_day = getResources().getStringArray(R.array.month_28);
				} else if (mon_select.equalsIgnoreCase("March")||mon_select.equalsIgnoreCase("መጋቢት")) {
					mon = "03";
					mon_day = getResources().getStringArray(R.array.month_31);
				} else if (mon_select.equalsIgnoreCase("April")||mon_select.equalsIgnoreCase("ሚያዚያ")) {
					mon = "04";
					mon_day = getResources().getStringArray(R.array.month_30);
				} else if (mon_select.equalsIgnoreCase("May")||mon_select.equalsIgnoreCase("ግንቦት")) {
					mon = "05";
					mon_day = getResources().getStringArray(R.array.month_31);
				} else if (mon_select.equalsIgnoreCase("June")||mon_select.equalsIgnoreCase("ሰኔ")) {
					mon = "06";
					mon_day = getResources().getStringArray(R.array.month_30);
				} else if (mon_select.equalsIgnoreCase("July")||mon_select.equalsIgnoreCase("ሀምሌ")) {
					mon = "07";
					mon_day = getResources().getStringArray(R.array.month_31);
				} else if (mon_select.equalsIgnoreCase("August")||mon_select.equalsIgnoreCase("ነሀሴ")) {
					mon = "08";
					mon_day = getResources().getStringArray(R.array.month_31);
				} else if (mon_select.equalsIgnoreCase("September")||mon_select.equalsIgnoreCase("መስከረም")) {
					mon = "09";
					mon_day = getResources().getStringArray(R.array.month_30);
				} else if (mon_select.equalsIgnoreCase("October")||mon_select.equalsIgnoreCase("ጥቅምት")) {
					mon = "10";
					mon_day = getResources().getStringArray(R.array.month_31);
				} else if (mon_select.equalsIgnoreCase("November")||mon_select.equalsIgnoreCase("ህዳር")) {
					mon = "11";
					mon_day = getResources().getStringArray(R.array.month_30);
				} else if (mon_select.equalsIgnoreCase("December")||mon_select.equalsIgnoreCase("ታህሳስ")) {
					mon = "12";
					mon_day = getResources().getStringArray(R.array.month_31);
				}
				//
				ArrayAdapter<String> adp = new ArrayAdapter<String>(Expense_show.this,android.R.layout.simple_dropdown_item_1line, mon_day);
				adp.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
				date_spin.setAdapter(adp);
				 
				display();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		display();
	}

	private void display() {
		Double food = 00.00;
		Double trans = 00.00;
		Double fam = 00.00;
		Double house = 00.00;
		Double bill = 00.00;
		Double edu = 00.00;
		Double enter = 00.00;
		Double health = 00.00;
		Double other = 00.00;
		Double sum = 00.00;
		final Calendar c = Calendar.getInstance();
		
		String yr = ""+ c.get(Calendar.YEAR);
		
		
		dat = date_spin.getSelectedItem().toString();
		db = new Finance_DatabaseAdapter(this);
		try {
			db.open_expense();

			Cursor cur = db.getAllTitles_expense_special(dat, mon, yr);
			//Cursor cur = db.getAllTitles_expense();
			while (cur.moveToNext()) {
				food = food + cur.getDouble(1);
				trans = trans + cur.getDouble(2);
				fam = fam + cur.getDouble(3);
				house = house + cur.getDouble(4);
				bill = bill + cur.getDouble(5);
				edu = edu + cur.getDouble(6);
				enter = enter + cur.getDouble(7);
				health = health + cur.getDouble(8);
				other = other + cur.getDouble(9);

			}
			sum = food + trans + fam + house + bill + edu + enter + health
					+ other;
			//
			food_edit.setText("" + food);
			transport_edit.setText("" + trans);
			family_edit.setText("" + fam);
			housing_edit.setText("" + house);
			billing_edit.setText("" + bill);
			education_edit.setText("" + edu);
			entrmnt_edit.setText("" + enter);
			health_edit.setText("" + health);
			others_edit.setText("" + other);
			total_edit.setText("" + sum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close_expense();

		}

	}
}
