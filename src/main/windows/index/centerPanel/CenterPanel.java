package main.windows.index.centerPanel;

import main.eventDrivers.Command;
import main.tictactoeDrivers.Cell;
import main.tictactoeDrivers.Table;
import main.resourceManagers.constants.Constant;
import main.resourceManagers.images.Image;
import main.resourceManagers.sounds.SoundsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

//    TODO
//      enable/ disable left click operations while under right click element (flag, question mark)

public class CenterPanel extends JPanel implements PropertyChangeListener {
//    todo stop timer when computer is playing

    private final PropertyChangeSupport support;

    //    table - blueprint of field
    private Table table;

    //    can you click on button (including left and right click operations)
    private boolean areButtonsActive = true;

    private JButton[][] buttons;

//    used for starting timer
    private boolean isFirstButtonClicked = false;

    public CenterPanel() {

        support = new PropertyChangeSupport(this);

        setLayout(new GridLayout((int) Constant.NUMBER_OF_COLUMNS.getValue(),
                (int) Constant.NUMBER_OF_ROWS.getValue()));


        buttons = new JButton[(int) Constant.NUMBER_OF_COLUMNS.getValue()][(int) Constant.NUMBER_OF_ROWS.getValue()];

        table = new Table();

        for (int i = 0; i < (int) Constant.NUMBER_OF_COLUMNS.getValue(); i++) {
            for (int j = 0; j < (int) Constant.NUMBER_OF_ROWS.getValue(); j++) {

                buttons[i][j] = new JButton();

                buttons[i][j].setIcon(Image.CLOSED_CELL.getImageIcon());

                buttons[i][j].addActionListener(new LeftClickListener(this));

                this.add(buttons[i][j]);

            }
        }
    }


    /**
     * all logic for setting buttons
     *
     * @param value
     */
    private void setButtons(boolean value) {
        areButtonsActive = value;
    }

    public void checkForWin() {
        if (table.checkForWin(Cell.CellStatus.USER)) {

            System.out.println("game is won");
            setButtons(false);

            support.firePropertyChange("game won", null, Command.GAME_WON);
        }
    }


    public void restart() {
        System.out.println("center panel: restart");

        isFirstButtonClicked = false;

        setButtons(true);
        restartButtons();

        table = new Table();

//        table = new Table((int) Constant.NUMBER_OF_ROWS.getValue(),
//                (int) Constant.NUMBER_OF_COLUMNS.getValue(),
//                (int) Constant.NUMBER_OF_MINES.getValue());
    }

    /**
     * enables all buttons and sets icon to {@code CLOSED_CELL}
     */
    private void restartButtons() {

        for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
            for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setIcon(Image.CLOSED_CELL.getImageIcon());
            }
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == Command.NEW_GAME) {
            restart();
        } else {
            System.out.println("unsupported command in center panel");
            System.out.println(evt);
        }
    }

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener p) {
        support.removePropertyChangeListener(p);
    }

    private class LeftClickListener implements ActionListener {

        private final CenterPanel centerPanel;

        public LeftClickListener(CenterPanel centerPanel) {
            this.centerPanel = centerPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            checkForWin();

            if (areButtonsActive) {

                for (int i = 0; i < (int) Constant.NUMBER_OF_ROWS.getValue(); i++) {
                    for (int j = 0; j < (int) Constant.NUMBER_OF_COLUMNS.getValue(); j++) {
                        if (buttons[i][j].toString().equals(e.getSource().toString())) {

                            System.out.println("clicked " + i + " " + j);

                            if (!centerPanel.isFirstButtonClicked) {

                                support.firePropertyChange("start timer", null,
                                        Command.START_TIMER);
                                centerPanel.isFirstButtonClicked = true;
                            }

                            if (! table.getCell(i, j).setCellStatus(Cell.CellStatus.USER)) {
                                System.out.println("cant place there");

                            } else {
                                buttons[i][j].setIcon(Image.VICTORY.getImageIcon());
                                buttons[i][j].setEnabled(false);

                                checkForWin();

//                            todo
//                                add option to count time only when user playing and only when computer playing
//                                play computer move
                                checkForGameOver();
                            }

//                            while (! table.getCell(i, j).setCellStatus(Cell.CellStatus.USER)) {
//
//                            }

//                            if (table.getCell(i, j).getCellStatus()  == Cell.CellStatus.ZERO ) {
//                                openBlanks(i, j);
//
//                            } else {
//
//                                openCell(i, j);
//
////                                game over check
//                                if (checkForGameOver(i, j)) return;
//                            }

                            checkForWin();

                            return;
                        }
                    }
                }
            }
        }

        private boolean checkForGameOver(int i, int j) {
//            todo
//            if (table.getCell(i, j).getCellStatus()  == Cell.CellStatus.MINE && !buttons[i][j].isEnabled()) {
            if (false) {
                System.out.println("game over");

//              TODO extract to new thread (swing worker)

                support.firePropertyChange("game over", null, Command.GAME_OVER);

                SoundsManager.playGameOverSound();

                setButtons(false);
                return true;
            }
            return false;
        }
    }
}
