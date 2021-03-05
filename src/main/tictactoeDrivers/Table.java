package main.tictactoeDrivers;

import main.resourceManagers.constants.Constant;

public class Table {


    private final Cell[][] table;

    private final int numberOfRows;
    private final int numberOfColumns;
    private final int howManyInRowToWin;

    public Table() {
        numberOfRows = (int) Constant.NUMBER_OF_ROWS.getValue();
        numberOfColumns = (int) Constant.NUMBER_OF_COLUMNS.getValue();
        howManyInRowToWin = (int) Constant.HOW_MANY_IN_SEQUENCE_TO_WIN.getValue();

        table = new Cell[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                table[i][j] = new Cell();
            }
        }

    }

    public static void main(String[] args) {
        Table t;
        t = new Table();

        t.printTable();

//        t.getCell(1, 1).setCellStatus(Cell.CellStatus.USER);

        t.getCell(1, 1).setCellStatus(Cell.CellStatus.COMPUTER);
        t.getCell(1, 2).setCellStatus(Cell.CellStatus.COMPUTER);
        t.getCell(1, 0).setCellStatus(Cell.CellStatus.COMPUTER);

        t.printTable();

        t.check();
    }

    public void check() {
        System.out.println(checkForWin(Cell.CellStatus.COMPUTER));
    }

    public boolean checkForWin(Cell.CellStatus cellStatus) {

//        todo do this algorith if min three cells are set

//        check row
        for (int i = 0; i < numberOfColumns; i++) {
            int count = 0;

            for (int j = 0; j < numberOfRows; j++) {
                if (table[i][j].getCellStatus() == cellStatus) {
                    count++;
                }
            }

            if (count == howManyInRowToWin) {
                System.out.println("row");
                return true;
            }
        }

//        check column
        for (int i = 0; i < numberOfRows; i++) {
            int count = 0;

            for (int j = 0; j < numberOfColumns; j++) {
                if (table[i][j].getCellStatus() == cellStatus) {
                    count++;
                }
            }

            if (count == howManyInRowToWin) {
                System.out.println("column");
                return true;
            }
        }

//        check main diagonal
//        fixme i suppose that num of rows = num of columns
        for (int i = 0; i < numberOfRows; i++) {
            int count = 0;

            if (table[i][i].getCellStatus() == cellStatus) {
                count++;
            }

            if (count == howManyInRowToWin) {
                System.out.println("main diagonal");
                return true;
            }
        }

        //        check non-main diagonal
        for (int i = 0; i < numberOfRows; i++) {
            int count = 0;


                if (table[i][numberOfColumns - 1 - i].getCellStatus() == cellStatus) {
                    count++;
                }

            if (count == howManyInRowToWin) {
                System.out.println("main diagonal");
                return true;
            }
        }

        return false;
    }

    public Cell getCell(int x, int y) {
        return table[x][y];
    }

    /**
     * formatted print of {@code table}
     */
    private void printTable() {
        System.out.println("table:");

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(table[i][j].getCellStatus() + " ");
            }

            System.out.println();
        }
        System.out.println();
    }

}

