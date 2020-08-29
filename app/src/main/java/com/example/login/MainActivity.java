package com.example.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    public Button Login;
    private int counter =5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name= findViewById(R.id.etName);
        Password= findViewById(R.id.etPassword);
        Info= findViewById(R.id.Info);
        Login= findViewById(R.id.btnlogin);
        TextView userRegistration = findViewById(R.id.tvRegister);
        forgotPassword=findViewById(R.id.tvForgotPassword);

        Info.setText("No. of attempts remaining: 5");

        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));

        }

        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this,PasswordActivity.class));

            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void validate(String userName, String userPassword)
    {
        progressDialog.setMessage("Its lit");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                   checkEmailVerification();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No. of attempts remaining:"+ counter);
                    progressDialog.dismiss();
                    if(counter==0)
                    {
                        Login.setEnabled(false);
                    }
                }

            }
        });
    }
    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser= firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag)
        {
            finish();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        }
        else
        {
            Toast.makeText(this,"Verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}