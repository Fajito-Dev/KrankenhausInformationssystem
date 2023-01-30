package com.example.kis.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.kis.Models.PatientModel;
import com.example.kis.Models.EntryModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
    public static final String COLUMN_ENTRY_IMAGE = "ENTRY_IMAGE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Krankenakten.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement1 = "CREATE TABLE " + PATIENT_TABLE + " (" + COLUMN_PATIENT_PATIENTID + " INTEGER PRIMARY KEY, " + COLUMN_PATIENT_PRENAME + " TEXT, " + COLUMN_PATIENT_NAME + " TEXT, " + COLUMN_PATIENT_BIRTHDATE + " TEXT)";
        String createTableStatement2 = "CREATE TABLE " + ENTRY_TABLE + " (" + COLUMN_ENTRY_EINTRAGID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ENTRY_PATIENTIDE + " INT, " + COLUMN_ENTRY_DATE + " TEXT, " + COLUMN_ENTRY_BEDNR + " INT, " + COLUMN_ENTRY_VISITED + " INT, " + COLUMN_ENTRY_CONDITION + " TEXT, " + COLUMN_ENTRY_MRT + " BOOL, " + COLUMN_ENTRY_BLOODTEST + " BOOL, " + COLUMN_ENTRY_NOTE + " TEXT, " + COLUMN_ENTRY_LEUKONL + " FLOAT, " + COLUMN_ENTRY_LYMPHOPROZENT + " FLOAT, " + COLUMN_ENTRY_LYMPHOABSOLUT + " FLOAT, " + COLUMN_ENTRY_IMAGE + " TEXT)";

        db.execSQL(createTableStatement1);
        db.execSQL(createTableStatement2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {}

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
        String queryString = "SELECT * FROM "+ PATIENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                int patientID = cursor.getInt(0);
                String patientPreName = cursor.getString(1);
                String patientName = cursor.getString(2);
                String patientBirthdate = cursor.getString(3);

                PatientModel newPatient = new PatientModel(patientID,patientPreName,patientName,patientBirthdate);
                returnList.add(newPatient);
            }while(cursor.moveToNext());
        }else{
        }
        cursor.close();
        db.close();
        return returnList;
    }

    //gitb Liste mit bestimmten Patient aus
    //Kann ich am besten einfach umschreiben indem ich anstatt Array List mit den PatientModel nehme und returnen lasse
    public ArrayList<PatientModel> getSpecificPatient(int patientIdDetails){
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
                String patientBirthdate = cursor.getString(3);

                if(patientID==patientIdDetails) {
                    PatientModel newPatient = new PatientModel(patientID, patientPreName, patientName, patientBirthdate);
                    returnList.add(newPatient);
                }
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
        cv.put(COLUMN_ENTRY_VISITED, entryModel.getVisited());
        cv.put(COLUMN_ENTRY_CONDITION, entryModel.getCondition());
        cv.put(COLUMN_ENTRY_MRT, entryModel.isMrt());
        cv.put(COLUMN_ENTRY_BLOODTEST, entryModel.isBloodtest());
        cv.put(COLUMN_ENTRY_NOTE, entryModel.getNote());
        cv.put(COLUMN_ENTRY_LEUKONL, entryModel.getLeukoNl());
        cv.put(COLUMN_ENTRY_LYMPHOPROZENT, entryModel.getLymphoProzent());
        cv.put(COLUMN_ENTRY_LYMPHOABSOLUT, entryModel.getLymphoAbsolut());
        cv.put(COLUMN_ENTRY_IMAGE, entryModel.getImage());

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
                String date = cursor.getString(2);
                int bedNr = cursor.getInt(3);
                int visited = cursor.getInt(4);
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);
                String image = cursor.getString(12);

                EntryModel newEntry = new EntryModel(eintragId,patientIDE,date,bedNr,visited,condition,mrt,bloodtest,note,leukoNl,lymphoProzent,lymphoAbsolut,image);
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
    //gibt Liste mit allen Einträgen von bestimmten Patienten aus
    public ArrayList<EntryModel> getPatientEntry(int patientIdDetails){
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
                String date = cursor.getString(2);
                int bedNr = cursor.getInt(3);
                int visited = cursor.getInt(4);
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);
                String image = cursor.getString(12);

                if(patientIDE==patientIdDetails) {
                    EntryModel newEntry = new EntryModel(eintragId, patientIDE, date, bedNr, visited, condition, mrt, bloodtest, note, leukoNl, lymphoProzent, lymphoAbsolut,image);
                    returnList2.add(newEntry);
                }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return returnList2;
    }


    //gibt Liste mit allen Einträgen von bestimmten Patienten aus
    public int getFreeBed(){
        int freeBed = 0;
        //get data from the database
        String queryString = "SELECT * FROM "+ ENTRY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        for(int i=1;i<=100;i++) {
            int test = 0;
            if (cursor.moveToFirst()) {
                //loop thru the cursor(result set) and creat a new customer object put them in the return List
                do {
                    int bedNr = cursor.getInt(3);

                    if(i==bedNr){
                    test = 1;
                    }
                } while (cursor.moveToNext());
            } else {
                //failure do not add anything to the List
            }
            if(test==0){
                //close both cursor and database
                freeBed = i;
                cursor.close();
                db.close();
                return freeBed;
            }
        }
        cursor.close();
        db.close();
        return freeBed;
    }

    public PatientModel getSpecificPatientModel (int patientIdDetails){
        PatientModel newPatient = null;
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
                String patientBirthdate = cursor.getString(3);

                if(patientID==patientIdDetails) {
                    newPatient = new PatientModel(patientID, patientPreName, patientName, patientBirthdate);
                    cursor.close();
                    db.close();
                    return newPatient;
                }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return newPatient;
    }

    //gibt Liste mit allen Einträgen von bestimmten Patienten aus
    //hier müsste man eigentlich immer den aktuellsten Eintrag abfragen
    public EntryModel getSpecificEntryModel(int patientIdDetails){
        EntryModel newEntry = null;
        //get data from the database
        String queryString = "SELECT * FROM "+ ENTRY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop thru the cursor(result set) and creat a new customer object put them in the return List
            do{
                int eintragId = cursor.getInt(0);
                int patientIDE = cursor.getInt(1);
                String date = cursor.getString(2);
                int bedNr = cursor.getInt(3);
                int visited = cursor.getInt(4);
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);
                String image = cursor.getString(12);

                if(patientIDE==patientIdDetails) {
                    newEntry = new EntryModel(eintragId, patientIDE, date, bedNr, visited, condition, mrt, bloodtest, note, leukoNl, lymphoProzent, lymphoAbsolut,image);
                    cursor.close();
                    db.close();
                    return newEntry;
                }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return newEntry;
    }
    //gibt Liste mit allen Einträgen von bestimmten Patienten aus
    //hier müsste man eigentlich immer den aktuellsten Eintrag abfragen
    public EntryModel getSpecificEntryModelEntryId(int entryIdDetails){
        EntryModel newEntry = null;
        //get data from the database
        String queryString = "SELECT * FROM "+ ENTRY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop thru the cursor(result set) and creat a new customer object put them in the return List
            do{
                int eintragId = cursor.getInt(0);
                int patientIDE = cursor.getInt(1);
                String date = cursor.getString(2);
                int bedNr = cursor.getInt(3);
                int visited = cursor.getInt(4);
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);
                String image = cursor.getString(12);

                if(eintragId==entryIdDetails) {
                    newEntry = new EntryModel(eintragId, patientIDE, date, bedNr, visited, condition, mrt, bloodtest, note, leukoNl, lymphoProzent, lymphoAbsolut,image);
                    cursor.close();
                    db.close();
                    return newEntry;
                }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return newEntry;
    }

    public ArrayList<EntryModel> getEntryLabor(){
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
                String date = cursor.getString(2);
                int bedNr = cursor.getInt(3);
                int visited = cursor.getInt(4);
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);
                String image = cursor.getString(12);

                if(visited != 2 == true){
                    if(mrt||bloodtest == true) {
                        EntryModel newEntry = new EntryModel(eintragId, patientIDE, date, bedNr, visited, condition, mrt, bloodtest, note, leukoNl, lymphoProzent, lymphoAbsolut, image);
                        returnList2.add(newEntry);
                    }
                }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return returnList2;
    }
    public ArrayList<EntryModel> getEntryLaborFilter(ArrayList<PatientModel> patientModelList){
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
                String date = cursor.getString(2);
                int bedNr = cursor.getInt(3);
                int visited = cursor.getInt(4);
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);
                String image = cursor.getString(12);

                if(visited != 2 == true){
                    if(mrt||bloodtest == true) {
                        for(int i = 0; i < patientModelList.size();i++){
                            if(patientModelList.get(i).getPatientId()==patientIDE) {
                                EntryModel newEntry = new EntryModel(eintragId, patientIDE, date, bedNr, visited, condition, mrt, bloodtest, note, leukoNl, lymphoProzent, lymphoAbsolut, image);
                                returnList2.add(newEntry);
                            }
                        }

                    }
                }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return returnList2;
    }

    public ArrayList<PatientModel> getEveryPatientBed(ArrayList<EntryModel> entryModelList){
        ArrayList<PatientModel> returnList = new ArrayList<>();
        int bedNrW = 0;
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
                String patientBirthdate = cursor.getString(3);

                for(int i = 0;i<entryModelList.size();i++){
                    if(entryModelList.get(i).getPatientIde()==patientID){
                        if(entryModelList.get(i).getBedNr()!=0){
                            bedNrW = 1;
                        }else{
                            bedNrW = 2;
                        }
                    }
                }
                    if(bedNrW==1) {
                        PatientModel newPatient = new PatientModel(patientID, patientPreName, patientName, patientBirthdate);
                        returnList.add(newPatient);
                    }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return returnList;
    }

    public void updateEntry(EntryModel entryModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ENTRY_VISITED, entryModel.getVisited());
        db.update(ENTRY_TABLE,contentValues,COLUMN_ENTRY_EINTRAGID + " = " + entryModel.getEintragId(),null);
        db.close();
    }


    public ArrayList<PatientModel> getEveryPatientVIsit(ArrayList<EntryModel> entryModelList){
        ArrayList<PatientModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM "+ PATIENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        if(cursor.moveToFirst()){
            do{
                int bedNrW = 0;
                int dateNr = 0;

                int patientID = cursor.getInt(0);
                String patientPreName = cursor.getString(1);
                String patientName = cursor.getString(2);
                String patientBirthdate = cursor.getString(3);

                for(int i = 0;i<entryModelList.size();i++){
                    if(entryModelList.get(i).getPatientIde()==patientID){
                        String thisDate = entryModelList.get(i).getDate();
                        if(date.equals(thisDate)) {
                            dateNr = 1;
                        }
                        if(entryModelList.get(i).getNote().equals("Patient eingewiesen") || entryModelList.get(i).getNote().equals("Auftrag bearbeitet")){
                            if(date.equals(thisDate)) {
                                dateNr = 0;
                            }
                        }
                        if(entryModelList.get(i).getBedNr()!=0){
                            bedNrW = 1;
                        }else{
                            bedNrW = 2;
                        }
                    }
                }
               if(dateNr == 0) {
                   if(bedNrW == 1) {
                       PatientModel newPatient = new PatientModel(patientID, patientPreName, patientName, patientBirthdate);
                       returnList.add(newPatient);
                   }
               }
            }while(cursor.moveToNext());
        }else{ }
        cursor.close();
        db.close();
        return returnList;
    }

    public String getImage(int entryIdDetails){
        String image ="";
        EntryModel newEntry = null;
        //get data from the database
        String queryString = "SELECT * FROM "+ ENTRY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop thru the cursor(result set) and creat a new customer object put them in the return List
            do{
                int eintragId = cursor.getInt(0);
                int patientIDE = cursor.getInt(1);
                String date = cursor.getString(2);
                int bedNr = cursor.getInt(3);
                int visited = cursor.getInt(4);
                String condition = cursor.getString(5);
                boolean mrt = cursor.getInt(6) == 1 ? true: false;
                boolean bloodtest = cursor.getInt(7) == 1 ? true: false;
                String note = cursor.getString(8);
                float leukoNl = cursor.getFloat(9);
                float lymphoProzent = cursor.getFloat(10);
                float lymphoAbsolut = cursor.getFloat(11);
                image = cursor.getString(12);

                if(eintragId==entryIdDetails) {
                    newEntry = new EntryModel(eintragId, patientIDE, date, bedNr, visited, condition, mrt, bloodtest, note, leukoNl, lymphoProzent, lymphoAbsolut,image);
                    cursor.close();
                    db.close();
                    return image;
                }
            }while(cursor.moveToNext());
        }else{
            //failure do not add anything to the List
        }
        //close both cursor and database
        cursor.close();
        db.close();
        return image;
    }
}