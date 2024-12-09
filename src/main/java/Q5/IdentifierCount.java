package Q5;

import java.io.File;
import java.util.*;

public class IdentifierCount {
    public static void main(String[] args) {
        String filePath = "src/main/java/Q5/test.java";

        File file = new File(filePath);
        if(!file.exists()) {
            System.out.println("File not found");
            return;
        }

        Map<String, Integer> identifierCountMap = new HashMap<>();
        Map<String, List<String>> identifierLinesMap = new HashMap<>();


        try {
            Scanner scannerFile = new Scanner(file);
            int lineNumber = 0;

            while(scannerFile.hasNextLine()) {
                lineNumber++;
                String line = scannerFile.nextLine();

                String[] words = line.split("[^A-Za-z0-9_]+");

                for(String word : words) {
                    if(word.length() == 0) {
                        continue;
                    }

                    if(Character.isDigit(word.charAt(0))) {
                        continue;
                    }

                    if(identifierCountMap.containsKey(word)) { 
                        identifierCountMap.put(word, identifierCountMap.get(word) + 1);
                        identifierLinesMap.get(word).add(lineNumber + ". " + line);
                    } else {
                        identifierCountMap.put(word, 1);
                        List<String> lines = new ArrayList<>();
                        lines.add(lineNumber + ". " + line);
                        identifierLinesMap.put(word, lines);
                    }
                }
            }
            scannerFile.close();

            ArrayList<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(identifierCountMap.entrySet());

            sortedEntries.sort((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));

            for (Map.Entry<String, Integer> identifier : sortedEntries) {
                System.out.println(identifier.getKey() + " " + identifierCountMap.get(identifier.getKey()));
                for(String line : identifierLinesMap.get(identifier.getKey())) {
                    System.out.println(line);
                }
            }
            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


}