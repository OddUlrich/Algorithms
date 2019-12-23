'''
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
'''

class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        
        # maxSum = [nums[0]]
        # for i in range(1, len(nums)):
        #     maxSum.append(max(nums[i], nums[i] + maxSum[i-1]))
        # return max(maxSum)
    
        # Basis condition.
        if len(nums) < 2:
            return nums[0]
        
        maxSum = leftSum = rightSum = float('-inf')
        l = r = 0
        
        # Start from the middle to right.
        for i in range(len(nums)//2, len(nums)):
            r += nums[i]
            rightSum = r if r > rightSum else rightSum
            
        # Start from the middle to left.
        for i in range(len(nums)//2-1, -1, -1):
            l += nums[i]
            leftSum = l if l > leftSum else leftSum
            
        # Connect the result between two parts.
        total = leftSum + rightSum
        
        return max(total, self.maxSubArray(nums[ : len(nums)//2]), self.maxSubArray(nums[len(nums)//2 : ]))