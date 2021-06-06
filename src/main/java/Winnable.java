public interface Winnable {

	int getYAxisLength();
	int getXAxisLength();
	Tile getTile(int x, int y);

	/**
	 * main driver for checking if game is won
	 *
	 * @return true if player1 won
	 */
	default boolean isGameWon(Point p) {

//        for (boolean state : List.of(true, false)) {

		if (checkHorizontalAndVertical(p)) {
			return true;
		}

		return checkDiagonals(p);

	}


	//     * Minor, Counter, Secondary, Anti, secondary
	default boolean isGameWinnableBySecondaryDiagonal(Point p) {

		if (getXAxisLength() > getYAxisLength()) {
			System.out.println("first");

//            position of cell based on position of lower line
//            we want it to be on it or on top of it
			PositionOfDot lowerLine = isGoodCandidate(
					new Point(0, getYAxisLength() - 1),
					new Point(getYAxisLength() - 1, 0),
					p
			);

//            position of cell based on position of upper line
//            we want it to be on it or below of it
			PositionOfDot upperLine = isGoodCandidate(
					new Point(getXAxisLength() - getYAxisLength(), getYAxisLength() - 1),
					new Point(getXAxisLength() - 1, 0),
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

		} else if (getXAxisLength() < getYAxisLength()) {


//        bellow or on the upper line
			PositionOfDot upperLineOk = isGoodCandidate(
					new Point(0, getXAxisLength() - 1),
					new Point(getXAxisLength() - 1, 0),
					p
			);


//        higher then or on the lower line
			PositionOfDot lowerLineOk = isGoodCandidate(
					new Point(0, getYAxisLength() - 1),
					new Point(getXAxisLength() - 1, getYAxisLength() - getXAxisLength()),
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

			if (p.getX() + p.getY() == getXAxisLength() - 1) {
				System.out.println("on line");
				return true;
			} else {
				System.out.println("not good candidate");
//                todo skip
				return false;
			}

		}
	}

	default boolean isGameWinnableByMainDiagonal(Point p) {

		if (getXAxisLength() > getYAxisLength()) {

//          position of cell based on position of lower line
//          we want it to be on it or on top of it
			PositionOfDot lowerLine = isGoodCandidate(
					new Point(0, 0),
					new Point(getYAxisLength() - 1, getYAxisLength() - 1),
					p
			);

//          position of cell based on position of upper line
//          we want it to be on it or below of it
			PositionOfDot upperLine = isGoodCandidate(
					new Point(getXAxisLength() - getYAxisLength(), 0),
					new Point(getXAxisLength() - 1, getYAxisLength() - 1),
					p
			);

			if (lowerLine != PositionOfDot.DOWN && upperLine != PositionOfDot.UP) {
				System.out.println("good candidate");
				return true;

			} else {
				System.out.println("not good candidate");
				return false;
			}

		} else if (getXAxisLength() < getYAxisLength()) {

//          position of cell based on position of upper line
			PositionOfDot upperLineOk = isGoodCandidate(
					new Point(0, 0),
					new Point(getXAxisLength() - 1, getXAxisLength() - 1),
					p
			);

//          higher then or on the lower line
			PositionOfDot lowerLineOk = isGoodCandidate(
					new Point(0, getYAxisLength() - getXAxisLength()),
					new Point(getXAxisLength() - 1, getYAxisLength() - 1),
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

	default boolean checkHorizontalAndVertical(Point p) {
		boolean allSame = true;

//        check y axis
		for (int y = 0; y < getYAxisLength(); y++) {
//            System.out.println(newX + " " + y);

//			if (tiles[y][p.getX()].getOwner() != Owner.USER_1) {
			if (getTile(p.getX(), y).getOwner() != Owner.USER_1) {

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

		for (int x = 0; x < getXAxisLength(); x++) {
//            System.out.println(x + " " + newY);

			if (getTile(x, p.getY()).getOwner() == Owner.USER_1) {

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
	default PositionOfDot isGoodCandidate(Point p1, Point p2, Point point) {
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

		System.out.println("x " + getXAxisLength());
		System.out.println("y " + getYAxisLength());
		System.out.println("new x " + p.getX());
		System.out.println("new y " + p.getY());

		if (isGameWinnableByMainDiagonal(p)) {

			boolean areAllUser1 = true;

			for (int x = p.getX() + 1, y = p.getY() + 1; x < getXAxisLength() && y < getYAxisLength(); x++, y++) {
				System.out.println(x + " " + y);
				if (getTile(x, y).getOwner() != Owner.USER_1) {
					areAllUser1 = false;
					break;
				}
			}

			System.out.println();

			if (areAllUser1) {
				for (int x = p.getX() - 1, y = p.getY() - 1; x >= 0 && y >= 0; x--, y--) {
					System.out.println(x + " " + y);
					if (getTile(x, y).getOwner() != Owner.USER_1) {
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

			for (int x = p.getX() + 1, y = p.getY() - 1; x < getXAxisLength() && y >= 0; x++, y--) {
				System.out.println(x + " " + y);
				if (getTile(x, y).getOwner() != Owner.USER_1) {
					areAllUser1 = false;
					break;
				}
			}

			System.out.println();

			if (areAllUser1) {
				for (int x = p.getX() - 1, y = p.getY() + 1; x >= 0 && y < getYAxisLength(); x--, y++) {
					System.out.println(x + " " + y);

					if (getTile(x, y).getOwner() != Owner.USER_1) {
						return false;
					}
				}
				System.out.println("player1 won");

				return true;
			}

		}

		return false;

	}
}
