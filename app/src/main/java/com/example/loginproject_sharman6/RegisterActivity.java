package com.example.loginproject_sharman6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Handler;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    TextView firstName, lastName, DateOfBirth, EmailAdd, PasswordIn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    // This method is to check registration information
    public void fnRegister(View view){

        boolean isAllFieldsChecked = false;
        final int DISPLAY_LENGTH = 2000;
        //Get user entered data
        firstName = findViewById(R.id.firstNm);
        lastName = findViewById(R.id.lastNm);
        DateOfBirth = findViewById(R.id.DOB);
        EmailAdd = findViewById(R.id.Email);
        PasswordIn = findViewById(R.id.Password);

        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked){
            //If all fields are correct then show message
            boolean isValidFormat = DateOfBirth.getText().toString().matches("((0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/((19|2[0-9])[0-9]{2}))");

            if (!isValidEmail(EmailAdd.getText())){
                EmailAdd.setError("Please Enter valid email address");
            } else if (!isValidFormat) {
                DateOfBirth.setError("Please Enter valid date in MM/DD/YYYY format");
            } else {
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this, "Registration Successful", duration);
                toast.show();
                //Go back to the select activity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /* Create an Intent that will start the Select-Activity. */
                        Intent mainIntent = new Intent(RegisterActivity.this, SelectActivity.class);
                        RegisterActivity.this.startActivity(mainIntent);
                        RegisterActivity.this.finish();
                    }
                }, DISPLAY_LENGTH);
            }

        }

    }
    //method to check user input data
    private boolean CheckAllFields() {
        if (firstName.length()  < 3 || firstName.length() > 30) {
            firstName.setError("First Name is a required field and should be between 3 to 30 characters");
            return false;
        }

        if (lastName.length() == 0) {
            lastName.setError("Family Name is a required field");
            return false;
        }
        if (DateOfBirth.length() == 0) {
            DateOfBirth.setError("Date of Birth is a required field");
            return false;
        }

        if (EmailAdd.length() == 0) {
            EmailAdd.setError("Email is a required field");
            return false;
        }
        if (PasswordIn.length() == 0) {
            PasswordIn.setError("Password is a required field");
            return false;
        }
        return true;
    }
    //Validate Email address
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}