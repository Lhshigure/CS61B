public class Flatten {
    public static int[] flatten(int[][]x){
        int totalLength = 0;
        for(int i = 0; i < x.length; i++){
            totalLength += x[i].length;
        }
        int[] a = new int[totalLength];
        int aIndex = 0;
        for(int i = 0; i < x.length; i++){
            for(int j = 0; j <x[i].length; j++){
                a[aIndex] = x[i][j];
                aIndex ++;
            }
        }
        return a;

    }
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String args[]){
        int[][] x = new int[][]{{1, 2, 3}, {}, {7, 8}};
        int[] y = Flatten.flatten(x);
        Flatten.printArray(y);
    }
}
