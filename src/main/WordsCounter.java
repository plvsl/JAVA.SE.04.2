package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class WordsCounter {

    private final static String KEY_WORDS = "private, protected, public, abstract, class, extends, final, implements, interface, " +
            "native, new, static, strictfp, synchronized, transient, volatile, break, case, continue, default, " +
            "do, else, for, if, instanceof, return, switch, while, import, package, boolean, byte, char, double, " +
            "float, int, long, short, assert, catch, finally, throw, throws, try, enum, super, this, void, const, goto";

    public void countWordFromFile(String originalFilePath) throws IOException {
        StringBuilder stringBuilder;
        int a;
        stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(originalFilePath)) {
            while ((a = fileReader.read()) != -1) {
                stringBuilder.append((char) a);
            }
        }

        String[] originalFileDataStringArray = stringBuilder.toString().split(" ");

        Map<String, Integer> counterMap = new HashMap();
        for (String string : KEY_WORDS.split(", ")) {
            counterMap.put(string, 0);
        }

        for (String key : counterMap.keySet()) {
            for (String string : originalFileDataStringArray) {
                if (string.equals(key)) {
                    counterMap.put(key, counterMap.get(key) + 1);
                }
            }
        }

        try (FileWriter fileWriter = new FileWriter("keyWordsCount.txt")) {
            for (String key : counterMap.keySet()) {
                if (!counterMap.get(key).equals(0)) {
                    fileWriter.write(key + " " + counterMap.get(key).toString() + "\n");
                }
            }
        }
    }
}