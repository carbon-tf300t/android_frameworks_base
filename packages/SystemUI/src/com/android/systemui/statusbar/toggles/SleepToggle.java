package com.android.systemui.statusbar.toggles;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.os.PowerManager;
import android.os.SystemClock;

import com.android.systemui.R;

public class SleepToggle extends BaseToggle {

    @Override
    public void init(Context c, int style) {
        super.init(c, style);
        setIcon(R.drawable.ic_qs_sleep);
        setLabel(R.string.quick_settings_sleep_label);
    }

    @Override
    public void onClick(View v) {
        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        pm.goToSleep(SystemClock.uptimeMillis());
    }

    @Override
    public boolean onLongClick(View v) {
        if (mFloatPref) {
            try {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setClassName("com.android.settings", "com.android.settings.Settings$DisplaySettingsActivity");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.addFlags(Intent.FLAG_FLOATING_WINDOW);
                startActivity(intent);
                collapseStatusBar();
            } catch(NullPointerException e) {
                // No intent found for activity component
            }
            return super.onLongClick(v);
        } else {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setClassName("com.android.settings", "com.android.settings.Settings$DisplaySettingsActivity");
            intent.addCategory("android.intent.category.LAUNCHER");
            startActivity(intent);
            collapseStatusBar();
            return super.onLongClick(v);
        }
    }

}
