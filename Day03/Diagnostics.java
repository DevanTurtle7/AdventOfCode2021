package Day03;

import java.util.HashMap;
import java.util.List;

public class Diagnostics {
    private List<String> bits;

    public Diagnostics(List<String> bits) {
        this.bits = bits;
    }

    public int runDiagnostics() {
        // Add all bit counts
        HashMap<Integer, Integer> zerosInPos = new HashMap<>();
        HashMap<Integer, Integer> onesInPos = new HashMap<>();

        for (String bit : this.bits) {
            for (int i = 0; i < bit.length(); i++) {
                Character current = bit.charAt(i);

                if (current == '0') {
                    if (zerosInPos.containsKey(i)) {
                        int value = zerosInPos.get(i);
                        value++;

                        zerosInPos.put(i, value);
                    } else {
                        zerosInPos.put(i, 1);
                    }
                } else {
                    if (onesInPos.containsKey(i)) {
                        int value = onesInPos.get(i);
                        value++;

                        onesInPos.put(i, value);
                    } else {
                        onesInPos.put(i, 1);
                    } 
                }
            }
        }

        // Get gamma and epsilon rates
        String gammaRate = "";
        String epsilonRate = "";

        for (int i = 0; i < 5; i++) {
            int numZeros = zerosInPos.get(i);
            int numOnes = onesInPos.get(i);

            gammaRate = gammaRate.concat(numZeros < numOnes ? "1" : "0");
            epsilonRate = epsilonRate.concat(numZeros > numOnes ? "1" : "0");
        }

        int gammaValue = Integer.parseInt(gammaRate, 2);
        int epsilonValue = Integer.parseInt(epsilonRate, 2);
        int powerConsumption = gammaValue * epsilonValue;

        return powerConsumption;
    }
}
