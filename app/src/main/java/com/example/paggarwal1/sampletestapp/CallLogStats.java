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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by PAggarwal1 on 9/11/2015.
 */
public class CallLogStats extends Activity {

    String truncatedNumber;
    TextView t;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<CallLogs> listOfCallLogs = new ArrayList<CallLogs>();
    HashMap<String, CallLogs> map = new LinkedHashMap<String,CallLogs>();



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
/*
    public void hello(Date d, String whenItHappened) {
        if (whenItHappened.equals("before")) {
            fetchCallRecordsFromPast(d,"before");
        } else if(whenItHappened.equals("today")){
            fetchCallRecordsFromPast(d,"today");
        }
        else {
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
            //RUN A BACKGROUND SERVICE HERE WHICH WILL FETCH THE CALL LOGS FOR THE UPCOMING DAYS SPECIFIED BY THE USER

            Intent i = new Intent(CallLogStats.this,BackgroundService.class);
            startService(i);
            
            *//*final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, (int)diffInDays);
        /*Toast.makeText(getApplicationContext(), whenItHappened + "in function", Toast.LENGTH_SHORT).show();
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");

        Log.i("date", d.toString());
      //  Log.i("value", String.valueOf(managedCursor.getCount()));
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

            if ((dir == "OUTGOING" && callDayTime.compareTo(d)>0 && whenItHappened.equals("before"))) {
                Toast.makeText(getApplicationContext(), whenItHappened + "in the Toast", Toast.LENGTH_SHORT).show();
                //Code to add the data of the Call Logs into a file
                try {
                    FileOutputStream fileout = openFileOutput("CallLogStats.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(phNumber + "," + callDayTime + "," + duration + "\n");
                    outputWriter.close();
                    File path = getApplicationContext().getFilesDir();

                    //display file saved message


                } catch (Exception e) {
                    e.printStackTrace();
                }

                sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- " + callDuration);
                sb.append("\n----------------------------------");
            }
            //    sb.append(sb.length());
        }
        Log.i("records", sb.toString());
        t.setText(sb.toString());
        Toast.makeText(getBaseContext(), "File saved successfully!",
                Toast.LENGTH_SHORT).show();
        // managedCursor.close();

    }
            Date date = cal.getTime();
    private void fetchCallRecordsFromPast(Date d, String whenItHappened) {

            String currentDate = dateFormat.format(date);*//*
        }*/

    public void fetchCallRecords(Date d) {
        String selection = "type = 2 AND ";
        ContentResolver resolver = getApplicationContext().getContentResolver();
        Cursor cur = resolver.query(CallLog.Calls.CONTENT_URI, projection, selection, null, null);
        while (cur.moveToNext()) {

            String number = cur.getString(cur.getColumnIndex(CallLog.Calls.NUMBER));
            String duration = cur.getString(cur.getColumnIndex(CallLog.Calls.DURATION));

            String date = cur.getString(cur.getColumnIndex(CallLog.Calls.DATE));
            Date callDate = new Date(Long.valueOf(date));

            if(number.length()==12 || number.length()==11)
            {
                truncatedNumber = number.substring((Math.abs(10-number.length())),number.length());
                Log.i("truncated number", truncatedNumber);
            }
            if(map.containsKey((String)truncatedNumber)){

               CallLogs obj =  map.get((String) truncatedNumber);
               obj.setDuration(obj.getDuration() + Integer.valueOf(duration));
                map.put(truncatedNumber, obj);
                Toast.makeText(getApplicationContext(), "In Toast", Toast.LENGTH_SHORT).show();
            }
            else
            {
                CallLogs object = new CallLogs(truncatedNumber,duration,callDate);
                map.put(truncatedNumber,object);
            }
            //  if(callDate.compareTo(d)>0) {

            Log.i("Call Records", number + ": " + "Duration: " + duration + "\n");
            // }
            //display call log or do ur logic
        }
        for (Map.Entry<String,CallLogs> entry : map.entrySet()) {
            String key = entry.getKey();
            CallLogs callLogs = entry.getValue();
            Log.i("Duration",key + "," + String.valueOf(callLogs.getDuration()));
        }
    }

}
