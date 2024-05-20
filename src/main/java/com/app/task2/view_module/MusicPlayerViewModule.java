package com.app.task2.view_module;

import com.app.task2.model.MusicDao;
import com.app.task2.model.entity.PlayerState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MusicPlayerViewModule implements Player {

    private final MusicDao musicDao;
    private int currentTrack;
    private PlayerState state;
    private boolean isRepeat = false;

    public MusicPlayerViewModule(MusicDao musicDao) {
        this.state = PlayerState.STOPPED;
        this.musicDao = musicDao;
    }

    @Override
    public PlayerState getState() {
        return state;
    }

    @Override
    public void playOrStop() {
        if (state == PlayerState.PLAYING) {
            state = PlayerState.STOPPED;
            log.info("Pausing: {}", getCurrentTrack());
            return;
        }
        state = PlayerState.PLAYING;
        log.info("Playing: {}", getCurrentTrack());
    }

    @Override
    public void next() {
        if (canPlayNextTrack()) {
            currentTrack++;
            log.info("Playing next track: {}", getCurrentTrack());
            return;
        }
        if (isRepeat) {
            currentTrack = 0;
            log.info("Replaying first track: {}", getCurrentTrack());
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
        log.info(isRepeat ? "Repeat on" : "Repeat off");
    }

    @Override
    public boolean isRepeat() {
        return isRepeat;
    }

    @Override
    public String getCurrentTrack() {
        return musicDao.getById(currentTrack).getName();
    }

}
