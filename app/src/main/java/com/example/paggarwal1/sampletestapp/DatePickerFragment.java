package com.example.paggarwal1.sampletestapp;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.util.Calendar.YEAR;

/**
 * Created by PAggarwal1 on 9/16/2015.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    long diffInMilis, diffInDays;
    CallLogStats callLogStats = new CallLogStats();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        String formatedDate = sdf.format(calendar.getTime());
        if (calendar.getTime().before(Calendar.getInstance().getTime())) {
            diffInMilis = Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis();
            diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
            if (diffInDays > 21) {
                String promptMessage = "Cannot fetch call logs for " + diffInDays + " days right now. Please wait for "
                        + String.valueOf(diffInDays - 21) + "days";
                Toast.makeText(getContext(), promptMessage, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Day before", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CallLogStats.class);
                Intent intent1=new Intent(getActivity(),MsgLogStats.class);
                intent.putExtra("date",calendar.getTime());
                startActivity(intent);
                startActivity(intent1);
            }
        } else {
            Toast.makeText(getContext(), "Day after", Toast.LENGTH_SHORT).show();
           callLogStats.hello(calendar.getTime(),"after");
        }
    }
}
