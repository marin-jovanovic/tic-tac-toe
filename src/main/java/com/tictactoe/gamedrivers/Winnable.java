package com.tictactoe.gamedrivers;

import com.tictactoe.gamedrivers.point.Point;
import com.tictactoe.gamedrivers.point.PointDotPosition;
import com.tictactoe.gamedrivers.tile.Tile;
import com.tictactoe.gamedrivers.tile.TileOwner;


/**
 * checks for game won
 */
public interface Winnable {
	// template method design pattern

	int getYAxisLength();

	int getXAxisLength();

	Tile getTile(int x, int y);


	default boolean checkVerticalIfWinnable(int numOfEmptyTiles, TileOwner turn, boolean verticalCheck) {
//		how much tiles are needed for victory this way

//		boolean verticalCheck = true;

//		int iUpperBound;
//		int jUpperBound;

		int iUpperBound = verticalCheck ? getXAxisLength() : getYAxisLength();
		int jUpperBound = verticalCheck ? getYAxisLength() : getXAxisLength();

		int howMuch;
		boolean isWinnableByThisPath;

		for (int i = 0; i < iUpperBound; i++) {
			isWinnableByThisPath = true;

			howMuch = 0;

			for (int j = 0; j < jUpperBound; j++) {
				if (getTile(i, j).isTileEmpty()) {
					howMuch += 1;
				} else if (getTile(i, j).getOwner() == turn.getOppositeTileOwner()) {
					isWinnableByThisPath = false;
					break;
				}
			}

			if (isWinnableByThisPath) {
				if (numOfEmptyTiles >= howMuch + howMuch - 1) {
					return true;
				}
			}

		}

		return false;
	}

	default boolean canIWin(TileOwner turn) {

		int numOfEmptyTiles = getNumOfEmptyTiles();

		if (numOfEmptyTiles == 0) {
			return false;
		}


		boolean isWinnable = checkVerticalIfWinnable(numOfEmptyTiles, turn, true);

		System.out.println("winnable by vertical: " + isWinnable);

		isWinnable = checkVerticalIfWinnable(numOfEmptyTiles, turn, false);

		System.out.println("winnable by horizontal: " + isWinnable);


///////////////////////////////////////////////////

			boolean areAllUser1 = true;

		int howMuch;
		boolean isWinnableByThisPath;

//			main diagonal
//		todo add offset

		int offset = Math.abs(getXAxisLength() - getYAxisLength());

		for (int offsetCount = 0; offsetCount <= offset; offsetCount++) {
			System.out.println("offset count " + offsetCount);

			howMuch = 0;
			isWinnableByThisPath = true;

			for (int x = 0 + offsetCount, y = 0; x < getXAxisLength() && y < getYAxisLength(); x++, y++) {

				System.out.println(x + ", " + y);

				if (getTile(x, y).isTileEmpty()) {
					howMuch += 1;
				} else if (getTile(x, y).getOwner() == turn.getOppositeTileOwner()) {
					isWinnableByThisPath = false;
					break;
				}


			}

			if (isWinnableByThisPath) {
				if (numOfEmptyTiles >= howMuch + howMuch - 1) {
//					return true;
					System.out.println("main diagonal winnable");
				}
			}
		}





//			////////////////////////////////
//			other diagonal
//		    ////////////////////////////////


			offset = Math.abs(getXAxisLength() - getYAxisLength());

		for (int offsetCount = 0; offsetCount <= offset; offsetCount++) {
			System.out.println("offset count " + offsetCount);

			howMuch = 0;
			isWinnableByThisPath = true;

			for (int x = 0 + offsetCount, y = getYAxisLength() - 1; x >= 0 && y >= 0; x++, y--) {

				System.out.println(x + ", " + y);

				if (getTile(x, y).isTileEmpty()) {
					howMuch += 1;
				} else if (getTile(x, y).getOwner() == turn.getOppositeTileOwner()) {
					isWinnableByThisPath = false;
					break;
				}


			}

			if (isWinnableByThisPath) {
				if (numOfEmptyTiles >= howMuch + howMuch - 1) {
//					return true;
					System.out.println("non main diagonal winnable");
				}
			}
		}



//			boolean areAllUser1 = true;
//
//			for (int x = p.getX() + 1, y = p.getY() - 1; x < getXAxisLength() && y >= 0; x++, y--) {
//				if (getTile(x, y).getOwner() != tileOwner) {
//					areAllUser1 = false;
//					break;
//				}
//			}
//
//			if (areAllUser1) {
//				for (int x = p.getX() - 1, y = p.getY() + 1; x >= 0 && y < getYAxisLength(); x--, y++) {
//
//					if (getTile(x, y).getOwner() != tileOwner) {
//						return false;
//					}
//				}
//
//				return true;
//			}



		return false;


	}

	default int getNumOfEmptyTiles() {
		int numOfEmptyTiles = 0;

		for (int x = 0; x < getXAxisLength(); x++) {
			for (int y = 0; y < getYAxisLength(); y++) {
				if (getTile(x, y).isTileEmpty()) {
					numOfEmptyTiles++;
				}
			}
		}

		return numOfEmptyTiles;
	}


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

//			todo check if this is always true

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

//			iterate over main diagonal from current point forward
			for (int x = p.getX() + 1, y = p.getY() + 1; x < getXAxisLength() && y < getYAxisLength(); x++, y++) {
				if (getTile(x, y).getOwner() != tileOwner) {
					areAllUser1 = false;
					break;
				}
			}

//			iterate over main diagonal from current point backward
			if (areAllUser1) {
				for (int x = p.getX() - 1, y = p.getY() - 1; x >= 0 && y >= 0; x--, y--) {
					if (getTile(x, y).getOwner() != tileOwner) {
						areAllUser1 = false;
						break;
					}
				}

				if (areAllUser1) {
					return true;
				}
//				return areAllUser1;
			}

		}

		if (isGameWinnableBySecondaryDiagonal(p)) {

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
