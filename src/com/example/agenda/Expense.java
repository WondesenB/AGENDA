package com.example.agenda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.Finance_DatabaseAdapter;

public class Expense extends Activity {
	private EditText date_edit;
	private Spinner caltype_spin;
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
	private Finance_DatabaseAdapter db;
	private Double zero = 00.00;
    String dat;
    String dat_et;
    String ct;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense);

		//
		addItem();
	}

	@SuppressLint("SimpleDateFormat")
	private void addItem() {
		// TODO Auto-generated method stub
		Calandar cal= new Calandar();
		final Calendar c = Calendar.getInstance();
		SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		caltype_spin=(Spinner)findViewById(R.id.expense_caltype_spin);
		 ct=caltype_spin.getSelectedItem().toString();
		 dat=c.get(Calendar.DAY_OF_MONTH) + "/"
				+ df1.format(c.getTime())  + "/" + c.get(Calendar.YEAR);
		 dat_et=cal.toEthiopian(this, c.get(Calendar.DAY_OF_MONTH), Integer.parseInt(df1.format(c.getTime())), c.get(Calendar.YEAR));
		
		date_edit = (EditText) findViewById(R.id.expense_dat_edit);
		
		date_edit.setText(dat_et);
		food_edit = (EditText) findViewById(R.id.expense_food_edit);
		transport_edit = (EditText) findViewById(R.id.expense_transport_edit);
		family_edit = (EditText) findViewById(R.id.expense_family_edit);
		housing_edit = (EditText) findViewById(R.id.expense_home_edit);
		billing_edit = (EditText) findViewById(R.id.expense_billing_edit);
		education_edit = (EditText) findViewById(R.id.expense_education_edit);
		entrmnt_edit = (EditText) findViewById(R.id.expense_entertainment_edit);
		health_edit = (EditText) findViewById(R.id.expense_health_edit);
		others_edit = (EditText) findViewById(R.id.expense_other_edit);
		total_edit = (EditText) findViewById(R.id.expense_total_edit);
		save_btn = (Button) findViewById(R.id.expense_save_btn);
		caltype_spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ct=caltype_spin.getSelectedItem().toString();
				if(ct.equalsIgnoreCase("Ethiopian") ||ct.equalsIgnoreCase("ኢትዮጰያን") )
				{
					date_edit.setText(dat_et);
					
				}
				if(ct.equalsIgnoreCase("Gregorian") ||ct.equalsIgnoreCase("ግሪጎርያን") )
				{
					date_edit.setText(dat);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

		save_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				submitData();
				finish();
			}

		});

		food_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		transport_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		family_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		housing_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		billing_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		education_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		entrmnt_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		health_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});
		others_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!food_edit.getText().toString().equalsIgnoreCase("")
						&& !transport_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !family_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !housing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !billing_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !education_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !health_edit.getText().toString()
								.equalsIgnoreCase("")
						&& !others_edit.getText().toString()
								.equalsIgnoreCase("")) {
					Double food = Double.parseDouble(food_edit.getText()
							.toString());
					Double trans = Double.parseDouble(transport_edit.getText()
							.toString());
					Double fam = Double.parseDouble(family_edit.getText()
							.toString());
					Double house = Double.parseDouble(housing_edit.getText()
							.toString());
					Double bill = Double.parseDouble(billing_edit.getText()
							.toString());
					Double edu = Double.parseDouble(education_edit.getText()
							.toString());
					Double enter = Double.parseDouble(entrmnt_edit.getText()
							.toString());
					Double health = Double.parseDouble(health_edit.getText()
							.toString());
					Double other = Double.parseDouble(others_edit.getText()
							.toString());
					Double total = food + trans + fam + house + bill + edu
							+ enter + health + other;
					total_edit.setText("" + total);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

		});

	}

	@SuppressLint("SimpleDateFormat")
	private void submitData()  {
		// TODO Auto-generated method stub
		Calandar cal = new Calandar();
		if (!food_edit.getText().toString().equalsIgnoreCase("")
				&& !transport_edit.getText().toString().equalsIgnoreCase("")
				&& !family_edit.getText().toString().equalsIgnoreCase("")
				&& !housing_edit.getText().toString().equalsIgnoreCase("")
				&& !billing_edit.getText().toString().equalsIgnoreCase("")
				&& !education_edit.getText().toString().equalsIgnoreCase("")
				&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
				&& !health_edit.getText().toString().equalsIgnoreCase("")
				&& !others_edit.getText().toString().equalsIgnoreCase("")) {
			String caltype = caltype_spin.getSelectedItem().toString();
			String date = null;
			final Calendar c1 = Calendar.getInstance();
			final Calendar c11 = Calendar.getInstance();
			SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
			SimpleDateFormat formater = new java.text.SimpleDateFormat("dd/MM/yyyy");
			Date date1 = null;
			try {
				date1 = formater.parse(date_edit.getText().toString());
				c11.setTime(date1);
				int d = c11.get(Calendar.DAY_OF_MONTH);
				int m = c11.get(Calendar.MONTH);
				int y = c11.get(Calendar.YEAR);
				if(caltype.equalsIgnoreCase("Ethiopian") ||caltype.equalsIgnoreCase("ኢትዮጰያን") )
				{
					Date date2=formater.parse(cal.toGregorian(this, d, m+1, y));
					c1.setTime(date2);
					date = c1.get(Calendar.DAY_OF_MONTH) + "/"
							+ df1.format(c1.getTime())  + "/" + c1.get(Calendar.YEAR);
					
				}
				if(caltype.equalsIgnoreCase("Gregorian") ||caltype.equalsIgnoreCase("ግሪጎርያን") )
				{
					date=date_edit.getText().toString();
				}			
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
			Toast.makeText(this, date, 2000).show();
			Double food = Double.parseDouble(food_edit.getText().toString());
			Double trans = Double.parseDouble(transport_edit.getText()
					.toString());
			Double fam = Double.parseDouble(family_edit.getText().toString());
			Double house = Double
					.parseDouble(housing_edit.getText().toString());
			Double bill = Double.parseDouble(billing_edit.getText().toString());
			Double edu = Double
					.parseDouble(education_edit.getText().toString());
			Double enter = Double
					.parseDouble(entrmnt_edit.getText().toString());
			Double health = Double
					.parseDouble(health_edit.getText().toString());
			Double other = Double.parseDouble(others_edit.getText().toString());
			db = new Finance_DatabaseAdapter(this);
			long num;
			try {
				db.open_expense();
				num = db.addexpense_info(food, trans, fam, house, bill, edu,
						enter, health, other, date);
				db.close_expense();
			} catch (SQLException e) {
				num = -5;
			} finally {
				db.close_expense();
			}

			if (num > 0) {
				Toast.makeText(this, getResources().getString(R.string.expense_added), 2000).show();
				food_edit.setText("" + zero);
				transport_edit.setText("" + zero);
				family_edit.setText("" + zero);
				housing_edit.setText("" + zero);
				billing_edit.setText("" + zero);
				education_edit.setText("" + zero);
				entrmnt_edit.setText("" + zero);
				health_edit.setText("" + zero);
				others_edit.setText("" + zero);
			}

			else if (num == -1)
				Toast.makeText(this,getResources().getString(R.string.error_dublicat), 4000).show();
			else
				Toast.makeText(this,getResources().getString(R.string.error_insert), 2000).show();
		} else {
			    Toast.makeText(this, getResources().getString(R.string.info_msg1),2000).show();
		}
	}

}
