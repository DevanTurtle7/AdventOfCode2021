package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void parseData(String filename) throws IOException {
        FileReader reader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();

        int count = 0;
        List<Integer> drawings = new LinkedList<>();
        ArrayList<ArrayList<Position>> rows = new ArrayList<>();

        int quickestNumMoves = -1;
        int quickestScore = -1;

        while (line != null) {
            line = line.strip();

            if (count == 0) {
                String[] tokens = line.split(",");

                for (String token : tokens) {
                    int num = Integer.parseInt(token);
                    drawings.add(num);
                }

                quickestNumMoves = drawings.size();
            } else if (line == "") {
                if (count > 1) {
                    Board board = new Board(rows);
                    int index = 0;
                    int numMoves = 1;
                    boolean bingo = false;

                    while (!bingo && numMoves < quickestNumMoves) {
                        bingo = board.bingo(drawings.get(index));
                        index++;
                        numMoves++;
                    }

                    if (quickestNumMoves > numMoves) {
                        quickestNumMoves = numMoves;
                        quickestScore = board.getScore();
                    }

                    rows = new ArrayList<>();
                }
            } else {
                ArrayList<Position> currentRow = new ArrayList<>();
                String[] tokens = line.split(" ");

                for (String token : tokens) {
                    if (token != " " && token != "") {
                        int value = Integer.parseInt(token);
                        Position currentPosition = new Position(value);

                        currentRow.add(currentPosition);
                    }
                }

                rows.add(currentRow);
            }

            count++;
            line = bufferedReader.readLine();
        }

        System.out.println(quickestNumMoves);
        System.out.println(quickestScore);

        bufferedReader.close();
        reader.close();
    }

    public static void main(String args[]) throws IOException {
        parseData("Day04/input.txt");
    }
}
