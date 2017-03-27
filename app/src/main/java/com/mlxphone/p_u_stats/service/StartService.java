package com.mlxphone.p_u_stats.service;

import android.app.Service;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.mlxphone.p_u_stats.utils.UsageStatsManger;

import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import static android.content.ContentValues.TAG;

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
    public int onStartCommand(Intent intent, int flags, int startId) {
        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
        mTimer.schedule(task, 1000, 500);
        return super.onStartCommand(intent, flags, startId);
    }

    private void getToApp() {
        //usagestats
        UsageStatsManger mUsageStatsManger = (UsageStatsManger) getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        List<UsageStats> usageStatsList = mUsageStatsManger.queryUsageStats(UsageStatsManger.INTERVAL_BEST, time - 2000, time);
        if (usageStatsList != null && usageStatsList.isEmpty()) {
            SortedMap<Long, UsageStats> usageStatsMap = new TreeMap<>();
            for (UsageStats usageStats : usageStatsList) {
                usageStatsMap.put(usageStats.getLastTimeUsed(), usageStats);
            }
            if (!usageStatsMap.isEmpty()) {
                String topPackageName = usageStatsMap.get(usageStatsMap.lastKey()).getPackageName();

                //if ()//判断和当前包名是否一样，是则返回
                Log.e(TAG, topPackageName);

                //模拟home点击
                Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);

                //启动提示页面
                Intent intent1=new Intent();
            }
        }
    }
}
