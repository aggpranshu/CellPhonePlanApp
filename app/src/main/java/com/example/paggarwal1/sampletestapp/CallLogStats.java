package com.example.paggarwal1.sampletestapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by PAggarwal1 on 9/11/2015.
 */
public class CallLogStats extends Activity {


    TextView t;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<CallLogs> listOfCallLogs = new ArrayList<CallLogs>();
    HashMap<String, CallLogs> map = new LinkedHashMap<String, CallLogs>();


    String[] projection = new String[]{
            CallLog.Calls.DATE,
            CallLog.Calls.NUMBER,
            CallLog.Calls.DURATION,
            CallLog.Calls.TYPE};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = (TextView) findViewById(R.id.textview1);
        Date d = (Date) getIntent().getSerializableExtra("date");
        String whenItHappened = getIntent().getStringExtra("whenItHappened");

        if (whenItHappened.equals("before")) {
            fetchCallRecords(d);
        } else {
            //  fetchCallRecordsFromPast(d,whenItHappened);
        }


    }


    public void fetchCallRecords(Date d) {
        String selection = "type = 2 ";
        ContentResolver resolver = getApplicationContext().getContentResolver();
        Cursor cur = resolver.query(CallLog.Calls.CONTENT_URI, projection, selection, null, null);
        while (cur.moveToNext()) {
            String truncatedNumber = "";
            String number = cur.getString(cur.getColumnIndex(CallLog.Calls.NUMBER));
            String duration = cur.getString(cur.getColumnIndex(CallLog.Calls.DURATION));

            String date = cur.getString(cur.getColumnIndex(CallLog.Calls.DATE));
            Date callDate = new Date(Long.valueOf(date));

            Log.i("Number", number);

            if (number.length() > 10 ) {
                truncatedNumber = number.substring((Math.abs(10 - number.length())), number.length());
            }
            else
            truncatedNumber = number;

            Log.i("NULL NUMBER",String.valueOf(truncatedNumber.equals("")));
            if (map.containsKey((String) truncatedNumber)) {

                CallLogs obj = map.get((String) truncatedNumber);
                obj.setDuration(obj.getDuration() + Integer.valueOf(duration));
                map.put(truncatedNumber, obj);
                Toast.makeText(getApplicationContext(), "In Toast", Toast.LENGTH_SHORT).show();
            } else {
                CallLogs object = new CallLogs(truncatedNumber, duration, callDate);
                map.put(truncatedNumber, object);
            }
            //  if(callDate.compareTo(d)>0) {

            Log.i("Call Records", number + ": " + "Duration: " + duration + "\n");
            // }
            //display call log or do ur logic
        }
        for (Map.Entry<String, CallLogs> entry : map.entrySet()) {
            String key = entry.getKey();
            CallLogs callLogs = entry.getValue();
            Log.i("Duration", key + "," + String.valueOf(callLogs.getDuration()));
        }
    }

}
