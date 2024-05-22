package com.app.task2.view;

import com.app.task2.model.entity.PlayerState;
import com.app.task2.view_module.Player;
import com.app.task2.view_module.PlayerObserver;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PlayerFrame extends JFrame implements PlayerObserver {

  private final JLabel musicNameLabel;
  private final Player player;
  private final JButton playOrPauseButton;
  private final JButton repeatButton;

  public PlayerFrame(String title, Player player) {
    super(title);
    this.player = player;
    this.player.addObserver(this);

    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    musicNameLabel = new JLabel();
    playOrPauseButton = new JButton("Play");
    JButton nextButton = createNextButton();
    JButton previousButton = createPreviousButton();
    repeatButton = new JButton("Repeat off");

    repeatButton.setBackground(Color.RED);
    repeatButton.addActionListener(e -> player.repeatOnOrOff());
    playOrPauseButton.addActionListener(e -> player.playOrStop());

    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 3;
    add(musicNameLabel, c);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 2;

    add(previousButton, c);
    c.gridx++;
    add(playOrPauseButton, c);
    c.gridx++;
    add(nextButton, c);
    c.gridx = 0;
    c.gridy++;
    c.gridwidth = 3;
    add(repeatButton, c);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  private JButton createNextButton() {
    JButton nextButton = new JButton("Next");
    nextButton.addActionListener(e -> player.next());
    return nextButton;
  }

  private JButton createPreviousButton() {
    JButton previousButton = new JButton("Previous");
    previousButton.addActionListener(e -> player.previous());
    return previousButton;
  }

  @Override
  public void trackChanged(String trackName) {
    musicNameLabel.setText(trackName);
  }

  @Override
  public void playerStateChanged(PlayerState state) {
    playOrPauseButton.setText(state == PlayerState.PLAYING ? "Pause" : "Play");
  }

  @Override
  public void repetitionChanged(boolean isOn) {
    repeatButton.setText(isOn ? "Repeat on" : "Repeat off");
    repeatButton.setBackground(isOn ? Color.GREEN : Color.RED);
  }

}