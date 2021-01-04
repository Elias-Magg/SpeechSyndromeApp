package com.example.speechsyndromeapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class OnClearFromRecentService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MessageSender messageSender = new MessageSender();
        messageSender.execute("Start");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        MessageSender messageSender = new MessageSender();
        messageSender.execute("End");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        stopSelf();
        onDestroy();
    }

}
