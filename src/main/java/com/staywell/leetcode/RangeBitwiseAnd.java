package com.staywell.leetcode;

public class RangeBitwiseAnd {

    public static int rangeBitwiseAnd(int left, int right) {
        int curr = left;
        for (int i = left; i <= right; i++) {
            curr = curr & i;
        }

        return curr;
    }

    public static void main(String[] args) {
        int left = 5;
        int right = 7;

        System.out.println(rangeBitwiseAnd(left, right));
    }

}
