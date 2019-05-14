package com.example.bookmytable;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MakeBookingActivity extends AppCompatActivity {

    Spinner noOfPeople;
    Spinner fromTime;
    Spinner toTime;
    Button book;
    String dte;
    private int year, month, day;
    private Calendar calendar;
    String frmTme, toTme, pplCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_booking);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        noOfPeople = findViewById(R.id.spinner_no_of_people);
        fromTime = findViewById(R.id.spinner_from_time);
        toTime = findViewById(R.id.spinner_to_time);

        book = findViewById(R.id.button_make_reservation);

        String[] peopleCount = {"1", "2", "3", "4", "4"};
        ArrayAdapter<String> peopleCountadapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, peopleCount);
        noOfPeople.setAdapter(peopleCountadapter);

        String[] TimeList = {"11:00", "11:30", "12:00", "12:30", "01:00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, TimeList);
        fromTime.setAdapter(adapter);
        toTime.setAdapter(adapter);

        fromTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                frmTme = parent.getItemAtPosition(position).toString();
            }
        });

        toTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toTme = parent.getItemAtPosition(position).toString();
            }
        });

        noOfPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pplCount = parent.getItemAtPosition(position).toString();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingsBO book = new BookingsBO();
                book.customerId = "";
                book.bookingId = "";
                book.status = 3;
                book.noOfPeople = Integer.parseInt(pplCount);
                book.restaurantId = "";
                book.date = dte;
                book.fromtime = frmTme;
                book.toTime = toTme;
                makeReservation(book);
            }
        });


    }

    public void makeReservation( BookingsBO book){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        book.customerId = user.getDisplayName() + "_" + user.getUid();

        book.bookingId = book.customerId + book.restaurantId + book.date + book.fromtime + book.toTime;

        DatabaseReference keyRef =
                database.getReference("bookings").child(book.bookingId);
        keyRef.child("customerId").setValue(book.customerId);
        keyRef.child("bookingId").setValue(book.bookingId);
        keyRef.child("date").setValue(book.date);
        keyRef.child("fromtime").setValue(book.fromtime);
        keyRef.child("toTime").setValue(book.toTime);
        keyRef.child("noOfPeople").setValue(book.noOfPeople);
        keyRef.child("status").setValue(book.status);
        keyRef.child("restaurantId").setValue(book.restaurantId);

        startActivity(new Intent(MakeBookingActivity.this,BookingActivity.class));
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    setDateString(arg1, arg2 + 1, arg3);
                }
            };

    private void setDateString(int year, int month, int day) {
        dte = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year).toString();
    }
}
