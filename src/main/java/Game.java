import java.util.Scanner;

public class Game {

	//    private final int xAxisLength = 12;
	private final int xAxisLength = 4;
	//    private final int yAxisLength = 8;
	private final int yAxisLength = 6;

	private final boolean isUserX = true;
	private final Tile[][] tiles;

	/**
	 * len = 4, wid = 2
	 * ....
	 * ....
	 */
	public Game() {

		this.tiles = new Tile[yAxisLength][xAxisLength];
		for (int y = 0; y < yAxisLength; y++) {

			for (int x = 0; x < xAxisLength; x++) {
				tiles[y][x] = new Tile();

			}
		}
	}

	public static void main(String[] args) {
		Game g = new Game();

		g.printBoard();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("Enter x: ");
			int x = Integer.parseInt(sc.nextLine());
			System.out.print("Enter y: ");
			int y = Integer.parseInt(sc.nextLine());

			g.updateGame(new Point(x, y), Owner.USER_1);

			g.printBoard();

			//            computer move
//            g.computerMove();

//          todo check if other won
			if (g.isGameWon(new Point(x, y))) {
				break;
			}

			System.out.println();
		}

		System.out.println("end of game");
	}

	public int getxAxisLength() {
		return xAxisLength;
	}

	public int getyAxisLength() {
		return yAxisLength;
	}

	/**
	 * minmax alg driver for computer move
	 *
	 * @return true if move is made else false
	 */
	public boolean computerMove() {
		boolean isOneOrMoreEmpty = false;

		for (int x = 0; x < yAxisLength; x++) {
			for (int y = 0; y < xAxisLength; y++) {
				if (tiles[x][y].isTileEmpty()) {
					System.out.println("tile empty at " + x + " " + y);
					isOneOrMoreEmpty = true;

				}
			}
		}

		return true;
	}

	private boolean checkHorizontalAndVertical(Point p) {
		boolean allSame = true;

//        check y axis
		for (int y = 0; y < yAxisLength; y++) {
//            System.out.println(newX + " " + y);

			if (tiles[y][p.getX()].getOwner() != Owner.USER_1) {

				allSame = false;
				break;
			}

		}

//        System.out.println();
		if (allSame) {
			System.out.println("player1 won");
			return true;
		}

//        check x axis
		allSame = true;

		for (int x = 0; x < xAxisLength; x++) {
//            System.out.println(x + " " + newY);

			if (tiles[p.getY()][x].getOwner() == Owner.USER_1) {

			} else {
				return false;
			}

		}
		System.out.println("player1 won");

		return true;

	}

	/**
	 * check where {@code point} is in relation to line defined by {@code p1} and {@code p1}
	 *
	 * @param p1
	 * @param p2
	 * @param point
	 * @return
	 */
	private PositionOfDot isGoodCandidate(Point p1, Point p2, Point point) {
		System.out.println(p1 + ", " + p2 + " ? " + point);

		Point v1 = new Point(
				p2.getX() - p1.getX(),
				p2.getY() - p1.getY()
		);

		Point v2 = new Point(
				p2.getX() - point.getX(),
				p2.getY() - point.getY()
		);

		int xp = v1.getX() * v2.getY() - v1.getY() * v2.getX();

		if (xp > 0) {
			System.out.println("up");
			return PositionOfDot.UP;
		} else if (xp < 0) {
			System.out.println("down");
			return PositionOfDot.DOWN;
		} else {
			System.out.println("on the line");
			return PositionOfDot.ON;
		}

	}

	private boolean checkDiagonals(Point p) {
		//todo check len
		//        todo if same x and y no need to check this

		System.out.println("x " + xAxisLength);
		System.out.println("y " + yAxisLength);
		System.out.println("new x " + p.getX());
		System.out.println("new y " + p.getY());

		if (isGameWinnableByMainDiagonal(p)) {

			boolean areAllUser1 = true;

			for (int x = p.getX() + 1, y = p.getY() + 1; x < xAxisLength && y < yAxisLength; x++, y++) {
				System.out.println(x + " " + y);
				if (tiles[y][x].getOwner() != Owner.USER_1) {
					areAllUser1 = false;
					break;
				}
			}

			System.out.println();

			if (areAllUser1) {
				for (int x = p.getX() - 1, y = p.getY() - 1; x >= 0 && y >= 0; x--, y--) {
					System.out.println(x + " " + y);
					if (tiles[y][x].getOwner() != Owner.USER_1) {
						areAllUser1 = false;
						break;
					}
				}

				if (areAllUser1) {
					System.out.println("player1 won");
					return true;
				}
			}

			System.out.println();

		} else if (isGameWinnableBySecondaryDiagonal(p)) {

			boolean areAllUser1 = true;

			for (int x = p.getX() + 1, y = p.getY() - 1; x < xAxisLength && y >= 0; x++, y--) {
				System.out.println(x + " " + y);
				if (tiles[y][x].getOwner() != Owner.USER_1) {
					areAllUser1 = false;
					break;
				}
			}

			System.out.println();

			if (areAllUser1) {
				for (int x = p.getX() - 1, y = p.getY() + 1; x >= 0 && y < yAxisLength; x--, y++) {
					System.out.println(x + " " + y);

					if (tiles[y][x].getOwner() != Owner.USER_1) {
						return false;
					}
				}
				System.out.println("player1 won");

				return true;
			}

		}

		return false;

	}

	//     * Minor, Counter, Secondary, Anti, secondary
	private boolean isGameWinnableBySecondaryDiagonal(Point p) {

		if (xAxisLength > yAxisLength) {
			System.out.println("first");

//            position of cell based on position of lower line
//            we want it to be on it or on top of it
			PositionOfDot lowerLine = isGoodCandidate(
					new Point(0, yAxisLength - 1),
					new Point(yAxisLength - 1, 0),
					p
			);

//            position of cell based on position of upper line
//            we want it to be on it or below of it
			PositionOfDot upperLine = isGoodCandidate(
					new Point(xAxisLength - yAxisLength, yAxisLength - 1),
					new Point(xAxisLength - 1, 0),
					p
			);


			if (lowerLine != PositionOfDot.UP && upperLine != PositionOfDot.DOWN) {
				System.out.println("good candidate");
				return true;
			} else {
				System.out.println("not good candidate");
//            todo skip main diagonal
				return false;
			}

		} else if (xAxisLength < yAxisLength) {


//        bellow or on the upper line
			PositionOfDot upperLineOk = isGoodCandidate(
					new Point(0, xAxisLength - 1),
					new Point(xAxisLength - 1, 0),
					p
			);


//        higher then or on the lower line
			PositionOfDot lowerLineOk = isGoodCandidate(
					new Point(0, yAxisLength - 1),
					new Point(xAxisLength - 1, yAxisLength - xAxisLength),
					p
			);

			if (upperLineOk != PositionOfDot.UP && lowerLineOk != PositionOfDot.DOWN) {
				System.out.println("good candidate");
				return true;

			} else {
				System.out.println("not good candidate");
//            todo skip main diagonal
				return false;
			}

		} else {
//            we want cell to be on the main diagonal
//            there is no range in this case

//            ad hoc solution

			if (p.getX() + p.getY() == xAxisLength - 1) {
				System.out.println("on line");
				return true;
			} else {
				System.out.println("not good candidate");
//                todo skip
				return false;
			}

		}
	}

	private boolean isGameWinnableByMainDiagonal(Point p) {

		if (xAxisLength > yAxisLength) {

//          position of cell based on position of lower line
//          we want it to be on it or on top of it
			PositionOfDot lowerLine = isGoodCandidate(
					new Point(0, 0),
					new Point(yAxisLength - 1, yAxisLength - 1),
					p
			);

//          position of cell based on position of upper line
//          we want it to be on it or below of it
			PositionOfDot upperLine = isGoodCandidate(
					new Point(xAxisLength - yAxisLength, 0),
					new Point(xAxisLength - 1, yAxisLength - 1),
					p
			);

			if (lowerLine != PositionOfDot.DOWN && upperLine != PositionOfDot.UP) {
				System.out.println("good candidate");
				return true;

			} else {
				System.out.println("not good candidate");
				return false;
			}

		} else if (xAxisLength < yAxisLength) {

//          position of cell based on position of upper line
			PositionOfDot upperLineOk = isGoodCandidate(
					new Point(0, 0),
					new Point(xAxisLength - 1, xAxisLength - 1),
					p
			);

//          higher then or on the lower line
			PositionOfDot lowerLineOk = isGoodCandidate(
					new Point(0, yAxisLength - xAxisLength),
					new Point(xAxisLength - 1, yAxisLength - 1),
					p
			);

			if (upperLineOk != PositionOfDot.UP && lowerLineOk != PositionOfDot.DOWN) {
				System.out.println("good candidate");
				return true;
			} else {
				System.out.println("not good candidate");
				return false;
			}

		} else {
//            we want cell to be on the main diagonal
//            there is no range in this case

//            ad hoc solution

			if (p.getX() == p.getY()) {
				System.out.println("good candidate");
				return true;
			} else {
				System.out.println("not good candidate");
				return false;
			}

		}
	}

	/**
	 * main driver for checking if game is won
	 *
	 * @return true if player1 won
	 */
	public boolean isGameWon(Point p) {

//        for (boolean state : List.of(true, false)) {

		if (checkHorizontalAndVertical(p)) {
			return true;
		}

		return checkDiagonals(p);

	}

	public boolean updateGame(Point p, Owner owner) {
		if (p.getX() >= xAxisLength) {
			System.out.println("x to big");
			return false;
		} else if (p.getY() >= yAxisLength) {
			System.out.println("y to big");
			return false;
		}

		System.out.println("updating  " + p.getX() + " " + p.getY());

		tiles[p.getY()][p.getX()].setOwner(owner);

		return true;
	}

	public void printBoard() {
		for (int y = 0; y < yAxisLength; y++) {
			String buffer = "";

			for (int x = 0; x < xAxisLength; x++) {
				if (isUserX) {
					if (tiles[y][x].getOwner() == Owner.USER_1) {
						buffer += "x ";
					} else if (tiles[y][x].getOwner() == Owner.COMPUTER) {
						buffer += "o ";
					} else if (tiles[y][x].getOwner() == Owner.NONE) {
						buffer += ". ";
					}

				} else {
					if (tiles[y][x].getOwner() == Owner.USER_1) {
						buffer += "o ";
					} else if (tiles[y][x].getOwner() == Owner.COMPUTER) {
						buffer += "x ";
					} else if (tiles[y][x].getOwner() == Owner.NONE) {
						buffer += ". ";
					}
				}
			}
			System.out.println(buffer);

		}
	}

	enum PositionOfDot {
		UP,
		DOWN,
		ON
	}

}
