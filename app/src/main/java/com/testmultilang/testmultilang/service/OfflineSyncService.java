package com.testmultilang.testmultilang.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.testmultilang.testmultilang.dataModal.SaveDataModel;
import com.testmultilang.testmultilang.database.MyDB;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by lenovo on 14-04-2016.
 */
public class OfflineSyncService extends Service {
    Calendar cur_cal = Calendar.getInstance();
    ArrayList<SaveDataModel> savedRecordList;
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Intent intent = new Intent(this, OfflineSyncService.class);
        PendingIntent pintent = PendingIntent.getService(getApplicationContext(),
                0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        cur_cal.setTimeInMillis(System.currentTimeMillis());
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis(),
                60 * 1000 , pintent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        // your code for background process
        Log.i("Service running", "service running");
        ConnectivityManager conMgr = (ConnectivityManager)   getSystemService      (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null &&  conMgr.getActiveNetworkInfo().isAvailable() &&    conMgr.getActiveNetworkInfo().isConnected()) {
            MyDB myDB = new MyDB(OfflineSyncService.this);
            savedRecordList = new ArrayList<SaveDataModel>();
            savedRecordList = myDB.getRecords();
            if( savedRecordList.size() > 0 ) {
                for(int i = 0; i < savedRecordList.size(); i++) {

                }

            }

        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}