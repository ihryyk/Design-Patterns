package com.app.task2;

import com.app.task2.model.MusicDao;
import com.app.task2.view.PlayerFrame;
import com.app.task2.view_module.MusicPlayerViewModule;
import com.app.task2.view_module.Player;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Player player = new MusicPlayerViewModule(new MusicDao());
            JFrame frame = new PlayerFrame("Music Player", player);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setVisible(true);
        });
    }

}
