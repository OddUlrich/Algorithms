'''
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
'''
class Solution(object):
    def findDisappearedNumbers(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        
        # Marked the index with element by multiplying -1.
        for idx in range(len(nums)):
            num = nums[idx]
            if nums[abs(num)-1] > 0:
                nums[abs(num)-1] *= -1
            
        # The indeces of remain positive numbers, adding one, are the targets.
        ret_list = []    
        for idx in range(len(nums)):
            if nums[idx] > 0:
                ret_list.append(idx+1)
        
        return ret_list