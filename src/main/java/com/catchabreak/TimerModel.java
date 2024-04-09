package com.catchabreak;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TimerModel {

    private static final TimerModel instance = new TimerModel();
    private final IntegerProperty timerSeconds = new SimpleIntegerProperty(1200);

    private TimerModel() {
        // Private constructor to enforce singleton pattern
    }

    public static TimerModel getInstance() {
        return instance;
    }

    public int getTimerSeconds() {
        return timerSeconds.get();
    }

    @SuppressWarnings("exports")
    public IntegerProperty timerSecondsProperty() {
        return timerSeconds;
    }

    public void setTimerSeconds(int timerSeconds) {
        this.timerSeconds.set(timerSeconds);
    }
}
