package com.example.agenda;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.DataBaseAdapter;

public class Note_Show extends Activity {

	private DataBaseAdapter db_note;
	ArrayList<Note_Item> noteElements = new ArrayList<Note_Item>();
	Button delete_btn;
	Button close_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_note);
		populate();
		show();
		ListView context_option = (ListView) findViewById(R.id.Note_listView);
		registerForContextMenu(context_option);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		 new MenuInflater(this).inflate(R.menu.option, menu);
		 super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		int pos = info.position;
		final Note_Item ItemAtpos=noteElements.get(pos);
		switch (item.getItemId()) {
		case R.id.edit:
			
			
			Intent int1= new Intent(Note_Show.this, Note.class);
			int1.putExtra("key", 2);
			int1.putExtra("date", ItemAtpos.getDate());
			int1.putExtra("title", ItemAtpos.getTitle());
			int1.putExtra("note", ItemAtpos.getNote());
			startActivity(int1);
			break;
		case R.id.delete_:
			AlertDialog.Builder builder = new AlertDialog.Builder(
					Note_Show.this);
			builder.setTitle(getResources().getString(R.string.caution));
			builder.setMessage(getResources().getString(R.string.warning_msg));
			builder.setPositiveButton(getResources().getString(R.string.yes),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							db_note = new DataBaseAdapter(Note_Show.this);
							try {
								db_note.open_note();
								db_note.delete_row_note(ItemAtpos.getDate(), ItemAtpos.getTitle(), ItemAtpos.getNote());
								Toast.makeText(Note_Show.this,
										getResources().getString(R.string.deleted), 2000).show();
								finish();
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								db_note.close_note();

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
			
			break;
		}
		return super.onContextItemSelected(item);
	}

	private void populate() {
		// TODO Auto-generated method stub
		 
		delete_btn = (Button) findViewById(R.id.btn_note_deleteAll);
		close_btn = (Button) findViewById(R.id.btn_note_close);
		delete_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Note_Show.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg1));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								db_note = new DataBaseAdapter(Note_Show.this);
								try {
									db_note.open_note();
									db_note.delete_note();
									Toast.makeText(Note_Show.this,
											getResources().getString(R.string.note_deleted), 2000).show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_note.close_note();

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
		close_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void show() {
		// TODO Auto-generated method stub

		noteElements.clear();
		db_note = new DataBaseAdapter(this);
		try {
			db_note.open_note();

			Cursor cur = db_note.getAllTitles_note();

			while (cur.moveToNext()) {
				
				String valueofdate = cur.getString(1);
				String valueoftitle = cur.getString(2);
				String valueofnote = cur.getString(3);
				//
				noteElements.add(new Note_Item(valueofdate, valueoftitle,
						valueofnote));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db_note.close_note();

		}
		ArrayAdapter<Note_Item> adapter_note = new NoteAdapter();
		ListView notelist = (ListView) findViewById(R.id.Note_listView);
		notelist.setAdapter(adapter_note);
		
	}

	private class NoteAdapter extends ArrayAdapter<Note_Item> {

		public NoteAdapter() {
			super(Note_Show.this, R.layout.note_view, noteElements);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View elementView = convertView;
			if (elementView == null) {
				elementView = getLayoutInflater().inflate(R.layout.note_view,
						parent, false);
			}
			Note_Item currentElement = noteElements.get(position);
			TextView dateText = (TextView) elementView
					.findViewById(R.id.note_date_view);
			dateText.setText(currentElement.getDate());
			TextView titleText = (TextView) elementView
					.findViewById(R.id.note_title_view);
			SpannableString content = new SpannableString(
					currentElement.getTitle());
			content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
			titleText.setText(content);
			EditText noteEdit = (EditText) elementView
					.findViewById(R.id.note_note_view);
			noteEdit.setText(currentElement.getNote());
			return elementView;
		}

	}
}
