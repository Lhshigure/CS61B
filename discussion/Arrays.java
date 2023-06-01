public class Arrays {
    public static int[] insert(int[] arr, int item, int position){
        int[] newarr = new int[arr.length + 1];
        int index1 = 0;
        int index2 = 0;
        while(index2 < newarr.length){
            if(position == index2){
                newarr[index2] = item;
                index2 ++;
            }
            newarr[index2] = arr[index1];
            index1 ++;
            index2 ++;
        }
        return newarr;
    }
    public static void reverse(int[] arr){
        int start = 0;
        int end = arr.length - 1;
        while(start < end){
            int temp = arr[end];  // 交换
            arr[end] = arr[start];
            arr[start] = temp;

            end--;
            start++;
        }
    }
    public static int[] replicate(int[] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++ ){
            sum += arr[i];
        }
        int [] newarr = new int[sum];
        int index = 0;
        for(int t : arr){
            int count = 0;
            while(count < t){
                newarr[index] = t;
                index ++;
                count ++;
            }
        }
        return newarr;
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
