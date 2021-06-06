import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Scanner;

public class Game {

//    private final int xAxisLength = 12;
    private final int xAxisLength = 8;
    private final int yAxisLength = 8;
//    private final int yAxisLength = 12;

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

            g.updateGame(x, y, Owner.USER_1);

            g.printBoard();

            //            computer move
//            g.computerMove();

//          todo check if other won
            if (g.isGameWon(x, y)) {
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
//    public boolean computerMove() {
//        boolean isOneOrMoreEmpty = false;
//
//        for (int x = 0; x < yAxisLength; x++) {
//            for (int y = 0; y < xAxisLength; y++) {
//                if (tiles[x][y].isTileEmpty()) {
//                    System.out.println("tile empty at " + x + " " + y);
//                    isOneOrMoreEmpty = true;
//
//
//                }
//            }
//        }
//
//        return true;
//    }

//    private boolean gameWonDriverMain(int length, int width, boolean isReversed) {
//
//        int count;
//
//        for (int x = 0; x < (isReversed ? length : width); x++) {
//            count = 0;
//
//            for (int y = 0; y < (isReversed ? width : length); y++) {
//                if (tiles[!isReversed ? x : y][!isReversed ? y : x].getOwner() == Owner.USER_1) {
//                    count += 1;
//                }
//            }
//
//            if (count == (!isReversed ? length : width)) {
//                System.out.println("player1 wins");
//                return true;
//            }
//        }
//
//        return false;
//    }

    private boolean checkHorizontalAndVertical(int xAxisLength, int yAxisLength, int newX, int newY) {
        boolean allSame = true;

//        check y axis
        for (int y = 0; y < yAxisLength; y++) {
//            System.out.println(newX + " " + y);
            if (tiles[y][newX].getOwner() == Owner.USER_1) {

            } else {
                allSame = false;
                break;
                //                return false;
            }

        }

        System.out.println();
        if (allSame) {
            System.out.println("player1 won");
            return true;
        }

//        check x axis
        allSame = true;

        for (int x = 0; x < xAxisLength; x++) {
//            System.out.println(x + " " + newY);

            if (tiles[newY][x].getOwner() == Owner.USER_1) {

            } else {
                return false;
            }

        }
        System.out.println("player1 won");

        return true;

//
//        for (boolean state : List.of(true, false)) {
//
//                    int count;
//
//                    boolean isReversed = state;
//                    int x = !isReversed ? newX : newY;
//                    count = 0;
//
//                    for (int y = 0; y < (isReversed ? width : length); y++) {
//                        if (tiles[!isReversed ? x : y][!isReversed ? y : x].getOwner() == Owner.USER_1) {
//                            count += 1;
//                        }
//                    }
//
//                    if (count == (!isReversed ? length : width)) {
//                        System.out.println("player1 wins");
//                        return true;
//                    }
//
//                }
//        return false;
    }


    /**
     * Minor, Counter, Secondary, Anti, secondary
     *
     * @param length x
     * @param width y
     * @param isReversed length <= width
     * @return true if all code {Owner.USER_1} on non-main diagonal(s)
     */
//    private boolean gameWonNonMainDriverDiagonals(int length, int width, boolean isReversed) {
//        int t = !isReversed ? length - width : -length + width;
//        int perX = t + 1;
//
//        for (int offset = 0; offset < perX; offset++) {
//            System.out.println("offset " + offset);
//            boolean areAllUser1 = true;
//            for (int x = 0; x < (isReversed ? length : width); x++) {
//                int a = !isReversed ? x : length - x - 1 - offset;
//                int b = !isReversed ? length - x - 1 - offset : x;
//
//                System.out.println(a + " " + b);
//                if (tiles[a][b].getOwner() != Owner.USER_1) {
//                    areAllUser1 = false;
//                    break;
//                }
//            }
//            if (areAllUser1) {
//                System.out.println("player1  first won");
//                return true;
//            }
//        }
////        int t = !isReversed ? length - width : -length + width;
////        int perX = t + 1;
////
////        for (int offset = 0; offset < perX; offset++) {
////
////            boolean areAllUser1 = true;
////            for (int x = 0; x < (isReversed ? length : width); x++) {
////                int a = !isReversed ? x : length - x - 1 - offset;
////                int b = !isReversed ? length - x - 1 - offset : x;
////
////                if (tiles[a][b].getOwner() != Owner.USER_1) {
////                    areAllUser1 = false;
////                    break;
////                }
////            }
////            if (areAllUser1) {
////                System.out.println("player1  first won");
////                return true;
////            }
////        }
//
//        return false;
//    }


    /**
     *
//     * @param length x
//     * @param width y
//     * @param isReversed length <= width
//     * @return true if all {@code Owner.USER_1} on main diagonal(s)
     */
//    private boolean gameWonDriverMainDiagonals(int length, int width, boolean isReversed) {
//        int t = !isReversed ? length - width : -(length - width);
//        int perX = t + 1;
//
//        for (int offset = 0; offset < perX; offset++) {
//            boolean areAllUser1 = true;
//            for (int x = 0; x < (isReversed ? length: width); x++) {
//                int a = !isReversed ? x : x + offset;
//                int b = !isReversed ? x + offset : x;
//
//                if (tiles[a][b].getOwner() != Owner.USER_1) {
//                    areAllUser1 = false;
//                    break;
//                }
//            }
//            if (areAllUser1) {
//                System.out.println("player1 first won");
//                return true;
//            }
//        }
//
//        return false;
//    }

    enum PositionOfDot {
        UP,
        DOWN,
        ON
    }

    private  PositionOfDot isGoodCandidate(int x1,int  y1, int x2, int y2, int pointX, int pointY) {
        System.out.println("(" + x1 + ", " + y1+ "), (" + x2 + ", " + y2 + ")");
        int v1_x_up = x2 - x1;
        int v1_y_up = y2 - y1;

        int v2_x_up = x2 - pointX;
        int v2_y_up = y2 - pointY;

        int xp = v1_x_up * v2_y_up - v1_y_up * v2_x_up;
//        System.out.println("x pr " + xp);

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

    private boolean checkDiagonals(int xAxisLength, int yAxisLength, int newX, int newY) {
//todo check len
//        todo if same x and y no need to check this

        mainDiagonalCheck(xAxisLength, yAxisLength, newX, newY);

        secondaryDiagonalCheck(xAxisLength, yAxisLength, newX, newY);


//        boolean areAllUser1 = true;
//
//        System.out.println("x " + xAxisLength);
//        System.out.println("y " + yAxisLength) ;
//        System.out.println("newx " + newX);
//        System.out.println("newy " + newY);
////
//        for (int x = newX, y = newY;  x < xAxisLength && y < yAxisLength; x++, y++) {
//                System.out.println(x + " " + y);
//            if (tiles[y][x].getOwner() != Owner.USER_1) {
//                areAllUser1 = false;
//                break;
//            }
//
//        }
//
//        System.out.println();
//
//        if (areAllUser1) {
//            for (int x = newX, y = newY;  x >= 0 && y >= 0; x--, y--) {
//                System.out.println(x + " " + y);
//                if (tiles[y][x].getOwner() != Owner.USER_1) {
//                    areAllUser1 = false;
//                    break;
//                }
//            }
//
//            if (areAllUser1) {
//                System.out.println("player1 won");
//                return true;
//            }
//        }
//
//        System.out.println("-------");
//
//        areAllUser1 = true;
//        for (int x = newX, y = newY;  x < xAxisLength && y >= 0; x++, y--) {
//            System.out.println(x + " " + y);
//            if (tiles[y][x].getOwner() != Owner.USER_1) {
//                areAllUser1 = false;
//                break;
//            }
//        }
//        System.out.println();
//
//        if (areAllUser1) {
//            for (int x = newX, y = newY; x >= 0 && y < yAxisLength; x--, y++) {
//                System.out.println(x + " " + y);
//
//                if (tiles[y][x].getOwner() != Owner.USER_1) {
//                    return false;
//                }
//            }
//            System.out.println("player1 won");
//
//            return true;
//        }

        return false;

    }

    private void secondaryDiagonalCheck(int xAxisLength, int yAxisLength, int newX, int newY) {
        if (xAxisLength > yAxisLength) {
            System.out.println("first");

//            point 1
            int x1 = 0;
            int y1 = yAxisLength - 1;

//            point 2
            int x2 = yAxisLength - 1;
            int y2 = 0;

//            position of cell based on position of lower line
//            we want it to be on it or on top of it
            PositionOfDot lowerLine = isGoodCandidate(x1, y1, x2, y2, newX, newY);

//            point 1
            x1 = xAxisLength - yAxisLength;
            y1 = yAxisLength - 1;

//            point 2
            x2 = xAxisLength - 1;
            y2 = 0;

//            position of cell based on position of upper line
//            we want it to be on it or below of it
            PositionOfDot upperLine = isGoodCandidate(x1, y1, x2, y2, newX, newY);

            if (lowerLine != PositionOfDot.UP && upperLine != PositionOfDot.DOWN) {
                System.out.println("good candidate");
            } else {
                System.out.println("not good candidate");
//            todo skip main diagonal
            }

        } else if (xAxisLength < yAxisLength) {

//            point 1
            int x1 = 0;
            int y1 = xAxisLength - 1;

//            point 2
            int x2 = xAxisLength - 1;
            int y2 = 0;

//        bellow or on the upper line
            PositionOfDot upperLineOk = isGoodCandidate(x1, y1, x2, y2, newX, newY);

            x1 = 0;
            y1 = yAxisLength - 1;

            x2 = xAxisLength - 1;
            y2 = yAxisLength - xAxisLength;

//        higher then or on the lower line
            PositionOfDot lowerLineOk = isGoodCandidate(x1, y1, x2, y2, newX, newY);

            if (upperLineOk != PositionOfDot.UP && lowerLineOk != PositionOfDot.DOWN) {
                System.out.println("EXCVELET candidate");
            } else {
                System.out.println("not good candidate");
//            todo skip main diagonal
            }

        } else {
//            we want cell to be on the main diagonal
//            there is no range in this case

            //            ad hoc solution

            if (newX + newY == xAxisLength - 1) {
                System.out.println("on line");
            } else {
                System.out.println("not good candidate");
//                todo skip
            }
        
        }
    }

    private void mainDiagonalCheck(int xAxisLength, int yAxisLength, int newX, int newY) {
        if (xAxisLength > yAxisLength) {

//            point 1
            int x1 = 0;
            int y1 = 0;

//            point 2
            int x2 = yAxisLength - 1;
            int y2 = yAxisLength - 1;

//            position of cell based on position of lower line
//            we want it to be on it or on top of it
            PositionOfDot lowerLine = isGoodCandidate(x1, y1, x2, y2, newX, newY);

//            point 1
            x1 = xAxisLength - yAxisLength;
            y1 = 0;

//            point 2
            x2 = xAxisLength - 1;
            y2 = yAxisLength - 1;

//            position of cell based on position of upper line
//            we want it to be on it or below of it
            PositionOfDot upperLine = isGoodCandidate(x1, y1, x2, y2, newX, newY);

            if (lowerLine != PositionOfDot.DOWN && upperLine != PositionOfDot.UP) {
                System.out.println("good candidate");
            } else {
                System.out.println("not good candidate");
//            todo skip main diagonal
            }

        } else if (xAxisLength < yAxisLength) {

            int x1 = 0;
            int y1 = 0;

            int x2 = xAxisLength - 1;
            int y2 = xAxisLength - 1;

//        bellow or on the upper line
            PositionOfDot upperLineOk = isGoodCandidate(x1, y1, x2, y2, newX, newY);

            x1 = 0;
            y1 = yAxisLength - xAxisLength;

            x2 = xAxisLength - 1;
            y2 = yAxisLength - 1;

//        higher then or on the lower line
            PositionOfDot lowerLineOk = isGoodCandidate(x1, y1, x2, y2, newX, newY);

            if (upperLineOk != PositionOfDot.UP && lowerLineOk != PositionOfDot.DOWN) {
                System.out.println("EXCVELET candidate");
            } else {
                System.out.println("not good candidate");
//            todo skip main diagonal
            }

        } else {
//            we want cell to be on the main diagonal
//            there is no range in this case

//            ad hoc solution

            if (newX == newY) {
                System.out.println("on line");
            } else {
                System.out.println("not good candidate");
//                todo skip
            }

        }
    }

    /**
     * @return true if player1 won
     */
    public boolean isGameWon(int newX, int newY) {
//        todo improve performance
//          calculate only by newly placed tile if it contributed to wictory

//        for (boolean state : List.of(true, false)) {
//            /*
//                xxx
//                ...
//                ...
//
//                and
//
//                x..
//                x..
//                x..
//             */
        if (checkHorizontalAndVertical(xAxisLength, yAxisLength, newX, newY)) {
            return true;
        }

        if (checkDiagonals(xAxisLength, yAxisLength, newX, newY)) {
            return true;
        }



        return false;

//        int count;
//
//        boolean isReversed = false;
//        int x = newX;
//        count = 0;
//
//        for (int y = 0; y < (isReversed ? width : length); y++) {
//            if (tiles[!isReversed ? x : y][!isReversed ? y : x].getOwner() == Owner.USER_1) {
//                count += 1;
//            }
//        }
//
//        if (count == (!isReversed ? length : width)) {
//            System.out.println("player1 wins");
//            return true;
//        }
//
//        isReversed = true;
//        x = newY;
//        count = 0;
//
//        for (int y = 0; y < (isReversed ? width : length); y++) {
//            if (tiles[!isReversed ? x : y][!isReversed ? y : x].getOwner() == Owner.USER_1) {
//                count += 1;
//            }
//        }
//
//        if (count == (!isReversed ? length : width)) {
//            System.out.println("player1 wins");
//            return true;
//        }



//        return false;

//
//
//
//
//            if (gameWonDriverMain(length, width, state)) {
//                return true;
//            }
//
//
//        }
//
//            /*
//                main diagonal
//                x..
//                .x.
//                ..x
//            */
//
//        if (gameWonDriverMainDiagonals(length, width, length <= width )) {
//            return true;
//        }
//
//            /*
//                non main diagonal
//                ..x
//                .x.
//                x..
//            */
//        if (gameWonNonMainDriverDiagonals(length, width, length <= width)) {
//            return true;
//        }
//

//        return false;
    }

//    public void maxValue() {
//
//    }

//    public void minValue() {
//
//    }

    public boolean updateGame(int x, int y, Owner owner) {
        if (x >= xAxisLength) {
            System.out.println("x to big");
            return false;
        } else if (y >= yAxisLength) {
            System.out.println("y to big");
            return false;
        }

        System.out.println("updating  " + x + " " + y);

        tiles[y][x].setOwner(owner);

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

}
