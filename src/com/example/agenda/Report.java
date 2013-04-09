package com.example.agenda;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.agenda.view.viewgroup.Finance_DatabaseAdapter;

public class Report extends Activity {
	private Spinner month_spin;

	private EditText exp_food_edit;
	private EditText exp_transport_edit;
	private EditText exp_family_edit;
	private EditText exp_housing_edit;
	private EditText exp_billing_edit;
	private EditText exp_education_edit;
	private EditText exp_entrmnt_edit;
	private EditText exp_health_edit;
	private EditText exp_others_edit;
	private EditText exp_total_edit;

	private EditText budg_food_edit;
	private EditText budg_transport_edit;
	private EditText budg_family_edit;
	private EditText budg_housing_edit;
	private EditText budg_billing_edit;
	private EditText budg_education_edit;
	private EditText budg_entrmnt_edit;
	private EditText budg_health_edit;
	private EditText budg_others_edit;
	private EditText budg_total_edit;

	private EditText diff_food_edit;
	private EditText diff_transport_edit;
	private EditText diff_family_edit;
	private EditText diff_housing_edit;
	private EditText diff_billing_edit;
	private EditText diff_education_edit;
	private EditText diff_entrmnt_edit;
	private EditText diff_health_edit;
	private EditText diff_others_edit;
	private EditText diff_total_edit;

