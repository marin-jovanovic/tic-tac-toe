import java.util.Scanner;
import java.util.TreeMap;

public class Game {

    private final int length = 4;

    public int getLength() {
        return length;
    }

    private final int width = 3;

    public int getWidth() {
        return width;
    }

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

            g.updateGame(x, y, Owner.USER);

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

    private boolean gameWonDriverMain(int xMax, int yMax, boolean isReversed) {
//        todo change xmax and ymax impl
        int count;
        if (isReversed) {
            System.out.println("reversed");
        }
        for (int x = 0; x < xMax; x++) {
            count = 0;

            for (int y = 0; y < yMax; y++) {
                if (tiles[isReversed ? x : y][isReversed ? y : x].getOwner() == Owner.USER) {
                    count += 1;
                }
            }
            //todo test, check names, add comments
            //            System.out.println("count " + count);
            if (count == yMax) {
                System.out.println("player1 wins");
                return true;
            }
        }

        return false;
    }

//    private boolean gameWonDriverMainDiagonal(int xMax, int yMax, boolean isReversed) {
//        int perX = length - width +1;
//
//        for (int offset = 0; offset < perX; offset ++) {
//            boolean areAllUser1 = true;
//            for (int x = 0; x < width ; x++) {
//                if (tiles[x][x+offset].getOwner() != Owner.USER) {
//                    areAllUser1 = false;
//                    break;
//                }
//            }
//            if (areAllUser1) {
//                System.out.println("player1  first won");
//                return true;
//            }
//        }
//
//        return false;
//    }


    private boolean gameWonDriverDiagonals(int length, int width, boolean isReversed) {

        int t = !isReversed ? length - width : -length + width ;
        int perX = t + 1;

        for (int offset = 0; offset < perX; offset ++) {

            boolean areAllUser1 = true;
            for (int x = 0; x < (isReversed? length : width ); x++) {
                int a = !isReversed ? x : length - x - 1 - offset;
                int b = !isReversed ? length - x - 1 - offset : x;

                if (tiles[a][b].getOwner() != Owner.USER) {
                    areAllUser1 = false;
                    break;
                }
            }
            if ( areAllUser1) {
                System.out.println("player1  first won");
                return true;
            }
        }

        return false;
    }

    private boolean gameWonDriverMainDiagonals(int length, int width, boolean isReversed) {

        int t = !isReversed ? length - width : -(length - width) ;
        int perX = t + 1;

        for (int offset = 0; offset < perX; offset ++) {
            boolean areAllUser1 = true;
            for (int x = 0; x < width ; x++) {
                int a = !isReversed ? x : x + offset;
                int b = !isReversed ? x + offset:    x;

                if (tiles[a][b].getOwner() != Owner.USER) {
                    areAllUser1 = false;
                    break;
                }
            }
            if ( areAllUser1) {
                System.out.println("player1  first won");
                return true;
            }
        }


        return false;
    }

    /**
     * @return true if player1 won
     */
    public boolean isGameWon() {

        /*
            xxx
            ...
            ...
         */
        if (gameWonDriverMain(length, width, false)) {
            return true;
        }

        /*
            x..
            x..
            x..
         */
        if (gameWonDriverMain(width, length, true)) {
            return true;
        }

        /*
            x..
            .x.
            ..x
         */

        if (gameWonDriverMainDiagonals(length, width, false)) {
            return true;
        }

        /*
            x..
            x..
            x..
         */
        if (gameWonDriverMainDiagonals(length, width, true)) {
            return true;
        }


//
//        if (length > width) {
//
//            int perX = length - width +1;
//
//            for (int offset = 0; offset < perX; offset ++) {
//                boolean areAllUser1 = true;
//                for (int x = 0; x < width ; x++) {
//                    if (tiles[x][x+offset].getOwner() != Owner.USER) {
//                        areAllUser1 = false;
//                        break;
//                    }
//                }
//                if ( areAllUser1) {
//                    System.out.println("player1  first won");
//                    return true;
//                }
//            }
//
//
//        } else if (width >= length) {
//            int perX = -(length - width) +1;
//
//            for (int offset = 0; offset < perX; offset ++) {
//                boolean areAllUser1 = true;
//                for (int x = 0; x < length ; x++) {
//                    if (tiles[x+offset][x].getOwner() != Owner.USER) {
//                        areAllUser1 = false;
//                        break;
//                    }
//
//                }
//                if ( areAllUser1) {
//                    System.out.println("player1 second won");
//                    return true;
//                }
//            }
//
//        }

//        else {
////            both values are same
//            System.out.println("same");
//            System.out.println("----------------------------------------------------------");
//                boolean areAllUser1 = true;
//                for (int x = 0; x < length ; x++) {
//                    System.out.println("x " + x);
//                    if (tiles[x][x].getOwner() == Owner.USER) {
//                    } else {
//                        areAllUser1 = false;
//                        break;
//                    }
//                }
//                if ( areAllUser1) {
//                    System.out.println("player1 same won");
//                    return true;
//                }
//        }



//        non main diagonals
//todo comment which is which
        if (gameWonDriverDiagonals(length, width, false)) {
            return true;
        }

        if (gameWonDriverDiagonals(length, width, true) ) {
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
                    if (tiles[y][x].getOwner() == Owner.USER) {
                        buffer += "x ";
                    } else if (tiles[y][x].getOwner() == Owner.COMPUTER) {
                        buffer += "o ";
                    } else if (tiles[y][x].getOwner() == Owner.NONE) {
                        buffer += ". ";
                    }

                } else {
                    if (tiles[y][x].getOwner() == Owner.USER) {
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
