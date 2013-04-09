package com.example.agenda.view.viewgroup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_STARTDATE = "startdate";
	public static final String KEY_STARTTIME = "starttime";
	public static final String KEY_STARTTIME_CLASS = "s_am_pm";
	public static final String KEY_ENDDATE = "enddate";
	public static final String KEY_ENDTIME = "endtime";
	public static final String KEY_ENDTIME_CLASS = "e_am_pm";
	public static final String KEY_FREQUNCEY = "frequency";
	public static final String KEY_NOTE = "note";

	//
	public static final String KEY_place = "place";
	public static final String KEY_st_place = "start_place";
	public static final String KEY_des_place = "des_place";
	public static final String KEY_Date = "date";
	public static final String KEY_Calendar = "calendar";
	//

	private static final String DATABASE_Bill = "mydbbill";
	private static final String DATABASE_TABLE_Bill = "mytablebill";
	//
	private static final String DATABASE_MEETING = "mydbmeet";
	private static final String DATABASE_TABLE_MEETING = "mytablemeet";
	//
	private static final String DATABASE_CELEBRATION = "mydbceleb";
	private static final String DATABASE_TABLE_CELEBRATION = "mytableceleb";
	//
	private static final String DATABASE_EDUCATION = "mydbedu";
	private static final String DATABASE_TABLE_EDUCATION = "mytableedu";
	//
	private static final String DATABASE_ENTERTAINMENT = "mydbentr";
	private static final String DATABASE_TABLE_ENTERTAINMENT = "mytableentr";
	//
	private static final String DATABASE_TRAVEL = "mydbtrvl";
	private static final String DATABASE_TABLE_TRAVEL = "mytabletrvl";
	//
	private static final String DATABASE_OTHERS = "mydbother";
	private static final String DATABASE_TABLE_OTHERS = "mytableother";
	//
	private static final String DATABASE_NOTE = "mydbnote";
	private static final String DATABASE_TABLE_NOTE = "mytablenote";
	//
	private static final int DATABASE_VERSION = 1;

	//
	private final Context ourContext;
	private DbHelperBill dbh;
	private DbHelperMeet dbh_meet;
	private DbHelperEntr dbh_entr;
	private DbHelperEdu dbh_edu;
	private DbHelperCelle dbh_celle;
	private DbHelperTrvl dbh_trvl;
	private DbHelperother dbh_other;
	private DbHelpernote dbh_note;
	private SQLiteDatabase odb;
	//
	private static final String CREAT_TABLE_BILL = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_Bill
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_TITLE
			+ " VARCHAR(15) , "
			+ KEY_CATEGORY
			+ " VARCHAR(15) , "
			+ KEY_STARTDATE
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME_CLASS
			+ " VARCHAR(15), "
			+ KEY_ENDDATE
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME_CLASS
			+ " VARCHAR(15) , "
			+ KEY_FREQUNCEY
			+ " VARCHAR(15) , "
			+ KEY_NOTE
			+ " VARCHAR(150) , "
			+ KEY_Calendar
			+ " VARCHAR(150))";
	private static final String CREAT_TABLE_MEET = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_MEETING
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_TITLE
			+ " VARCHAR(15) , "
			+ KEY_CATEGORY
			+ " VARCHAR(15) , "
			+ KEY_STARTDATE
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME_CLASS
			+ " VARCHAR(15), "
			+ KEY_ENDDATE
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME_CLASS
			+ " VARCHAR(15) , "
			+ KEY_FREQUNCEY
			+ " VARCHAR(15) , "
			+ KEY_NOTE
			+ " VARCHAR(150), "
			+ KEY_Calendar
			+ " VARCHAR(150))";
	private static final String CREAT_TABLE_ENTER = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_ENTERTAINMENT
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_TITLE
			+ " VARCHAR(15) , "
			+ KEY_CATEGORY
			+ " VARCHAR(15) , "
			+ KEY_STARTDATE
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME_CLASS
			+ " VARCHAR(15), "
			+ KEY_ENDDATE
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME_CLASS
			+ " VARCHAR(15) , "
			+ KEY_place
			+ " VARCHAR(15) , "
			+ KEY_FREQUNCEY
			+ " VARCHAR(15) , "
			+ KEY_NOTE
			+ " VARCHAR(150), " + KEY_Calendar + " VARCHAR(150))";
	private static final String CREAT_TABLE_EDU = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_EDUCATION + "(" + KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE
			+ " VARCHAR(15) , " + KEY_CATEGORY + " VARCHAR(15) , "
			+ KEY_STARTDATE + " VARCHAR(15) , " + KEY_STARTTIME
			+ " VARCHAR(15) , " + KEY_STARTTIME_CLASS + " VARCHAR(15), "
			+ KEY_ENDDATE + " VARCHAR(15) , " + KEY_ENDTIME + " VARCHAR(15) , "
			+ KEY_ENDTIME_CLASS + " VARCHAR(15) , " + KEY_FREQUNCEY
			+ " VARCHAR(15) , " + KEY_NOTE + " VARCHAR(150), " + KEY_Calendar
			+ " VARCHAR(150))";
	private static final String CREAT_TABLE_CELLE = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_CELEBRATION
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_TITLE
			+ " VARCHAR(15) , "
			+ KEY_CATEGORY
			+ " VARCHAR(15) , "
			+ KEY_STARTDATE
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME_CLASS
			+ " VARCHAR(15), "
			+ KEY_ENDDATE
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME_CLASS
			+ " VARCHAR(15) , "
			+ KEY_FREQUNCEY
			+ " VARCHAR(15) , "
			+ KEY_NOTE
			+ " VARCHAR(150), "
			+ KEY_Calendar
			+ " VARCHAR(150))";
	private static final String CREAT_TABLE_TRAVEL = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_TRAVEL
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_TITLE
			+ " VARCHAR(15) , "
			+ KEY_CATEGORY
			+ " VARCHAR(15) , "
			+ KEY_STARTDATE
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME_CLASS
			+ " VARCHAR(15), "
			+ KEY_ENDDATE
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME_CLASS
			+ " VARCHAR(15) , "
			+ KEY_st_place
			+ " VARCHAR(15) , "
			+ KEY_des_place
			+ " VARCHAR(15) , "
			+ KEY_FREQUNCEY
			+ " VARCHAR(15) , "
			+ KEY_NOTE
			+ " VARCHAR(150), "
			+ KEY_Calendar + " VARCHAR(150))";
	private static final String CREAT_TABLE_OTHERS = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_OTHERS
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_TITLE
			+ " VARCHAR(15) , "
			+ KEY_CATEGORY
			+ " VARCHAR(15) , "
			+ KEY_STARTDATE
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME
			+ " VARCHAR(15) , "
			+ KEY_STARTTIME_CLASS
			+ " VARCHAR(15), "
			+ KEY_ENDDATE
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME
			+ " VARCHAR(15) , "
			+ KEY_ENDTIME_CLASS
			+ " VARCHAR(15) , "
			+ KEY_FREQUNCEY
			+ " VARCHAR(15) , "
			+ KEY_NOTE
			+ " VARCHAR(150), "
			+ KEY_Calendar
			+ " VARCHAR(150))";
	private static final String CREAT_TABLE_NOTE = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_NOTE
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_Date
			+ " VARCHAR(15) , "
			+ KEY_TITLE
			+ " VARCHAR(15) , "
			+ KEY_NOTE
			+ " VARCHAR(150))";

	//
	private static class DbHelperBill extends SQLiteOpenHelper {

		public DbHelperBill(Context context) {
			super(context, DATABASE_Bill, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_BILL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperMeet extends SQLiteOpenHelper {

		public DbHelperMeet(Context context) {
			super(context, DATABASE_MEETING, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_MEET);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperEntr extends SQLiteOpenHelper {

		public DbHelperEntr(Context context) {
			super(context, DATABASE_ENTERTAINMENT, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_ENTER);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperEdu extends SQLiteOpenHelper {

		public DbHelperEdu(Context context) {
			super(context, DATABASE_EDUCATION, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_EDU);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperCelle extends SQLiteOpenHelper {

		public DbHelperCelle(Context context) {
			super(context, DATABASE_CELEBRATION, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_CELLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperTrvl extends SQLiteOpenHelper {

		public DbHelperTrvl(Context context) {
			super(context, DATABASE_TRAVEL, null, DATABASE_VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_TRAVEL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperother extends SQLiteOpenHelper {

		public DbHelperother(Context context) {
			super(context, DATABASE_OTHERS, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_OTHERS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelpernote extends SQLiteOpenHelper {

		public DbHelpernote(Context context) {
			super(context, DATABASE_NOTE, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREAT_TABLE_NOTE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	//
	public DataBaseAdapter(Context c) {
		ourContext = c;
		dbh = new DbHelperBill(ourContext);
		dbh_meet = new DbHelperMeet(ourContext);
		dbh_entr = new DbHelperEntr(ourContext);
		dbh_edu = new DbHelperEdu(ourContext);
		dbh_celle = new DbHelperCelle(ourContext);
		dbh_trvl = new DbHelperTrvl(ourContext);
		dbh_other = new DbHelperother(ourContext);
		dbh_note = new DbHelpernote(ourContext);
	}

	//
	public DataBaseAdapter open() throws SQLException {
		odb = dbh.getWritableDatabase();
		return this;
	}

	public DataBaseAdapter open_meet() throws SQLException {
		odb = dbh_meet.getWritableDatabase();
		return this;
	}

	public DataBaseAdapter open_entr() throws SQLException {
		odb = dbh_entr.getWritableDatabase();
		return this;
	}

	public DataBaseAdapter open_edu() throws SQLException {
		odb = dbh_edu.getWritableDatabase();
		return this;
	}

	public DataBaseAdapter open_celle() throws SQLException {
		odb = dbh_celle.getWritableDatabase();
		return this;
	}

	public DataBaseAdapter open_travel() throws SQLException {
		odb = dbh_trvl.getWritableDatabase();
		return this;
	}

	public DataBaseAdapter open_others() throws SQLException {
		odb = dbh_other.getWritableDatabase();
		return this;
	}

	public DataBaseAdapter open_note() throws SQLException {
		odb = dbh_note.getWritableDatabase();
		return this;
	}

	//
	public void close() {
		dbh.close();
	}

	public void close_meet() {
		dbh_meet.close();
	}

	public void close_entr() {
		dbh_entr.close();
	}

	public void close_edu() {
		dbh_edu.close();
	}

	public void close_celle() {
		dbh_celle.close();
	}

	public void close_travel() {
		dbh_trvl.close();
	}

	public void close_others() {
		dbh_other.close();
	}

	public void close_note() {
		dbh_note.close();
	}

	//
	public long addbill_info(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency, String note,String cale)
			throws SQLException {
		// Log.d("", col1);
		// Log.d("", col2);

		ContentValues IV = new ContentValues();

		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		return odb.insert(DATABASE_TABLE_Bill, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addmeet_info(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency, String note,String cale)
			throws SQLException {
		// Log.d("", col1);
		// Log.d("", col2);

		ContentValues IV = new ContentValues();

		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		
		return odb.insert(DATABASE_TABLE_MEETING, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addcelle_info(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency, String note,String cale)
			throws SQLException {
		// Log.d("", col1);
		// Log.d("", col2);

		ContentValues IV = new ContentValues();

		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		return odb.insert(DATABASE_TABLE_CELEBRATION, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addedu_info(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency, String note,String cale)
			throws SQLException {
		// Log.d("", col1);
		// Log.d("", col2);

		ContentValues IV = new ContentValues();

		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		return odb.insert(DATABASE_TABLE_EDUCATION, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addenter_info(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String place,
			String frequency, String note,String cale) throws SQLException {
		// Log.d("", col1);
		// Log.d("", col2);

		ContentValues IV = new ContentValues();

		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_place, place);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		return odb.insert(DATABASE_TABLE_ENTERTAINMENT, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addtravel_info(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String s_place,
			String des_place, String frequency, String note,String cale)
			throws SQLException {
		// Log.d("", col1);
		// Log.d("", col2);

		ContentValues IV = new ContentValues();

		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_st_place, s_place);
		IV.put(KEY_des_place, des_place);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		return odb.insert(DATABASE_TABLE_TRAVEL, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addothers_info(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency, String note,String cale)
			throws SQLException {
		// Log.d("", col1);
		// Log.d("", col2);

		ContentValues IV = new ContentValues();

		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		return odb.insert(DATABASE_TABLE_OTHERS, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addnote_info(String date, String title, String note)
			throws SQLException {
	
		ContentValues IV = new ContentValues();

		IV.put(KEY_Date, date);
		IV.put(KEY_TITLE, title);
		IV.put(KEY_NOTE, note);
		return odb.insert(DATABASE_TABLE_NOTE, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public void updateRow_note(String date, String title, String note,
			String dat, String tit, String not) {
		ContentValues values = new ContentValues();

		values.put(KEY_Date, date);
		values.put(KEY_TITLE, title);
		values.put(KEY_NOTE, note);

		try {
			odb.update(DATABASE_TABLE_NOTE, values, KEY_Date + "=? and  "
					+ KEY_TITLE + "=? and  " + KEY_NOTE + "=?", new String[] {
					dat, tit, not });
		} catch (Exception e) {
		}
	}

	//
	public void updateRow_bill(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency,
			String note,String cale, String titl, String categ, String sdate, String stime,
			String st_class, String edate, String etime, String et_class,
			String freq, String not) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_CATEGORY, category);
		values.put(KEY_STARTDATE, startdate);
		values.put(KEY_STARTTIME, starttime);
		values.put(KEY_STARTTIME_CLASS, starttime_class);
		values.put(KEY_ENDDATE, enddate);
		values.put(KEY_ENDTIME, endtime);
		values.put(KEY_ENDTIME_CLASS, endtime_class);
		values.put(KEY_FREQUNCEY, frequency);
		values.put(KEY_NOTE, note);
		values.put(KEY_Calendar, cale);

		try {
			odb.update(DATABASE_TABLE_Bill, values, KEY_TITLE + "=? and  "
					+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
					+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS
					+ "=? and  " + KEY_ENDDATE + "=? and  " + KEY_ENDTIME
					+ "=? and  " + KEY_ENDTIME_CLASS + "=? and  "
					+ KEY_FREQUNCEY + "=? and  " + KEY_NOTE + "=?",
					new String[] { titl, categ, sdate, stime, st_class, edate,
							etime, et_class, freq, not });
		} catch (Exception e) {
		}
	}

	public void updateRow_cele(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency,
			String note,String cale, String titl, String categ, String sdate, String stime,
			String st_class, String edate, String etime, String et_class,
			String freq, String not) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_CATEGORY, category);
		values.put(KEY_STARTDATE, startdate);
		values.put(KEY_STARTTIME, starttime);
		values.put(KEY_STARTTIME_CLASS, starttime_class);
		values.put(KEY_ENDDATE, enddate);
		values.put(KEY_ENDTIME, endtime);
		values.put(KEY_ENDTIME_CLASS, endtime_class);
		values.put(KEY_FREQUNCEY, frequency);
		values.put(KEY_NOTE, note);
		values.put(KEY_Calendar, cale);

		try {
			odb.update(DATABASE_TABLE_CELEBRATION, values,
					KEY_TITLE + "=? and  " + KEY_CATEGORY + "=? and  "
							+ KEY_STARTDATE + "=? and  " + KEY_STARTTIME
							+ "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
							+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME
							+ "=? and  " + KEY_ENDTIME_CLASS + "=? and  "
							+ KEY_FREQUNCEY + "=? and  " + KEY_NOTE + "=?",
					new String[] { titl, categ, sdate, stime, st_class, edate,
							etime, et_class, freq, not });
		} catch (Exception e) {
		}
	}

	public void updateRow_meet(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency,
			String note,String cale, String titl, String categ, String sdate, String stime,
			String st_class, String edate, String etime, String et_class,
			String freq, String not) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_CATEGORY, category);
		values.put(KEY_STARTDATE, startdate);
		values.put(KEY_STARTTIME, starttime);
		values.put(KEY_STARTTIME_CLASS, starttime_class);
		values.put(KEY_ENDDATE, enddate);
		values.put(KEY_ENDTIME, endtime);
		values.put(KEY_ENDTIME_CLASS, endtime_class);
		values.put(KEY_FREQUNCEY, frequency);
		values.put(KEY_NOTE, note);
		values.put(KEY_Calendar, cale);
		try {
			odb.update(DATABASE_TABLE_MEETING, values, KEY_TITLE + "=? and  "
					+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
					+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS
					+ "=? and  " + KEY_ENDDATE + "=? and  " + KEY_ENDTIME
					+ "=? and  " + KEY_ENDTIME_CLASS + "=? and  "
					+ KEY_FREQUNCEY + "=? and  " + KEY_NOTE + "=?",
					new String[] { titl, categ, sdate, stime, st_class, edate,
							etime, et_class, freq, not });
		} catch (Exception e) {
		}
	}

	public void updateRow_edu(String title, String category, String startdate,
			String starttime, String starttime_class, String enddate,
			String endtime, String endtime_class, String frequency,
			String note,String cale, String titl, String categ, String sdate, String stime,
			String st_class, String edate, String etime, String et_class,
			String freq, String not) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_CATEGORY, category);
		values.put(KEY_STARTDATE, startdate);
		values.put(KEY_STARTTIME, starttime);
		values.put(KEY_STARTTIME_CLASS, starttime_class);
		values.put(KEY_ENDDATE, enddate);
		values.put(KEY_ENDTIME, endtime);
		values.put(KEY_ENDTIME_CLASS, endtime_class);
		values.put(KEY_FREQUNCEY, frequency);
		values.put(KEY_NOTE, note);
		values.put(KEY_Calendar, cale);
		try {
			odb.update(DATABASE_TABLE_EDUCATION, values, KEY_TITLE + "=? and  "
					+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
					+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS
					+ "=? and  " + KEY_ENDDATE + "=? and  " + KEY_ENDTIME
					+ "=? and  " + KEY_ENDTIME_CLASS + "=? and  "
					+ KEY_FREQUNCEY + "=? and  " + KEY_NOTE + "=?",
					new String[] { titl, categ, sdate, stime, st_class, edate,
							etime, et_class, freq, not });
		} catch (Exception e) {
		}
	}

	public void updateRow_other(String title, String category,
			String startdate, String starttime, String starttime_class,
			String enddate, String endtime, String endtime_class,
			String frequency, String note,String cale, String titl, String categ,
			String sdate, String stime, String st_class, String edate,
			String etime, String et_class, String freq, String not) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_CATEGORY, category);
		values.put(KEY_STARTDATE, startdate);
		values.put(KEY_STARTTIME, starttime);
		values.put(KEY_STARTTIME_CLASS, starttime_class);
		values.put(KEY_ENDDATE, enddate);
		values.put(KEY_ENDTIME, endtime);
		values.put(KEY_ENDTIME_CLASS, endtime_class);
		values.put(KEY_FREQUNCEY, frequency);
		values.put(KEY_NOTE, note);
		values.put(KEY_Calendar, cale);
		try {
			odb.update(DATABASE_TABLE_OTHERS, values, KEY_TITLE + "=? and  "
					+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
					+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS
					+ "=? and  " + KEY_ENDDATE + "=? and  " + KEY_ENDTIME
					+ "=? and  " + KEY_ENDTIME_CLASS + "=? and  "
					+ KEY_FREQUNCEY + "=? and  " + KEY_NOTE + "=?",
					new String[] { titl, categ, sdate, stime, st_class, edate,
							etime, et_class, freq, not });
		} catch (Exception e) {
		}
	}

	public void updateRow_enter(String title, String category,
			String startdate, String starttime, String starttime_class,
			String enddate, String endtime, String endtime_class, String place,
			String frequency, String note,String cale, String titl, String categ,
			String sdate, String stime, String st_class, String edate,
			String etime, String et_class, String pla, String freq, String not) {
		ContentValues IV = new ContentValues();
		IV.put(KEY_TITLE, title);
		IV.put(KEY_CATEGORY, category);
		IV.put(KEY_STARTDATE, startdate);
		IV.put(KEY_STARTTIME, starttime);
		IV.put(KEY_STARTTIME_CLASS, starttime_class);
		IV.put(KEY_ENDDATE, enddate);
		IV.put(KEY_ENDTIME, endtime);
		IV.put(KEY_ENDTIME_CLASS, endtime_class);
		IV.put(KEY_place, place);
		IV.put(KEY_FREQUNCEY, frequency);
		IV.put(KEY_NOTE, note);
		IV.put(KEY_Calendar, cale);
		try {
			odb.update(DATABASE_TABLE_ENTERTAINMENT, IV,
					KEY_TITLE + "=? and  " + KEY_CATEGORY + "=? and  "
							+ KEY_STARTDATE + "=? and  " + KEY_STARTTIME
							+ "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
							+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME
							+ "=? and  " + KEY_ENDTIME_CLASS + "=? and  "
							+ KEY_place + "=? and  " + KEY_FREQUNCEY
							+ "=? and  " + KEY_NOTE + "=?", new String[] {
							titl, categ, sdate, stime, st_class, edate, etime,
							et_class, pla, freq, not });
		} catch (Exception e) {
		}
	}

	public void updateRow_travel(String title, String category,
			String startdate, String starttime, String starttime_class,
			String enddate, String endtime, String endtime_class,
			String s_place, String des_place, String frequency, String note,String cale,
			String titl, String categ, String sdate, String stime,
			String st_class, String edate, String etime, String et_class,
			String s_pla, String des_pla, String freq, String not) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_CATEGORY, category);
		values.put(KEY_STARTDATE, startdate);
		values.put(KEY_STARTTIME, starttime);
		values.put(KEY_STARTTIME_CLASS, starttime_class);
		values.put(KEY_ENDDATE, enddate);
		values.put(KEY_ENDTIME, endtime);
		values.put(KEY_ENDTIME_CLASS, endtime_class);
		values.put(KEY_st_place, s_place);
		values.put(KEY_des_place, des_place);
		values.put(KEY_FREQUNCEY, frequency);
		values.put(KEY_NOTE, note);
		values.put(KEY_Calendar, cale);
		try {
			odb.update(DATABASE_TABLE_TRAVEL, values, KEY_TITLE + "=? and  "
					+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
					+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS
					+ "=? and  " + KEY_ENDDATE + "=? and  " + KEY_ENDTIME
					+ "=? and  " + KEY_ENDTIME_CLASS + "=? and  "
					+ KEY_st_place + "=? and  " + KEY_des_place + "=? and  "
					+ KEY_FREQUNCEY + "=? and  " + KEY_NOTE + "=?",
					new String[] { titl, categ, sdate, stime, st_class, edate,
							etime, et_class, s_pla, des_pla, freq, not });
		} catch (Exception e) {
		}
	}

	public boolean delete_bill() {
		return odb.delete(DATABASE_TABLE_Bill, null, null) > 0;
	}

	public boolean delete_note() {
		return odb.delete(DATABASE_TABLE_NOTE, null, null) > 0;
	}

	public boolean delete_row_note(String date, String title, String note) {
		return odb.delete(DATABASE_TABLE_NOTE, KEY_Date + "=? and  "
				+ KEY_TITLE + "=? and  " + KEY_NOTE + "=?", new String[] {
				date, title, note }) > 0;
	}

	public boolean delete_row_bill(String titl, String categ, String sdate,
			String stime, String st_class, String edate, String etime,
			String et_class, String freq, String not) {
		return odb.delete(DATABASE_TABLE_Bill, KEY_TITLE + "=? and  "
				+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
				+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
				+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME + "=? and  "
				+ KEY_ENDTIME_CLASS + "=? and  " + KEY_FREQUNCEY + "=? and  "
				+ KEY_NOTE + "=?", new String[] { titl, categ, sdate, stime,
				st_class, edate, etime, et_class, freq, not }) > 0;
	}

	public boolean delete_row_meet(String titl, String categ, String sdate,
			String stime, String st_class, String edate, String etime,
			String et_class, String freq, String not) {
		return odb.delete(DATABASE_TABLE_MEETING, KEY_TITLE + "=? and  "
				+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
				+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
				+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME + "=? and  "
				+ KEY_ENDTIME_CLASS + "=? and  " + KEY_FREQUNCEY + "=? and  "
				+ KEY_NOTE + "=?", new String[] { titl, categ, sdate, stime,
				st_class, edate, etime, et_class, freq, not }) > 0;
	}

	public boolean delete_row_cele(String titl, String categ, String sdate,
			String stime, String st_class, String edate, String etime,
			String et_class, String freq, String not) {
		return odb.delete(DATABASE_TABLE_CELEBRATION, KEY_TITLE + "=? and  "
				+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
				+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
				+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME + "=? and  "
				+ KEY_ENDTIME_CLASS + "=? and  " + KEY_FREQUNCEY + "=? and  "
				+ KEY_NOTE + "=?", new String[] { titl, categ, sdate, stime,
				st_class, edate, etime, et_class, freq, not }) > 0;
	}

	public boolean delete_row_edu(String titl, String categ, String sdate,
			String stime, String st_class, String edate, String etime,
			String et_class, String freq, String not) {
		return odb.delete(DATABASE_TABLE_EDUCATION, KEY_TITLE + "=? and  "
				+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
				+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
				+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME + "=? and  "
				+ KEY_ENDTIME_CLASS + "=? and  " + KEY_FREQUNCEY + "=? and  "
				+ KEY_NOTE + "=?", new String[] { titl, categ, sdate, stime,
				st_class, edate, etime, et_class, freq, not }) > 0;
	}

	public boolean delete_row_other(String titl, String categ, String sdate,
			String stime, String st_class, String edate, String etime,
			String et_class, String freq, String not) {
		return odb.delete(DATABASE_TABLE_OTHERS, KEY_TITLE + "=? and  "
				+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
				+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
				+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME + "=? and  "
				+ KEY_ENDTIME_CLASS + "=? and  " + KEY_FREQUNCEY + "=? and  "
				+ KEY_NOTE + "=?", new String[] { titl, categ, sdate, stime,
				st_class, edate, etime, et_class, freq, not }) > 0;
	}

	public boolean delete_row_enter(String titl, String categ, String sdate,
			String stime, String st_class, String edate, String etime,
			String et_class, String place, String freq, String not) {
		return odb.delete(DATABASE_TABLE_ENTERTAINMENT, KEY_TITLE + "=? and  "
				+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
				+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
				+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME + "=? and  "
				+ KEY_ENDTIME_CLASS + "=? and  " + KEY_place + "=? and  "
				+ KEY_FREQUNCEY + "=? and  " + KEY_NOTE + "=?", new String[] {
				titl, categ, sdate, stime, st_class, edate, etime, et_class,
				place, freq, not }) > 0;
	}

	public boolean delete_row_travel(String titl, String categ, String sdate,
			String stime, String st_class, String edate, String etime,
			String et_class, String splace, String dplace, String freq,
			String not) {
		return odb.delete(DATABASE_TABLE_TRAVEL, KEY_TITLE + "=? and  "
				+ KEY_CATEGORY + "=? and  " + KEY_STARTDATE + "=? and  "
				+ KEY_STARTTIME + "=? and  " + KEY_STARTTIME_CLASS + "=? and  "
				+ KEY_ENDDATE + "=? and  " + KEY_ENDTIME + "=? and  "
				+ KEY_ENDTIME_CLASS + "=? and  " + KEY_st_place + "=? and  "
				+ KEY_des_place + "=? and  " + KEY_FREQUNCEY + "=? and  "
				+ KEY_NOTE + "=?", new String[] { titl, categ, sdate, stime,
				st_class, edate, etime, et_class, splace, dplace, freq, not }) > 0;
	}

	//
	public Cursor getAllTitles_bill() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_Bill, null);
	}

	public Cursor getAllTitles_meet() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_MEETING, null);
	}

	public Cursor getAllTitles_celle() {
		// using simple SQL query
		return odb
				.rawQuery("select * from " + DATABASE_TABLE_CELEBRATION, null);
	}

	public Cursor getAllTitles_edu() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_EDUCATION, null);
	}

	public Cursor getAllTitles_enter() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_ENTERTAINMENT,
				null);
	}

	public Cursor getAllTitles_travel() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_TRAVEL, null);
	}

	public Cursor getAllTitles_others() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_OTHERS, null);
	}

	public Cursor getAllTitles_note() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_NOTE, null);
	}

	public Cursor getrow_note(String date, String title, String note) {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_NOTE + " WHERE "
				+ KEY_Date + "='" + date + "' and  " + KEY_TITLE + "='" + title
				+ "' and  " + KEY_NOTE + "='" + note + "'", null);
	}

	//
	public Cursor getallCols_bill(String id) throws SQLException {
		Cursor mCursor = odb.query(DATABASE_TABLE_Bill, new String[] {
				KEY_TITLE, KEY_CATEGORY, KEY_STARTDATE, KEY_STARTTIME,
				KEY_STARTTIME_CLASS, KEY_ENDDATE, KEY_ENDTIME,
				KEY_ENDTIME_CLASS, KEY_FREQUNCEY, KEY_NOTE }, null, null, null,
				null, null);
		Log.e("getallcols zmv", "opening successfull");
		return mCursor;
	}

	public Cursor getColsById_bill(String id) throws SQLException {
		Cursor mCursor = odb.query(DATABASE_TABLE_Bill, new String[] {
				KEY_TITLE, KEY_CATEGORY, KEY_STARTDATE, KEY_STARTTIME,
				KEY_STARTTIME_CLASS, KEY_ENDDATE, KEY_ENDTIME,
				KEY_ENDTIME_CLASS, KEY_FREQUNCEY, KEY_NOTE }, KEY_ROWID + " = "
				+ id, null, null, null, null);
		Log.e("getallcols zmv", "opening successfull");
		return mCursor;
	}
}
