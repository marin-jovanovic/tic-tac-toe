import java.util.List;
import java.util.Scanner;

public class Game {

    private final int length = 5;
    private final int width = 3;
    private final boolean isUserX = true;
    private final Tile[][] tiles;

    /**
     * len = 4, wid = 2
     * ....
     * ....
     */
    public Game() {

        this.tiles = new Tile[width][length];
        for (int y = 0; y < width; y++) {

            for (int x = 0; x < length; x++) {
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
            if (g.isGameWon()) {
                break;
            }

            System.out.println();
        }

        System.out.println("end of game");
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    /**
     * minmax alg driver for computer move
     *
     * @return true if move is made else false
     */
    public boolean computerMove() {
        boolean isOneOrMoreEmpty = false;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                if (tiles[x][y].isTileEmpty()) {
                    System.out.println("tile empty at " + x + " " + y);
                    isOneOrMoreEmpty = true;


                }
            }
        }

        return true;
    }

    private boolean gameWonDriverMain(int length, int width, boolean isReversed) {

        int count;

        for (int x = 0; x < (isReversed ? length : width); x++) {
            count = 0;

            for (int y = 0; y < (isReversed ? width : length); y++) {
                if (tiles[!isReversed ? x : y][!isReversed ? y : x].getOwner() == Owner.USER_1) {
                    count += 1;
                }
            }

            if (count == (!isReversed ? length : width)) {
                System.out.println("player1 wins");
                return true;
            }
        }

        return false;
    }


    /**
     * Minor, Counter, Secondary, Anti, secondary
     *
     * @param length x
     * @param width y
     * @param isReversed length <= width
     * @return true if all code {Owner.USER_1} on non-main diagonal(s)
     */
    private boolean gameWonNonMainDriverDiagonals(int length, int width, boolean isReversed) {

        int t = !isReversed ? length - width : -length + width;
        int perX = t + 1;

        for (int offset = 0; offset < perX; offset++) {

            boolean areAllUser1 = true;
            for (int x = 0; x < (isReversed ? length : width); x++) {
                int a = !isReversed ? x : length - x - 1 - offset;
                int b = !isReversed ? length - x - 1 - offset : x;

                if (tiles[a][b].getOwner() != Owner.USER_1) {
                    areAllUser1 = false;
                    break;
                }
            }
            if (areAllUser1) {
                System.out.println("player1  first won");
                return true;
            }
        }

        return false;
    }


    /**
     *
     * @param length x
     * @param width y
     * @param isReversed length <= width
     * @return true if all {@code Owner.USER_1} on main diagonal(s)
     */
    private boolean gameWonDriverMainDiagonals(int length, int width, boolean isReversed) {
        int t = !isReversed ? length - width : -(length - width);
        int perX = t + 1;

        for (int offset = 0; offset < perX; offset++) {
            boolean areAllUser1 = true;
            for (int x = 0; x < (isReversed ? length: width); x++) {
                int a = !isReversed ? x : x + offset;
                int b = !isReversed ? x + offset : x;

                if (tiles[a][b].getOwner() != Owner.USER_1) {
                    areAllUser1 = false;
                    break;
                }
            }
            if (areAllUser1) {
                System.out.println("player1 first won");
                return true;
            }
        }

        return false;
    }

    /**
     * @return true if player1 won
     */
    public boolean isGameWon() {

        for (boolean state : List.of(true, false)) {
            /*
                xxx
                ...
                ...

                and

                x..
                x..
                x..
             */

            if (gameWonDriverMain(length, width, state)) {
                return true;
            }


        }

            /*
                main diagonal
                x..
                .x.
                ..x
            */

        if (gameWonDriverMainDiagonals(length, width, length <= width )) {
            return true;
        }

            /*
                non main diagonal
                ..x
                .x.
                x..
            */
        if (gameWonNonMainDriverDiagonals(length, width, length <= width)) {
            return true;
        }


        return false;
    }

    public void maxValue() {

    }

    public void minValue() {

    }

    public boolean updateGame(int x, int y, Owner owner) {
        if (x >= length) {
            System.out.println("x to big");
            return false;
        } else if (y >= width) {
            System.out.println("y to big");
            return false;
        }

        System.out.println("updating  " + x + " " + y);

        tiles[y][x].setOwner(owner);

        return true;
    }

    public void printBoard() {
        for (int y = 0; y < width; y++) {
            String buffer = "";

            for (int x = 0; x < length; x++) {
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
