package com.example.adnan.floatingnotificationui;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyService extends Service {

//    WindowManager windowManager;
//    LinearLayout linearLayout;
//    ImageView iv;
WindowManager wm;
    LinearLayout lay;
    float downX,downY;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
//        Button btnstart=(Button)findViewById(R.id.button1);
//        Button btnstop=(Button)findViewById(R.id.button2);

//        btnstart.setOnClickListener(new OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                             TODO Auto-generated method stub

                                            if (lay == null) {
                                                wm = (WindowManager) getApplicationContext().getSystemService(
                                                        Context.WINDOW_SERVICE);
                                                final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                                                        WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,
                                                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
                                                                | WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                                                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                                                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                                        PixelFormat.TRANSLUCENT);
                                                params.x = (int) wm.getDefaultDisplay().getWidth();
                                                params.y = 0;
                                                // params.height = wm.getDefaultDisplay().getHeight()/2;
                                                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                                                params.format = PixelFormat.TRANSLUCENT;

                                                params.gravity = Gravity.CENTER | Gravity.LEFT;
                                                params.setTitle("Info");


                                                // lay.setAlpha(0.5f);

                                                final TextView txt_no = new TextView(getApplicationContext());
                                                txt_no.setTextSize(10.0f);
                                                txt_no.setText("Moving view by stack user!");

                                                txt_no.setTextColor(Color.BLACK);
                                                // txt_no.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                                                // LayoutParams.WRAP_CONTENT));

                                             ;

                                                txt_no.setGravity(Gravity.RIGHT);
                                                txt_no.setBackgroundColor(Color.WHITE);
                                                txt_no.setPadding(10, 10, 10, 10);


                                                // Tell it to persist after the animation ends
                                                // And then on your layout
                                                wm.addView(txt_no, params);

                                                downX = params.x;
                                                downY = params.y;

                                                Log.v("MSES>", "x=" + downX + ",y=" + downY);

                                                txt_no.setOnTouchListener(new View.OnTouchListener() {

                                                    @Override
                                                    public boolean onTouch(View v, MotionEvent event) {
                                                        // TODO Auto-generated method stub

                                                        switch (event.getAction()) {
                                                            case MotionEvent.ACTION_MOVE:

                                                                params.x = Math.round(event.getRawX() - downX);
                                                                params.y = Math.round(event.getRawY() - downY);
                                                                wm.updateViewLayout(txt_no, params);
                                                                Log.v("MSES EVENT>", "x=" + event.getRawX() + ",y=" + event.getRawY());
                                                                Log.v("MSES MOVE>", "x=" + params.x + ",y=" + params.y);
                                                                return true;
                                                            case MotionEvent.ACTION_DOWN:
                                                                downX = event.getRawX() - params.x;
                                                                downY = event.getRawY() - params.y;
                                                                Log.v("MSES DOWN>", "x=" + params.x + ",y=" + params.y);
                                                                return true;
                                                            case MotionEvent.ACTION_UP:
                                                                //params.x = Math.round(event.getRawX() - downX);
                                                                //params.y = Math.round(event.getRawY() - downY);
                                                                //wm.updateViewLayout(lay, params);
                                                                return true;

                                                        }
                                                        return false;
                                                    }
                                                });
                                            }

                                        }

                                        //        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE)
//        linearLayout = new LinearLayout(this);
//        iv = new ImageView(this);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(130, 130);
//        iv.setImageResource(R.drawable.lolo);
//        iv.setBackgroundResource(R.color.trans);
//        iv.setLayoutParams(layoutParams);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        linearLayout.setBackgroundResource(R.color.trans);
//        linearLayout.setLayoutParams(params);
//
//        final WindowManager.LayoutParams wLayoutParams = new WindowManager.LayoutParams(130, 130, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
//        wLayoutParams.x = 0;
//        wLayoutParams.y = 0;
//        wLayoutParams.gravity = Gravity.CENTER | Gravity.CENTER;
//        linearLayout.addView(iv);
//        windowManager.addView(linearLayout, wLayoutParams);
//        linearLayout.setOnTouchListener(new View.OnTouchListener() {
//            private WindowManager.LayoutParams updatedParams = wLayoutParams;
//            int x, y;
//            float touchX, touchY;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//
//                        x = updatedParams.x;
//                        y = updatedParams.y;
//
//                        touchX = event.getRawX();
//                        touchY = event.getRawY();
//
//
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        updatedParams.x = (int) (x + (event.getRawX() - touchX));
//                        updatedParams.y = (int) (y + (event.getRawY() - touchY));
//
//                        windowManager.updateViewLayout(linearLayout, updatedParams);
//                        break;
//                }
//                return false;
//            }
//        });
//        super.onCreate();
//    }
                                        public void stop() {
                                            stopSelf();

                                        }

                                        @Override
                                        public void onDestroy() {
                                            super.onDestroy();
//        windowManager.removeView(linearLayout);

                                            Log.d("des", "destroy");
                                        }
                                    }
//  public void floting(final String name, final String message, final String url, final String pid) {
//        if (wm != null) {
//            wm.removeView(stop);
//
//        }
//        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//
////        ll = new LinearLayout(this);
//
////        LinearLayout.LayoutParams layoutParameteres = new LinearLayout.LayoutParams(
////                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
////        ll.setLayoutParams(layoutParameteres);
//
//        final WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(
//                130, 130, 2007, 8, -3);
//        parameters.x = 0;
//        parameters.y = 0;
//        parameters.gravity = Gravity.CENTER_HORIZONTAL | Gravity.RIGHT;
//
//
//        stop = new CircularImageView(this);
//        stop.setBorderWidth(1);
//        stop.setBorderColor(Color.BLACK);
//        stop.setBackgroundResource(R.color.trans);
//        stop.setFocusable(true);
//        Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.userdefaul).error(R.drawable.userdefaul).into(stop);
//
//
//        ViewGroup.LayoutParams btnParameters = new ViewGroup.LayoutParams(130, 130);
//        stop.setLayoutParams(btnParameters);
//
////
//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
//                dialog(name, message, url, pid);
//            }
//        });
//
//        wm.addView(stop, parameters);
//
//        stop.setOnTouchListener(new View.OnTouchListener() {
//            WindowManager.LayoutParams updatedParameters = parameters;
//            double x;
//            double y;
//            double pressedX;
//            double pressedY;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//
//                        x = updatedParameters.x;
//                        y = updatedParameters.y;
//
//                        pressedX = event.getRawX();
//                        pressedY = event.getRawY();
//
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//                        updatedParameters.x = (int) (x + (event.getRawX() - pressedX));
//                        updatedParameters.y = (int) (y + (event.getRawY() - pressedY));
//
//                        wm.updateViewLayout(stop
//                                , updatedParameters);
//
//                    default:
//                        break;
//                }
//
//                return false;
//            }
//        });
