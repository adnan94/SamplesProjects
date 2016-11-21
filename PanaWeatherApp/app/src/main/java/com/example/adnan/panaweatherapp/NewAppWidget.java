package com.example.adnan.panaweatherapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    public static AppWidgetManager manager;
    public static RemoteViews views;
    static int id;
    private static final String SYNC_CLICKED = "automaticWidgetSyncButtonClick";


    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        id = appWidgetId;
        manager = appWidgetManager;
        views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setOnClickPendingIntent(R.id.sync_button, getPendingSelfIntent(context, SYNC_CLICKED));

        if (Global.temperature != null) {
            views.setTextViewText(R.id.appwidget_text, Global.temperature.toString());
            views.setTextViewText(R.id.textViewCity, Global.city);
            views.setTextViewText(R.id.textViewCountry, Global.country);
            views.setTextViewText(R.id.sunrise, "Sunrise :" + Global.sunrise);
            views.setTextViewText(R.id.sunset, "Sunset :" + Global.sunset);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null) {
            Log.d("TAG", "Click");
            Toast.makeText(context,"clicked",Toast.LENGTH_SHORT).show();
//            MyService s = new MyService();
//            s.todayUpdate();
//            myApplication appClass = new myApplication();
//            appClass.todayUpdate();
        }
    }

    public static void updatee(Context con) {

        views = new RemoteViews(con.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, Global.temperature.toString());
        views.setTextViewText(R.id.textViewCity, Global.city);
        views.setTextViewText(R.id.textViewCountry, Global.country);
        views.setTextViewText(R.id.sunrise, "Sunrise :" + Global.sunrise);
        views.setTextViewText(R.id.sunset, "Sunset :" + Global.sunset);

        // Instruct the widget manager to update the widget
        manager.updateAppWidget(id, views);

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

