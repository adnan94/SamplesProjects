package com.example.summerboot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {

    EditText user;
    EditText pass;
Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.passward);

        login= (Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equalsIgnoreCase("saylani") ){
                }else{
                    Toast.makeText(Login.this,"plz insert the correct username",Toast.LENGTH_SHORT).show();

                }



                if(pass.getText().toString().equalsIgnoreCase("saylani")){

                }else{
                    Toast.makeText(Login.this,"plz insert the correct password",Toast.LENGTH_SHORT).show();

                }
            if(user.getText().toString().equalsIgnoreCase("saylani") &&    pass.getText().toString().equalsIgnoreCase("saylani") ){
                Toast.makeText(Login.this,"login sucessfully",Toast.LENGTH_SHORT).show();

                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }

            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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
