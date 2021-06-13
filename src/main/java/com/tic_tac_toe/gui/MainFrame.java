package com.tic_tac_toe.gui;

import com.tic_tac_toe.gui.gamepanel.GamePanel;
import com.tic_tac_toe.gui.utilspanel.UtilsPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        setSize(500, 500);

        UtilsPanel utilsPanel = new UtilsPanel();
        GamePanel gamePanel = new GamePanel();

//        utilsPanel.addlistene

        add(utilsPanel);
        add(gamePanel);

    }

}
