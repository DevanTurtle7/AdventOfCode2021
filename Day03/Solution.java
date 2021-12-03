package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static List<String> parseInput(String filename) throws IOException {
        List<String> bits = new LinkedList<>();
        FileReader reader = new FileReader(filename);
        BufferedReader buffReader = new BufferedReader(reader);
        String line = buffReader.readLine();

        while (line != null) {
            String trimmed = line.trim();
            bits.add(trimmed);
            line = buffReader.readLine();
        }

        buffReader.close();
        reader.close();

        return bits;
    }

    public static void main(String args[]) {
        try {
            List<String> bits = Solution.parseInput("Day03/input.txt");
            int bitLength = bits.get(0).length();

            Diagnostics diagnostics = new Diagnostics(bits, bitLength);
            int powerConsumption = diagnostics.runDiagnostics();

            System.out.println(powerConsumption);
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}