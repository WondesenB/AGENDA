package com.example.agenda;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.DataBaseAdapter;

public class Note extends Activity {

	private EditText date_edit;
	private EditText title_edit;
	private EditText note_edit;
	private Button save_btn;
	
	private Button close_btn;
	private DataBaseAdapter db;
	private DataBaseAdapter db_note;
	String dat;
	String tit;
	String not;
	int pos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note);
		Intent b = getIntent();
		 pos = b.getIntExtra("key", 0);
		dat=b.getStringExtra("date");
		tit=b.getStringExtra("title");
		not=b.getStringExtra("note");
		addItem();
	}
	@SuppressLint("SimpleDateFormat")
	private void addItem() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
		
		date_edit = (EditText) findViewById(R.id.editText_Note_Date);
		date_edit.setText(c.get(Calendar.DAY_OF_MONTH) + "/"
				+ df1.format(c.getTime())  + "/" + c.get(Calendar.YEAR));
		
		save_btn = (Button) findViewById(R.id.Button_Note_save);
		close_btn = (Button) findViewById(R.id.Button_Note_close);
		title_edit = (EditText) findViewById(R.id.editText_Note_Title);
		note_edit = (EditText) findViewById(R.id.editText_Note_Note);
		if(pos==2)
		{
			save_btn.setText(getResources().getString(R.string.update));
			db_note = new DataBaseAdapter(Note.this);
			try {
				db_note.open_note();
				
				Cursor cur = db_note.getrow_note(dat,tit,not);

				while (cur.moveToNext()) {
					String valueofdate = cur.getString(1);
					String valueoftitle = cur.getString(2);
					String valueofnote = cur.getString(3);
					date_edit.setText(valueofdate);
					title_edit.setText(valueoftitle);
					note_edit.setText(valueofnote);
					

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db_note.close_note();

			}
		}
		
		save_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(pos==1)
				{
				SubmitData();
				}
				if(pos==2)
				{	
					update_note();
					finish();	
				}
			}
		});
		
		close_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
private void SubmitData()
{
	String date = date_edit.getText().toString();
	String title = title_edit.getText().toString();
	String note = note_edit.getText().toString();
	
	db = new DataBaseAdapter(this);
	long num;
	try {
		db.open_note();
		num = db.addnote_info(date, title, note);
		db.close_note();
	} catch (SQLException e) {
		num = -5;
	} finally {
		db.close_note();
	}

	if (num > 0) {
		Toast.makeText(this, getResources().getString(R.string.note_added), 2000).show();
		title_edit.setText("");
		note_edit.setText("");
		
	}

	else if (num == -1)
		Toast.makeText(this, getResources().getString(R.string.error_dublicat), 4000).show();
	else
		Toast.makeText(this, getResources().getString(R.string.error_insert), 2000).show();
}
	private void update_note()
	{
		String date = date_edit.getText().toString();
		String title = title_edit.getText().toString();
		String note = note_edit.getText().toString();
		
		db = new DataBaseAdapter(this);
		
		try {
			db.open_note();
			db.updateRow_note( date, title, note,dat,tit,not);
			db.close_note();
		} catch (SQLException e) {
			
		} finally {
			db.close_note();
			Toast.makeText(this, getResources().getString(R.string.updated), 2000).show();
		}

		
	}
}
