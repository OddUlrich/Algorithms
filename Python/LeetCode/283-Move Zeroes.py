'''
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.


You must do this in-place without making a copy of the array.
Minimize the total number of operations.
'''

class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        
        non_zero_len = len(nums)
        idx = 0
        while idx < non_zero_len:
            if nums[idx] == 0:
                # Move the zero to the end of list.
                nums.pop(idx)
                nums.append(0)
                non_zero_len -= 1
            else:
                idx += 1