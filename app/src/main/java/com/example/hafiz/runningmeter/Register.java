package com.example.hafiz.runningmeter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hafiz on 7/3/2016.
 */
public class Register extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void onRegisterClick(View v){
        if(v.getId()==R.id.signup){
            EditText username = (EditText)findViewById(R.id.TFuserName);
            EditText password = (EditText)findViewById(R.id.TFpassword);
            EditText conpassword = (EditText)findViewById(R.id.conpass);
            EditText tfheight = (EditText)findViewById(R.id.height);
            EditText tfweight = (EditText)findViewById(R.id.weight);
            EditText tfage = (EditText)findViewById(R.id.age);
            EditText tfsex = (EditText)findViewById(R.id.sex);

            String namestr=username.getText().toString();
            String passwordstr=password.getText().toString();
            String conpasswordstr=conpassword.getText().toString();
            String heightstr=tfheight.getText().toString();
            String weightstr=tfweight.getText().toString();
            String agestr=tfage.getText().toString();
            String sexstr=tfsex.getText().toString();

            if(!passwordstr.equals(conpasswordstr)){
                //popup msg
                Toast pass = Toast.makeText(Register.this,"Passwords don't match",Toast.LENGTH_SHORT);
                pass.show();
            }
            else
            {
                //insert data into db
                Contact c = new Contact();
                c.setUsername(namestr);
                c.setPassword(passwordstr);
                c.setHeight(heightstr);
                c.setWeight(weightstr);
                c.setAge(agestr);
                c.setSex(sexstr);
                helper.insertContact(c);
            }
            Intent i =new Intent(Register.this,LoginActivity.class);
            startActivity(i);
        }
    }

}
