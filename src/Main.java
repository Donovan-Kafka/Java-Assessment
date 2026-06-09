public class Main {

    public static int findIndex(char[] refTable, char target) {
        for (int i = 0; i < refTable.length; i++) {
            if (refTable[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static char[] createShiftedTable(char[] refTable, int shift) {
        char[] shiftedTable = new char[refTable.length];

        for (int i = 0; i < refTable.length; i++) {
            shiftedTable[(i + shift) % refTable.length] =   refTable[i];
        }
        return shiftedTable;
    }

    public static String encode(String word, char[] refTable, char[] shiftedTable) {
        String result = "";

        for (int i = 0; i < word.length(); i++ ) {
            char current = word.charAt(i);
            int index = findIndex(refTable, current);
            // to ensure spacing between words
            if (current == ' ') {
                result += ' ';
                continue;
            }
            if (index != -1) {
                result += shiftedTable[index];
            }
        }
        return result;
    }

    public static String decode(String word, char[] refTable) {
        //offset character = first char of word.
        char offsetChar = word.charAt(0);
        int shift = findIndex(refTable, offsetChar);

        if (shift == -1) {
            return "";
        }

        char[] shiftedTable = createShiftedTable(refTable, shift);
        String result = "";

        for (int i = 1; i < word.length(); i++) {
            char current = word.charAt(i);
            int index = findIndex(shiftedTable, current);
            // to ensure spacing between words.
            if (current == ' ') {
                result += ' ';
                continue;
            }
            if (index != -1) {
                result += refTable[index];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[] refTable = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', '+',
                ',', '-', '.', '/'};

        //test case 'B'
        char offsetChar = 'B';
        int shift = findIndex(refTable, offsetChar);
        char[] shiftedTable = createShiftedTable(refTable, shift);

        String encodedWord = offsetChar + encode("HELLO WORLD", refTable, shiftedTable);
        System.out.println(encodedWord);
        String decodedWord = decode(encodedWord, refTable);
        System.out.println(decodedWord);
    }
}