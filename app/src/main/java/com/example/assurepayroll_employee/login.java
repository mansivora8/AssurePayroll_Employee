package com.example.assurepayroll_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class login extends AppCompatActivity {
    EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPassword=findViewById(R.id.etPassword);
    }
    public void visible(View view) {
        if(view.getId()==R.id.show_pass_btn){

            if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Show Password
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Hide Password
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    public void login(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}