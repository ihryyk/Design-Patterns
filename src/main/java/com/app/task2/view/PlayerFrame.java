package com.app.task2.view;

import com.app.task2.model.entity.PlayerState;
import com.app.task2.view_module.Player;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PlayerFrame extends JFrame {

    private final JLabel nowPlayingLabel;
    private final Player player;

    public PlayerFrame(String title, Player player) {
        super(title);
        this.player = player;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        nowPlayingLabel = new JLabel("Now playing: ");
        JButton playOrPauseButton = createPlayOrPauseButton();
        JButton nextButton = createNextButton();
        JButton previousButton = createPreviousButton();
        JButton repeatButton = createRepeatButton();
        addLabelsAndButtonsToFrame(constraints, playOrPauseButton, nextButton, previousButton, repeatButton);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createPlayOrPauseButton() {
        JButton playOrPauseButton = new JButton("Play");
        playOrPauseButton.addActionListener(e -> {
            player.playOrStop();
            if (player.getState() == PlayerState.PLAYING) {
                playOrPauseButton.setText("Pause");
                nowPlayingLabel.setText("Now playing: " + player.getCurrentTrack());
                return;
            }
            playOrPauseButton.setText("Play");
            nowPlayingLabel.setText("Now pausing: " + player.getCurrentTrack());
        });
        return playOrPauseButton;
    }

    private JButton createNextButton() {
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            player.next();
            nowPlayingLabel.setText("Now playing: " + player.getCurrentTrack());
        });
        return nextButton;
    }

    private JButton createPreviousButton() {
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(e -> {
            player.previous();
            nowPlayingLabel.setText("Now playing: " + player.getCurrentTrack());
        });
        return previousButton;
    }

    private JButton createRepeatButton() {
        JButton repeatButton = new JButton("Repeat");
        repeatButton.setBackground(Color.RED);
        repeatButton.addActionListener(e -> {
            player.repeatOnOrOff();
            if (player.isRepeat()) {
                repeatButton.setBackground(Color.GREEN);
                return;
            }
            repeatButton.setBackground(Color.RED);
        });
        return repeatButton;
    }

    private void addLabelsAndButtonsToFrame(GridBagConstraints constraints, JButton playOrPauseButton,
                                            JButton nextButton, JButton previousButton, JButton repeatButton) {

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        add(nowPlayingLabel, constraints);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(previousButton, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(playOrPauseButton, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(nextButton, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        add(repeatButton, constraints);
    }

}