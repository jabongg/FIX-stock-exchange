package leetcode;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        // linkedhashset use kar sakte hain (not for in-place though)

        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        return set.size();
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = removeDuplicates(nums);
        System.out.println(k);
    }
}
