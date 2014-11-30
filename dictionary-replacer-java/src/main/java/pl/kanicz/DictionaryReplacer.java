package pl.kanicz;

import java.util.Map;

public class DictionaryReplacer {
    public String replace(String input, Map<String, String> dict) {
        if (input == null || dict == null) {
            return "";
        }

        return replaceWords(input, dict);
    }

    private String replaceWords(String input, Map<String, String> dict) {
        String output = input;
        for (Map.Entry<String, String> dictEntry : dict.entrySet()) {
            output = output.replace("$" + dictEntry.getKey() + "$", dictEntry.getValue());
        }
        return output;
    }
}
