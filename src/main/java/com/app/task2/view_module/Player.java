package com.app.task2.view_module;

public interface Player {

  void addObserver(PlayerObserver observer);

  void playOrStop();

  void next();

  void previous();

  void repeatOnOrOff();

}
