package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class WordsCounter {
    
    public void countWordFromFile(String originalFilePath) throws IOException {
        StringBuilder stringBuilder;
        FileReader fileReader = new FileReader(originalFilePath);
        int a;
        stringBuilder = new StringBuilder();
        while ((a = fileReader.read()) != -1) {
            stringBuilder.append((char) a);
        }
        fileReader.close();

        String[] originalFileDataStringArray = stringBuilder.toString().split(" ");


        String keyWords = "private, protected, public, abstract, class, extends, final, implements, interface, " +
                "native, new, static, strictfp, synchronized, transient, volatile, break, case, continue, default, " +
                "do, else, for, if, instanceof, return, switch, while, import, package, boolean, byte, char, double, " +
                "float, int, long, short, assert, catch, finally, throw, throws, try, enum, super, this, void, const, goto";


        Map<String, Integer> counterMap = new HashMap();
        for (String string : keyWords.split(", ")) {
            counterMap.put(string, 0);
        }

        for (String key : counterMap.keySet()) {
            for (String string : originalFileDataStringArray) {
                if (string.equals(key)) {
                    counterMap.put(key, counterMap.get(key) + 1);
                }
            }
        }

        FileWriter fileWriter = new FileWriter("keyWordsCount.txt");
        for (String key : counterMap.keySet()) {
            fileWriter.write(key + " " + counterMap.get(key).toString() + "\n");
        }
        fileWriter.close();
    }
}