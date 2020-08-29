package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;


public class ThirdActivity extends AppCompatActivity
{
    Button aBtn,bBtn;
    TextView mTv,nTv;
    private Button Nextbtn;

    Calendar c,cc;
    DatePickerDialog dpd,dpdd;

    String in,out;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mTv=findViewById(R.id.tvArrival);
        nTv=findViewById(R.id.tvDeparture);
        aBtn=findViewById(R.id.btnPick);
        bBtn=findViewById(R.id.btnPickk);
        Nextbtn=findViewById(R.id.btnNextt);

        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this,FourthActivity.class));

            }
        });

        aBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                c=Calendar.getInstance();
                int day=c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);

                dpd=new DatePickerDialog(ThirdActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay)
                    {
                        mTv.setText(mDay+ "/"+ (mMonth+1)+"/"+mYear);

                    }
                },year,month,day);
                dpd.show();

            }
        });
        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cc=Calendar.getInstance();
                int dayy=c.get(Calendar.DAY_OF_MONTH);
                int monthh=c.get(Calendar.MONTH);
                int yearr=c.get(Calendar.YEAR);

                dpdd=new DatePickerDialog(ThirdActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int nYear, int nMonth, int nDay)
                    {
                        nTv.setText(nDay+ "/"+ (nMonth+1)+"/"+nYear);
                    }
                },yearr,monthh,dayy);
                dpdd.show();
            }
        });
        in= mTv.getText().toString();
        out= nTv.getText().toString();

    }
    private void sendUserdata()
    {

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myref= firebaseDatabase.getReference(Objects.requireNonNull(firebaseAuth.getUid()));
        Date date=new Date(in,out);
        myref.setValue(date);
    }

}




