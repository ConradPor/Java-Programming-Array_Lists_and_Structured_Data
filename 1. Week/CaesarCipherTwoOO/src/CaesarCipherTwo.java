public class CaesarCipherTwo {

    private String Alphabet;
    private String alphabet;
    private String ShiftedAlphabet1;
    private String shiftedAlphabet1;
    private String ShiftedAlphabet2;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {
        Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        ShiftedAlphabet1 = Alphabet.substring(key1)+ Alphabet.substring(0,key1);
        shiftedAlphabet1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        ShiftedAlphabet2 = Alphabet.substring(key2)+ Alphabet.substring(0,key2);
        shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    // Compute the shifted alphabet
    public String encrypt (String input) {

        StringBuilder encrypted = new StringBuilder (input);
        for (int i = 0; i <encrypted.length();i+=2){
            char currChar = encrypted.charAt(i);
            if ((i %2 == 0) && (Character.isLowerCase(currChar))) {
                int idx = alphabet.indexOf(currChar);
                if (idx!= 0)
                {
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            else if ((i %2 == 0) && (Character.isUpperCase(currChar)))
            {
                int idx = Alphabet.indexOf(currChar);
                if (idx != 0)
                {
                    char newChar = ShiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
        }
        for (int i = 1; i <encrypted.length();i+=2){
            char currChar = encrypted.charAt(i);
            if ((i %2 != 0) && (Character.isLowerCase(currChar)))
            {
                int idx = alphabet.indexOf(currChar);
                if (idx != 0)
                {
                    char newChar = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            else if ((i %2 != 0) && (Character.isUpperCase(currChar))) {
                int idx = Alphabet.indexOf(currChar);
                if (idx != 0)
                {
                    char newChar = ShiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
        }
        return encrypted.toString();
    }


    public String decrypt (String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
}
