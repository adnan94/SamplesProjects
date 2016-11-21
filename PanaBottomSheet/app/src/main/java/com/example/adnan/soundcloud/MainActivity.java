package com.example.adnan.soundcloud;

import android.media.Image;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.custom.widget.waveformseekbar.WaveformSeekBar;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton ib;
    boolean showFAB;
    TextView tv, tv2;
    Animation shrinkAnimation;
    Animation growAnimation;
    int height, width;
    boolean flag;
    BottomSheetBehavior behavior;
    MediaPlayer player;
    //    =MediaPlayer.create(this,R.raw.manamad);
    View bottomSheet;
    //    private KenBurnsView mImg;
    WaveformSeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);

//        mImg = (KenBurnsView) findViewById(R.id.image);
//        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
//        RandomTransitionGenerator generator = new RandomTransitionGenerator(1000, ACCELERATE_DECELERATE);
//duration = 10000ms = 10s and interpolator = ACCELERATE_DECELERATE
//        mImg.setTransitionGenerator(generator); //set new transition on kbv
//        mImg.resume();

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        Log.d("RED", "" + width);

        ib = (ImageButton) findViewById(R.id.imgBtn);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        player = MediaPlayer.create(this, R.raw.manamad);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    player.pause();
//                    mImg.pause();
                    ib.setBackgroundResource(R.drawable.play);
                    flag = false;
                } else {
                    player.start();
//                    mImg.resume();
                    ib.setBackgroundResource(R.drawable.pause);
                    flag = true;
                }
//                player.start();
            }
        });

        growAnimation = AnimationUtils.loadAnimation(this, R.anim.grow);
        shrinkAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink);
//        ()

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        if (showFAB) {
//                            behavior.setPeekHeight(height - 190);

//
//                            YoYo.with(Techniques.ZoomOut).duration(1000).playOn(tv);
//                            YoYo.with(Techniques.ZoomOut).duration(1000).playOn(ib);
                        } else {
////                            tv.startAnimation(shrinkAnimation);
//                            YoYo.with(Techniques.ZoomIn).duration(1000).playOn(tv);
//                            YoYo.with(Techniques.ZoomIn).duration(1000).playOn(ib);
//
//                            behavior.setPeekHeight(120);

                        }
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        showFAB = true;
//                        YoYo.with(Techniques.ZoomIn).duration(1000).playOn(tv);
//                        YoYo.with(Techniques.ZoomIn).duration(1000).playOn(ib);
//                        YoYo.with(Techniques.ZoomIn).duration(1000).playOn(tv2);

//                        tv.setVisibility(View.VISIBLE);
///**/                        tv.startAnimation(growAnimation);
                        tv.setTextSize(18);
                        tv2.setTextSize(13);

                        tv.setBackgroundResource(R.color.colorCollapse);
                        tv2.setBackgroundResource(R.color.colorCollapse);
                        tv2.setPadding(0, 0, 0, 0);
//                        tv.setBottom(R.id.textView2);
                        ib.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        showFAB = false;

                        bottomSheet.getLayoutParams().height = height;

//                        YoYo.with(Techniques.ZoomIn).duration(1000).playOn(tv);
//                        YoYo.with(Techniques.FadeIn).duration(1000).playOn(ib);
//                        YoYo.with(Techniques.FadeIn).duration(1000).playOn(tv2);
//                        tv.setVisibility(View.INVISIBLE);
//                        tv2.setVisibility(View.INVISIBLE);
//                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)tv.getLayoutParams();
//                        layoutParams.setMargins(0, 100, 0, 0);
//                        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams)tv2.getLayoutParams();
//                        layoutParams2.setMargins(0, 100, 0, 0);
                        tv.setTextSize(24);
                        tv2.setTextSize(20);
                        tv.setBackgroundResource(R.color.colorExpanded);
                        tv2.setBackgroundResource(R.color.colorExpanded);


//                        tv.setPadding(0, 100, 0, 0);
                        tv2.setPadding(0, 100, 0, 0);

                        ib.setVisibility(View.INVISIBLE);


                        break;


                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.bottom_sheet,new BlankFragment()).commit();

    }
}
