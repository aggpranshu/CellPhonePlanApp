package com.example.paggarwal1.sampletestapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by PAggarwal1 on 9/11/2015.
 */
public class CallLogStats extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date d = (Date)getIntent().getSerializableExtra("date");
        hello(d,"before");
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void hello(Date d, String whenItHappened) {
        if (whenItHappened.equals("before")) {
            fetchCallRecordsFromPast(d);
        } else {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
            //RUN A BACKGROUND SERVICE HERE WHICH WILL FETCH THE CALL LOGS FOR THE UPCOMING DAYS SPECIFIED BY THE USER
            
            /*final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, (int)diffInDays);
            Date date = cal.getTime();
            String currentDate = dateFormat.format(date);*/
        }

    }

    private void fetchCallRecordsFromPast(Date d) {
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");
        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            if (dir == "OUTGOING" && callDayTime.compareTo(d)>0) {
                sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- " + callDuration);
                sb.append("\n----------------------------------");
            }
            //    sb.append(sb.length());
        }
        Log.i("records", sb.toString());
        // managedCursor.close();

    }
}
