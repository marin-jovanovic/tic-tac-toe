package main.tictactoeDrivers;

public class Cell {
//    todo add option to change x and y

    private CellStatus cellStatus;

    public Cell() {
        cellStatus = CellStatus.NOT_SET;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public boolean setCellStatus(CellStatus cellStatus) {
        if (this.cellStatus == CellStatus.NOT_SET) {
            this.cellStatus = cellStatus;
            return true;
        } else {
            System.out.println("cell already set");
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(cellStatus);
    }

    public enum CellStatus {
        NOT_SET("_"),
        USER("u"),
        COMPUTER("c");

        private final String string;

        CellStatus(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }
}
