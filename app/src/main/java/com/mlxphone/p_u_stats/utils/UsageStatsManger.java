package com.mlxphone.p_u_stats.utils;

import android.app.usage.UsageStats;

import java.util.List;

/**
 * Created by MLXPHONE on 2017/3/27.
 */

public final class UsageStatsManger {
    public static final int INTERVAL_BEST = 4;//根据提供的开始、结束时间决定时间间隔
    public static final int INTERVAL_DAILY = 0;//以天为时间间隔（最长7天）
    public static final int INTERVAL_WEEKLY = 1;//以月为时间间隔（最长6个月）
    public static final int INTERVAL_MONTHLY = 2;//以周为时间间隔（最长4个星期）
    public static final int INTERVAL_YEARLY = 3;//以年为时间间隔（最长2年）

    UsageStatsManger() {
        throw new RuntimeException("Stub!");
    }

    public List<UsageStats> queryUsageStats(int intervalType,
                                            long beginTime, long endTime) {
        throw new RuntimeException("Stub!");
    }

    public List<UsageStats> queryConfigurations(int intervalType,
                                            long beginTime, long endTime) {
        throw new RuntimeException("Stub!");
    }

    public List<UsageStats> queryEvents(int intervalType,
                                            long beginTime, long endTime) {
        throw new RuntimeException("Stub!");
    }

    public List<UsageStats> queryAndAggregateUsageStats(int intervalType,
                                            long beginTime, long endTime) {
        throw new RuntimeException("Stub!");
    }

    public List<UsageStats> isAppInactive(String packageName) {
        throw new RuntimeException("Stub!");
    }

}
