package com.example.paggarwal1.sampletestapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by PAggarwal1 on 9/11/2015.
 */
public class CallLogStats extends Activity {

    TextView t;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      t = (TextView) findViewById(R.id.textview1);
        Date d = (Date) getIntent().getSerializableExtra("date");
        hello(d, "before");
    }

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
        int data= managedCursor.getColumnIndex(CallLog.Calls.DATA_USAGE);
        sb.append("Call Log :");

        Log.i("date", d.toString());
        Log.i("value", String.valueOf(managedCursor.getCount()));
        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            //int data_usage= managedCursor.getColumnIndex(CallLog.Calls.DATA_USAGE);
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
            if (dir == "OUTGOING" && callDayTime.compareTo(d)>0 ) {

                //Code to add the data of the Call Logs into a file
                /*try {
                    FileOutputStream fileout = openFileOutput("CallLogStats.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(phNumber + "," + callDayTime + "," + duration + "\n");
                    outputWriter.close();
                    File path = getApplicationContext().getFilesDir();

                    //display file saved message
                    *//*Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();*//*

                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- "+callDuration);
                sb.append("\n----------------------------------");
            }
            //    sb.append(sb.length());
        }
        Log.i("records", sb.toString());
        t.setText(sb.toString());
        // managedCursor.close();

    }
}
