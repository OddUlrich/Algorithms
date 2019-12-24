'''
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.
'''

class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        
        # for i, n in enumerate(nums):
        #     if target-n in nums[i+1: ]:
        #         return [i, nums[i+1: ].index(target-n)+i+1]
        
        dic = {} # key: value, val: index
        for i, n in enumerate(nums):
            if target-n in dic.keys():
                return [dic[target-n], i]
            else:
                dic[n] = i