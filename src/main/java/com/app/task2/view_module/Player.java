package com.app.task2.view_module;

import com.app.task2.model.entity.PlayerState;

public interface Player {

    PlayerState getState();

    void playOrStop();

    void next();

    void previous();

    void repeatOnOrOff();

    String getCurrentTrack();

    boolean isRepeat();
}
