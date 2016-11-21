package com.example.adnan.panarecyclerview;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Person> list;
    RVadaptor rVadaptor;
    private int edit_position;
    RecyclerView recyclerView;
    private boolean add = false;

    View view;
    private EditText et_country;
    private AlertDialog.Builder alertDialog;
    private Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));
        list.add(new Person("Ayesha ALi", "20", R.drawable.alia));
        list.add(new Person("Ayesha ALi", "20", R.drawable.aliaa));

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
//        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.rv2);
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        LinearLayoutManager manager2 = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
//        recyclerView2.setLayoutManager(manager2);
        rVadaptor = new RVadaptor(list, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(v.getContext(), "" + position, Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(rVadaptor);
//        recyclerView2.setAdapter(rVadaptor);

        initializeSwipe();
        initDialog();
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView();
                add = true;
                alertDialog.setTitle("Add Country");
                et_country.setText("");
                alertDialog.show();
            }
        });
    }

    private void removeView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    public void initializeSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                rVadaptor.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    rVadaptor.removeItem(position);
                } else {
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit Country");
                    et_country.setText(list.get(position).getName());
                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.edit);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);

                    } else {

                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.del);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }

                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(rVadaptor);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);

        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (add) {
                    add = false;
                    rVadaptor.addItem(new Person(et_country.getText().toString(), "21", R.drawable.alia));
                    dialog.dismiss();
                } else {
                    list.set(edit_position, new Person(et_country.getText().toString(), "21", R.drawable.alia));
                    rVadaptor.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        et_country = (EditText) view.findViewById(R.id.et_country);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:

                break;
        }
    }
//onStart

}

