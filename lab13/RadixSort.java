import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        // find the longest element in digit
        int maxLength = Integer.MIN_VALUE;
        for(String s: asciis){
            maxLength = Math.max(maxLength, s.length());
        }
        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, asciis.length);
        // LSD
        for(int index = 0; index < maxLength; index++){
            sorted = sortHelperLSD(sorted, index);
        }
        return sorted;
    }
    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index){
        // create a counts arr, to store the num of aciis
        int[] counts = new int[256];
        for(String ascii: asciis){
            // get least siginificant digit
            // if digit of curr ascii is larger than curr index
            if(ascii.length() > index){
                int charIndex = (int)ascii.charAt(ascii.length() - index - 1);
                counts[charIndex] += 1;
            }else{
                counts[0] += 1;
            }
        }
        // create a startPos arr to store start postion for each element
        int[] startPos = new int[256];
        startPos[0] = 0;
        for(int i = 1; i < counts.length; i++){
            startPos[i] = startPos[i-1] + counts[i-1];
        }
        // create a sorted arr
        String[] sorted = new String[asciis.length];
        for(String ascii: asciis) {
            if (ascii.length() > index) {
                int pos = startPos[(int) ascii.charAt(ascii.length() - index - 1)];
                sorted[pos] = ascii;
                startPos[(int) ascii.charAt(ascii.length() - index - 1)]++;
            } else {
                int pos = startPos[0];
                sorted[pos] = ascii;
                startPos[0]++;
            }
        }
        return sorted;
    }
    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args){
        String[] arr = new String[]{"ab", "ac", "ad", "ba", "bb", "bd", "a", "b"};
        String[] sorted = sort(arr);
        System.out.println(Arrays.toString(sorted));
    }
}
