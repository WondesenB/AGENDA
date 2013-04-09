package com.example.agenda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.Finance_DatabaseAdapter;

public class Income_show extends Activity {
	private EditText income_salary_edit;
	private EditText income_business_edit;
	private EditText income_pertime_edit;
	private EditText income_others_edit;
	private EditText income_total_edit;
	//
	private Button save_btn;
	private Button clear_btn;
	private Finance_DatabaseAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.income);
		show();
	}

	private void show() {
		// TODO Auto-generated method stub
		income_salary_edit = (EditText) findViewById(R.id.income_salary_edit);
		income_salary_edit.setFocusable(false);
		income_business_edit = (EditText) findViewById(R.id.income_business_edit);
		income_business_edit.setFocusable(false);
		income_pertime_edit = (EditText) findViewById(R.id.income_pertime_edit);
		income_pertime_edit.setFocusable(false);
		income_others_edit = (EditText) findViewById(R.id.income_other_edit);
		income_others_edit.setFocusable(false);
		income_total_edit = (EditText) findViewById(R.id.income_total_edit);
		income_total_edit.setFocusable(false);
		save_btn = (Button) findViewById(R.id.income_save_btn);
		clear_btn = (Button) findViewById(R.id.income_clear_btn);
		clear_btn.setText(getResources().getString(R.string.Delete));
		save_btn.setText(getResources().getString(R.string.Ok));
		save_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		clear_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Income_show.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								db = new Finance_DatabaseAdapter(Income_show.this);
								try
								{
									db.open_income();
									db.delete_income();
									Toast.makeText(Income_show.this, getResources().getString(R.string.income_event_deleted) , 2000).show();
									finish();
								}
								catch (Exception e) {
									e.printStackTrace();
								} finally {
									db.close_income();

								}
							}

						});
				builder.setNegativeButton(getResources().getString(R.string.No),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
								
							}
						});
				Dialog dd = builder.create();
				dd.show();

			}
		});
		display();
	}

	private void display() {

		db = new Finance_DatabaseAdapter(this);
		try {
			db.open_income();

			Cursor cur = db.getAllTitles_income();

			while (cur.moveToNext()) {
				Double valueofsalary = cur.getDouble(1);
				Double valueofbusiness = cur.getDouble(2);
				Double valueofpertime = cur.getDouble(3);
				Double valueofother = cur.getDouble(4);

				Double sum = valueofsalary + valueofbusiness + valueofpertime
						+ valueofother;
				//
				income_salary_edit.setText("" + valueofsalary);
				income_business_edit.setText("" + valueofbusiness);
				income_pertime_edit.setText("" + valueofpertime);
				income_others_edit.setText("" + valueofother);
				income_total_edit.setText("" + sum);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close_income();

		}

	}
}
