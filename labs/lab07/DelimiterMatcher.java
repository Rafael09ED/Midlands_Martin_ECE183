package labs.Lab7;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * EGR 283 B01
 * DelimiterMatcher.java
 *
 * @author Rafael
 * @version 1.0 2/16/2017
 */
public class DelimiterMatcher {
    final static Map<Character, Character> symbols = new HashMap<>();

    static {
        symbols.put('{', '}');
        symbols.put('[', ']');
        symbols.put('(', ')');
    }

    private String filePath;
    private Stack<Character> delimiterStack = new Stack<>();
    private int lineNumber = 1;
    private boolean isFileValid = true;

    public static void main(String[] args) {
        System.out.println("Enter the full path of the file to check");
        try (Scanner sc = new Scanner(System.in)){
            if (new DelimiterMatcher(sc.nextLine()).checkFile())
                System.out.println("File is Valid");
        } catch (IOException e) {
            System.out.println("Error Reading File:");
            e.printStackTrace();
        }
    }

    /**
     * Initializes class
     *
     * @param filePath the full path of the file to check
     */
    public DelimiterMatcher(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks the file delimiters to verify they are matching
     *
     * @return true if the file is good, false if there were problems
     * @throws IOException if there is a problem reading the file
     */
    public boolean checkFile() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(this::processLine);
        }
        if (!delimiterStack.empty()) {
            System.err.println("Extra Opening Delimiter or Missing Closing Delimiter x" + delimiterStack.size());
            isFileValid = false;
        }
        return isFileValid;
    }

    /**
     * @param line a string representing one line to process
     */
    private void processLine(String line) {
        for (int i = 0; i < line.length(); i++) {
            char value = line.charAt(i);
            if (symbols.containsKey(value)) {
                delimiterStack.push(value);
            } else if (symbols.containsValue(value)) {
                if (delimiterStack.empty()) {
                    System.err.println("Extra Closing Delimiter at: " + lineNumber + ":" + (i + 1));
                    isFileValid = false;
                } else if (!symbols.get(delimiterStack.pop()).equals(value)) {
                    System.err.println("Delimiter Mismatch at: " + lineNumber + ":" + (i + 1));
                    isFileValid = false;
                }
            }
        }
        lineNumber++;
    }
}
