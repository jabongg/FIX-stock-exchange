package com.staywell.leetcode;

public class SquareOfSortedArray {

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        sortedSquares(nums);
    }

    // 3 pointer approach
    public static int[] sortedSquares(int[] nums) {
        int[] arr = new int[nums.length];
        int i = 0, j = nums.length - 1, k = nums.length - 1;
        while (i < arr.length && k >= 0 && j >= 0) {
            if (Math.abs(nums[i]) < Math.abs(nums[j])) {
                arr[k] = (int) Math.pow(nums[j], 2);
                j--;
                k--;
            } else {
                arr[k] = (int) Math.pow(nums[i], 2);
                i++;
                k--;
            }
        }
        return arr;
    }
}
