package com.app.task2.view_module;

import com.app.task2.model.MusicDao;
import com.app.task2.model.entity.PlayerState;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MusicPlayerViewModule implements Player {

  private final MusicDao musicDao;
  private int currentTrack;
  private PlayerState state;
  private boolean isRepeat = false;
  private final List<PlayerObserver> observers;

  public MusicPlayerViewModule(MusicDao musicDao) {
    this.observers = new ArrayList<>();
    this.state = PlayerState.STOPPED;
    this.musicDao = musicDao;
  }

  @Override
  public void addObserver(PlayerObserver observer) {
    observers.add(observer);
  }

  @Override
  public void playOrStop() {
    if (state == PlayerState.PLAYING) {
      state = PlayerState.STOPPED;
      notifyObserversPlayerStateChanged();
      log.info("Pausing: {}", getCurrentTrack());
      return;
    }
    state = PlayerState.PLAYING;
    notifyObserversPlayerStateChanged();
    log.info("Playing: {}", getCurrentTrack());
  }

  @Override
  public void next() {
    if (canPlayNextTrack()) {
      currentTrack++;
      log.info("Playing next track: {}", getCurrentTrack());
      notifyObserversTrackChange();
      return;
    }
    if (isRepeat) {
      currentTrack = 0;
      log.info("Replaying first track: {}", getCurrentTrack());
      notifyObserversTrackChange();
      return;
    }
    log.info("Already on the last track");

  }

  private boolean canPlayNextTrack() {
    return currentTrack + 1 < musicDao.getNumberOfMusic();
  }

  @Override
  public void previous() {
    if (canPlayPreviousTrack()) {
      currentTrack--;
      log.info("Playing previous track: {}", musicDao.getById(currentTrack).getAuthor());
      notifyObserversTrackChange();
      return;
    }
    log.info("Already on the first track");
  }

  private boolean canPlayPreviousTrack() {
    return currentTrack - 1 >= 0;
  }


  @Override
  public void repeatOnOrOff() {
    isRepeat = !isRepeat;
    notifyObserversRepetitionChange();
    log.info(isRepeat ? "Repeat on" : "Repeat off");
  }

  private String getCurrentTrack() {
    return musicDao.getById(currentTrack).getName();
  }

  private void notifyObserversPlayerStateChanged() {
    for (PlayerObserver observer : observers) {
      observer.playerStateChanged(state);
    }
  }

  private void notifyObserversRepetitionChange() {
    for (PlayerObserver observer : observers) {
      observer.repetitionChanged(isRepeat);
    }
  }

  private void notifyObserversTrackChange() {
    for (PlayerObserver observer : observers) {
      observer.trackChanged(getCurrentTrack());
    }
  }

}
