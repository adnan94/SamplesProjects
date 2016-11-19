package com.example.adnan.panaanimation;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class pictureViewer extends AppCompatActivity {

    int mCurrentDrawable = 0;
    int drawableIDs[] = {
            R.mipmap.p1,
            R.mipmap.p2,
            R.mipmap.p3,
            R.mipmap.p4,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_viewer);

        // This app works by having two views, which get faded in/out for the cross-fade effect
        final ImageView prevImageView = (ImageView) findViewById(R.id.prevImageView);
        final ImageView nextImageView = (ImageView) findViewById(R.id.nextImageView);
        prevImageView.setBackgroundColor(Color.TRANSPARENT);
        nextImageView.setBackgroundColor(Color.TRANSPARENT);

        // Setup default ViewPropertyAnimator durations for the two ImageViews
        prevImageView.animate().setDuration(1000);
        nextImageView.animate().setDuration(1000);

        // NOte that a real app would do this more robustly, and not just load all possible
        // bitmaps at onCreate() time.
        final BitmapDrawable drawables[] = new BitmapDrawable[drawableIDs.length];
        for (int i = 0; i < drawableIDs.length; ++i) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    drawableIDs[i]);
            drawables[i] = new BitmapDrawable(getResources(), bitmap);
        }
        prevImageView.setImageDrawable(drawables[0]);
        nextImageView.setImageDrawable(drawables[1]);

        prevImageView.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // Use ViewPropertyAnimator to fade the previous imageView out and the next one in
                prevImageView.animate().alpha(0).withLayer();
                nextImageView.animate().alpha(1).withLayer().
                        withEndAction(new Runnable() {
                            // When the animation ends, set up references to change the prev/next
                            // associations
                            @Override
                            public void run() {
                                mCurrentDrawable =
                                        (mCurrentDrawable + 1) % drawables.length;
                                int nextDrawableIndex =
                                        (mCurrentDrawable + 1) % drawables.length;
                                prevImageView.setImageDrawable(drawables[mCurrentDrawable]);
                                nextImageView.setImageDrawable(drawables[nextDrawableIndex]);
                                nextImageView.setAlpha(0f);
                                prevImageView.setAlpha(1f);
                            }
                        });
            }
        });
    }
}
