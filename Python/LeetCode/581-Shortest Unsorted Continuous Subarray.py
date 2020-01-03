'''
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
'''

class Solution(object):
    def findUnsortedSubarray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        
        length = len(nums)
        if length <= 1:
            return 0
        
        # Method 1: find the left and right edge elements.
#         left = right = -1
#         maxEdge = nums[0]
#         minEdge = nums[length-1]
        
#         for i in range(1, length):
#             maxEdge = max(maxEdge, nums[i])
#             if maxEdge > nums[i]:
#                 right = i
#         if right == -1:
#             return 0
        
#         for j in range(length-1, 0, -1):
#             minEdge = min(nums[j-1], minEdge)
#             if minEdge < nums[j-1]:
#                 left = j-1
            
#         return right - left + 1

        # Method 2
        arr = sorted(nums)
        length = len(nums)
        for i in range(length):
            if arr[i] != nums[i]:
                break
        for j in range(length, 0, -1):
            if arr[j-1] != nums[j-1]:
                break
        if j-1 < i:
            return 0
        else:
            return j-i
        