package com.example.hafiz.runningmeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void onButtonClick(View v) {

        if (v.getId() == R.id.login) {
            EditText a= (EditText)findViewById(R.id.userName);
            String str = a.getText().toString();
            EditText b= (EditText)findViewById(R.id.password);
            String pass=b.getText().toString();
            String password = helper.searchPass(str);
            if(pass.equals(password)){
                Intent i = new Intent(LoginActivity.this,Dashboard.class);
                startActivity(i);
            }
            else {
                Toast toast = Toast.makeText(LoginActivity.this,"Opps!! Password don't match",Toast.LENGTH_SHORT);
                toast.show();
            }



        }
        else if (v.getId() == R.id.register) {
            Intent i = new Intent(LoginActivity.this,Register.class);
            startActivity(i);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
