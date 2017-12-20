package topaz.util;

public class StringUtil {

    public static String toROT13Encoding(String string) {
        String rot13Encoding = "";
        for (char letter : string.toCharArray()) {
            int letterIndex = Letter.charToIndex(letter);
            letterIndex += 13;
            rot13Encoding += Letter.IndexToChar(letterIndex);
        }
        return rot13Encoding;
    }

    public static String toMorseCode(String string) {
        String morseCode = "";
        for (char charLetter : string.toCharArray()) {
            Letter letter = Letter.charToLetter(charLetter);
            morseCode += letter.getMorseCode();
        }
        return morseCode;
    }
}
