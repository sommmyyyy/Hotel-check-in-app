package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity
{
    private EditText userName,userPassword,userEmail,userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    String name,password,email,age;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (validate())
                {
                    String user_email= userEmail.getText().toString().trim();
                    String user_password=userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                               // sendEmailVerification();
                                Toast.makeText(RegistrationActivity.this,"Successfully Registered, Upload done",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }

    private void setupUIViews()
    {
        userName=findViewById(R.id.etUserName);
        userPassword=findViewById(R.id.etUserPassword);
        userEmail=findViewById(R.id.etUserEmail);
        regButton=findViewById(R.id.btnRegister);
        userLogin=findViewById(R.id.tvUserLogin);
        userAge=findViewById(R.id.etAgee);
    }
    private boolean validate()
    {
        boolean result =false;

        name=userName.getText().toString();
        password=userPassword.getText().toString();
        email=userEmail.getText().toString();
        age=userAge.getText().toString();

        if((name.isEmpty()) || (password.isEmpty()) || (email.isEmpty()) || age.isEmpty())
        {
            Toast.makeText (this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result ;
    }
    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RegistrationActivity.this,"Successfully Registered, Verification mail sent",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this,"Verification mail hasn't been sent", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}