package xyz.yoav.spylocation;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsHelper {

    Context context;
    SharedPreferences sp;

    public SharedPrefsHelper(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(context.getString(R.string.sp_prefs_file),Context.MODE_PRIVATE);
    }

    public void setDisplayName(String displayName) {
        sp.edit().putString(context.getString(R.string.sp_display_name),displayName).apply();
    }

    public String getDisplayName() {
        return sp.getString(context.getString(R.string.sp_display_name),"");
    }
}
