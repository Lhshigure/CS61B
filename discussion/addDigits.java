public class addDigits {
    public static int addDigits(int n){
        // base case
        if(n < 10){
            return n;
        }
        else{
            int sum =0;
            while(n != 0 ){
                sum += n % 10;
                n = n / 10;
            }
            return addDigits(sum);
        }
    }
}
