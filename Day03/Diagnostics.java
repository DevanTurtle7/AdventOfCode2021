package Day03;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Diagnostics {
    private List<String> bytes;
    private int byteLength;

    public Diagnostics(List<String> bytes, int byteLength) {
        this.bytes = bytes;
        this.byteLength = byteLength;
    }

    private HashMap<Integer, Integer> getMostCommonBits(Collection<String> bytes) {
        // Get counts of 0s and 1s in each pos
        HashMap<Integer, Integer> zerosInPos = new HashMap<>();
        HashMap<Integer, Integer> onesInPos = new HashMap<>();
        HashMap<Integer, Integer> mostCommonBits = new HashMap<>();

        for (String currentByte : bytes) {
            for (int i = 0; i < byteLength; i++) {
                Character current = currentByte.charAt(i);

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
        for (int i = 0; i < byteLength; i++) {
            int numZeros = zerosInPos.containsKey(i) ? zerosInPos.get(i) : 0;
            int numOnes = onesInPos.containsKey(i) ? onesInPos.get(i) : 0;

            int commonValue = numZeros > numOnes ? 0 : 1;
            mostCommonBits.put(i, commonValue);
        }

        return mostCommonBits;
    }

    public int runPowerDiagnostics() {
        // Get gamma and epsilon rates
        String gammaRate = "";
        String epsilonRate = "";
        HashMap<Integer, Integer> mostCommonBits = getMostCommonBits(this.bytes);

        for (int i = 0; i < byteLength; i++) {
            Integer commonValue = mostCommonBits.get(i);

            gammaRate = gammaRate.concat(commonValue.toString());
            epsilonRate = epsilonRate.concat(commonValue == 1 ? "0" : "1");
        }

        int gammaValue = Integer.parseInt(gammaRate, 2);
        int epsilonValue = Integer.parseInt(epsilonRate, 2);
        int powerConsumption = gammaValue * epsilonValue;

        return powerConsumption;
    }

    public int runLifeSupportDiagnostics() {
        // Get oxygen rating
        String oxygenGeneratorRating = "";
        String scrubberRating = "";
        Set<String> remainingCommonBytes = new HashSet<>(this.bytes);
        Set<String> remainingUncommonBytes = new HashSet<>(this.bytes);
        Set<String> nextCommonSet = new HashSet<>();
        Set<String> nextUncommonSet = new HashSet<>();
        int index = 0;

        while (remainingCommonBytes.size() > 1 && remainingUncommonBytes.size() > 1) {
            if (remainingCommonBytes.size() > 1) {
                HashMap<Integer, Integer> mostCommonBits = getMostCommonBits(remainingCommonBytes);

                for (String currentByte : remainingCommonBytes) {
                    int commonBit = mostCommonBits.get(index);
                    int currentBit = Character.getNumericValue(currentByte.charAt(index));

                    if (currentBit == commonBit) {
                        nextCommonSet.add(currentByte);
                        oxygenGeneratorRating = currentByte;
                    }
                }
                remainingCommonBytes = nextCommonSet;
                nextCommonSet = new HashSet<>();
            }

            if (remainingUncommonBytes.size() > 1) {
                HashMap<Integer, Integer> mostCommonBits = getMostCommonBits(remainingUncommonBytes);

                for (String currentByte : remainingUncommonBytes) {
                    int commonBit = mostCommonBits.get(index);
                    int uncommonBit = commonBit == 0 ? 1 : 0;
                    int currentBit = Character.getNumericValue(currentByte.charAt(index));

                    if (currentBit == uncommonBit) {
                        nextUncommonSet.add(currentByte);
                        scrubberRating = currentByte;
                    }
                }
                remainingUncommonBytes = nextUncommonSet;
                nextUncommonSet = new HashSet<>();
            } 

            index++;
        }

        int oxygenGeneratorValue = Integer.parseInt(oxygenGeneratorRating, 2);
        int scrubberValue = Integer.parseInt(scrubberRating, 2);
        int lifeSupportRating = oxygenGeneratorValue * scrubberValue;

        return lifeSupportRating;
    }
}
