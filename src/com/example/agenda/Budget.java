package com.example.agenda;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.Finance_DatabaseAdapter;

public class Budget extends Activity {

	private EditText date_edit;
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
	private TextView date_text;
	private Button save_btn;
	private Button clear_btn;
	private Finance_DatabaseAdapter db;
	private Finance_DatabaseAdapter db_budget;
	private Double zero = 00.00;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense);

		//
		addItem();
	}

	private void addItem() {
		// TODO Auto-generated method stub
		date_text = (TextView) findViewById(R.id.expense_dat_text);
		date_text.setText(getResources().getString(R.string.mon_budget));
		date_edit = (EditText) findViewById(R.id.expense_dat_edit);
		date_edit.setVisibility(View.INVISIBLE);
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
		clear_btn = (Button) findViewById(R.id.expense_clear_btn);
		save_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				submitData();
			}

		});
		clear_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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
						&& !transport_edit.getText().toString().equalsIgnoreCase("")
						&& !family_edit.getText().toString().equalsIgnoreCase("")
						&& !housing_edit.getText().toString().equalsIgnoreCase("")
						&& !billing_edit.getText().toString().equalsIgnoreCase("")
						&& !education_edit.getText().toString().equalsIgnoreCase("")
						&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
						&& !health_edit.getText().toString().equalsIgnoreCase("")
						&& !others_edit.getText().toString().equalsIgnoreCase("")) {
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

	private void submitData() {
		// TODO Auto-generated method stub
		if (!food_edit.getText().toString().equalsIgnoreCase("")
				&& !transport_edit.getText().toString().equalsIgnoreCase("")
				&& !family_edit.getText().toString().equalsIgnoreCase("")
				&& !housing_edit.getText().toString().equalsIgnoreCase("")
				&& !billing_edit.getText().toString().equalsIgnoreCase("")
				&& !education_edit.getText().toString().equalsIgnoreCase("")
				&& !entrmnt_edit.getText().toString().equalsIgnoreCase("")
				&& !health_edit.getText().toString().equalsIgnoreCase("")
				&& !others_edit.getText().toString().equalsIgnoreCase("")) {
			
		Double food = Double.parseDouble(food_edit.getText().toString());
		Double trans = Double.parseDouble(transport_edit.getText().toString());
		Double fam = Double.parseDouble(family_edit.getText().toString());
		Double house = Double.parseDouble(housing_edit.getText().toString());
		Double bill = Double.parseDouble(billing_edit.getText().toString());
		Double edu = Double.parseDouble(education_edit.getText().toString());
		Double enter = Double.parseDouble(entrmnt_edit.getText().toString());
		Double health = Double.parseDouble(health_edit.getText().toString());
		Double other = Double.parseDouble(others_edit.getText().toString());
		db = new Finance_DatabaseAdapter(this);

		db_budget = new Finance_DatabaseAdapter(this);
		try {
			db_budget.open_budget();
			Cursor cur = db_budget.getAllTitles_budget();

			if (cur != null && cur.getCount() > 0) {
				// cur.moveToFirst();
				Toast.makeText(this,
						getResources().getString(R.string.info_msg), 4000)
						.show();

			} else {
				long num;
				try {
					db.open_budget();
					num = db.addbudget_info(food, trans, fam, house, bill, edu,
							enter, health, other);
					db.close_budget();
				} catch (SQLException e) {
					num = -5;
				} finally {
					db.close_budget();
				}

				if (num > 0) {
					Toast.makeText(this, getResources().getString(R.string.budget_event_added), 2000).show();
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
					Toast.makeText(this, getResources().getString(R.string.error_insert), 2000).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db_budget.close_budget();

		}
		}
		else
		{
			Toast.makeText(this, getResources().getString(R.string.info_msg1), 2000).show();
		}
		finish();
	}

}
