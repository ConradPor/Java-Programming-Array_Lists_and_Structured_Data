

    public class wordPlay {

        //This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise.

        public boolean isVowel(char ch) {
            boolean vl = false;
            String vowel = "aeiouAEIOU";
            for (int i = 0; i < vowel.length(); i++) {
                char currentChar = vowel.charAt(i);
                if (currentChar == ch) {
                    vl = true;
                }
            }
            return vl;
        }

        //This method  return a String that is the string phrase with all the vowels (uppercase or lowercase) replaced by ch.

        public String replaceVowels(String phrase, char ch) {
            StringBuilder replace = new StringBuilder(phrase);
            for (int i = 0; i < phrase.length(); i++) {
                char currChar = replace.charAt(i);
                if (isVowel(currChar)) {
                    replace.setCharAt(i, ch);
                }
            }
            return replace.toString();
        }

/*   This method should return a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by
     ‘*’ if it is in an odd number location in the string (e.g. the first character has an odd number location but an even index, it is at index 0), or
     ‘+’ if it is in an even number location in the string (e.g. the second character has an even number location but an odd index, it is at index 1).*/

        public String emphasize(String phrase, char ch) {
            StringBuilder replace = new StringBuilder(phrase);
            for (int i = 0; i < replace.length(); i++) {
                char currentChar = replace.charAt(i);
                if ((i % 2 == 0) && (currentChar == Character.toLowerCase(ch) || currentChar == Character.toUpperCase(ch))) {
                    replace.setCharAt(i, '*');
                } else {
                    if (currentChar == Character.toLowerCase(ch) || currentChar == Character.toUpperCase(ch)) {
                        replace.setCharAt(i, '+');
                    }
                }
            }
            return replace.toString();
        }

        public void testWordplay() {
            Boolean w = isVowel('a');
            System.out.println(w);
        }

        public void testReplaceVowels() {
            String word = replaceVowels("Hello World", '*');
            System.out.println(word);

        }

        public void testEmphasize() {
            String word = emphasize("Mary Bella Abracadabra", 'a');
            System.out.println(word);
        }


        public static void main(String[] args) {
            wordPlay wp = new wordPlay();
            wp.testWordplay();
            wp.testReplaceVowels();
            wp.testEmphasize();
        }
    }

