public class twoSum {
    public int[] twoSum(int[] nums, int target){
        for(int i = 0; i < nums.length; i += 1) {
            int complement = target - nums[i];
            for (int j = 0; j < nums.length; j += 1) {
                if (complement == nums[j] && i != j) {
                    int[] result = {nums[i], nums[j]};
                    return result;
                }
            }
        }
    }
}
