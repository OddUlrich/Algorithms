'''
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
'''

class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        # single = []
        # for num in nums:
        #     if num in single:
        #         single.remove(num)
        #     else:
        #         single.append(num)
        # return single[0]
        
        ret = 0
        for num in nums:
            ret ^= num
        return ret