

public class CaesarCipher {

    private String Alphabet;
    private String alphabet;
    private String ShiftedAlphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet = Alphabet.toLowerCase();
        ShiftedAlphabet = Alphabet.substring(key)+ Alphabet.substring (0,key);
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring (0, key);
        mainKey = key;
    }

    // Compute the shifted alphabet
    public String encrypt (String input) {
            StringBuilder encrypted = new StringBuilder (input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)) {
                int inx = alphabet.indexOf(currChar);
                if (inx != -1) {
                    char newChar = shiftedAlphabet.charAt(inx);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int inx = Alphabet.indexOf(currChar);
                if (inx != -1) {
                    char newChar = ShiftedAlphabet.charAt(inx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }


    public String decrypt (String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}




