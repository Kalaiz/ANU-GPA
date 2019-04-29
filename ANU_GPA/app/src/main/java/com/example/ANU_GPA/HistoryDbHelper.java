package com.example.ANU_GPA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HistoryDbHelper extends SQLiteOpenHelper {

    private static int DATABASEVERSION = 1;
    private static String DATABASENAME = "notes.db";
    private static String TABLENAME = "notes";

    private static String ID = "id";
    private static String Number_Of_Courses_done_value = "course_done";
    private static String Current_GPA_value = "current_gpa";
    private static String GPA_wanted_to_achieve_value = "gpa_wanted_to_achieve";
    private static String Courses_left_value = "courses_left";


    public HistoryDbHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLENAME +
                "("
                + ID + " INTEGER PRIMARY KEY,"
                + Number_Of_Courses_done_value + " TEXT,"
                + Current_GPA_value + " TEXT,"
                + GPA_wanted_to_achieve_value + " TEXT,"
                + Courses_left_value + " TEXT"
                //This line is correct
                + ");";

        db.execSQL(CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        // Create tables again
        onCreate(db);
    }

    // Adding new customer
    public void insertUser(notes note) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(Number_Of_Courses_done_value, note.getNumber_of_courses_done_value());
        values.put(Current_GPA_value, note.getCurrent_GPA_value());
        values.put(GPA_wanted_to_achieve_value, note.getGPA_want_to_achieve_value());
        values.put(Courses_left_value, note.getCourses_left_value());

        System.out.println("------values added are------ " + values);
        // Inserting Row
        db.insert(TABLENAME, null, values);
        System.out.println("------database created is------ " + db);

        db.close(); // Closing database connection
    }

    public void getUser(notes note){
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(Number_Of_Courses_done_value, note.getNumber_of_courses_done_value());
        values.put(Current_GPA_value, note.getCurrent_GPA_value());
        values.put(GPA_wanted_to_achieve_value, note.getGPA_want_to_achieve_value());
        values.put(Courses_left_value, note.getCourses_left_value());

        System.out.println("------values added are------ " + values);
        // Inserting Row
        db.insert(TABLENAME, null, values);
        System.out.println("------database created is------ " + db);

        db.close(); // Closing database connection
    }

    public ArrayList<notes> getAllNotes() {

        ArrayList<notes> notes1 = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLENAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                notes notes2 = new notes();
                notes2.setNumber_of_courses_done_value(cursor.getString(1));
                notes2.setCurrent_GPA_value(cursor.getString(2));
                notes2.setGPA_want_to_achieve_value(cursor.getString(3));
                notes2.setCourses_left_value(cursor.getString(4));
                notes1.add(notes2);
            } while (cursor.moveToNext());
        }
        return notes1;
    }

    public notes getUsersByName(notes not) {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLENAME + "  where username= " + not.getNumber_of_courses_done_value();
        // Should find a variable for username that is unique
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        notes notes3 = new notes();
        if (cursor.moveToFirst()) {
            do {
                notes3.setNumber_of_courses_done_value(cursor.getString(1));
                notes3.setCurrent_GPA_value(cursor.getString(2));
                notes3.setGPA_want_to_achieve_value(cursor.getString(3));
                notes3.setCourses_left_value(cursor.getString(4));

            } while (cursor.moveToNext());
        }
        return notes3;
    }

    public boolean Is_Avaliable(String username) {

        ArrayList<String> strings = GetAllUsers();
        if(strings != null || strings.size()>0)
        {
            if(strings.contains(username)){
                return true;
            }
            else{
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public ArrayList<String> GetAllUsers(){
        ArrayList<String> getallnotes = new ArrayList<>();
        ArrayList<notes> allNotes = this.getAllNotes();

        for(int i=0; i< allNotes.size();i++){
            getallnotes.add(allNotes.get(i).getNumber_of_courses_done_value());
            getallnotes.add(allNotes.get(i).getCurrent_GPA_value());
            getallnotes.add(allNotes.get(i).getGPA_want_to_achieve_value());
            getallnotes.add(allNotes.get(i).getCourses_left_value());
        }

        return getallnotes;
    }
}
