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
import android.widget.Toast;

import com.example.agenda.view.viewgroup.Finance_DatabaseAdapter;

public class Income extends Activity {

	private EditText income_salary_edit;
	private EditText income_business_edit;
	private EditText income_pertime_edit;
	private EditText income_others_edit;
	private EditText income_total_edit;
	//
	private Button save_btn;
	private Button clear_btn;
	private Finance_DatabaseAdapter db;
	private Finance_DatabaseAdapter db_income;
    private Double zero=00.00;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.income);
		addItem();

	}

	private void addItem() {
		// TODO Auto-generated method stub
		income_salary_edit = (EditText) findViewById(R.id.income_salary_edit);
		income_business_edit = (EditText) findViewById(R.id.income_business_edit);
		income_pertime_edit = (EditText) findViewById(R.id.income_pertime_edit);
		income_others_edit = (EditText) findViewById(R.id.income_other_edit);
		income_total_edit = (EditText) findViewById(R.id.income_total_edit);
		save_btn = (Button) findViewById(R.id.income_save_btn);
		clear_btn = (Button) findViewById(R.id.income_clear_btn);
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
				income_salary_edit.setText(""+zero);
				income_business_edit.setText(""+zero);
				income_pertime_edit.setText(""+zero);
				income_others_edit.setText(""+zero);
			}
		});
		income_salary_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!income_salary_edit.getText().toString().equalsIgnoreCase("")
						&& !income_business_edit.getText().toString().equalsIgnoreCase("")
						&& !income_pertime_edit.getText().toString().equalsIgnoreCase("")
						&& !income_others_edit.getText().toString().equalsIgnoreCase("")) {
				Double salary = Double.parseDouble(income_salary_edit.getText()
						.toString());
				Double business = Double.parseDouble(income_business_edit
						.getText().toString());
				Double pertime = Double.parseDouble(income_pertime_edit
						.getText().toString());
				Double other = Double.parseDouble(income_others_edit.getText()
						.toString());
				Double sum = salary + business + pertime + other;
				income_total_edit.setText("" + sum);
				}
			}
		});
		income_business_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!income_salary_edit.getText().toString().equalsIgnoreCase("")
						&& !income_business_edit.getText().toString().equalsIgnoreCase("")
						&& !income_pertime_edit.getText().toString().equalsIgnoreCase("")
						&& !income_others_edit.getText().toString().equalsIgnoreCase("")) {
				Double salary = Double.parseDouble(income_salary_edit.getText()
						.toString());
				Double business = Double.parseDouble(income_business_edit
						.getText().toString());
				Double pertime = Double.parseDouble(income_pertime_edit
						.getText().toString());
				Double other = Double.parseDouble(income_others_edit.getText()
						.toString());
				Double sum = salary + business + pertime + other;
				income_total_edit.setText("" + sum);
				}
			}
		});
		income_pertime_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!income_salary_edit.getText().toString().equalsIgnoreCase("")
						&& !income_business_edit.getText().toString().equalsIgnoreCase("")
						&& !income_pertime_edit.getText().toString().equalsIgnoreCase("")
						&& !income_others_edit.getText().toString().equalsIgnoreCase("")) {
				Double salary = Double.parseDouble(income_salary_edit.getText()
						.toString());
				Double business = Double.parseDouble(income_business_edit
						.getText().toString());
				Double pertime = Double.parseDouble(income_pertime_edit
						.getText().toString());
				Double other = Double.parseDouble(income_others_edit.getText()
						.toString());
				Double sum = salary + business + pertime + other;
				income_total_edit.setText("" + sum);
				}
			}
		});
		income_others_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!income_salary_edit.getText().toString().equalsIgnoreCase("")
						&& !income_business_edit.getText().toString().equalsIgnoreCase("")
						&& !income_pertime_edit.getText().toString().equalsIgnoreCase("")
						&& !income_others_edit.getText().toString().equalsIgnoreCase("")) {
				Double salary = Double.parseDouble(income_salary_edit.getText()
						.toString());
				Double business = Double.parseDouble(income_business_edit
						.getText().toString());
				Double pertime = Double.parseDouble(income_pertime_edit
						.getText().toString());
				Double other = Double.parseDouble(income_others_edit.getText()
						.toString());
				Double sum = salary + business + pertime + other;
				income_total_edit.setText("" + sum);
				}
			}
		});
	}

	private void submitData() {
		// TODO Auto-generated method stub
		if (!income_salary_edit.getText().toString().equalsIgnoreCase("")
				&& !income_business_edit.getText().toString().equalsIgnoreCase("")
				&& !income_pertime_edit.getText().toString().equalsIgnoreCase("")
				&& !income_others_edit.getText().toString().equalsIgnoreCase("")) {
		Double salary = Double.parseDouble(income_salary_edit.getText()
				.toString());
		Double business = Double.parseDouble(income_business_edit.getText()
				.toString());
		Double pertime = Double.parseDouble(income_pertime_edit.getText()
				.toString());
		Double other = Double.parseDouble(income_others_edit.getText()
				.toString());

		db = new Finance_DatabaseAdapter(this);

		//
		db_income = new Finance_DatabaseAdapter(this);
		try {
			db_income.open_income();
			Cursor cur = db_income.getAllTitles_income();

			if (cur != null && cur.getCount() > 0) {
				// cur.moveToFirst();
				Toast.makeText(this,
						getResources().getString(R.string.info_msg), 4000)
						.show();

			} else {
				long num;
				try {
					db.open_income();
					num = db.addincome_info(salary, business, pertime, other);
					db.close_income();
					finish();
				} catch (SQLException e) {
					num = -5;
				} finally {
					db.close_income();
				}

				if (num > 0) {
					Toast.makeText(this, getResources().getString(R.string.income_event_added) , 2000).show();
					income_salary_edit.setText(""+zero);
					income_business_edit.setText(""+zero);
					income_pertime_edit.setText(""+zero);
					income_others_edit.setText(""+zero);

				}

				else if (num == -1)
					Toast.makeText(this,getResources().getString(R.string.error_dublicat), 4000).show();
				else
					Toast.makeText(this,getResources().getString(R.string.error_insert), 2000).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db_income.close_income();

		}

		//
		}
		else
		{
			Toast.makeText(this, getResources().getString(R.string.info_msg1), 2000).show();
		}
	}
}
