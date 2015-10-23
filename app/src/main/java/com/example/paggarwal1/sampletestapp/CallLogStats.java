package com.example.paggarwal1.sampletestapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    String[] projectionCall = new String[]{
            CallLog.Calls.DATE,
            CallLog.Calls.NUMBER,
            CallLog.Calls.DURATION,
            CallLog.Calls.TYPE};

    String[] projectionMesg = new String[]{
            "body" , "address","date" ,"type"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = (TextView) findViewById(R.id.mesgTextView);
        Date d = (Date) getIntent().getSerializableExtra("date");
        String whenItHappened = getIntent().getStringExtra("whenItHappened");

        if (whenItHappened.equals("before")) {
            CallRecords(d);
            MesgRecords(d);
        } else {
            //  fetchCallRecordsFromPast(d,whenItHappened);
        }
    }

    public void MesgRecords(Date d) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("*********SMS History*************** :");
        Uri uri = Uri.parse("content://sms");
        Cursor curMesg = getContentResolver().query(uri, null, "type=2", null, null);
       /* String[] name=curMesg.getColumnNames();
        for(int i=0;i<name.length;i++){
            Log.i("Column",name[i]);
        }*/
        if (curMesg.moveToFirst()) {
            for (int i = 0; i < curMesg.getCount(); i++) {
                String body = curMesg.getString(curMesg.getColumnIndexOrThrow("body"));
                String date = curMesg.getString(curMesg.getColumnIndexOrThrow("date"));
                Date smsDayTime = new Date(Long.valueOf(date));
                String type = curMesg.getString(curMesg.getColumnIndexOrThrow("type"));

                stringBuffer.append("\nPhone Number:--- "  + " \nMessage Type:--- "
                        + type + " \nMessage Date:--- " + smsDayTime
                        + " \nMessage Body:--- " + body);
                stringBuffer.append("\n----------------------------------");
                curMesg.moveToNext();
                t.setText(stringBuffer.toString());
            }
            curMesg.close();
        }
    }

    public void CallRecords(Date d) {
        String selection = "type = 2";
        ContentResolver resolver = getApplicationContext().getContentResolver();
        Cursor curCall = resolver.query(CallLog.Calls.CONTENT_URI, projectionCall, selection, null, null);
        while (curCall.moveToNext()) {
            String truncatedNumber = "";
            String number = curCall.getString(curCall.getColumnIndex(CallLog.Calls.NUMBER));
            String duration = curCall.getString(curCall.getColumnIndex(CallLog.Calls.DURATION));

            String date = curCall.getString(curCall.getColumnIndex(CallLog.Calls.DATE));
            Date callDate = new Date(Long.valueOf(date));

            if (callDate.compareTo(d)>0) {
                if (number.length() > 10) {
                    truncatedNumber = number.substring((Math.abs(10 - number.length())), number.length());
                } else
                    truncatedNumber = number;

                if (map.containsKey(truncatedNumber)) {
                    CallLogs obj = map.get(truncatedNumber);
                    obj.setDuration(Integer.valueOf(duration),callDate);
                    map.put(truncatedNumber, obj);
                    Toast.makeText(getApplicationContext(), "In Toast", Toast.LENGTH_SHORT).show();
                } else {
                    CallLogs object = new CallLogs(truncatedNumber, duration, callDate);
                    object.setDuration(Integer.valueOf(duration),callDate);
                    map.put(truncatedNumber, object);
                }
            }
        }
        curCall.close();
        for (Map.Entry<String, CallLogs> entry : map.entrySet()) {
            String key = entry.getKey();
            CallLogs callLogs = entry.getValue();
            Log.i("Duration", key + "," + String.valueOf(callLogs.getDurationDay()) + "," + String.valueOf(callLogs.getDurationNight()) + "," + String.valueOf(callLogs.getDate()));
        }
    }

}
