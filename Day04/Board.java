package Day04;

import java.util.ArrayList;

public class Board {
    private Position[][] board;
    private int lastNum;

    public Board(ArrayList<ArrayList<Position>> data) {
        this.board = new Position[5][5];
        this.lastNum = -1;

        for (var rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            ArrayList<Position> row = data.get(rowIndex);

            for (var column = 0; column < row.size(); column++) {
                Position position = row.get(column);

                this.board[rowIndex][column] = position;
            }
        }
    }

    public boolean bingo(int num) {
        int[] colCounts = new int[5];
        lastNum = num;

        for (Position[] row : this.board) {
            int rowCount = 0;

            for (int column = 0; column < row.length; column++) {
                Position position = row[column];

                if (position.getValue() == num) {
                    position.mark();
                }

                if (position.isMarked()) {
                    colCounts[column] += 1;
                    rowCount++;

                    if (colCounts[column] == 5 || rowCount == 5) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int getScore() {
        int unmarkedSum = 0;

        for (Position[] row : this.board) {
            for (Position position : row) {
                if (!position.isMarked()) {
                    unmarkedSum += position.getValue();
                }
            }
        }

        return unmarkedSum * lastNum;
    }
}
