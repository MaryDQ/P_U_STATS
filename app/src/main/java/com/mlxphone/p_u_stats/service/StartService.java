package com.mlxphone.p_u_stats.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class StartService extends Service {

    Timer mTimer;
    public StartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        mTimer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {

            }
        };
        mTimer.schedule(task,1000,500);
        return super.onStartCommand(intent, flags, startId);
    }
}
