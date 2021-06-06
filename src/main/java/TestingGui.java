import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class TestingGui extends JFrame {
	private final JButton[][] buttons;

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

					g.get().updateGame(new Point(finalX, finalY), Owner.USER_1);

					buttons[finalY][finalX].setEnabled(false);

					g.get().printBoard();

					if (g.get().isGameWon(new Point(finalX, finalY))) {
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
			for (int i = 0; i < g.get().getYAxisLength(); i++) {
				for (int j = 0; j < g.get().getXAxisLength(); j++) {

					buttons[i][j].setEnabled(true);

				}
			}
		});


	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(TestingGui::new);
	}

}



