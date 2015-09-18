package com.example.paggarwal1.sampletestapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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

        if (arrayList.isEmpty()) {

        }
        Log.i("Random", arrayList.toString());
        Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -7);

        Date date = cal.getTime();
        String currentDate = dateFormat.format(date);


        Log.i("New date", currentDate);
    }


    public void hello(long diffInDays)
    {
        Log.i("hello", String.valueOf(diffInDays));
    }
}