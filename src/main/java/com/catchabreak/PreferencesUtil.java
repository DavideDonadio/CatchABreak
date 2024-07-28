package com.catchabreak;

import java.util.prefs.Preferences;

public class PreferencesUtil {

    private static final Preferences prefs = Preferences.userNodeForPackage(PreferencesUtil.class);

    private static final String THEME_KEY = "THEME";
    private static final String BREAKTIME_MINUTES_KEY = "BTM";
    private static final String WORKTIME_MINUTES_KEY = "WTS";
    private static final String NUMOFBREAKS_KEY = "BREAKS";
    private static final String NUMOF_TIMERSTARTED_KEY = "TIMERSTART";
    private static final String NUMOF_TIMERSTOPPED_KEY = "TIMERSTOP";
    private static final String NUMOF_TIMER_RESTARTED_KEY = "TIMERESTART";

    public static String getTheme(){
        return prefs.get(THEME_KEY, "light");
    }

    public static void setTheme(String THEME){
        prefs.put(THEME_KEY, THEME);
    }

    public static int getBreakTimeMinutes(){
        return prefs.getInt(BREAKTIME_MINUTES_KEY, 5);
    }

    public static int getWorkTimeMinutes(){
        return prefs.getInt(WORKTIME_MINUTES_KEY, 20);
    }

    public static int getWorkTimeSeconds(){
        return (prefs.getInt(WORKTIME_MINUTES_KEY, 20) * 60);
    }

    public static int getBreakTimeSeconds(){
        return (prefs.getInt(BREAKTIME_MINUTES_KEY, 5) * 60);
    }

    public static void setBreakTimeMinutes(int minutes){
        prefs.putInt(BREAKTIME_MINUTES_KEY, minutes);
    }

    public static void setWorkTimeMinutes(int minutes){
        prefs.putInt(WORKTIME_MINUTES_KEY, minutes);
    }

    public static int getNumberOfBreaks(){
        return (prefs.getInt(NUMOFBREAKS_KEY, 0));
    }

    public static void addBreak(){
        prefs.putInt(NUMOFBREAKS_KEY, getNumberOfBreaks() + 1);
    }

    public static int getNumberOfTimerStarted(){
        return (prefs.getInt(NUMOF_TIMERSTARTED_KEY, 0));
    }

    public static int getNumberOfTimerStopped(){
        return (prefs.getInt(NUMOF_TIMERSTOPPED_KEY, 0));
    }

    public static int getNumberOfTimerRestarted(){
        return (prefs.getInt(NUMOF_TIMER_RESTARTED_KEY, 0));
    }

    public static void addTimerStart(){
        prefs.putInt(NUMOF_TIMERSTARTED_KEY, getNumberOfTimerStarted() + 1);
    }   

    public static void addTimerStop(){
        prefs.putInt(NUMOF_TIMERSTOPPED_KEY, getNumberOfTimerStopped() + 1);
    }   

    public static void addTimerRestart(){
        prefs.putInt(NUMOF_TIMER_RESTARTED_KEY, getNumberOfTimerRestarted() + 1);
    }

    @SuppressWarnings("exports")
    public static Preferences getPrefs() {
        return prefs;
    }
        
}
