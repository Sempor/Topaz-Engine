package topaz.util;

public class Letter {


    public static Letter LETTER_A = new Letter('A', 1, true, 1, ".-");
    public static Letter LETTER_B = new Letter('B', 2, false, 3, "-...");
    public static Letter LETTER_C = new Letter('C', 3, false, 3, "-.-.");
    public static Letter LETTER_D = new Letter('D', 4, false, 2, "-..");
    public static Letter LETTER_E = new Letter('E', 5, true, 1, ".");
    public static Letter LETTER_F = new Letter('F', 6, false, 4, "..-.");
    public static Letter LETTER_G = new Letter('G', 7, false, 2, "--.");
    public static Letter LETTER_H = new Letter('H', 8, false, 4, "....");
    public static Letter LETTER_I = new Letter('I', 9, true, 1, "..");
    public static Letter LETTER_J = new Letter('J', 10, false, 8, ".---");
    public static Letter LETTER_K = new Letter('K', 11, false, 5, "-.-");
    public static Letter LETTER_L = new Letter('L', 12, false, 1, ".-..");
    public static Letter LETTER_M = new Letter('M', 13, false, 3, "--");
    public static Letter LETTER_N = new Letter('N', 14, false, 1, "-.");
    public static Letter LETTER_O = new Letter('O', 15, true, 1, "---");
    public static Letter LETTER_P = new Letter('P', 16, false, 3, ".--.");
    public static Letter LETTER_Q = new Letter('Q', 17, false, 10, "--.-");
    public static Letter LETTER_R = new Letter('R', 18, false, 1, ".-.");
    public static Letter LETTER_S = new Letter('S', 19, false, 1, "...");
    public static Letter LETTER_T = new Letter('T', 20, false, 1, "-");
    public static Letter LETTER_U = new Letter('U', 21, true, 1, "..-");
    public static Letter LETTER_V = new Letter('V', 22, false, 4, "...-");
    public static Letter LETTER_W = new Letter('W', 23, false, 4, ".--");
    public static Letter LETTER_X = new Letter('X', 24, false, 8, "-..-");
    public static Letter LETTER_Y = new Letter('Y', 25, false, 4, "-.--");
    public static Letter LETTER_Z = new Letter('Z', 26, false, 10, "--..");
    public static Letter charToLetter(char letter) {
        switch (letter) {
            case 'a':
                return LETTER_A;
            case 'b':
                return LETTER_B;
            case 'c':
                return LETTER_C;
            case 'd':
                return LETTER_D;
            case 'e':
                return LETTER_E;
            case 'f':
                return LETTER_F;
            case 'g':
                return LETTER_G;
            case 'h':
                return LETTER_H;
            case 'i':
                return LETTER_I;
            case 'j':
                return LETTER_J;
            case 'k':
                return LETTER_K;
            case 'l':
                return LETTER_L;
            case 'm':
                return LETTER_M;
            case 'n':
                return LETTER_N;
            case 'o':
                return LETTER_O;
            case 'p':
                return LETTER_P;
            case 'q':
                return LETTER_Q;
            case 'r':
                return LETTER_R;
            case 's':
                return LETTER_S;
            case 't':
                return LETTER_T;
            case 'u':
                return LETTER_U;
            case 'v':
                return LETTER_V;
            case 'w':
                return LETTER_W;
            case 'x':
                return LETTER_X;
            case 'y':
                return LETTER_Y;
            case 'z':
                return LETTER_Z;
            default:
                return null;
        }
    }
    public static int charToIndex(char letter) {
        switch (letter) {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
            case 'i':
                return 9;
            case 'j':
                return 10;
            case 'k':
                return 11;
            case 'l':
                return 12;
            case 'm':
                return 13;
            case 'n':
                return 14;
            case 'o':
                return 15;
            case 'p':
                return 16;
            case 'q':
                return 17;
            case 'r':
                return 18;
            case 's':
                return 19;
            case 't':
                return 20;
            case 'u':
                return 21;
            case 'v':
                return 22;
            case 'w':
                return 23;
            case 'x':
                return 24;
            case 'y':
                return 25;
            case 'z':
                return 26;
            default:
                return -1;
        }
    }
    public static char IndexToChar(int letterIndex) {
        switch (letterIndex) {
            case 1:
                return 'a';
            case 2:
                return 'b';
            case 3:
                return 'c';
            case 4:
                return 'd';
            case 5:
                return 'e';
            case 6:
                return 'f';
            case 7:
                return 'g';
            case 8:
                return 'h';
            case 9:
                return 'i';
            case 10:
                return 'j';
            case 11:
                return 'k';
            case 12:
                return 'l';
            case 13:
                return 'm';
            case 14:
                return 'n';
            case 15:
                return 'o';
            case 16:
                return 'p';
            case 17:
                return 'q';
            case 18:
                return 'r';
            case 19:
                return 's';
            case 20:
                return 't';
            case 21:
                return 'u';
            case 22:
                return 'v';
            case 23:
                return 'w';
            case 24:
                return 'x';
            case 25:
                return 'y';
            case 26:
                return 'z';
            default:
                return 'a';
        }
    }
    private char letter;
    private int index;
    private boolean vowel;
    private int scrabbleValue;
    private String morseCode;
    public Letter(char letter, int index, boolean vowel, int scrabbleValue, String morseCode) {
        this.letter = letter;
        this.index = index;
        this.vowel = vowel;
        this.scrabbleValue = scrabbleValue;
        this.morseCode = morseCode;
    }
    public char getLetter() {
        return letter;
    }
    public int getIndex() {
        return index;
    }
    public boolean isVowel() {
        return vowel;
    }
    public int getScrabbleValue() {
        return scrabbleValue;
    }
    public String getMorseCode() {
        return morseCode;
    }
}
