package com.example.paggarwal1.sampletestapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button dateButton, submit;
    TextView serviceProvider;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        dateButton = (Button) findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        /*submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CallLogStats.class);
                intent.putIntegerArrayListExtra("Date", );
                startActivity(intent);
            }
        });
*/
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        serviceProvider = (TextView) findViewById(R.id.phoneCarrier);

        TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

        serviceProvider.setText(tMgr.getNetworkOperatorName());

      /*  Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/
    }


    /*private void initializeDate() {
        datePicker = (DatePicker) findViewById(R.id.calendarView);
    }

    private ArrayList initializeCalender() {


        //  calendarView = (CalendarView) findViewById(R.id.calendarView2);
        calendarView.setShowWeekNumber(false);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


                // Date date1 = cal.getTime();
                String currentDate = dateFormat.format(new Date(view.getDate()));

                date.add(dayOfMonth);
                date.add(month);
                date.add(year);

                Toast.makeText(getApplicationContext(), currentDate, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();

            }
        });

        return date;

    }
*/
}






        /*b = (Button) findViewById(R.id.button);
        t = (TextView) findViewById(R.id.textview1);

        initializeCalender();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               getdetails();
            }
        });
    }

    private void initializeCalender() {
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setShowWeekNumber(true);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(),dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getdetails() {

        // Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery(CONTENT_URI, null, null, null, null);
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
            if(dir == "OUTGOING") {
                sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime + " \nCall duration in sec :--- " + callDuration);
                sb.append("\n----------------------------------");
            }
        //    sb.append(sb.length());
        }
        // managedCursor.close();
        t.setText(sb);

        CallLogStats.statisticsMethod(managedCursor, number, type, duration);
    }*/






