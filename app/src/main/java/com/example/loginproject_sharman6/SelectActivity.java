package com.example.loginproject_sharman6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }


    public void fnCallLoginActivity(View view) {
        //Call Login activity if login button is clicked
        startActivity(new Intent(SelectActivity.this, LoginAcitivty.class));
        SelectActivity.this.finish();
    }

    public void fnCallRegisterActivity(View view) {
        //Call Login activity if Registration button is clicked
        startActivity(new Intent(SelectActivity.this, RegisterActivity.class));
        SelectActivity.this.finish();
    }
}