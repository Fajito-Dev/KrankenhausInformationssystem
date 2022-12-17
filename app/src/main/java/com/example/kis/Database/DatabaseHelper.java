package com.example.kis.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.kis.Models.PatientModel;
import com.example.kis.Models.EntryModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

//neu
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String PATIENT_TABLE = "PATIENT_TABLE";
    public static final String COLUMN_PATIENT_PATIENTID = "PATIENT_PATIENTID";
    public static final String COLUMN_PATIENT_PRENAME = "PATIENT_PRENAME";
    public static final String COLUMN_PATIENT_NAME = "PATIENT_NAME";
    public static final String COLUMN_PATIENT_BIRTHDATE = "PATIENT_BIRTHDATE";

    public static final String ENTRY_TABLE = "ENTRY_TABLE";
    public static final String COLUMN_ENTRY_EINTRAGID = "ENTRY_EINTRAGID";
    public static final String COLUMN_ENTRY_PATIENTIDE = "ENTRY_PATIENTIDE";
    public static final String COLUMN_ENTRY_DATE = "ENTRY_DATE";
    public static final String COLUMN_ENTRY_BEDNR = "ENTRY_BEDNR";
    public static final String COLUMN_ENTRY_VISITED = "ENTRY_VISITED";
    public static final String COLUMN_ENTRY_CONDITION= "ENTRY_CONDITION";
    public static final String COLUMN_ENTRY_MRT = "ENTRY_MRT";
    public static final String COLUMN_ENTRY_BLOODTEST = "ENTRY_BLOODTEST";
    public static final String COLUMN_ENTRY_NOTE = "ENTRY_NOTE";
    public static final String COLUMN_ENTRY_LEUKONL = "ENTRY_LEUKONL";
    public static final String COLUMN_ENTRY_LYMPHOPROZENT = "ENTRY_LYMPHOPROZENT";
    public static final String COLUMN_ENTRY_LYMPHOABSOLUT = "ENTRY_LYMPHOABSOLUT";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Krankenakten.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement1 = "CREATE TABLE " + PATIENT_TABLE + " (" + COLUMN_PATIENT_PATIENTID + " INTEGER PRIMARY KEY, " + COLUMN_PATIENT_PRENAME + " TEXT, " + COLUMN_PATIENT_NAME + " TEXT, " + COLUMN_PATIENT_BIRTHDATE + " INT)";
        String createTableStatement2 = "CREATE TABLE " + ENTRY_TABLE + " (" + COLUMN_ENTRY_EINTRAGID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ENTRY_PATIENTIDE + " INT, " + COLUMN_ENTRY_DATE + " INT, " + COLUMN_ENTRY_BEDNR + " INT, " + COLUMN_ENTRY_VISITED + " BOOL, " + COLUMN_ENTRY_CONDITION + " TEXT, " + COLUMN_ENTRY_MRT + " BOOL, " + COLUMN_ENTRY_BLOODTEST + " BOOL, " + COLUMN_ENTRY_NOTE + " TEXT, " + COLUMN_ENTRY_LEUKONL + " FLOAT, " + COLUMN_ENTRY_LYMPHOPROZENT + " FLOAT, " + COLUMN_ENTRY_LYMPHOABSOLUT + " FLOAT)";

        db.execSQL(createTableStatement1);
        db.execSQL(createTableStatement2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean addPatient(PatientModel patientModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PATIENT_PATIENTID, patientModel.getPatientId());
        cv.put(COLUMN_PATIENT_PRENAME, patientModel.getPreName());
        cv.put(COLUMN_PATIENT_NAME, patientModel.getName());
        cv.put(COLUMN_PATIENT_BIRTHDATE, patientModel.getBirthDate());

        long insert = db.insert(PATIENT_TABLE, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<PatientModel> getEveryPatient(){
        ArrayList<PatientModel> returnList = new ArrayList<>();
        //get data from the database
        String queryString = "SELECT * FROM "+ PATIENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop thru the cursor(result set) and creat a new customer object put them in the return List
            do{
                int patientID = cursor.getInt(0);
                String patientPreName = cursor.getString(1);
                String patientName = cursor.getString(2);
                int patientBirthdate = cursor.getInt(3);

                PatientModel newPatient = new PatientModel(patientID,patientPreName,patientName,patientBirthdate);
                returnList.add(newPatient);
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addEntry(EntryModel entryModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ENTRY_PATIENTIDE, entryModel.getPatientIde());
        cv.put(COLUMN_ENTRY_DATE, entryModel.getDate());
        cv.put(COLUMN_ENTRY_BEDNR, entryModel.getBedNr());
        cv.put(COLUMN_ENTRY_VISITED, entryModel.isVisited());
        cv.put(COLUMN_ENTRY_CONDITION, entryModel.getCondition());
        cv.put(COLUMN_ENTRY_MRT, entryModel.isMrt());
        cv.put(COLUMN_ENTRY_BLOODTEST, entryModel.isBloodtest());
        cv.put(COLUMN_ENTRY_NOTE, entryModel.getNote());
        cv.put(COLUMN_ENTRY_LEUKONL, entryModel.getLeukoNl());
        cv.put(COLUMN_ENTRY_LYMPHOPROZENT, entryModel.getLymphoProzent());
        cv.put(COLUMN_ENTRY_LYMPHOABSOLUT, entryModel.getLymphoAbsolut());

        long insert = db.insert(ENTRY_TABLE, null, cv);

        if(insert == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<EntryModel> getEveryEntry(){
        ArrayList<EntryModel> returnList2 = new ArrayList<>();
        //get data from the database
        String queryString = "SELECT * FROM "+ ENTRY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop thru the cursor(result set) and creat a new customer object put them in the return List
            do{
                int eintragId = cursor.getInt(0);
                int patientIDE = cursor.getInt(1);
                int date = cursor.getInt(2);
                int bedNr = cursor.getInt(3);
                boolean visited = cursor.getInt(4) == 1 ? true: false;
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);

                EntryModel newEntry = new EntryModel(eintragId,patientIDE,date,bedNr,visited,condition,mrt,bloodtest,note,leukoNl,lymphoProzent,lymphoAbsolut);
                returnList2.add(newEntry);
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return returnList2;
    }
}

