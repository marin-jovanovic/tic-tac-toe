import java.util.Scanner;

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

            g.updateGame(x, y, Owner.COMPUTER);

            g.printBoard();

//            computer move
//            g.computerMove();
            g.isGameWon();
            System.out.println();
        }

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
        /*
            xxx
            ...
            ...
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
            x..
            x..
            x..
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
