package com.testmultilang.testmultilang.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.testmultilang.testmultilang.dataModal.SaveDataModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenovo on 13-04-2016.
 */
public class MyDB{

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;

    public final static String SURVEYDATA_TABLE="SurveyData"; // name of table

    public final static String SURVEYFORM_ID="surveyFormId"; // id value for employee
    public final static String SURVEYFORM_DETAIL_OBJ="surveyFormDetailObj";  // name of employee
    /**
     *
     * @param context
     */
    public MyDB(Context context){
        dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public long insertRecords(String formDetail){
        ContentValues values = new ContentValues();
        //values.put(SURVEYFORM_ID, id);
        values.put(SURVEYFORM_DETAIL_OBJ, formDetail);
        return database.insert(SURVEYDATA_TABLE, null, values);
    }

    public ArrayList<SaveDataModel> getRecords() {
        String[] cols = new String[]{SURVEYFORM_ID, SURVEYFORM_DETAIL_OBJ};
        Cursor mCursor = database.rawQuery("SELECT  * FROM " + SURVEYDATA_TABLE,null);

        ArrayList<SaveDataModel> savedRecordList = new ArrayList<SaveDataModel>();


        if (mCursor != null) {

            mCursor.moveToFirst();

            while (mCursor.isAfterLast() == false) {
                SaveDataModel saveRecord = new SaveDataModel();
                saveRecord.setSurveyFormId(mCursor.getInt((mCursor.getColumnIndex(SURVEYFORM_ID))));
                saveRecord.setSurveyFormDetailObj((mCursor.getString(mCursor.getColumnIndex(SURVEYFORM_DETAIL_OBJ))));
                mCursor.moveToNext();

                savedRecordList.add(saveRecord);
            }

            mCursor.close();
            //dBClose();
        }
        return savedRecordList;
    }

    public void deleteRow(int rowId) {
        database.delete(SURVEYDATA_TABLE, SURVEYFORM_ID + " = ?",
                new String[] { String.valueOf(rowId) });
    }

   /* public int deleteRow(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int a = db.delete(TABLE_REGISTER, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        return a;
    }*/


    public void dBClose() {
        if(database != null && database.isOpen())
            database.close();
    }
}
