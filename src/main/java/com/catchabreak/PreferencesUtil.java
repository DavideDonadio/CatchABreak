package com.catchabreak;

import java.util.prefs.Preferences;

public class PreferencesUtil {

    private static final Preferences prefs = Preferences.userNodeForPackage(PreferencesUtil.class);

    private static final String THEME_KEY = "THEME";
    private static final String BREAKTIMEMINUTES_KEY = "BTM";
    private static final String WORKTIMEMINUTES_KEY = "WTS";

    public static String getTheme(){
        return prefs.get(THEME_KEY, "light");
    }

    public static void setTheme(String THEME){
        prefs.put(THEME_KEY, THEME);
    }

    public static int getBreakTimeMinutes(){
        return prefs.getInt(BREAKTIMEMINUTES_KEY, 5);
    }

    public static int getWorkTimeMinutes(){
        return prefs.getInt(WORKTIMEMINUTES_KEY, 20);
    }

    public static int getWorkTimeSeconds(){
        return (prefs.getInt(WORKTIMEMINUTES_KEY, 20) * 60);
    }

    public static int getBreakTimeSeconds(){
        return (prefs.getInt(BREAKTIMEMINUTES_KEY, 5) * 60);
    }

    public static void setBreakTimeMinutes(int minutes){
        prefs.putInt(BREAKTIMEMINUTES_KEY, minutes);
        TimerController.setBreakTimeMinutes(minutes);
    }

    public static void setWorkTimeMinutes(int minutes){
        prefs.putInt(WORKTIMEMINUTES_KEY, minutes);
        TimerController.setWorkTimeMinutes(minutes);
    }


}