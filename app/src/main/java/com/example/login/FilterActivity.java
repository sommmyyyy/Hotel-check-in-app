package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity
{
    private Chip chipAll,chip2,chip3,chip4;
    private Chip LtoH,HtoL;
    private Button btnApply;
    private ArrayList<String> selectChipData;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        chipAll=findViewById(R.id.chipAll);
        chip2=findViewById(R.id.chip2);
        chip3=findViewById(R.id.chip3);
        chip4=findViewById(R.id.chip4);
        LtoH=findViewById(R.id.chipLtoH);
        HtoL=findViewById(R.id.chipHtoL);
        btnApply=findViewById(R.id.btnApply);
        selectChipData=new ArrayList<>();

        CompoundButton.OnCheckedChangeListener checkedChangeListener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectChipData.add(buttonView.getText().toString());
                } else {
                    selectChipData.remove(buttonView.getText().toString());
                }

            }
        };
        chipAll.setOnCheckedChangeListener(checkedChangeListener);
        chip2.setOnCheckedChangeListener(checkedChangeListener);
        chip3.setOnCheckedChangeListener(checkedChangeListener);
        chip4.setOnCheckedChangeListener(checkedChangeListener);
        LtoH.setOnCheckedChangeListener(checkedChangeListener);
        HtoL.setOnCheckedChangeListener(checkedChangeListener);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent=new Intent();
                resultIntent.putExtra("data", selectChipData);
                setResult(101,resultIntent);
                finish();
            }
        });
    }
}