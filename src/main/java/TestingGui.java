import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class TestingGui extends JFrame {
    private final JButton[][] buttons;

    public TestingGui() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AtomicReference<Game> g = new AtomicReference<>(new Game());
        setLayout(new GridLayout(0, g.get().getLength()));
        setSize(500, 500);
        buttons = new JButton[g.get().getWidth()][g.get().getLength()];


//       this.tiles = new Tile[width][length];
//        for (int y = 0; y < width; y++) {
//
//            for (int x = 0; x < length; x++) {
//                tiles[y][x] = new Tile();
//
//            }
//        }

        for (int i = 0; i < g.get().getWidth(); i++) {
            for (int j = 0; j < g.get().getLength(); j++) {

                buttons[i][j] = new JButton(i + " " + j);


                this.add(buttons[i][j]);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e -> {
                    g.get().updateGame(finalJ, finalI, Owner.USER_1);
                    buttons[finalI][finalJ].setEnabled(false);
                    g.get().printBoard();
                    if (g.get().isGameWon()) {
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

                    }
                    System.out.println();
                });
            }
        }

        JButton restartBtn = new JButton("restart");
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            g.set(new Game());
            for (int i = 0; i < g.get().getWidth(); i++) {
                for (int j = 0; j < g.get().getLength(); j++) {

                    buttons[i][j].setEnabled(true);

                }
            }
        });


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestingGui::new);
    }

}



