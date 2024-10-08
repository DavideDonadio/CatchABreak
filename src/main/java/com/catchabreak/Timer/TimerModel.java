package com.catchabreak.Timer;

import com.catchabreak.Utils.PreferencesUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TimerModel {

    private static final IntegerProperty timerSeconds = new SimpleIntegerProperty(PreferencesUtil.getWorkTimeSeconds());

    public static int getTimerSeconds() {
        return timerSeconds.get();
    }

    public static void setTimerSeconds(int seconds) {
        timerSeconds.set(seconds);
    }

    @SuppressWarnings("exports")
    public static IntegerProperty timerSecondsProperty() {
        return timerSeconds;
    }
}
