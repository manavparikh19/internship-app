package com.example.internshipapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText email,password;
    TextView signup;
    ImageView hideIv,showIv;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        login=findViewById(R.id.login_button);
        email=findViewById(R.id.login_et_email);
        password=findViewById(R.id.login_et_password);
        signup=findViewById(R.id.login_tv_create_account);
        hideIv=findViewById(R.id.login_password_hide_iv);
        showIv=findViewById(R.id.login_password_show_iv);


        hideIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideIv.setVisibility(View.GONE);
                showIv.setVisibility(View.VISIBLE);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

            }
        });
        showIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIv.setVisibility(View.GONE);
                hideIv.setVisibility(View.VISIBLE);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);*/
                new CommonMethod(LoginActivity.this,SignupActivity.class);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().trim().equals("")){
                    email.setError("Email Id Required");
                }
                else if (!email.getText().toString().trim().matches(emailPattern)){
                    email.setError("Valid Email id Required");
                }
                else if (password.getText().toString().trim().equals("")){
                    password.setError("Password Required");
                }
                else {
                    if (email.getText().toString().trim().equals("admin@gmail.com") && password.getText().toString().trim().equalsIgnoreCase("ADMIN@007")) {
                        System.out.println("Login Successfully!!!");
                        Log.d("LOGIN", "Login Successfully!!!");
                        Log.e("LOGIN", "Login Successfully!!!");

                        /*Toast.makeText(LoginActivity.this, "Login Successfully!!!", Toast.LENGTH_LONG).show();
                        Snackbar.make(view, "Login Successfully!!!", Snackbar.LENGTH_LONG).show();

                        Intent intent= new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);*/
                        new CommonMethod(LoginActivity.this,"Login Successful!!");
                        new CommonMethod(view,"Login Successful!!");

                        new CommonMethod(LoginActivity.this,HomeActivity.class);
                        finish();
                    }
                    else {
                        System.out.println("Login Unsuccessfully!!!");
                        Log.d("LOGIN", "Login Unsuccessfully!!!");
                        Log.e("LOGIN", "Login Unsuccessfully!!!");

                        /*Toast.makeText(LoginActivity.this, "Login Unsuccessfully!!!", Toast.LENGTH_LONG).show();
                        Snackbar.make(view, "Login Unsuccessfully!!!", Snackbar.LENGTH_LONG).show();*/
                        new CommonMethod(LoginActivity.this,"Login Unsuccessful!!");
                        new CommonMethod(view,"Login Unsucessful!!");
                         }
                    }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id== android.R.id.home){
            //onBackPressed();
            alertMethod();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        alertMethod();
        //super.onBackPressed();
    }

    private void alertMethod() {
        AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this);
        builder.setIcon(R.drawable.ic_happy);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage("Are you sure you want to exit?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new CommonMethod(LoginActivity.this,"Rate Us");
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);

        builder.show();
    }
}