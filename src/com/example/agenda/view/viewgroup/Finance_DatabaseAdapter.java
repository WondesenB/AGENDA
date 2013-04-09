package com.example.agenda.view.viewgroup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Finance_DatabaseAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_FOOD = "food";
	public static final String KEY_TRANSPORT = "transport";
	public static final String KEY_FAMILLY = "familly";
	public static final String KEY_HOME = "home";
	public static final String KEY_BILLING = "billing";
	public static final String KEY_EDUCATION = "education";
	public static final String KEY_ENTERTAINMENT = "entertainment";
	public static final String KEY_HEALTH = "health";
	public static final String KEY_OTHERS = "others";
	public static final String KEY_DATE = "date";
	//
	public static final String KEY_SALARY = "salary";
	public static final String KEY_BUSINESS = "business";
	public static final String KEY_PERTIME = "pertime";
	//
	private static final String DATABASE_BUDGET = "mydbbudget";
	private static final String DATABASE_TABLE_BUDGET = "mytablebudget";
	//
	private static final String DATABASE_EXPENSE = "mydbexpense";
	private static final String DATABASE_TABLE_EXPENSE = "mytableexpense";
	//
	private static final String DATABASE_INCOME = "mydbincome";
	private static final String DATABASE_TABLE_INCOME = "mytableincome";
	//
	private static final int DATABASE_VERSION = 1;

	private final Context ourContext;
	private DbHelperBudget dbh_budget;
	private DbHelperExpense dbh_expense;
	private DbHelperIncome dbh_income;
	private SQLiteDatabase odb;
	//
	private static final String CREATE_TABLE_BUDGET = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_BUDGET
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_FOOD
			+ " REAL , "
			+ KEY_TRANSPORT
			+ " REAL , "
			+ KEY_FAMILLY
			+ " REAL , "
			+ KEY_HOME
			+ " REAL , "
			+ KEY_BILLING
			+ " REAL , "
			+ KEY_EDUCATION
			+ " REAL , "
			+ KEY_ENTERTAINMENT
			+ " REAL , "
			+ KEY_HEALTH
			+ " REAL , " + KEY_OTHERS + " REAL )";
	private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_EXPENSE
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_FOOD
			+ " REAL , "
			+ KEY_TRANSPORT
			+ " REAL , "
			+ KEY_FAMILLY
			+ " REAL , "
			+ KEY_HOME
			+ " REAL , "
			+ KEY_BILLING
			+ " REAL, "
			+ KEY_EDUCATION
			+ " REAL , "
			+ KEY_ENTERTAINMENT
			+ " REAL , "
			+ KEY_HEALTH
			+ " REAL , "
			+ KEY_OTHERS
			+ " REAL , " + KEY_DATE + " VARCHAR(150))";
	private static final String CREATE_TABLE_INCOME = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_INCOME
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_SALARY
			+ " REAL , "
			+ KEY_BUSINESS
			+ " REAL , "
			+ KEY_PERTIME
			+ " REAL , "
			+ KEY_OTHERS
			+ " REAL )";

	public Finance_DatabaseAdapter(Context c) {
		ourContext = c;
		dbh_budget = new DbHelperBudget(ourContext);
		dbh_expense = new DbHelperExpense(ourContext);
		dbh_income = new DbHelperIncome(ourContext);

	}

	private static class DbHelperBudget extends SQLiteOpenHelper {

		public DbHelperBudget(Context context) {
			super(context, DATABASE_BUDGET, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_BUDGET);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperExpense extends SQLiteOpenHelper {

		public DbHelperExpense(Context context) {
			super(context, DATABASE_EXPENSE, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_EXPENSE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	private static class DbHelperIncome extends SQLiteOpenHelper {

		public DbHelperIncome(Context context) {
			super(context, DATABASE_INCOME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_INCOME);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// if DATABASE VERSION changes
			// Drop old tables and call super.onCreate()
		}
	}

	//
	public Finance_DatabaseAdapter open_budget() throws SQLException {
		odb = dbh_budget.getWritableDatabase();
		return this;
	}

	public Finance_DatabaseAdapter open_expense() throws SQLException {
		odb = dbh_expense.getWritableDatabase();
		return this;
	}

	public Finance_DatabaseAdapter open_income() throws SQLException {
		odb = dbh_income.getWritableDatabase();
		return this;
	}

	//
	public void close_budget() {
		dbh_budget.close();
	}

	public void close_expense() {
		dbh_expense.close();
	}

	public void close_income() {
		dbh_income.close();
	}

	//
	public long addbudget_info(Double food, Double transport, Double familly,
			Double home, Double billing, Double education,
			Double entertainment, Double health, Double others)
			throws SQLException {

		ContentValues IV = new ContentValues();

		IV.put(KEY_FOOD, food);
		IV.put(KEY_TRANSPORT, transport);
		IV.put(KEY_FAMILLY, familly);
		IV.put(KEY_HOME, home);
		IV.put(KEY_BILLING, billing);
		IV.put(KEY_EDUCATION, education);
		IV.put(KEY_ENTERTAINMENT, entertainment);
		IV.put(KEY_HEALTH, health);
		IV.put(KEY_OTHERS, others);

		return odb.insert(DATABASE_TABLE_BUDGET, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addexpense_info(Double food, Double transport, Double familly,
			Double home, Double billing, Double education,
			Double entertainment, Double health, Double others, String date)
			throws SQLException {

		ContentValues IV = new ContentValues();

		IV.put(KEY_FOOD, food);
		IV.put(KEY_TRANSPORT, transport);
		IV.put(KEY_FAMILLY, familly);
		IV.put(KEY_HOME, home);
		IV.put(KEY_BILLING, billing);
		IV.put(KEY_EDUCATION, education);
		IV.put(KEY_ENTERTAINMENT, entertainment);
		IV.put(KEY_HEALTH, health);
		IV.put(KEY_OTHERS, others);
		IV.put(KEY_DATE, date);
		return odb.insert(DATABASE_TABLE_EXPENSE, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public long addincome_info(Double salary, Double business, Double pertime,
			Double others) throws SQLException {

		ContentValues IV = new ContentValues();

		IV.put(KEY_SALARY, salary);
		IV.put(KEY_BUSINESS, business);
		IV.put(KEY_PERTIME, pertime);
		IV.put(KEY_OTHERS, others);

		return odb.insert(DATABASE_TABLE_INCOME, null, IV);
		// returns a number >0 if inserting data is successful
	}

	public boolean delete_budget() {
		return odb.delete(DATABASE_TABLE_BUDGET, null, null) > 0;
	}
	public boolean delete_income() {
		return odb.delete(DATABASE_TABLE_INCOME, null, null) > 0;
	}
	public boolean delete_expense(String dat) {
		String whereClause = KEY_DATE + "= '" + dat + "'";
		return odb.delete(DATABASE_TABLE_EXPENSE, whereClause, null) > 0;
	}
	//
	public Cursor getAllTitles_budget() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_BUDGET, null);
	}

	public Cursor getAllTitles_expense() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_EXPENSE, null);
	}
	public Cursor getAllTitles_expense_special(String date, String month, String year) {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_EXPENSE+" where date='"+date+"/"+month+"/"+year+"'", null);
	}
	public Cursor getAllTitles_expense_report(String month) {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_EXPENSE+" where date LIKE '%/"+month+"/%'", null);
	}

	public Cursor getAllTitles_income() {
		// using simple SQL query
		return odb.rawQuery("select * from " + DATABASE_TABLE_INCOME, null);
	}

	//
	public void updateRow_budget(long rowID, Double food, Double transport,
			Double familly, Double home, Double billing, Double education,
			Double entertainment, Double health, Double others) {

		ContentValues values = new ContentValues();
		values.put(KEY_FOOD, food);
		values.put(KEY_TRANSPORT, transport);
		values.put(KEY_FAMILLY, familly);
		values.put(KEY_HOME, home);
		values.put(KEY_BILLING, billing);
		values.put(KEY_EDUCATION, education);
		values.put(KEY_ENTERTAINMENT, entertainment);
		values.put(KEY_HEALTH, health);
		values.put(KEY_OTHERS, others);

		try {
			odb.update(DATABASE_TABLE_BUDGET, values, KEY_ROWID + "=" + rowID,
					null);
		} catch (Exception e) {
		}
	}
}
