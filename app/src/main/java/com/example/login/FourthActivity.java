package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FourthActivity extends AppCompatActivity
{
    TextView CheckIn,CheckOut;
    int t1Hour,t1Min,t2Hour,t2Min;
    Calendar c,cc;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        CheckIn=findViewById(R.id.tvCheckIn);
        CheckOut=findViewById(R.id.tvCheckOut);
        btn=findViewById(R.id.btnNextt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FourthActivity.this,FifthActivity.class));
            }
        });

        CheckIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        FourthActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int HourOfDay, int Minute) {
                                t1Hour = HourOfDay;
                                t1Min = Minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0, 0, 0, t1Hour, t1Min);

                                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                CheckIn.setText(sdf.format(c.getTime()));

                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(t1Hour, t1Min);
                timePickerDialog.show();
            }
        });

        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        FourthActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                                t2Hour = hourOfDay;
                                t2Min = min;
                                String time = t2Hour + ":" + t2Min;

                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:MM");
                                try {
                                    Date date = f24hours.parse(time);

                                    SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");

                                    CheckOut.setText(f12hours.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();


                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(t2Hour,t2Min);

                timePickerDialog.show();
            }
        });
    }
}