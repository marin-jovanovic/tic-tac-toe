import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TestingGui extends JFrame{
    private JButton[][] buttons;

    public TestingGui() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Game g = new Game();
        setLayout(new GridLayout(0, g.getLength()));
        setSize(500, 500);
        buttons = new JButton[g.getWidth()][g.getLength()];
//       this.tiles = new Tile[width][length];
//        for (int y = 0; y < width; y++) {
//
//            for (int x = 0; x < length; x++) {
//                tiles[y][x] = new Tile();
//
//            }
//        }

        for (int i = 0; i < g.getWidth(); i++) {
            for (int j = 0; j < g.getLength(); j++) {

                buttons[i][j] = new JButton(i + " " + j);


                this.add(buttons[i][j]);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> {
                    g.updateGame(finalJ, finalI, Owner.USER);
                    g.printBoard();
                    if (g.isGameWon()) {
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

                    }
                    System.out.println();
                });
            }
        }

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestingGui::new);
    }

}



