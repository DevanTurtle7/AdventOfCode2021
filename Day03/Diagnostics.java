package Day03;

import java.util.HashMap;
import java.util.List;

public class Diagnostics {
    private List<String> bits;
    private int bitLength;
    private HashMap<Integer, Integer> mostCommonBits;

    public Diagnostics(List<String> bits, int bitLength) {
        this.bits = bits;
        this.bitLength = bitLength;
        this.mostCommonBits = new HashMap<>();

        this.getMostCommonBits();
    }

    public void getMostCommonBits() {
        // Get counts of 0s and 1s in each pos
        HashMap<Integer, Integer> zerosInPos = new HashMap<>();
        HashMap<Integer, Integer> onesInPos = new HashMap<>();

        for (String bit : this.bits) {
            for (int i = 0; i < bitLength; i++) {
                Character current = bit.charAt(i);

                if (current == '0') {
                    int value = 1;

                    if (zerosInPos.containsKey(i)) {
                        value = zerosInPos.get(i) + 1;
                    }

                    zerosInPos.put(i, value);
                } else {
                    int value = 1;

                    if (onesInPos.containsKey(i)) {
                        value = onesInPos.get(i) + 1;
                    } 

                    onesInPos.put(i, value);
                }
            }
        }

        // Populate most common value
        for (int i = 0; i < bitLength; i++) {
            int numZeros = zerosInPos.get(i);
            int numOnes = onesInPos.get(i);

            int commonValue = numZeros > numOnes ? 0 : 1;
            mostCommonBits.put(i, commonValue);
        }
    }

    public int runDiagnostics() {
        // Get gamma and epsilon rates
        String gammaRate = "";
        String epsilonRate = "";

        for (int i = 0; i < bitLength; i++) {
            Integer commonValue = mostCommonBits.get(i);

            gammaRate = gammaRate.concat(commonValue.toString());
            epsilonRate = epsilonRate.concat(commonValue == 1 ? "0" : "1");
        }

        int gammaValue = Integer.parseInt(gammaRate, 2);
        int epsilonValue = Integer.parseInt(epsilonRate, 2);
        int powerConsumption = gammaValue * epsilonValue;

        return powerConsumption;
    }
}
