'''
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
'''

class Solution(object):
    def productExceptSelf(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        # Method 1: Division.
        # product = 1
        # output = []
        # zero_cnt = 0
        
        # for num in nums:
        #     if num != 0:
        #         product *= num
        #     else:
        #         zero_cnt += 1
        # for i in range(len(nums)):
        #     if nums[i] == 0:
        #         if zero_cnt == 1:
        #             output.append(product)
        #         else:
        #             output.append(0)
        #     else:
        #         if zero_cnt != 0:
        #             output.append(0)
        #         else:
        #             output.append(product/nums[i])
            
        # return output

        # Method 2.
        n = len(nums)
        output = [1]
        
        # Product of the first n-1 elements (from left to right).
        for i in range(n-1):
            output.append(nums[i]*output[i])
        # Multiply the left rest elements (from right to right).
        right_product = nums[-1]
        for i in range(n-1, 0, -1):
            output[i-1] *= right_product
            right_product *= nums[i-1]
            
        return output