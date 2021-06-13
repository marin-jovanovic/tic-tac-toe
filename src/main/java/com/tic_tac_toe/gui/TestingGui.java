package com.tic_tac_toe.gui;

import com.tic_tac_toe.gamedrivers.board.Game;
import com.tic_tac_toe.gamedrivers.point.Point;
import com.tic_tac_toe.gamedrivers.tile.TileOwner;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TestingGui extends JFrame {
    private final JButton[][] buttons;
    JTextField jTextField;

    public TestingGui() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AtomicReference<Game> g = new AtomicReference<>(new Game());
        setLayout(new GridLayout(0, g.get().getXAxisLength()));
        setSize(500, 500);
        buttons = new JButton[g.get().getYAxisLength()][g.get().getXAxisLength()];


        for (int y = 0; y < g.get().getYAxisLength(); y++) {
            for (int x = 0; x < g.get().getXAxisLength(); x++) {

                buttons[y][x] = new JButton(x + " " + y);

                this.add(buttons[y][x]);

                int finalX = x;
                int finalY = y;
                buttons[y][x].addActionListener(e -> {

                    g.get().setTile(new Point(finalX, finalY), TileOwner.USER_1);

                    buttons[finalY][finalX].setEnabled(false);
                    buttons[finalY][finalX].setDisabledIcon(getImageIcon("o"));
                    buttons[finalY][finalX].setIcon(getImageIcon("o"));

                    g.get().printBoard(0);

                    if (g.get().isGameWon(new Point(finalX, finalY), TileOwner.USER_1)) {
                        System.out.println("game won");
                        jTextField.setText("VICTORY");
                        for (int i = 0; i < g.get().getYAxisLength(); i++) {
                            for (int j = 0; j < g.get().getXAxisLength(); j++) {

                                buttons[i][j].setEnabled(false);
//								buttons[i][j].setIcon(null);

                            }
                        }

                    }

                    System.out.println();

                    Point computerMove = g.get().computerMove();

                    g.get().setTile(new Point(computerMove.getX(), computerMove.getY()), TileOwner.COMPUTER);
                    buttons[computerMove.getY()][computerMove.getX()].setEnabled(false);
                    buttons[computerMove.getY()][computerMove.getX()].setIcon(getImageIcon("x"));
                    buttons[computerMove.getY()][computerMove.getX()].setDisabledIcon(getImageIcon("x"));

                });

            }


        }
        jTextField = new JTextField();
        add(jTextField);

        JButton restartBtn = new JButton("restart");
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            g.set(new Game());
            for (int i = 0; i < g.get().getYAxisLength(); i++) {
                for (int j = 0; j < g.get().getXAxisLength(); j++) {

                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setIcon(null);

                }
            }
            jTextField.setText("");
        });


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestingGui::new);
    }

    ImageIcon getImageIcon(String val) {
        return new ImageIcon(Objects.requireNonNull(TestingGui.class.getResource("/images/" + val + ".png")));
    }

}



