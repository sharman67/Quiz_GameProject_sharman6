package com.example.loginproject_sharman6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginAcitivty extends AppCompatActivity {
    TextView emailIn, pwdIn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //This method is check if fields are entered correctly
    public void fnLogin(View view){

        boolean isAllFieldsChecked = false;
        // Get user inputs
        emailIn = findViewById(R.id.emailIdInput);
        pwdIn = findViewById(R.id.pwdId);

        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked){
            // if all fields are correct then show message
            if (!isValidEmail(emailIn.getText())){
                emailIn.setError("Please Enter valid email address");
            } else {
                int duration = Toast.LENGTH_SHORT;
                final int DISPLAY_LENGTH = 2000;
                Toast toast = Toast.makeText(this, "Login Successful", duration);
                toast.show();
                // Go back to the select activity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /* Create an Intent that will start the Select-Activity. */
                        Intent mainIntent = new Intent(LoginAcitivty.this, QuizActivity.class);
                        //Pass Email to next activity for storing quiz results
                        mainIntent.putExtra("Email", emailIn.getText().toString());
                        LoginAcitivty.this.startActivity(mainIntent);
                        LoginAcitivty.this.finish();
                    }
                }, DISPLAY_LENGTH);
            }
        }

    }
    //method to check field values
    private boolean CheckAllFields() {
        if (emailIn.length() == 0) {
            emailIn.setError("Email is a required field");
            return false;
        }

        if (pwdIn.length() == 0) {
            pwdIn.setError("Password is a required field");
            return false;
        }
        return true;
    }
    //Validate Email address
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}