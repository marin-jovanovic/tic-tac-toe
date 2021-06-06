import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class TestingGui extends JFrame {
    private final JButton[][] buttons;

    public TestingGui() {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        AtomicReference<Game> g = new AtomicReference<>(new Game());
        setLayout(new GridLayout(0, g.get().getxAxisLength()));
        setSize(500, 500);
        buttons = new JButton[g.get().getyAxisLength()][g.get().getxAxisLength()];



        for (int y = 0; y < g.get().getyAxisLength(); y++) {
            for (int x = 0; x < g.get().getxAxisLength(); x++) {

                buttons[y][x] = new JButton(x + " " + y);

                this.add(buttons[y][x]);

                int finalX = x;
                int finalY = y;
                buttons[y][x].addActionListener(e -> {

                    g.get().updateGame(finalX, finalY, Owner.USER_1);

                    buttons[finalY][finalX].setEnabled(false);

                    g.get().printBoard();

                    if (g.get().isGameWon(finalX, finalY)) {
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
            for (int i = 0; i < g.get().getyAxisLength(); i++) {
                for (int j = 0; j < g.get().getxAxisLength(); j++) {

                    buttons[i][j].setEnabled(true);

                }
            }
        });


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestingGui::new);
    }

}



