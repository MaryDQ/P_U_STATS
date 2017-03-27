package com.mlxphone.p_u_stats.service;

import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;

import com.mlxphone.p_u_stats.activities.TipActivity;
import com.mlxphone.p_u_stats.utils.UsageStatsManger;

import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import static android.content.ContentValues.TAG;

public class StartService extends Service {

    Context mContext;
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
                getTopApp();
            }
        };
        mTimer.schedule(task, 1000, 500);
        return super.onStartCommand(intent, flags, startId);
    }

    private void getTopApp() {
        UsageStatsManager mUsageStatsManager= (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        List<UsageStats> usageStatsList = mUsageStatsManager.queryUsageStats(UsageStatsManger.INTERVAL_BEST, time - 2000, time);

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
                Intent intent1 = new Intent(mContext, TipActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        }
    }

    public String getLauncherPackageName(Context context) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);
        if (res.activityInfo == null) {
            return "";
        }
        if (res.activityInfo.packageName.equals("android")) {
            return "";
        } else {
            return res.activityInfo.packageName;
        }
    }
}
