package com.example.leet_lib;

import java.util.Arrays;

public class Solution {

    public int rob(int[] nums) {
        int result = 0;
        for(int i = 0 ;i < nums.length;i++){
            if (nums.length <= i + 2 || i + 2 == nums.length-1){
                result = Math.max(nums[i],result);
            }else{
                int temp = nums[i];
                int temp1 = 0;
                for(int j = i + 2; j < nums.length;j++){
                    temp = Math.max(nums[j]+temp,temp1);
                    temp1 = temp;
                }
                result = Math.max(temp,result);
            }
        }
        return result;
    }

    public int rob2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public int rob3(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }

}
