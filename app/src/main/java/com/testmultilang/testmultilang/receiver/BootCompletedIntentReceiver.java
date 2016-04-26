package com.testmultilang.testmultilang.receiver;

/**
 * Created by lenovo on 14-04-2016.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.testmultilang.testmultilang.service.OfflineSyncService;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, OfflineSyncService.class);
            context.startService(pushIntent);
        }
    }
}
