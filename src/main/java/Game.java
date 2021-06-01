import java.util.Scanner;

public class Game {

    private final int length = 3;
    private final int width = 3;
    private final boolean isUserX = true;
    private Tile[][] tiles;

    public Game() {
        this.tiles = new Tile[length][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                tiles[x][y] = new Tile();

            }
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.printBoard();

        while (true) {
            System.out.println("enter num");

            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
            System.out.print("Enter x: ");
            String str= sc.nextLine();              //reads string
            int x = Integer.parseInt(str);
            System.out.print("Enter y: ");
            str= sc.nextLine();              //reads string
            int y = Integer.parseInt(str);

            System.out.println(x + " " + y);
        }

    }


    public void updateGame(int x, int y) {
        System.out.println("updating  " + x +  " " +y);

        
    }

    public void printBoard() {
        for (int x = 0; x < width; x++) {
            String buffer = "";
            for (int y = 0; y < length; y++) {

                if (isUserX) {
                    if (tiles[x][y].getOwner() == Owner.USER) {
                        buffer += "x ";
                    } else if (tiles[x][y].getOwner() == Owner.COMPUTER) {
                        buffer += "o ";
                    } else if (tiles[x][y].getOwner() == Owner.NONE) {
                        buffer += ". ";
                    }

                } else {
                    if (tiles[x][y].getOwner() == Owner.USER) {
                        buffer += "o ";
                    } else if (tiles[x][y].getOwner() == Owner.COMPUTER) {
                        buffer += "x ";
                    } else if (tiles[x][y].getOwner() == Owner.NONE) {
                        buffer += ". ";
                    }
                }
            }
            System.out.println(buffer);

        }
    }

}
