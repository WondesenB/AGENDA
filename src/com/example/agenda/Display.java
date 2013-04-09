package com.example.agenda;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.view.viewgroup.DataBaseAdapter;

public class Display extends Activity {
	private DataBaseAdapter db_bill;
	private DataBaseAdapter db_meet;
	private DataBaseAdapter db_celle;
	private DataBaseAdapter db_edu;
	private DataBaseAdapter db_enter;
	private DataBaseAdapter db_travel;
	private DataBaseAdapter db_other;
	ArrayList<Elements> myElements = new ArrayList<Elements>();
	int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		Bundle b = getIntent().getExtras();
		pos = b.getInt("key", 0);
		show(pos);
		ListView context_option = (ListView) findViewById(R.id.event_listView);
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
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int pos = info.position;
		final Elements ItemAtpos = myElements.get(pos);
		switch (item.getItemId()) {
		case R.id.edit:
			Intent int1 = null;
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Billing")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ክፍያ")) {
				int1 = new Intent(Display.this, Billing.class);
			}
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Meeting")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ስብሰባ")) {
				int1 = new Intent(Display.this, Meeting.class);

			}
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Celebration")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ክብረ በዓል")) {
				int1 = new Intent(Display.this, Cellebration.class);

			}
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Education")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ትምህርት")) {
				int1 = new Intent(Display.this, Education.class);
			}
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Entertainment")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("መዝናኛ")) {
				int1 = new Intent(Display.this, Entertainment.class);
			}
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Travel")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ጉዞ")) {
				int1 = new Intent(Display.this, Travel.class);
			}
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Others")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ሌላ")) {
				int1 = new Intent(Display.this, Others.class);
			}
			int1.putExtra("num", 2);
			int1.putExtra("title", ItemAtpos.getTitle());
			int1.putExtra("category", ItemAtpos.getCategory());
			int1.putExtra("startingDate", ItemAtpos.getStartingDate());
			int1.putExtra("startingTime", ItemAtpos.getStartingTime());
			int1.putExtra("startingTime_class",ItemAtpos.getStartingTime_class());
			int1.putExtra("endingDate", ItemAtpos.getEndingDate());
			int1.putExtra("endingTime", ItemAtpos.getEndingTime());
			int1.putExtra("endingTime_class",ItemAtpos.getEndingTime_class());
			int1.putExtra("s_place", ItemAtpos.getS_place());
			int1.putExtra("des_place", ItemAtpos.getDes_place());
			int1.putExtra("frequency", ItemAtpos.getFrequency());
			int1.putExtra("note", ItemAtpos.getNote());
			int1.putExtra("caltype", ItemAtpos.getcaltype());
			startActivity(int1);
			finish();
			break;
		case R.id.delete_:
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Billing")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ክፍያ")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Display.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg2));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which)
							{
								db_bill = new DataBaseAdapter(Display.this);
								try {
									db_bill.open();
									db_bill.delete_row_bill(ItemAtpos.getTitle(),
											ItemAtpos.getCategory(),
											ItemAtpos.getStartingDate(),
											ItemAtpos.getStartingTime(),
											ItemAtpos.getStartingTime_class(),
											ItemAtpos.getEndingDate(),
											ItemAtpos.getEndingTime(),
											ItemAtpos.getEndingTime_class(),
											ItemAtpos.getFrequency(), ItemAtpos.getNote());
									Toast.makeText(Display.this,
											getResources().getString(R.string.deleted), 2000)
											.show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_bill.close();

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
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Meeting")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ስብሰባ")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Display.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg2));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which)
							{
								db_meet = new DataBaseAdapter(Display.this);
								try {
									db_meet.open_meet();
									db_meet.delete_row_meet(ItemAtpos.getTitle(),
											ItemAtpos.getCategory(),
											ItemAtpos.getStartingDate(),
											ItemAtpos.getStartingTime(),
											ItemAtpos.getStartingTime_class(),
											ItemAtpos.getEndingDate(),
											ItemAtpos.getEndingTime(),
											ItemAtpos.getEndingTime_class(),
											ItemAtpos.getFrequency(), ItemAtpos.getNote());
									Toast.makeText(Display.this,
											getResources().getString(R.string.deleted), 2000)
											.show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_meet.close_meet();

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
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Celebration")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ክብረ በዓል")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Display.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg2));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which)
							{
								db_celle = new DataBaseAdapter(Display.this);
								try {
									db_celle.open_celle();
									db_celle.delete_row_cele(ItemAtpos.getTitle(),
											ItemAtpos.getCategory(),
											ItemAtpos.getStartingDate(),
											ItemAtpos.getStartingTime(),
											ItemAtpos.getStartingTime_class(),
											ItemAtpos.getEndingDate(),
											ItemAtpos.getEndingTime(),
											ItemAtpos.getEndingTime_class(),
											ItemAtpos.getFrequency(), ItemAtpos.getNote());
									Toast.makeText(Display.this,
											getResources().getString(R.string.deleted), 2000)
											.show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_celle.close_celle();

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
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Education")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ትምህርት")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Display.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg2));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which)
							{
								db_edu = new DataBaseAdapter(Display.this);
								try {
									db_edu.open_edu();
									db_edu.delete_row_edu(ItemAtpos.getTitle(),
											ItemAtpos.getCategory(),
											ItemAtpos.getStartingDate(),
											ItemAtpos.getStartingTime(),
											ItemAtpos.getStartingTime_class(),
											ItemAtpos.getEndingDate(),
											ItemAtpos.getEndingTime(),
											ItemAtpos.getEndingTime_class(),
											ItemAtpos.getFrequency(), ItemAtpos.getNote());
									Toast.makeText(Display.this,
											getResources().getString(R.string.deleted), 2000)
											.show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_edu.close_edu();

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
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Entertainment")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("መዝናኛ")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Display.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg2));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which)
							{
								db_enter = new DataBaseAdapter(Display.this);
								try {
									db_enter.open_entr();
									db_enter.delete_row_enter(ItemAtpos.getTitle(),
											ItemAtpos.getCategory(),
											ItemAtpos.getStartingDate(),
											ItemAtpos.getStartingTime(),
											ItemAtpos.getStartingTime_class(),
											ItemAtpos.getEndingDate(),
											ItemAtpos.getEndingTime(),
											ItemAtpos.getEndingTime_class(),
											ItemAtpos.getS_place(), ItemAtpos.getFrequency(),
											ItemAtpos.getNote());
									Toast.makeText(Display.this,
											getResources().getString(R.string.deleted), 2000)
											.show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_enter.close_entr();

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
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Travel")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ጉዞ")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Display.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg2));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which)
							{
								db_travel = new DataBaseAdapter(Display.this);
								try {
									db_travel.open_travel();
									db_travel.delete_row_travel(ItemAtpos.getTitle(),
											ItemAtpos.getCategory(),
											ItemAtpos.getStartingDate(),
											ItemAtpos.getStartingTime(),
											ItemAtpos.getStartingTime_class(),
											ItemAtpos.getEndingDate(),
											ItemAtpos.getEndingTime(),
											ItemAtpos.getEndingTime_class(),
											ItemAtpos.getS_place(), ItemAtpos.getDes_place(),
											ItemAtpos.getFrequency(), ItemAtpos.getNote());
									Toast.makeText(Display.this,
											getResources().getString(R.string.deleted), 2000)
											.show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_travel.close_travel();

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
			if (ItemAtpos.getBig_category().equalsIgnoreCase("Others")
					|| ItemAtpos.getBig_category().equalsIgnoreCase("ሌላ")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Display.this);
				builder.setTitle(getResources().getString(R.string.caution));
				builder.setMessage(getResources().getString(R.string.warning_msg2));
				builder.setPositiveButton(getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,int which)
							{
								db_other = new DataBaseAdapter(Display.this);
								try {
									db_other.open_others();
									db_other.delete_row_other(ItemAtpos.getTitle(),
											ItemAtpos.getCategory(),
											ItemAtpos.getStartingDate(),
											ItemAtpos.getStartingTime(),
											ItemAtpos.getStartingTime_class(),
											ItemAtpos.getEndingDate(),
											ItemAtpos.getEndingTime(),
											ItemAtpos.getEndingTime_class(),
											ItemAtpos.getFrequency(), ItemAtpos.getNote());
									Toast.makeText(Display.this,
											getResources().getString(R.string.deleted), 2000)
											.show();
									finish();
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									db_other.close_others();

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

			break;
		}
		return super.onContextItemSelected(item);
	}

	private void show(int pos) {
		// TODO Auto-generated method stub
		myElements.clear();
		switch (pos) {
		case 1:
			db_bill = new DataBaseAdapter(this);
			try {
				db_bill.open();

				Cursor cur = db_bill.getAllTitles_bill();

				while (cur.moveToNext()) {
					String valueoftitle = cur.getString(1);
					String valueofcat = cur.getString(2);
					String valueofsdate = cur.getString(3);
					String valueofstime = cur.getString(4);
					String valueofstime_class = cur.getString(5);
					String valueofedate = cur.getString(6);
					String valueofetime = cur.getString(7);
					String valueofetime_class = cur.getString(8);
					String valueoffreq = cur.getString(9);
					String valueofnote = cur.getString(10);
					String valueofcaltype = cur.getString(11);
					//
					myElements.add(new Elements(valueoftitle, valueofcat,
							getResources().getString(R.string.billing),
							valueofsdate, valueofstime, valueofstime_class,
							valueofedate, valueofetime, valueofetime_class, "",
							"", valueoffreq, valueofnote,valueofcaltype, "", ""));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db_bill.close();

			}
			break;
		case 2:
			db_meet = new DataBaseAdapter(this);
			try {
				db_meet.open_meet();
				Cursor cur_meet = db_meet.getAllTitles_meet();
				while (cur_meet.moveToNext()) {
					String valueoftitle = cur_meet.getString(1);
					String valueofcat = cur_meet.getString(2);
					String valueofsdate = cur_meet.getString(3);
					String valueofstime = cur_meet.getString(4);
					String valueofstime_class = cur_meet.getString(5);
					String valueofedate = cur_meet.getString(6);
					String valueofetime = cur_meet.getString(7);
					String valueofetime_class = cur_meet.getString(8);
					String valueoffreq = cur_meet.getString(9);
					String valueofnote = cur_meet.getString(10);
					String valueofcaltype = cur_meet.getString(11);
					//
					myElements.add(new Elements(valueoftitle, valueofcat,
							getResources().getString(R.string.meeting_),
							valueofsdate, valueofstime, valueofstime_class,
							valueofedate, valueofetime, valueofetime_class, "",
							"", valueoffreq, valueofnote,valueofcaltype, "", ""));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				db_meet.close_meet();
			}
			break;
		case 3:
			db_celle = new DataBaseAdapter(this);
			try {
				db_celle.open_celle();
				Cursor cur_celle = db_celle.getAllTitles_celle();
				while (cur_celle.moveToNext()) {
					String valueoftitle = cur_celle.getString(1);
					String valueofcat = cur_celle.getString(2);
					String valueofsdate = cur_celle.getString(3);
					String valueofstime = cur_celle.getString(4);
					String valueofstime_class = cur_celle.getString(5);
					String valueofedate = cur_celle.getString(6);
					String valueofetime = cur_celle.getString(7);
					String valueofetime_class = cur_celle.getString(8);
					String valueoffreq = cur_celle.getString(9);
					String valueofnote = cur_celle.getString(10);
					String valueofcaltype = cur_celle.getString(11);
					//
					myElements.add(new Elements(valueoftitle, valueofcat,
							getResources().getString(R.string.cellebration_),
							valueofsdate, valueofstime, valueofstime_class,
							valueofedate, valueofetime, valueofetime_class, "",
							"", valueoffreq, valueofnote,valueofcaltype, "", ""));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				db_celle.close_celle();
			}
			break;
		case 4:
			db_edu = new DataBaseAdapter(this);
			try {
				db_edu.open_edu();
				Cursor cur_edu = db_edu.getAllTitles_edu();
				while (cur_edu.moveToNext()) {
					String valueoftitle = cur_edu.getString(1);
					String valueofcat = cur_edu.getString(2);
					String valueofsdate = cur_edu.getString(3);
					String valueofstime = cur_edu.getString(4);
					String valueofstime_class = cur_edu.getString(5);
					String valueofedate = cur_edu.getString(6);
					String valueofetime = cur_edu.getString(7);
					String valueofetime_class = cur_edu.getString(8);
					String valueoffreq = cur_edu.getString(9);
					String valueofnote = cur_edu.getString(10);
					String valueofcaltype = cur_edu.getString(11);
					//
					myElements.add(new Elements(valueoftitle, valueofcat,
							getResources().getString(R.string.education_),
							valueofsdate, valueofstime, valueofstime_class,
							valueofedate, valueofetime, valueofetime_class, "",
							"", valueoffreq, valueofnote,valueofcaltype, "", ""));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				db_edu.close_edu();
			}
			break;
		case 5:
			db_enter = new DataBaseAdapter(this);
			try {
				db_enter.open_entr();
				Cursor cur_enter = db_enter.getAllTitles_enter();
				while (cur_enter.moveToNext()) {
					String valueoftitle = cur_enter.getString(1);
					String valueofcat = cur_enter.getString(2);
					String valueofsdate = cur_enter.getString(3);
					String valueofstime = cur_enter.getString(4);
					String valueofstime_class = cur_enter.getString(5);
					String valueofedate = cur_enter.getString(6);
					String valueofetime = cur_enter.getString(7);
					String valueofetime_class = cur_enter.getString(8);
					String valueofplace = cur_enter.getString(9);
					String valueoffreq = cur_enter.getString(10);
					String valueofnote = cur_enter.getString(11);
					String valueofcaltype = cur_enter.getString(12);
					//
					myElements.add(new Elements(valueoftitle, valueofcat,
							getResources().getString(R.string.entertainment_),
							valueofsdate, valueofstime, valueofstime_class,
							valueofedate, valueofetime, valueofetime_class,
							valueofplace, "", valueoffreq, valueofnote,valueofcaltype,
							getResources().getString(R.string.At), ""));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				db_enter.close_entr();
			}
			break;
		case 6:
			db_travel = new DataBaseAdapter(this);
			try {
				db_travel.open_travel();
				Cursor cur_travel = db_travel.getAllTitles_travel();
				while (cur_travel.moveToNext()) {
					String valueoftitle = cur_travel.getString(1);
					String valueofcat = cur_travel.getString(2);
					String valueofsdate = cur_travel.getString(3);
					String valueofstime = cur_travel.getString(4);
					String valueofstime_class = cur_travel.getString(5);
					String valueofedate = cur_travel.getString(6);
					String valueofetime = cur_travel.getString(7);
					String valueofetime_class = cur_travel.getString(8);
					String valueofsplace = cur_travel.getString(9);
					String valueofdesplace = cur_travel.getString(10);
					String valueoffreq = cur_travel.getString(11);
					String valueofnote = cur_travel.getString(12);
					String valueofcaltype = cur_travel.getString(13);
					//
					myElements.add(new Elements(valueoftitle, valueofcat,
							getResources().getString(R.string.travel_),
							valueofsdate, valueofstime, valueofstime_class,
							valueofedate, valueofetime, valueofetime_class,
							valueofsplace, valueofdesplace, valueoffreq,
							valueofnote,valueofcaltype, getResources()
									.getString(R.string.from), getResources()
									.getString(R.string.To)));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				db_travel.close_travel();
			}
			break;
		case 7:
			db_other = new DataBaseAdapter(this);
			try {
				db_other.open_others();
				Cursor cur_other = db_other.getAllTitles_others();
				while (cur_other.moveToNext()) {
					String valueoftitle = cur_other.getString(1);
					String valueofcat = cur_other.getString(2);
					String valueofsdate = cur_other.getString(3);
					String valueofstime = cur_other.getString(4);
					String valueofstime_class = cur_other.getString(5);
					String valueofedate = cur_other.getString(6);
					String valueofetime = cur_other.getString(7);
					String valueofetime_class = cur_other.getString(8);
					String valueoffreq = cur_other.getString(9);
					String valueofnote = cur_other.getString(10);
					String valueofcaltype = cur_other.getString(11);
					//
					myElements.add(new Elements(valueoftitle, valueofcat,
							getResources().getString(R.string.others_),
							valueofsdate, valueofstime, valueofstime_class,
							valueofedate, valueofetime, valueofetime_class, "",
							"", valueoffreq, valueofnote,valueofcaltype, "", ""));

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				db_other.close_others();
			}
			break;
		}

		ArrayAdapter<Elements> adapter_today = new ElementAdapter();
		ListView eventList_today = (ListView) findViewById(R.id.event_listView);
		eventList_today.setAdapter(adapter_today);
	}

	private class ElementAdapter extends ArrayAdapter<Elements> {

		public ElementAdapter() {
			super(Display.this, R.layout.eventlist_view, myElements);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View elementView = convertView;
			if (elementView == null) {
				elementView = getLayoutInflater().inflate(
						R.layout.eventlist_view, parent, false);
			}
			Elements currentElement = myElements.get(position);
			TextView CatText = (TextView) elementView
					.findViewById(R.id.textView1);
			CatText.setText(currentElement.getCategory());
			TextView BingCatText = (TextView) elementView
					.findViewById(R.id.textView15);
			BingCatText.setText(currentElement.getBig_category());
			TextView TitleText = (TextView) elementView
					.findViewById(R.id.textView2);
			TitleText.setText(currentElement.getTitle() + "  ");
			TextView sDateText = (TextView) elementView
					.findViewById(R.id.textView3);
			sDateText.setText(currentElement.getStartingDate());
			TextView eDateText = (TextView) elementView
					.findViewById(R.id.textView4);
			eDateText.setText(currentElement.getEndingDate());
			TextView sTimeText = (TextView) elementView
					.findViewById(R.id.textView6);
			sTimeText.setText(currentElement.getStartingTime() + " "
					+ currentElement.getStartingTime_class());
			TextView eTimeText = (TextView) elementView
					.findViewById(R.id.textView7);
			eTimeText.setText(currentElement.getEndingTime() + " "
					+ currentElement.getEndingTime_class());

			return elementView;
		}

	}

}
