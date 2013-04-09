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
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.Finance_DatabaseAdapter;

public class Budget_Show extends Activity {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense);
		show();
	}

	private void show() {
		// TODO Auto-generated method stub
		date_text = (TextView) findViewById(R.id.expense_dat_text);
		date_text.setText(getResources().getString(R.string.mon_budget));
		date_edit = (EditText) findViewById(R.id.expense_dat_edit);
		date_edit.setVisibility(View.INVISIBLE);
		food_edit = (EditText) findViewById(R.id.expense_food_edit);
		food_edit.setFocusable(false);
		transport_edit = (EditText) findViewById(R.id.expense_transport_edit);
		transport_edit.setFocusable(false);
		family_edit = (EditText) findViewById(R.id.expense_family_edit);
		family_edit.setFocusable(false);
		housing_edit = (EditText) findViewById(R.id.expense_home_edit);
		housing_edit.setFocusable(false);
		billing_edit = (EditText) findViewById(R.id.expense_billing_edit);
		billing_edit.setFocusable(false);
		education_edit = (EditText) findViewById(R.id.expense_education_edit);
		education_edit.setFocusable(false);
		entrmnt_edit = (EditText) findViewById(R.id.expense_entertainment_edit);
		entrmnt_edit.setFocusable(false);
		health_edit = (EditText) findViewById(R.id.expense_health_edit);
		health_edit.setFocusable(false);
		others_edit = (EditText) findViewById(R.id.expense_other_edit);
		others_edit.setFocusable(false);
		total_edit = (EditText) findViewById(R.id.expense_total_edit);
		total_edit.setFocusable(false);
		save_btn = (Button) findViewById(R.id.expense_save_btn);
		clear_btn = (Button) findViewById(R.id.expense_clear_btn);
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
				
                AlertDialog.Builder builder = new AlertDialog.Builder(Budget_Show.this);
                builder.setTitle(getResources().getString(R.string.caution));
                builder.setMessage(getResources().getString(R.string.warning_msg));
                builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	db = new Finance_DatabaseAdapter(Budget_Show.this);
        				try
        				{
        					db.open_budget();
        					db.delete_budget();
        					Toast.makeText(Budget_Show.this,getResources().getString(R.string.budget_event_deleted) , 2000).show();
        					finish();
        				}
        				catch (Exception e) {
        					e.printStackTrace();
        				} finally {
        					db.close_budget();

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
				// TODO Auto-generated method stub
				
			
			}
		});

		display();
	}

	private void display() {
		db = new Finance_DatabaseAdapter(this);
		try {
			db.open_budget();

			Cursor cur = db.getAllTitles_budget();

			while (cur.moveToNext()) {
				Double valueoffood = cur.getDouble(1);
				Double valueoftrans = cur.getDouble(2);
				Double valueoffam = cur.getDouble(3);
				Double valueofhome = cur.getDouble(4);
				Double valueofbill = cur.getDouble(5);
				Double valueofedu = cur.getDouble(6);
				Double valueofenter = cur.getDouble(7);
				Double valueofhealth = cur.getDouble(8);
				Double valueofother = cur.getDouble(9);
				Double sum = valueoffood + valueoftrans + valueoffam
						+ valueofhome + valueofbill + valueofedu + valueofenter
						+ valueofhealth + valueofother;
				//
				food_edit.setText("" + valueoffood);
				transport_edit.setText("" + valueoftrans);
				family_edit.setText("" + valueoffam);
				housing_edit.setText("" + valueofhome);
				billing_edit.setText("" + valueofbill);
				education_edit.setText("" + valueofedu);
				entrmnt_edit.setText("" + valueofenter);
				health_edit.setText("" + valueofhealth);
				others_edit.setText("" + valueofother);
				total_edit.setText("" + sum);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close_budget();

		}
	}
}
