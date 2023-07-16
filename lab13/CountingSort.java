/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // TODO make counting sort work with arrays containing negative numbers.
        // find min negative int
        int min = Integer.MAX_VALUE;
        for(int i: arr){
            min = min < i ? min : i;
        }
        // add min, create a non-negative arrar
        for(int i = 0; i < arr.length; i++){
            arr[i] -= min;
        }
        // find max int;
        int max = Integer.MIN_VALUE;
        for(int i: arr){
            max = max > i ? max : i;
        }
        int [] counts = new int[max + 1];
        for(int i: arr){
            counts[i] += 1;
        }

        int[] sorted = new int[arr.length];
        // create startPoints array
        int[] startPoints = new int[max+1];
        startPoints[0] = 0;
        for(int i = 1; i < startPoints.length; i++){
            startPoints[i] = startPoints[i-1] + counts[i-1];
        }

        for(int i = 0; i < arr.length; i++){
            int item = arr[i];
            int pos = startPoints[arr[i]];
            sorted[pos] = item + min;
            startPoints[arr[i]] += 1;
        }
        // 还原arr
        for(int i = 0; i < arr.length; i++){
            arr[i] += min;
        }

        return sorted;
    }

    public static String toString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args){
        int[] someNegative = {9, 5, -4, 2, 1, -2, 5, 3, 0, -2, 3, 1, 1};
        int[] sortedArray = betterCountingSort(someNegative);
        System.out.println(toString(sortedArray));
    }

}
