'''
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
'''

class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        
        size = len(nums)
        if size == 0:
            return 0
        elif size == 1:
            return nums[0]
        elif size == 2:
            return max(nums[0], nums[1])
        
        # Dynamic programming (every 4 numbers a group)
        maxV1 = nums[0]
        maxV2 = nums[1]
        
        maxV3 = max(nums[1], nums[2] + maxV1)
        
        for i in range(3, size):
            maxVal = max(maxV3, maxV1 + nums[i], maxV2 + nums[i])
            maxV1, maxV2, maxV3 = maxV2, maxV3, maxVal
            
        return max(maxV2, maxV3)