package com.example.paggarwal1.sampletestapp;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.provider.CallLog.Calls.CONTENT_URI;


/**
 * Created by PAggarwal1 on 9/11/2015.
 */
public class CallLogStats extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
                arrayList = getIntent().getExtras().getIntegerArrayList("Date");

        if(arrayList.isEmpty())
        {

        }
        Log.i("Random", arrayList.toString());
        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);

        Date date = cal.getTime();
        String currentDate = dateFormat.format(date);



        Log.i("New date",currentDate);
    }
}