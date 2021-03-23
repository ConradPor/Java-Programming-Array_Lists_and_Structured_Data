import edu.duke.FileResource;

public class CaesarCipher {

    //Method return encrypted string using the Caesar Cipher algorithm.
    public String encrypt (String input, int key) {
        StringBuilder encrypted = new StringBuilder (input);
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet = Alphabet.toLowerCase();
        String ShiftedAlphabet = Alphabet.substring(key)+ Alphabet.substring(0,key);
        String shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        for (int i = 0; i < encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)) {
                int inx = alphabet.indexOf(currChar);
                if (inx !=-1){
                    char newChar = shiftedAlphabet.charAt(inx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            else {
                int inx = Alphabet.indexOf(currChar);
                if (inx !=-1){
                    char newChar = ShiftedAlphabet.charAt(inx);
                    encrypted.setCharAt(i,newChar);
                }
            }
        }
        return encrypted.toString();
    }


    /* Method encryptTwoKeys returns a String that has been encrypted using the following algorithm.
        Parameter key1 is used to encrypt every second character with the Caesar Cipher algorithm,
        starting with the first character, and key2 is used to encrypt every second character,
        starting with the second character.*/

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder (input);
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet = Alphabet.toLowerCase();
        String ShiftedAlphabet1 = Alphabet.substring(key1)+ Alphabet.substring(0,key1);
        String shiftedAlphabet1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        String ShiftedAlphabet2 = Alphabet.substring(key2)+ Alphabet.substring(0,key2);
        String shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);

        for (int i = 0; i <encrypted.length();i+=2){
            char currChar = encrypted.charAt(i);
            if ((i %2 == 0) && (Character.isLowerCase(currChar))) {
                int inx = alphabet.indexOf(currChar);
                if (inx!= 0)
                {
                    char newChar = shiftedAlphabet1.charAt(inx);
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

        for (int i = 1; i < encrypted.length(); i+=2){
            char currChar = encrypted.charAt(i);
            if ((i %2 != 0) && (Character.isLowerCase(currChar)))
            {
                int inx = alphabet.indexOf(currChar);
                if (inx != 0)
                {
                    char newChar = shiftedAlphabet2.charAt(inx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else if ((i %2 != 0) && (Character.isUpperCase(currChar))) {
                int inx = Alphabet.indexOf(currChar);
                if (inx != 0)
                {
                    char newChar = ShiftedAlphabet2.charAt(inx);
                    encrypted.setCharAt(i,newChar);
                }
            }

        }

        return encrypted.toString();
    }

    // Test method to check Caesar Cipher
    public void testCaesar() {
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("Key is: "+ key+"\n"+encrypted);
    }

    // Method to check Caesar Cipher with two keys.
    public void testEncryptTwoKeys() {
        int key1 = 10;
        int key2 = 7;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("Key1 is: "+ key1 + " and Key2 is: "+ key2 +"\n"+encrypted);

    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        cc.testCaesar();
        //cc.testEncryptTwoKeys();
    }
}