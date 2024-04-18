package com.catchabreak;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TimerModel {

    private static final IntegerProperty timerSeconds = new SimpleIntegerProperty(TimerController.getWorkTimeSeconds());

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
