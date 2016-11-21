package com.example.summerboot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText editRollno;
    EditText editName;
    EditText editMarks;

    SQLiteDatabase db;
    Button btnAdd;
    Button btnDelete;
    Button btnModify;
    Button btnView;
    Button btnViewall;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");

        //editTxtt

        editRollno = (EditText) findViewById(R.id.editRollno);
        editName = (EditText) findViewById(R.id.editName);
        editMarks = (EditText) findViewById(R.id.editMarks);

        //buttons

        btnViewall = (Button) findViewById(R.id.btnViewall);
        btnView = (Button) findViewById(R.id.btnView);
        btnModify = (Button) findViewById(R.id.btnModify);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnAdd = (Button) findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRollno.getText().toString().trim().length() == 0 || editName.getText().toString().trim().length() == 0 || editMarks.getText().toString().trim().length() == 0) {
                    toast("values should not be kept empty");

                } else {
                    db.execSQL("INSERT INTO student VALUES('" + editRollno.getText() + "','" + editName.getText() + "','" + editMarks.getText() + "');");
                    toast("data sucessfull added to our data base");
                    clearText();

                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRollno.getText().toString().trim().length() == 0) {
                    toast("Please enter the roll number first");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("DELETE FROM student WHERE rollno='" + editRollno.getText() + "' ");
                    toast("The record is sucessfully deleted from our database");
                } else {
                    toast("Invalid roll number , this roll number is not exist.");
                }
                clearText();
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRollno.getText().toString().trim().length() == 0) {
                    toast("Please enter the roll number.");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "' ", null);
                if (c.moveToFirst()) {
                    db.execSQL("UPDATE student SET name='" + editName.getText() + "' , marks='" + editMarks.getText() + "' WHERE rollno='" + editRollno.getText() + "' ");
                    toast("Your record is updated sucessfully.");

                } else {
                    toast("Your roll number is invalid");

                }
                clearText();
            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editRollno.getText().toString().trim().length() == 0) {
                    showMessage("Warning", "Please enter the roll number first");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + editRollno.getText() + "'", null);
                if (c.moveToFirst()) {
                    editName.setText(c.getString(1));
                    editMarks.setText(c.getString(2));

                } else {
                    showMessage("Error", "Invalid Rollno");
                    clearText();
                }


            }
        });


        btnViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM student", null);
                if (c.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No records found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sb = new StringBuffer();
                while (c.moveToNext()) {
                    sb.append("Student Rollno:" + c.getString(0) + "\n");
                    sb.append("Student Name:" + c.getString(1) + "\n");
                    sb.append("Student Marks:" + c.getString(2) + "\n\n");

                }
                showMessage("Students Detail", sb.toString());

            }
        });


    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void toast(String messagee) {
        Toast.makeText(this, messagee, Toast.LENGTH_SHORT).show();
    }

    public void clearText() {
        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollno.requestFocus();
    }
}


