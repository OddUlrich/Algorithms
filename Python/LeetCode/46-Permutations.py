'''
Given a collection of distinct integers, return all possible permutations.
'''

class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        
#         ret = []
        
#         def subPermute(nums, arr):
#             if nums == None or len(nums) == 0:
#                 ret.append(arr)
#                 return
            
#             for i in range(len(nums)):
#                 subPermute(nums[:i] + nums[i+1:], arr+[nums[i]])
        
#         subPermute(nums, [])
#         return ret
    
        if nums == None or len(nums) == 0:
            return [[]]
        
        ret = [[nums[0]]]
        
        idx = 1
        while idx < len(nums):
            tmp = []
            # Iterate each temperory permution result.
            for perm in ret:
                # Insert new number into each temperory permution result.
                for i in range(idx+1):
                    tmp.append(perm[:i] + [nums[idx]] + perm[i:])
            # Update the subpermution result and index.
            ret = tmp
            idx += 1
        return ret
