package com.aziz.practivcee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class PopUpMenu extends AppCompatActivity {
    Button button1;
    TextView descText;
    PopupWindow popUp;

    ImageButton show, hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        descText = (TextView) findViewById(R.id.description_text);
        show = (ImageButton) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Show button");
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);

            }
        });
        hide = (ImageButton) findViewById(R.id.hide);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Hide button");
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(1);

            }
        });
        button1 = (Button) findViewById(R.id.button1);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PopUpMenu.this, ExpandableListViewss.class));
            }
        });
        ((Button) findViewById(R.id.button12)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUp = new PopupWindow(PopUpMenu.this);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(PopUpMenu.this, button1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.layout_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()) {
                            case "one":
                                Toast.makeText(PopUpMenu.this, "You Clicked one : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case "two":
                                Toast.makeText(PopUpMenu.this, "You Clicked two : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                break;
                            case "three":
                                Toast.makeText(PopUpMenu.this, "You Clicked three : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                break;

                        }
                        return true;
                    }
                });


                popup.show();//showing popup menu
            }
        });


    }

}

