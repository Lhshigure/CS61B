public class ArraysTest {
    public static void main(String[] args){
        int[] x = new int[]{3, 2, 1};
        //int[] y = Arrays.insert(x, 6, 2);
        //Arrays.printArray(y);
        //Arrays.reverse(y);
        //Arrays.printArray(y);
        int[] y = Arrays.replicate(x);
        Arrays.printArray(y);
    }
}
