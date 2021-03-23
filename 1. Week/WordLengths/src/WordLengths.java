import edu.duke.FileResource;

    public class WordLengths {

        // Create void method countWordLengths that has two parameters,
        // a FileResource named resource and an integer array named counts.
        //These method storing
        // and count the number of words of each length for all the words in resource,
        // storing these counts in the array counts.


        public void CountWordLengths (FileResource fr , int [] counts ) {
        for (String word : fr.words()){

            int currCount=word.length();
            if (!Character.isLetter(word.charAt(0)))
                currCount--;
            if (!Character.isLetter(word.charAt(word.length()-1)))
                currCount--;
            if (currCount >-1)
                counts[currCount]++;
        }
    }

        //Create a method indexOfMax that has one parameter named values that is an integer array.
        // This method returns the index position of the largest element in values.

        public int indexOfMax(int[] values) {

            int index = 0;
            int max = 0;
            for (int i=0; i < values.length; i++){

                if (values[i] > max)
                {
                    max = values[i];
                    index = i;
                }
            }
            return index;
        }

        public void testCountWordLengths() {
            FileResource fr = new FileResource();
            int [] counts= new int[21];
            CountWordLengths (fr, counts);
            for (int i=0 ; i < counts.length; i++){
                if (counts[i] !=0)
                    System.out.println("Length/number of words: " + i +" / "+counts[i]);
            }
            System.out.println("The most common are words with " + indexOfMax(counts) + " characters");
        }


        public static void main(String[] args) {
            WordLengths wl = new WordLengths();
            wl.testCountWordLengths();
        }
    }


