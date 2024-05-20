package com.app.task2.view_module;

import com.app.task2.model.entity.PlayerState;

public interface PlayerObserver {

  void trackChanged(String trackName);

  void playerStateChanged(PlayerState state);

  void repetitionChanged(boolean isOn);

}

