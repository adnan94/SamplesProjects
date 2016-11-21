package com.example.adnan.horizontallistviews;

import android.app.ActionBar;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.devsmart.android.ui.HorizontalListView;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.io.File;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    adaptor adap;
    ArrayList<imagee> list;
    ArrayList<imagee> imageList;
    ArrayList<imagee> imageList1;
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ScrollView child = (ScrollView) findViewById(R.id.ScrollChild);
//        parent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                findViewById(R.id.ScrollChild).getParent().requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        });
//        child.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                child.requestDisallowInterceptTouchEvent(false);
//                return false;
//            }
//        });


        list = new ArrayList<>();
        imageList = new ArrayList<>();
        imageList1 = new ArrayList<>();

        imageList.add(new imagee(R.drawable.big));
        imageList.add(new imagee(R.drawable.big));
        imageList.add(new imagee(R.drawable.big));
        imageList1.add(new imagee(R.drawable.big));
        imageList1.add(new imagee(R.drawable.big));
        imageList1.add(new imagee(R.drawable.big));
        imageList1.add(new imagee(R.drawable.big));
        imageList1.add(new imagee(R.drawable.big));
        imageList1.add(new imagee(R.drawable.big));


        Log.d("Tage", "" + list.size());

        final ExpandableHeightListView listVieww = (ExpandableHeightListView) findViewById(R.id.listView);
        ExpandableHeightListView expandableListView = (ExpandableHeightListView) findViewById(R.id.listView2);
        listView2 = (ListView) findViewById(R.id.listView2);

        adap = new adaptor(imageList1, this);

        listVieww.setAdapter(new adaptorVertical(imageList, this));
        listVieww.setExpanded(true);


        HorizontalListView listViewHozizontal = (HorizontalListView) findViewById(R.id.horizontalList);

        listViewHozizontal.setAdapter(adap);
        final adaptorVertical adaptorVertical = new adaptorVertical(imageList1, this);

        expandableListView.setAdapter(adaptorVertical);

        // This actually does the magic
        expandableListView.setExpanded(true);

        listView2.setAdapter(adaptorVertical);


        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageList1.add(new imagee(R.drawable.big));
                adaptorVertical.notifyDataSetChanged();

            }
        });
    }

    public ArrayList<String> getFilePaths() {


        Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA};
        Cursor c = null;
        SortedSet<String> dirList = new TreeSet<String>();
        ArrayList<String> resultIAV = new ArrayList<String>();

        String[] directories = null;
        if (u != null) {
            c = managedQuery(u, projection, null, null, null);
        }

        if ((c != null) && (c.moveToFirst())) {
            do {
                String tempDir = c.getString(0);
                tempDir = tempDir.substring(0, tempDir.lastIndexOf("/"));
                try {
                    dirList.add(tempDir);
                } catch (Exception e) {

                }
            }
            while (c.moveToNext());
            directories = new String[dirList.size()];
            dirList.toArray(directories);

        }

        for (int i = 0; i < dirList.size(); i++) {
            File imageDir = new File(directories[i]);
            File[] imageList = imageDir.listFiles();
            if (imageList == null)
                continue;
            for (File imagePath : imageList) {
                try {

                    if (imagePath.isDirectory()) {
                        imageList = imagePath.listFiles();

                    }
                    if (imagePath.getName().contains(".jpg") || imagePath.getName().contains(".JPG")
                            || imagePath.getName().contains(".jpeg") || imagePath.getName().contains(".JPEG")
                            || imagePath.getName().contains(".png") || imagePath.getName().contains(".PNG")
                            || imagePath.getName().contains(".gif") || imagePath.getName().contains(".GIF")
                            || imagePath.getName().contains(".bmp") || imagePath.getName().contains(".BMP")
                            ) {


                        String path = imagePath.getAbsolutePath();
                        resultIAV.add(path);

                    }
                }
                //  }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return resultIAV;


    }

    public void getListViewSize(ListView myListView, ArrayList<imagee> list) {

        int size = list.size();
        int height = size * 100;

        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = height;
        myListView.setLayoutParams(params);
        myListView.requestLayout();
    }
}