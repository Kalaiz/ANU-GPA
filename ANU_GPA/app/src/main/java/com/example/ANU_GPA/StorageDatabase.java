package com.example.ANU_GPA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/*Authorship Prateek Arora (u6742441)*/


public class StorageDatabase extends SQLiteOpenHelper {

    private static int DATABASEVERSION = 1;
    private static String DATABASENAME = "StorageData.db";
    private static String TABLENAME = "notes";
    private static String ID = "id";
    private static String Number_Of_Courses_done_value = "course_done";
    private static String Current_GPA_value = "current_gpa";
    private static String Current_Points = "current_points";
    private static String Grades = "grades";
    private static String Title = "title";


    public StorageDatabase(Context context) {
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
                + Current_Points + " TEXT,"
                + Grades + " TEXT,"
                + Title + " TEXT"
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
    public void insertUser(StorageData note) {
        //TODO Check whether there exist an database
        //  if  there does not exist one create one else use the existing one
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(Number_Of_Courses_done_value, note.getNumber_of_courses_done_value());
        values.put(Current_GPA_value, note.getCurrent_GPA_value());
        values.put(Current_Points, note.getCurrentPoints());
        values.put(Grades, note.getGrades());
        values.put(Title, note.getTitle());

        System.out.println("------values added are------ " + values);
        // Inserting Row
        db.insert(TABLENAME, null, values);
        System.out.println("------database created is------ " + db);

        db.close(); // Closing database connection
    }

    public void getUser(StorageData note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Number_Of_Courses_done_value, note.getNumber_of_courses_done_value());
        values.put(Current_GPA_value, note.getCurrent_GPA_value());
        values.put(Current_Points, note.getCurrentPoints());
        values.put(Grades, note.getGrades());
        values.put(Title, note.getTitle());


        System.out.println("------values added are------ " + values);
        // Inserting Row
        db.insert(TABLENAME, null, values);
        System.out.println("------database created is------ " + db);

        db.close(); // Closing database connection
    }

    public ArrayList<StorageData> getAllNotes() {

        ArrayList<StorageData> storageData1 = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLENAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StorageData storageData2 = new StorageData();
                storageData2.setNumber_of_courses_done_value(cursor.getString(1));
                storageData2.setCurrent_GPA_value(cursor.getString(2));
                storageData2.setCurrentPoints(cursor.getString(3));
                storageData2.setGrades(cursor.getString(4));
                storageData1.add(storageData2);
            } while (cursor.moveToNext());
        }
        return storageData1;
    }

    public StorageData getUsersByName(StorageData not) {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLENAME + "  where username= " + not.getNumber_of_courses_done_value();
        // Should find a variable for username that is unique
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        StorageData storageData3 = new StorageData();
        if (cursor.moveToFirst()) {
            do {
                storageData3.setNumber_of_courses_done_value(cursor.getString(1));
                storageData3.setCurrent_GPA_value(cursor.getString(2));
                storageData3.setCurrentPoints(cursor.getString(3));
                storageData3.setGrades(cursor.getString(4));

            } while (cursor.moveToNext());
        }
        return storageData3;
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
        ArrayList<StorageData> allNotes = this.getAllNotes();

        for(int i=0; i< allNotes.size();i++){
            getallnotes.add(allNotes.get(i).getNumber_of_courses_done_value());
            getallnotes.add(allNotes.get(i).getCurrent_GPA_value());
            getallnotes.add(allNotes.get(i).getCurrentPoints());
            getallnotes.add(allNotes.get(i).getGrades());
        }

        return getallnotes;
    }
}
