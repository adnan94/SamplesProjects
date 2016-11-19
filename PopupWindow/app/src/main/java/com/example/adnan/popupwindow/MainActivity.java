package com.example.adnan.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> str;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup_content, null);
        str = new ArrayList<>();

        str.add("Adnan");
        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");

        str.add("Zeeshan");
        str.add("Adnan");


        str.add("Zeeshan");
        str.add("Adnan");

        final adaptor adaptor = new adaptor(MainActivity.this, str);
        final ListView list = (ListView) popupView.findViewById(R.id.listView);
        final Button btnOpenPopup = (Button) findViewById(R.id.idopenPopup);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (popupWindow == null) {
                    popupWindow = new PopupWindow(
                            popupView,
                            ActionBar.LayoutParams.WRAP_CONTENT,
                            ActionBar.LayoutParams.WRAP_CONTENT);
                    list.setAdapter(adaptor);
                    popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

                } else if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else if (!popupWindow.isShowing()) {
                    popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

                }


            }


//                }


        });
//        ((RelativeLayout)findViewById(R.id.activity_main)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (popupWindow != null) {
//                    popupWindow.dismiss();
//
//                }
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (popupWindow != null) {
            popupWindow.dismiss();

        }
    }
}
