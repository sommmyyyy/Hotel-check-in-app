package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity
{
    private EditText firstname,lastname,email,phone,age,adults,child;
    String fname,lname,emaill,phonee,agee,adultss,childs;
    private FirebaseAuth firebaseAuth;
    private Button Nextbtn;
    String[] SPINNERLIST ={"Male", "Female"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setupUIviews();

        Nextbtn=findViewById(R.id.btnNext);

        Nextbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (validate())
                {
                    sendUserdata();
                    startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
                }
                else
                    {
                        Toast.makeText(SecondActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        firebaseAuth= FirebaseAuth.getInstance();

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,SPINNERLIST);
        MaterialBetterSpinner betterSpinner=findViewById(R.id.android_material_design_spinner);
        betterSpinner.setAdapter(arrayAdapter);

        Button logout = findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                logout();
            }
        });
    }
    private void logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutMenu:
            {
                logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupUIviews()
    {
        firstname=findViewById(R.id.etFirstname);
        lastname=findViewById(R.id.etLastname);
        email=findViewById(R.id.etEmail);
        phone=findViewById(R.id.etPhone);
        age=findViewById(R.id.etAgee);
        adults=findViewById(R.id.etAdult);
        child=findViewById(R.id.etChild);
    }

    private boolean validate()
    {
        boolean result=false;
        fname=firstname.getText().toString();
        lname=lastname.getText().toString();
        emaill=email.getText().toString();
        phonee=phone.getText().toString();
        agee=age.getText().toString();
        adultss=adults.getText().toString();
        childs=child.getText().toString();

        if((fname.isEmpty()) || (lname.isEmpty()) || (emaill.isEmpty()) || agee.isEmpty() || phonee.isEmpty() || adultss.isEmpty() || childs.isEmpty())
        {
            Toast.makeText (this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result;
    }
    private void sendUserdata()
    {
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myref= firebaseDatabase.getReference(Objects.requireNonNull(firebaseAuth.getUid()));
        UserProfile userProfile=new UserProfile(fname,lname,phonee,emaill,agee,adultss,childs);
        myref.setValue(userProfile);
    }

}