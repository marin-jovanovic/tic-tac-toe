import java.util.Scanner;
import java.util.TreeMap;

public class Game {

    private final int length = 4;

    private final int width = 2;

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

    /**
     * @return true if player1 won
     */
    public boolean isGameWon() {
//
//        this.tiles = new Tile[width][length];
//        for (int y = 0; y < width; y++) {
//
//            for (int x = 0; x < length; x++) {
//                tiles[y][x] = new Tile();
//
//            }
//        }


        /*
            x..
            x..
            x..
         */
        int count;

        for (int x = 0; x < length; x++) {
            count = 0;

            for (int y = 0; y < width; y++) {
                if (tiles[y][x].getOwner() == Owner.USER) {
                    count += 1;
                }
            }
            if (count == width) {
                System.out.println("player1 wins");
                return true;
            }
        }

        /*
            xxx
            ...
            ...
         */

        for (int y = 0; y < width; y++) {
            count = 0;

            for (int x = 0; x < length; x++) {
                if (tiles[y][x].getOwner() == Owner.USER) {
                    count += 1;
                }
            }
            if (count == length) {
                System.out.println("player1 wins");
                return true;
            }
        }

        /*
            x..
            .x.
            ..x
         */
//        int offset = 0;
        if (length > width) {
            int perX = length - width +1;
            System.out.println("len > wid " + perX);

            for (int offset = 0; offset < perX; offset ++) {
                boolean areAllUser1 = true;
                System.out.println("offset " + offset);
                for (int x = 0; x < width ; x++) {
                    System.out.println("x " + x);
                    if (tiles[x][x+offset].getOwner() == Owner.USER) {
                    } else {
                        areAllUser1 = false;
                        break;
                    }

                }
                if ( areAllUser1) {
                    System.out.println("all same");
                    return true;

                }

            }


        } else if (width > length) {
            int perX = length - width + 1;
            System.out.println("len < wid " + perX);

        } else {
//            both values are same
            System.out.println("same");
        }

        //        int minFromBoth = length < width ? length : width;


//        for (int x = 0; x < length; x++) {
//            count = 0;
//
//            for (int y = 0; y < width; y++) {
//                if (tiles[y][x].getOwner() == Owner.USER) {
//                    count += 1;
//                }
//            }
//            if (count == width) {
//                System.out.println("player1 wins");
//                return true;
//            }
//        }


//        int minFromBoth = length < width ? length : width;
//        System.out.println("smaller is " + minFromBoth);
//        for (int x = 0; x < minFromBoth; x++) {
//            boolean areAllUsers1 = true;
//            count = 0;
//
//            for (int y = 0; y < minFromBoth; y++) {
//                if (tiles[y][x].getOwner() == Owner.USER) {
//                    count += 1;
//                } else {
//                    areAllUsers1 = false;
//                    System.out.println("no for x = " + x);
//                    break;
//                }
//
////                if (x == y) {
////                    if (tiles[y][x].getOwner() == Owner.USER) {
////                        count += 1;
////                    } else {
////                        areAllUsers1 = false;
////                        System.out.println("no for x = " + x);
////                        break;
////                    }
////
////                }
//
//            }
//
//            if (areAllUsers1) {
//                System.out.println("cout " + count);
//                System.out.println("widht" + width);
//                if (count == width) {
//                    System.out.println("yes for x = " + x);
//                    return true;
//
//                } else {
//                    System.out.println("width mismatch");
//                }
//
//            }
//
//        }



//        todo diagonals

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