	private EditText inc_total;
	private EditText exp_total_all;
	private EditText balance_total;
	private Button save_btn;
	private Finance_DatabaseAdapter db;
	private Finance_DatabaseAdapter db_budg;
	private Finance_DatabaseAdapter db_inc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		show();
	}

	private void show() {
		// TODO Auto-generated method stub
		month_spin = (Spinner) findViewById(R.id.report_spin);

		exp_food_edit = (EditText) findViewById(R.id.report_food_expense);
		exp_transport_edit = (EditText) findViewById(R.id.report_trans_expense);
		exp_family_edit = (EditText) findViewById(R.id.report_family_expense);
		exp_housing_edit = (EditText) findViewById(R.id.report_home_expense);
		exp_billing_edit = (EditText) findViewById(R.id.report_bill_expense);
		exp_education_edit = (EditText) findViewById(R.id.report_edu_expense);
		exp_entrmnt_edit = (EditText) findViewById(R.id.report_enter_expense);
		exp_health_edit = (EditText) findViewById(R.id.report_health_expense);
		exp_others_edit = (EditText) findViewById(R.id.report_other_expense);
		exp_total_edit = (EditText) findViewById(R.id.report_total_expense);

		budg_food_edit = (EditText) findViewById(R.id.report_food_budget);
		budg_transport_edit = (EditText) findViewById(R.id.report_trans_budget);
		budg_family_edit = (EditText) findViewById(R.id.report_family_budget);
		budg_housing_edit = (EditText) findViewById(R.id.report_home_budget);
		budg_billing_edit = (EditText) findViewById(R.id.report_bill_budget);
		budg_education_edit = (EditText) findViewById(R.id.report_edu_budget);
		budg_entrmnt_edit = (EditText) findViewById(R.id.report_enter_budget);
		budg_health_edit = (EditText) findViewById(R.id.report_health_budget);
		budg_others_edit = (EditText) findViewById(R.id.report_other_budget);
		budg_total_edit = (EditText) findViewById(R.id.report_total_budget);

		diff_food_edit = (EditText) findViewById(R.id.report_food_difference);
		diff_transport_edit = (EditText) findViewById(R.id.report_trans_difference);
		diff_family_edit = (EditText) findViewById(R.id.report_family_difference);
		diff_housing_edit = (EditText) findViewById(R.id.report_home_difference);
		diff_billing_edit = (EditText) findViewById(R.id.report_bill_difference);
		diff_education_edit = (EditText) findViewById(R.id.report_edu_difference);
		diff_entrmnt_edit = (EditText) findViewById(R.id.report_enter_difference);
		diff_health_edit = (EditText) findViewById(R.id.report_health_difference);
		diff_others_edit = (EditText) findViewById(R.id.report_other_difference);
		diff_total_edit = (EditText) findViewById(R.id.report_total_difference);

		inc_total = (EditText) findViewById(R.id.report_total_income);
		exp_total_all = (EditText) findViewById(R.id.report_total_expense2);
		balance_total = (EditText) findViewById(R.id.report_total_balance);
		save_btn = (Button) findViewById(R.id.report_btn);
		save_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		month_spin.setOnItemSelectedListener(new OnItemSelectedListener() {

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
		display();
	}

	private void display() {
		Double exp_food = 00.00;
		Double exp_trans = 00.00;
		Double exp_fam = 00.00;
		Double exp_house = 00.00;
		Double exp_bill = 00.00;
		Double exp_edu = 00.00;
		Double exp_enter = 00.00;
		Double exp_health = 00.00;
		Double exp_other = 00.00;
		Double exp_total = 00.00;
		//
		Double budg_food = 00.00;
		Double budg_trans = 00.00;
		Double budg_fam = 00.00;
		Double budg_house = 00.00;
		Double budg_bill = 00.00;
		Double budg_edu = 00.00;
		Double budg_enter = 00.00;
		Double budg_health = 00.00;
		Double budg_other = 00.00;
		Double budg_total = 00.00;
		//
		Double diff_food = 00.00;
		Double diff_trans = 00.00;
		Double diff_fam = 00.00;
		Double diff_house = 00.00;
		Double diff_bill = 00.00;
		Double diff_edu = 00.00;
		Double diff_enter = 00.00;
		Double diff_health = 00.00;
		Double diff_other = 00.00;
		Double diff_total = 00.00;
		//
		Double income_all = 00.00;
		Double balance = 00.00;

		String mon = "";
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
		//______________________________________________________________________________
		db = new Finance_DatabaseAdapter(this);
		try {
			db.open_expense();

			Cursor cur = db.getAllTitles_expense_report(mon);
			// Cursor cur = db.getAllTitles_expense();
			while (cur.moveToNext()) {
				exp_food = exp_food + cur.getDouble(1);
				exp_trans = exp_trans + cur.getDouble(2);
				exp_fam = exp_fam + cur.getDouble(3);
				exp_house = exp_house + cur.getDouble(4);
				exp_bill = exp_bill + cur.getDouble(5);
				exp_edu = exp_edu + cur.getDouble(6);
				exp_enter = exp_enter + cur.getDouble(7);
				exp_health = exp_health + cur.getDouble(8);
				exp_other = exp_other + cur.getDouble(9);
				

			}
			exp_total = exp_food + exp_trans + exp_fam + exp_house + exp_bill
					+ exp_edu + exp_enter + exp_health + exp_other;
			//
			exp_food_edit.setText("" + exp_food);
			exp_transport_edit.setText("" + exp_trans);
			exp_family_edit.setText("" + exp_fam);
			exp_housing_edit.setText("" + exp_house);
			exp_billing_edit.setText("" + exp_bill);
			exp_education_edit.setText("" + exp_edu);
			exp_entrmnt_edit.setText("" + exp_enter);
			exp_health_edit.setText("" + exp_health);
			exp_others_edit.setText("" + exp_other);
			exp_total_edit.setText("" + exp_total);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close_expense();

		}
    //________________________________________________________________________________
		db_budg = new Finance_DatabaseAdapter(this);
		try {
			db_budg.open_budget();

			Cursor cur = db_budg.getAllTitles_budget();

			while (cur.moveToNext()) {
				 budg_food = cur.getDouble(1);
				 budg_trans = cur.getDouble(2);
				 budg_fam = cur.getDouble(3);
				 budg_house = cur.getDouble(4);
				 budg_bill = cur.getDouble(5);
				 budg_edu = cur.getDouble(6);
				 budg_enter = cur.getDouble(7);
				 budg_health = cur.getDouble(8);
				 budg_other = cur.getDouble(9);
				
				//
				 
			}
			 budg_total = budg_food + budg_trans + budg_fam
						+ budg_house + budg_bill + budg_edu + budg_enter
						+ budg_health + budg_other;
			 budg_food_edit.setText("" + budg_food);
			 budg_transport_edit.setText("" + budg_trans);
			 budg_family_edit.setText("" + budg_fam);
			 budg_housing_edit.setText("" + budg_house);
			 budg_billing_edit.setText("" + budg_bill);
			 budg_education_edit.setText("" + budg_edu);
			 budg_entrmnt_edit.setText("" + budg_enter);
			 budg_health_edit.setText("" + budg_health);
			 budg_others_edit.setText("" + budg_other);
			 budg_total_edit.setText("" + budg_total);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db_budg.close_budget();

		}
		//_________________________________________________________________________
		 diff_food = budg_food-exp_food;
		 diff_trans = budg_trans-exp_trans;
		 diff_fam = budg_fam-exp_fam;
		 diff_house = budg_house-exp_house;
		 diff_bill = budg_bill-exp_bill;
		 diff_edu = budg_edu-exp_edu;
		 diff_enter = budg_enter-exp_enter;
		 diff_health = budg_health-exp_health;
		 diff_other = budg_other-exp_other;
		 diff_total = budg_total-exp_total;
		 
		 diff_food_edit.setText("" + diff_food);
		 diff_transport_edit.setText("" + diff_trans);
		 diff_family_edit.setText("" + diff_fam);
		 diff_housing_edit.setText("" + diff_house);
		 diff_billing_edit.setText("" + diff_bill);
		 diff_education_edit.setText("" + diff_edu);
		 diff_entrmnt_edit.setText("" + diff_enter);
		 diff_health_edit.setText("" + diff_health);
		 diff_others_edit.setText("" + diff_other);
		 diff_total_edit.setText("" + diff_total);
		 //__________________________________________________________________________
		 
		 db_inc = new Finance_DatabaseAdapter(this);
			try {
				db_inc.open_income();

				Cursor cur = db_inc.getAllTitles_income();

				while (cur.moveToNext()) {
					Double valueofsalary = cur.getDouble(1);
					Double valueofbusiness = cur.getDouble(2);
					Double valueofpertime = cur.getDouble(3);
					Double valueofother = cur.getDouble(4);
					
					 income_all = valueofsalary + valueofbusiness + valueofpertime
							+ valueofother ;	

				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db_inc.close_income();

			}
		//	_____________________________________________________________
			balance=income_all-exp_total;
			inc_total.setText("" + income_all);
			exp_total_all.setText("" + exp_total);
			balance_total.setText("" + balance);
	}
}
