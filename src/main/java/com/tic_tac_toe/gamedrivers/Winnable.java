package com.tic_tac_toe.gamedrivers;

public interface Winnable {
	// template method design pattern

	int getYAxisLength();

	int getXAxisLength();

	Tile getTile(int x, int y);

	/**
	 * main driver for checking if game is won
	 *
	 * @return true if player1 won
	 */
	default boolean isGameWon(Point p, TileOwner tileOwner) {

//        for (boolean state : List.of(true, false)) {

		if (checkHorizontalAndVertical(p, tileOwner)) {
			return true;
		}

		return checkDiagonals(p, tileOwner);

	}

	//     * Minor, Counter, Secondary, Anti, secondary
	default boolean isGameWinnableBySecondaryDiagonal(Point p) {

		if (getXAxisLength() > getYAxisLength()) {

//            position of cell based on position of lower line
//            we want it to be on it or on top of it
			PointDotPosition lowerLine = isGoodCandidate(
					new Point(0, getYAxisLength() - 1),
					new Point(getYAxisLength() - 1, 0),
					p
			);

//            position of cell based on position of upper line
//            we want it to be on it or below of it
			PointDotPosition upperLine = isGoodCandidate(
					new Point(getXAxisLength() - getYAxisLength(), getYAxisLength() - 1),
					new Point(getXAxisLength() - 1, 0),
					p
			);

			return lowerLine != PointDotPosition.UP && upperLine != PointDotPosition.DOWN;

		} else if (getXAxisLength() < getYAxisLength()) {

//        bellow or on the upper line
			PointDotPosition upperLineOk = isGoodCandidate(
					new Point(0, getXAxisLength() - 1),
					new Point(getXAxisLength() - 1, 0),
					p
			);

//        higher then or on the lower line
			PointDotPosition lowerLineOk = isGoodCandidate(
					new Point(0, getYAxisLength() - 1),
					new Point(getXAxisLength() - 1, getYAxisLength() - getXAxisLength()),
					p
			);

			return upperLineOk != PointDotPosition.UP && lowerLineOk != PointDotPosition.DOWN;

		} else {
//            we want cell to be on the main diagonal
//            there is no range in this case

//            ad hoc solution
			return p.getX() + p.getY() == getXAxisLength() - 1;

		}
	}

	default boolean isGameWinnableByMainDiagonal(Point p) {

		if (getXAxisLength() > getYAxisLength()) {

//          position of cell based on position of lower line
//          we want it to be on it or on top of it
			PointDotPosition lowerLine = isGoodCandidate(
					new Point(0, 0),
					new Point(getYAxisLength() - 1, getYAxisLength() - 1),
					p
			);

//          position of cell based on position of upper line
//          we want it to be on it or below of it
			PointDotPosition upperLine = isGoodCandidate(
					new Point(getXAxisLength() - getYAxisLength(), 0),
					new Point(getXAxisLength() - 1, getYAxisLength() - 1),
					p
			);

			return lowerLine != PointDotPosition.DOWN && upperLine != PointDotPosition.UP;

		} else if (getXAxisLength() < getYAxisLength()) {

//          position of cell based on position of upper line
			PointDotPosition upperLineOk = isGoodCandidate(
					new Point(0, 0),
					new Point(getXAxisLength() - 1, getXAxisLength() - 1),
					p
			);

//          higher then or on the lower line
			PointDotPosition lowerLineOk = isGoodCandidate(
					new Point(0, getYAxisLength() - getXAxisLength()),
					new Point(getXAxisLength() - 1, getYAxisLength() - 1),
					p
			);

			return upperLineOk != PointDotPosition.UP && lowerLineOk != PointDotPosition.DOWN;

		} else {
//            we want cell to be on the main diagonal
//            there is no range in this case

//            ad hoc solution

			return p.getX() == p.getY();

		}
	}

	default boolean checkHorizontalAndVertical(Point p, TileOwner tileOwner) {
		boolean allSame = true;

//        check y axis
		for (int y = 0; y < getYAxisLength(); y++) {

			if (getTile(p.getX(), y).getOwner() != tileOwner) {

				allSame = false;
				break;
			}

		}

		if (allSame) {
			return true;
		}

//        check x axis

		for (int x = 0; x < getXAxisLength(); x++) {

			if (getTile(x, p.getY()).getOwner() != tileOwner) {
				return false;
			}

		}

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
	default PointDotPosition isGoodCandidate(Point p1, Point p2, Point point) {

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
			return PointDotPosition.UP;
		} else if (xp < 0) {
			return PointDotPosition.DOWN;
		} else {
			return PointDotPosition.ON;
		}

	}

	default boolean checkDiagonals(Point p, TileOwner tileOwner) {
		//  todo check len
		//  todo if same x and y no need to check this

		if (isGameWinnableByMainDiagonal(p)) {

			boolean areAllUser1 = true;

			for (int x = p.getX() + 1, y = p.getY() + 1; x < getXAxisLength() && y < getYAxisLength(); x++, y++) {
				if (getTile(x, y).getOwner() != tileOwner) {
					areAllUser1 = false;
					break;
				}
			}


			if (areAllUser1) {
				for (int x = p.getX() - 1, y = p.getY() - 1; x >= 0 && y >= 0; x--, y--) {
					if (getTile(x, y).getOwner() != tileOwner) {
						areAllUser1 = false;
						break;
					}
				}

				return areAllUser1;
			}

		} else if (isGameWinnableBySecondaryDiagonal(p)) {

			boolean areAllUser1 = true;

			for (int x = p.getX() + 1, y = p.getY() - 1; x < getXAxisLength() && y >= 0; x++, y--) {
				if (getTile(x, y).getOwner() != tileOwner) {
					areAllUser1 = false;
					break;
				}
			}

			if (areAllUser1) {
				for (int x = p.getX() - 1, y = p.getY() + 1; x >= 0 && y < getYAxisLength(); x--, y++) {

					if (getTile(x, y).getOwner() != tileOwner) {
						return false;
					}
				}

				return true;
			}

		}

		return false;

	}
}
