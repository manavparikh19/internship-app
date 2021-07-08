package com.example.internshipapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class SignupActivity extends AppCompatActivity {
EditText name,email,password,confirm_password,dob,contact;
Button login,signup;
//RadioButton male,female,transgender;
RadioGroup gender;
Spinner cityspinner;
ImageView dobIv,hideIv,showIv,hideConfirmIv,showConfirmIv;
Calendar calendar;

//String[] cityArray={"Ahmedabad","Surat","Rajkot"};
ArrayList<String> cityArray;
String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("SignUp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name=findViewById(R.id.signup_name);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);
        confirm_password=findViewById(R.id.signup_confirm_password);
        contact=findViewById(R.id.signup_contact);
        cityspinner=findViewById(R.id.signup_city);
        dobIv=findViewById(R.id.signup_dob_iv);
        dob=findViewById(R.id.signup_dob);
        login=findViewById(R.id.signup_login_button);
        signup=findViewById(R.id.signup_button);
        hideIv=findViewById(R.id.signup_password_hide_iv);
        showIv=findViewById(R.id.signup_password_show_iv);
        hideConfirmIv=findViewById(R.id.signup_confirm_password_hide_iv);
        showConfirmIv=findViewById(R.id.signup_confirm_password_show_iv);

        //male=findViewById(R.id.signup_male);
        //female=findViewById(R.id.signup_female);
        //transgender=findViewById(R.id.signup_transgender);




        /* This Section is for Show and Hide Password*/

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
                hideIv.setVisibility(View.VISIBLE);
                showIv.setVisibility(View.GONE);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        hideConfirmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideConfirmIv.setVisibility(View.GONE);
                showConfirmIv.setVisibility(View.VISIBLE);
                confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });


        showConfirmIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideConfirmIv.setVisibility(View.VISIBLE);
                showConfirmIv.setVisibility(View.GONE);
                confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        /* Show and Hide Password section over*/





        /* This Section is for calendar image date selector*/
        calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateClick=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                dob.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        dobIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(SignupActivity.this,dateClick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });

        /* Section for Date Picker is over*/





        /* This Section is for spinner widget cities array*/
        cityArray=new ArrayList<>();
        cityArray.add("Ahmedabad");
        cityArray.add("Surat");
        cityArray.add("RAJKOT");
        cityArray.add("Vadodara");
        cityArray.add("Test");
        cityArray.add("Gandhinagar");

        cityArray.set(2,"Rajkot");
        cityArray.remove(4);



        ArrayAdapter adapter= new ArrayAdapter(SignupActivity.this, android.R.layout.simple_list_item_1,cityArray);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        cityspinner.setAdapter(adapter);

        cityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //cityArray[position];
             // new CommonMethod(SignupActivity.this,cityArray[position]);
               //String s =(String) parent.getItemAtPosition(position);
                new CommonMethod(SignupActivity.this,cityArray.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /* Section for spinner widget is over*/






        /* This Section is for gender radio button*/

        gender=findViewById(R.id.signup_gender);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id=gender.getCheckedRadioButtonId();
                RadioButton rb =findViewById(id);
                new CommonMethod(SignupActivity.this,rb.getText().toString());
            }
        });


       /* male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommonMethod(SignupActivity.this,male.getText().toString());
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommonMethod(SignupActivity.this,female.getText().toString());
            }
        });
        transgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommonMethod(SignupActivity.this,transgender.getText().toString());
            }
        });*/



        /* Section for gender radio button is over*/




        /* This Section is for Signup Form Validation*/



       login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onBackPressed();
        }
    });


       signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().equals("")){
                    name.setError("Name Required");
                }
                else if(email.getText().toString().equals("")){
                    email.setError("Email Id Required");
                }
                else if (!email.getText().toString().trim().matches(emailPattern)){
                    email.setError("Valid Email Id Required");
                }
                else if(contact.getText().toString().trim().equals("")) {
                    contact.setError("Contact Number Required");
                }
                else if(contact.getText().toString().length()<10 || contact.getText().toString().length()>10){
                    contact.setError("Valid Contact Number Required");
                }
                else if (password.getText().toString().trim().equals("")){
                    password.setError("Password Required");
                }
                else if (confirm_password.getText().toString().equals("")){
                    confirm_password.setError(" Confirm Password Required");
                }
                else if (!confirm_password.getText().toString().matches(password.getText().toString())){
                    confirm_password.setError("Please Enter the same password as above!");
                }
                else if (dob.getText().toString().trim().equals("")){
                    dob.setError("Date of Birth Required");
                }
                else if (gender.getCheckedRadioButtonId()==-1){
                    new CommonMethod(SignupActivity.this,"Please select Gender");
                }
                else {
                   new CommonMethod(SignupActivity.this,"Signup Successful!");
                    onBackPressed();
                }
            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if (id==android.R.id.home){
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }
}