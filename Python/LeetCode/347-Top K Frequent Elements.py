'''
Given a non-empty array of integers, return the k most frequent elements.


Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
'''

class Solution(object):
    def topKFrequent(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """
        
        if k == len(nums):
            return nums
        
        # Count the frequency with dictionary (key-value pair).
        dic = {}
        for num in nums:
            dic[num] = dic.get(num, 0) + 1
            
        # Sort in ascending order; compare frequency first, then compare the elements itself; reverse into descending order.
        sorted_list_pair = sorted(dic.items(), key=lambda kv:(kv[1], kv[0]), reverse=True)
        l = [element[0] for element in sorted_list_pair]
        return l[:k]
    
    	# Method 2.
        # count = collections.Counter(nums)   
        # return heapq.nlargest(k, count.keys(), key=count.get) 